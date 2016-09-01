
'use strict';

var gulp = require('gulp');
var gutil = require('gulp-util');

gulp.task('default', function() {
  gutil.log('Gulp is running!', gutil.colors.magenta('123'));
  gutil.beep();
});

