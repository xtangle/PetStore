var del = require('del');

module.exports = function (gulp, conf) {

    gulp.task('clean:fonts', function() {
        return del('.tmp/fonts');
    });

    gulp.task('clean:images', function() {
        return del('.tmp/images');
    });

    gulp.task('clean:js', function() {
        return del('.tmp/javascript');
    });

    gulp.task('clean:scripts', function() {
        return del('.tmp/scripts');
    });

    gulp.task('clean:styles', function() {
        return del('.tmp/styles');
    });

    gulp.task('clean', function() {
        return del('.tmp');
    });

}