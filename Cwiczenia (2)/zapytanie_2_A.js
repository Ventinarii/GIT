printjson(
db.people.aggregate([
	{"$unwind":{
		path: "$credit"
	}},
	{"$addFields":{
        "balanceNUM":{
            "$convert":{
                input: "$credit.balance",
                to: "double",
                onError:"$credit.balance"
            }
        }
    }},
    {"$group":{
		_id:"$credit.currency",
		balanceNUM:{"$sum":"$balanceNUM"}
	}}
]))