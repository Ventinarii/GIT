  printjson(
db.people.aggregate([
    {"$addFields":{
        "weightNUM":{
            "$convert":{
                input: "$weight",
                to: "double",
                onError:"$weight"
            }
        },
		"heightNUM":{
            "$convert":{
                input: "$height",
                to: "double",
                onError:"$height"
            }
        }
    }},
    {"$group":{
		_id:"$sex",
		avgWeightNUM:{"$avg":"$weightNUM"},
		avgHeightNUM:{"$avg":"$heightNUM"},
		inGroup:{"$sum":1}
	}}
]))