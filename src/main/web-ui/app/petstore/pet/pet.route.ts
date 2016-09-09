module com.example {
  'use strict';

  export class PetRouterConfig {
    /* @ngInject */
    constructor($stateProvider: ng.ui.IStateProvider) {
      $stateProvider
        .state('pet', {
          abstract: true,
          template: '<ui-view />',
          url: '/pet',
        })
        .state('pet.add', {
          controller: AddPetController,
          controllerAs: 'addPetCtrl',
          resolve: {
            categories: (PetCategoryService: ListService<string>) => PetCategoryService.getAll(),
            petResource: (PetService: ResourceService<IPet>) => PetService.getResource(),
            statuses: (PetStatusService: ListService<string>) => PetStatusService.getAll(),
          },
          templateUrl: 'app/petstore/pet/add/add-pet.html',
          url: '/add',
        })
        .state('pet.find', {
          controller: 'FindPetController',
          controllerAs: 'findPetCtrl',
          templateUrl: 'app/petstore/pet/find/find-pet.html',
          url: '/find',
        });
    }
  }
}
