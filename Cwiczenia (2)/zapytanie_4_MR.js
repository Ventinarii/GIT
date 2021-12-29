
var mapFunction = function() {
	we = parseFloat(this.weight);
	he = parseFloat(this.height);
	he = he * he;
	bmi = we / he;
	map = {
		BMIavg:bmi,
		BMImin:bmi,
		BMImax:bmi,
		inGroup:1
	}
	emit(this.nationality,map);
};

var reduceFunction = function(Key, Values) {
	reduce = {
		BMIavg:0,
		BMImin:1,
		BMImax:0,
		inGroup:0
	}
	for(var x = 0; x<Values.length; x++){
		reduce.BMIavg+=Values[x].BMIavg*Values[x].inGroup;
		reduce.inGroup+=Values[x].inGroup;
		
		reduce.BMImin=Math.min(reduce.BMImin,Values[x].BMImin);
		reduce.BMImax=Math.max(reduce.BMImax,Values[x].BMImax);
	}
	reduce.BMIavg=reduce.BMIavg/reduce.inGroup;
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


