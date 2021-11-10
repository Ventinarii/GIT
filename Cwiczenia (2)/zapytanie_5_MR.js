var mapFunction = function(){
	if(this.nationality=='Poland'){
		for(var x = 0; x<this.credit.length; x++){
			map = {
				balanceSUM:parseFloat(this.credit[x].balance),
				balanceAVG:parseFloat(this.credit[x].balance),
				inGroup:1
			}
			emit(this.credit[x].currency,map);
		}	
	}
};

var reduceFunction = function(Key, Values) {
	reduce = {
		balanceSUM:0,
		balanceAVG:0,
		inGroup:0
	}
	for(var x = 0; x<Values.length; x++){
		reduce.balanceSUM+=Values[x].balanceSUM;
		reduce.balanceAVG+=Values[x].balanceAVG;
		reduce.inGroup+=Values[x].inGroup;
	}
	reduce.balanceAVG=reduce.balanceAVG/reduce.inGroup;
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