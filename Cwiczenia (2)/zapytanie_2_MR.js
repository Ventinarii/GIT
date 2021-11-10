
var mapFunction = function() {
	for(var x = 0; x<this.credit.length; x++){
		map = {
			balanceNUM:parseFloat(this.credit[x].balance)
		}
		emit(this.credit[x].currency,map);
	}	
};

var reduceFunction = function(Key, Values) {
	reduce = {
		balanceNUM:0
	}
	for(var x = 0; x<Values.length; x++){
		reduce.balanceNUM+=Values[x].balanceNUM
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