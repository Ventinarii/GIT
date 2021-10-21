var temp =
db.people.aggregate([
    {"$addFields":{
        "heightNUM":{
            "$convert":{
                input: "$height",
                to: "double",
                onError:"$height"
            }
        }
    }},
    {"$match":{
        "heightNUM":{
                $gte:190
            }
    }}
]).map(function(d) {
	return d._id;
});

temp.forEach(function(myDoc) {
    db.people.deleteOne( {"_id": myDoc});
});