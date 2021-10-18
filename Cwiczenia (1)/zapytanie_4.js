printjson(
db.people.aggregate([
	{"$addFields":{
		"weightNUM":{
			"$convert":{
				input: "$weight",
				to: "double",
				onError:"$weight"
			}
		}
	}},
	{"$match":{
		"weightNUM":{
				$gte:68,
				$lte:71.5
			}
	}}
]))