printjson(
	db
		.people
		.find(
			{},
			{
				_id:0,
				first_name:1,
				last_name:1,
				location:{city:1}
			},
			{birth_date:{$gte:"2000-01-01"}}
		)
		.toArray()
)
