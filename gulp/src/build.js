
var browserSync = require('browser-sync');
var concat = require('gulp-concat');
var gulpNgConfig = require('gulp-ng-config');
var sass = require('gulp-sass');
var tslint = require('gulp-tslint');
var typescript = require('gulp-typescript');

module.exports = function (gulp, conf) {

  gulp.task('build:fonts', function () {
    return gulp.src(conf.paths.fonts).pipe(gulp.dest('.tmp/fonts'));
  });

  gulp.task('build:vendor', function() {
    return gulp.src(conf.app.global)
      .pipe(concat('vendor.js'))
      .pipe(gulp.dest('.tmp/scripts'));
  });

  gulp.task('build:compile', function () {
    var tsProject = typescript.createProject('tsconfig.json');
    var tsResult = tsProject.src()
      .pipe(tslint({
        formatter: "verbose"
      }))
      .pipe(typescript(tsProject));
    return tsResult.js.pipe(gulp.dest('.tmp/javascript'));
  });

  gulp.task('build:config', ['build:compile'], function () {
    if (conf.ngConfig) {
      return gulp.src(conf.ngConfig.file)
        .pipe(gulpNgConfig(conf.ngConfig.module, {
          environment: conf.ngConfig.environment
        }))
        .pipe(gulp.dest('.tmp/javascript'))
    }
    return;
  });

  gulp.task('build:scripts', ['build:config'], function () {
    if (conf.ngConfig) {
      return gulp.src(['.tmp/javascript/config.js', '.tmp/javascript/index.js'])
        .pipe(concat('index.js'))
        .pipe(gulp.dest('.tmp/javascript'));
    }
    return;
  });

  gulp.task('build:sass', function () {
    return gulp.src(conf.index.sass)
      .pipe(sass())
      .on('error', sass.logError)
      .pipe(gulp.dest('.tmp/styles'))
      .pipe(browserSync.stream());
  });

  gulp.task('build', ['build:fonts', 'build:vendor', 'build:scripts', 'build:sass']);
}