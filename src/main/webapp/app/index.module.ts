/// <reference path="../../../../typings/index.d.ts"/>
/// <reference path="index.route.ts"/>

'use strict';
module com.example {

  angular.module('com.example.petStoreApp', [

  ])
    .config(($compileProvider: ng.ICompileProvider, $logProvider: ng.ILogProvider) => {
      $compileProvider.debugInfoEnabled(true);
      $logProvider.debugEnabled(true);
    })
    .config(RouterConfig);

}
