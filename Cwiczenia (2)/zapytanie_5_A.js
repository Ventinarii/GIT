printjson(
db.people.aggregate([
	{"$match":{
        "nationality":'Poland'
	}},
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
		balanceSUM:{"$sum":"$balanceNUM"},
		balanceAVG:{"$avg":"$balanceNUM"}
	}}
]))