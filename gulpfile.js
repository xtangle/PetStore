'use strict';

var gulp = require('gulp');
var gutil = require('gulp-util');

var gulpApp = require('gulp-micro-app');

gulpApp.configure(gulp, {
    app: {
        api: 'http://localhost:8088/petstore/',
        ngModule: 'com.example.petStoreApp',
        baseDir: 'src/main/web',
        global: [
            'node_modules/jquery/dist/jquery.js',
            'node_modules/angular/angular.js',
            'node_modules/bootstrap/dist/js/bootstrap.js'
        ]
    },
    index: {
        sass: 'src/main/web/assets/styles/index.scss'
    },
    paths: {
        fonts: '',
        images: 'src/main/web/assets/images',
        src: 'src/main/web/app',
        styles: 'src/main/web/assets/styles'
    }
});
