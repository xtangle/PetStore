'use strict';

function listFiles() {

    return [
        '.tmp/scripts/vendor.js',
        'node_modules/angular-mocks/angular-mocks.js',
        'node_modules/moment/moment.js',
        'node_modules/lodash/lodash.js',
        '.tmp/scripts/index.js',
        'src/**/*.mock.js',
        'src/**/*.spec.js',
        'src/**/*.html'
    ];
}

module.exports = function(config) {

    var configuration = {
        files: listFiles(),
        singleRun: true,
        autoWatch: false,
        frameworks: ['jasmine'],

        ngHtml2JsPreprocessor: {
            stripPrefix: 'src/',
            moduleName: 'petApp'
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
            'src/**/*.html': ['ng-html2js']
        }
    };

    config.set(configuration);
};
