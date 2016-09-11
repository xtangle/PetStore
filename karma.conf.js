'use strict';

function listFiles() {

    return [
        '.tmp/scripts/vendor.js',
        'node_modules/angular-mocks/angular-mocks.js',
        'node_modules/lodash/lodash.js',
        '.tmp/scripts/index.js',
        'src/main/web/**/*.spec.js',
        'src/main/web/**/*.html'
    ];
}

module.exports = function(config) {

    var configuration = {
        files: listFiles(),
        singleRun: true,
        autoWatch: false,
        frameworks: ['jasmine'],

        ngHtml2JsPreprocessor: {
            stripPrefix: 'src/main/web/',
            moduleName: 'com.example.petStoreApp'
        },

        browsers : ['PhantomJS'],

        plugins : [
            'karma-phantomjs-launcher',
            'karma-jasmine',
            'karma-browserify',
            'karma-coverage',
            'karma-mocha-reporter',
            'karma-junit-reporter',
            'karma-ng-html2js-preprocessor'
        ],

        reporters: ['mocha', 'coverage', 'junit'],

        junitReporter: {
            outputDir: 'reporters/unit/'
        },

        coverageReporter: {
            type : 'html',
            dir : 'reporters/unit/coverage/'
        },

        preprocessors: {
            'src/main/web/**/*.html': ['ng-html2js']
        }
    };

    config.set(configuration);
};
