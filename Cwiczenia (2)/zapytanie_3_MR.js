var mapFunction = function() {
	map = {
		inGroup:1
	}
	emit(this.job,map);
};

var reduceFunction = function(Key, Values) {
	reduce = {
		inGroup:0
	}
	for(var x = 0; x<Values.length; x++){
		reduce.inGroup  +=Values[x].inGroup;
	}
    return reduce;
};

db.people.mapReduce(
    mapFunction,
    reduceFunction,
    {
		out: "map_reduce"
	}
);

printjson(db.map_reduce.find({}).toArray());