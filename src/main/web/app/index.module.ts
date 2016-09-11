/// <reference path="../../../../typings/index.d.ts"/>
/// <reference path="index.route.ts"/>
/// <reference path="petstore/pet/pet.module.ts"/>

module com.example {
  'use strict';

  angular.module('com.example.petStoreApp', [
    require('angular-resource'),
    require('angular-ui-router'),
    'com.example.common',
    'com.example.pet',
  ])
    .config(($compileProvider: ng.ICompileProvider, $logProvider: ng.ILogProvider) => {
      $compileProvider.debugInfoEnabled(true);
      $logProvider.debugEnabled(true);
    })
    .config(RouterConfig);
}
