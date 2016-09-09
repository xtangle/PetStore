
var path = require('path');
var karma = require('karma');

function runTests (singleRun, done) {

  var server = new karma.Server({
    configFile: path.join(process.cwd(), 'karma.conf.js'),
    singleRun: singleRun,
    autoWatch: !singleRun
  }, done);
  server.start()

}

module.exports = function (gulp, conf) {

  gulp.task('test', ['build', 'browserify'], function(done) {
    runTests(true, done);
  });

  gulp.task('test:auto', ['watch:scripts'], function(done) {
    runTests(false, done);
  });

};