module com.example {
  'use strict';

  export class RouterConfig {
    /* @ngInject */
    constructor($urlRouterProvider: ng.ui.IUrlRouterProvider, $stateProvider: ng.ui.IStateProvider) {
      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('home', {
          templateUrl: 'app/petstore/home/home.html',
          url: '/',
        });
    }
  }
}
