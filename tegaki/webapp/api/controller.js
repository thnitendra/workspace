'use strict';

//var mongoose = require('mongoose'),
//    Task = mongoose.model('Tasks');

exports.submit_image_for_processing = function(req, res) {
    //res.send(err);
    console.log("testing")
    res.json("{'key':'val1'}");
};

exports.retrieve_image = function(req, res) {
    //res.send(err);
    //res.json({key:req.query.imageId});
    res.json({key:req.params.imageId});
};
