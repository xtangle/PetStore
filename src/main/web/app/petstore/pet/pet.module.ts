/// <reference path="pet.route.ts"/>

module com.example {
  'use strict';

  angular.module('com.example.pet', [
    require('angular-ui-router'),
  ])
    .config(PetRouterConfig);

}
