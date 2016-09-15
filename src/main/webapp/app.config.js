'use strict';

angular.
  module('4fashionApp').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      //$locationProvider.hashPrefix('!');

      $routeProvider.
      	when('/', {
          templateUrl: 'modules/home/home.html'
        }).      
        when('/login', {
          templateUrl: 'modules/user/login.html'
        }).
        when('/signup', {
          templateUrl: 'modules/user/signup.html'
        });
    }
  ]);