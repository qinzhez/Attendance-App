'use strict';

angular.
  module('attendU').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      //$locationProvider.hashPrefix('!');

      $routeProvider.
		when('/index', {
	      templateUrl: 'dashboard/index.html'
		}).
        when('/home', {
          templateUrl: 'dashboard/home.html'
        }).
		when('/login', {
		  controller: 'LoginController',
         templateUrl: 'login/login.view.html',
         controllerAs: 'vm'
		}).
		when('/register', {
		  controller: 'RegisterController',
         templateUrl: 'login/register.view.html',
         controllerAs: 'vm'
		}).
        otherwise({redirectTo: '/index'});
    }
  ]);
