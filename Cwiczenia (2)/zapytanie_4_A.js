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
        },
		
    }},
	{"$addFields":{
		"heightNUM2":{"$multiply":["$heightNUM","$heightNUM"]}
    }},
	{"$addFields":{
		"BMIavg":{"$divide":["$weightNUM","$heightNUM2"]}
    }},
    {"$group":{
		_id:"$nationality",
		BMIavg:{"$avg":"$BMIavg"},
		BMImin:{"$min":"$BMIavg"},
		BMImax:{"$max":"$BMIavg"}
	}}
]))
		