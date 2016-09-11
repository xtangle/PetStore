/// <reference path="./providers/list/list-service.provider.ts"/>
/// <reference path="./providers/resource/resource-service.provider.ts"/>

module com.example.common {
  'use strict';
  import IPet = com.example.IPet;

  angular.module('com.example.common', [
    require('angular-resource'),
    require('angular-ui-router'),
  ])

    .provider('PetService', ResourceServiceProvider)
    .config((PetServiceProvider: ResourceServiceProvider<IPet>) => {
      PetServiceProvider.setResource('api/pet');
      PetServiceProvider.withActions({
        get: {
          method: 'GET',
          url: 'api/pet/:petIdQuery',
        },
        remove: {
          method: 'DELETE',
          url: 'api/pet/:petIdQuery',
        },
      });
    })

    .provider('PetCategoryService', ListServiceProvider)
    .config((PetCategoryServiceProvider: ListServiceProvider<string>) => {
      PetCategoryServiceProvider.setResource('api/pet/category');
    })

    .provider('PetStatusService', ListServiceProvider)
    .config((PetStatusServiceProvider: ListServiceProvider<string>) => {
      PetStatusServiceProvider.setResource('api/pet/status');
    });

}
