'use strict';
module com.example {

  export class RouterConfig {
    /* @ngInject */
    constructor($urlRouterProvider: ng.ui.IUrlRouterProvider, $stateProvider: ng.ui.IStateProvider) {
      $urlRouterProvider.otherwise('/');

      $stateProvider
        .state('default', {
          abstract: true,
          template: '<ui-view />',
        });
    }
  }
}
