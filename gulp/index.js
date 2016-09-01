
var _ = require('lodash');
var defaultConf = require('./js/conf');

// var browserify = require('./js/browserify');
var build = require('./js/build');
// var dist = require('./js/dist');
// var serve = require('./js/serve');
// var test = require('./js/test');

function configure(gulp, conf) {
  conf = _.defaultsDeep(conf, defaultConf);

  // browserify(gulp, conf);
  build(gulp, conf);
  // dist(gulp, conf);
  // serve(gulp, conf);
  // test(gulp, conf);

  gulp.task('default', ['build']);
};

module.exports = {
  configure: configure
}
