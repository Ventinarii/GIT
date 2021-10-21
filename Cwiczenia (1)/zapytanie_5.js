printjson(
db.people.aggregate([
	{"$project":{
		"first_name":1,
		"last_name":1,
		"city":"$location.city",
		"yearNUM":{
			"$dateFromString":{
				dateString:"$birth_date"
			}
		}
	}},
	{"$match":{
		"yearNUM":{
				$gte:new ISODate("2001-01-01T00:00:00.000Z")
			}
	}},
	{"$project":{
		"yearNUM":0
	}},
]))