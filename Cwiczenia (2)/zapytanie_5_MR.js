var mapFunction = function(){
	this
		.credit
		.forEach(function(credit){
			emit(credit.currency, 
				{
					money: parseFloat(credit.balance), 
					inGroup: 1
				}
			)
		})
};

var reduceFunction = function(key, values){
	reduceVal = 
	{
		money: 0, 
		inGroup: 0
	}
	for (var idx = 0; idx < values.length;idx++){
		reduceVal.money += values[idx].money;
		reduceVal.inGroup += values[idx].inGroup;
	}
	return reduceVal;
};

var finalizeFunction = function (key, reduceVal){
		reduceVal.avgBalance = reduceVal.money/reduceVal.inGroup;
		return reduceVal;
};

db
	.people
	.mapReduce(
		mapFunction, 
		reduceFunction, 
		{
			out: "mapreduce", 
			query:{
				"sex":"Female",
				"nationality":"Poland"
			},
			finalize: finalizeFunction
		}
	)
printjson(db.mapreduce.find({}).toArray())










