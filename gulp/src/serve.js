
var browserSync = require('browser-sync');
var browserSyncSpa = require('browser-sync-spa');
var proxyMiddleware = require('http-proxy-middleware');

function browserSyncInit(conf) {

  var server = {
    baseDir: ['.tmp', 'src']
  };

  server.middleware = proxyMiddleware('/api', {
    target: conf.app.api,
    changeOrigin: true,
    onProxyRes: function(proxyRes, req, res) {
      var cookies = proxyRes.headers['set-cookie'];
      if(cookies) {
        cookies.forEach(function(cookie) {
          // We only want the value, not the domain
          res.setHeader('set-cookie', cookie.split(';')[0] + ' Path=/; HttpOnly');
        });
      }
    }
  });

  browserSync.instance = browserSync.init({
    startPath: '/',
    server: server,
    browser: 'chrome',
    https: conf.app.serveAsHttps
  });
}

module.exports = function (gulp, conf) {

  browserSync.use(browserSyncSpa({selector: '[ng-app]'}));

  gulp.task('watch:scripts', function () {
    return gulp.watch('src/**/.ts', ['browserify']);
  });

  gulp.task('watch:sass', function () {
    return gulp.watch('src/**/.scss', ['build:sass']);
  });

  gulp.task('serve', ['browserify', 'build:fonts', 'build:sass', 'watch:scripts', 'watch:sass'], function () {
    browserSyncInit(conf);
  });

}