const {Client} = require('pg')
const client = new Client({
    user: "postgres",
    password: "211233edward",
    host: "database-1.c9sbt5rgel2x.us-east-1.rds.amazonaws.com",
    port: 5432,
    database: "mydb"
})

CurrentEmployees = client.connect()
    .then(() => console.log("Connected successfuly"))
    .then(() => client.query("SELECT * FROM  public.employee_table"))
    .then(results => console.table(results.rows))
    .catch(e => console.log(e))
    .finally(() => client.end());

//let allEmployees = document.getElementById("getAllEmployees");
//allEmployees.addEventListener("click", (event)=>{
//	console.log("Clicked");
//});