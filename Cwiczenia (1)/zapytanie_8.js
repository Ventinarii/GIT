db.people.updateMany(
{
	"location.city":"Moscow"
},{
	$set:{
		"location.city":"Moskwa"
	}
})
