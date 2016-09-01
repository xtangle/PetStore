
var browserSync = require('browser-sync');
var browserSyncSpa = require('browser-sync-spa');

module.exports = function (gulp, conf) {

  browserSync.use(browserSyncSpa({selector: '[ng-app]'}));

  gulp.task('watch:scripts', function () {
    return gulp.watch('src/**/.ts', ['browserify']);
  });

  gulp.task('watch:sass', function () {
    return gulp.watch('src/**/.scss', ['build:sass']);
  });

  gulp.task('serve', ['browserify', 'build:fonts', 'build:sass', 'watch:scripts', 'watch:sass'], function () {
    browserSync.instance = browserSync.init({
      startPath: '/',
      server: {
        baseDir: ['.tmp', 'src']
      },
      browser: 'chrome',
      https: conf.app.serveAsHttps
    });
  });

}