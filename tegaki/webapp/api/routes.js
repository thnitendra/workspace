'use strict';
module.exports = function(app) {
  var ctrl = require('./controller');

  app.get('/check', (req, res) => {
    res.send('Hello')
  });

  app.route('/image')
     .post(ctrl.submit_image_for_processing);

  app.route('/image/:imageId/thumbnail')
     .get(ctrl.retrieve_image)
};
