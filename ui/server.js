var express=require('express');
var app=express();

app.use(express.static(__dirname + '/'));//__dirname gives path to the current file location
// and commands the host server to host the file available in the path 

//Routes
app.get('/', function (req, res) {
    res.send("/views/index.html");
    //res.redirect('/views/index.html');
});

//commented codes that we wrote for learning
/*app.get("/test",function(req,res){
    res.send("testing");
});
app.get('/students/test', function (req, res) {
    res.send("from students test");
    //res.redirect('/views/index.html');
});*/

//Launching server
app.listen(8000, function (req,res) {
  console.log("Opening port 8000");    
  console.log('application launched at localhost:8000');
});
