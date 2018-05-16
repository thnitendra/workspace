//var express = require('express'),
const express = require('express'),
  app         = express(),
  bodyParser  = require('body-parser'),
  port        = process.env.PORT || 3000;

require('./api/routes')(app);

//app.listen(port, () => {
//  console.log('We are live on ' + port);
//});

app.listen(port);

console.log('todo list RESTful API server started on: ' + port);
