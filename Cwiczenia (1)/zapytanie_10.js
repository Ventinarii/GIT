db.people.updateMany(
{
    "job":"Editor"
},{
    $unset:{
        "email":""
    }
})