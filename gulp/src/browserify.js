
var browserify = require('browserify');
var browserifyShim = require('browserify-shim');
var watchify = require('watchify');
var browserSync = require('browser-sync');
var gutil = require('gulp-util');
var _ = require('lodash');

var source = require('vinyl-source-stream');
var buffer = require('vinyl-buffer');

var sourcemaps = require('gulp-sourcemaps');

var customOpts = { /*entries: ['.tmp/javascript/index.js'], */debug: true, transform: [browserifyShim]};
var opts = _.assign({}, watchify.args, customOpts);
var b = browserify(opts);

module.exports = function (gulp, conf) {
  function bundle() {
    return b.bundle()
      .on('error', gutil.log.bind(gutil, 'Browserify Error'))
      .on('error', process.exit.bind(process, 1))
      .pipe(source('index.js'))
      .pipe(buffer())
      .pipe(sourcemaps.init({loadMaps: true}))
      .pipe(sourcemaps.write('./'))
      .pipe(gulp.dest('.tmp/scripts'))
      .pipe(browserSync.stream());
  }

  gulp.task('browserify', ['build:vendor', 'build:scripts'], bundle);
};
