'use strict';

var gulp = require('gulp');
var gutil = require('gulp-util');

var gulpApp = require('gulp-micro-app');

gulp.task('default', function () {
    gutil.log('Gulp is running!', gutil.colors.magenta('123'));
    gutil.beep();
});

gulpApp.configure(gulp, {
    app: {
        api: 'http://localhost:8088/petstore/',
        ngModule: 'com.example.petStoreApp',
        baseDir: 'src/main/web-ui',
        global: [
            'node_modules/jquery/dist/jquery.js',
            'node_modules/angular/angular.js',
            'node_modules/bootstrap/dist/js/bootstrap.js'
        ]
    },
    index: {
        sass: 'src/main/web-ui/assets/styles/index.scss'
    },
    paths: {
        fonts: '',
        images: 'src/main/web-ui/assets/images',
        src: 'src/main/web-ui/app',
        styles: 'src/main/web-ui/assets/styles'
    }
});
