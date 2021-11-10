printjson(
db.people.aggregate([
    {"$group":{
		_id:"$job"
	}}
]))