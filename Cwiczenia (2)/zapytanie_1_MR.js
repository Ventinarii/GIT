
var mapFunction = function() {
	map = {
		weightNUM:parseFloat(this.weight),
		heightNUM:parseFloat(this.height),
		inGroup:1
	}
	emit(this.sex,map);
};

var reduceFunction = function(Key, Values) {
	reduce = {
		weightNUM:0,
		heightNUM:0,
		inGroup:0
	}
	for(var x = 0; x<Values.length; x++){
		reduce.weightNUM+=Values[x].weightNUM;
		reduce.heightNUM+=Values[x].heightNUM;
		reduce.inGroup  +=Values[x].inGroup;
	}
    return reduce;
};

var finalizeFunction = function(Key, ValuesF) {
	ValuesF.weightNUM = ValuesF.weightNUM/ValuesF.inGroup;
	ValuesF.heightNUM = ValuesF.heightNUM/ValuesF.inGroup;
    return ValuesF;
};

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
		out: "map_reduce",
		finalize: finalizeFunction 
	}
);

printjson(db.map_reduce.find({}).toArray());