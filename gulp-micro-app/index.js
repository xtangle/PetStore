var _ = require('lodash');
var defaultConf = require('./gulp/conf');

var browserify = require('./gulp/browserify');
var build = require('./gulp/build');
var clean = require('./gulp/clean');
var serve = require('./gulp/serve');
var test = require('./gulp/test');

function configure(gulp, conf) {
    conf = _.defaultsDeep(conf, defaultConf);

    browserify(gulp, conf);
    build(gulp, conf);
    clean(gulp, conf);
    serve(gulp, conf);
    test(gulp, conf);

    gulp.task('default', ['build']);
};

module.exports = {
    configure: configure
}
