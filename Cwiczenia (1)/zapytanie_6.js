db.people.findAndModify({
	query:{
		"first_name":"Filip",
		"last_name": "Rajzer"
	},
	update:{
		"sex":"male",
		"first_name":"Filip",
		"last_name": "Rajzer",
		
		
		job: 'Game Programmer',
		email: 's17149@pjwstk.edu.pl',
		location: {
			city: 'Warsaw',
			address: { streetname: 'Kokosowa', streetnumber: '53/C3' }
		},
		description: 'some random nerd',
		height: '200',
		weight: '100',
		birth_date: '1998-09-09T07:22:24Z',
		nationality: 'Poland',
		credit: [
		{
			type: 'mastercard',
			number: '0000000000',
			currency: 'PLN',
			balance: '4567.89'
		},
		{
			type: 'PayMe',
			number: '1111111111',
			currency: 'CCCP',
			balance: '1.01'
		}
	  ]
		
	},
	upsert:true
})