
var _ = require('lodash');
var defaultConf = require('./src/conf');

var browserify = require('./src/browserify');
var build = require('./src/build');
var serve = require('./src/serve');
var test = require('./src/test');

function configure(gulp, conf) {
  conf = _.defaultsDeep(conf, defaultConf);

  browserify(gulp, conf);
  build(gulp, conf);
  serve(gulp, conf);
  test(gulp, conf);

  gulp.task('default', ['build']);
};

module.exports = {
  configure: configure
}
