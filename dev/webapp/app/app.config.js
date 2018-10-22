'use strict';

angular.
  module('attendU').
  config(['$locationProvider' ,'$routeProvider',
    function config($locationProvider, $routeProvider) {
      $locationProvider.html5Mode(true);
      //$locationProvider.hashPrefix('!');

      $routeProvider.
//		when('', {
//	      templateUrl: 'index.html'
//		}).
        when('/home', {
          templateUrl: 'dashboard/home.html'
        }).
//		when('/login', {
//		  controller: 'LoginController',
//          templateUrl: 'login/login.view.html',
//          controllerAs: 'vm'
//		}).
//		when('/register', {
//		  controller: 'RegisterController',
//          templateUrl: 'login/register.view.html',
//          controllerAs: 'vm'
//		}).
        otherwise({redirectTo: '/home'});
  
      //首页
      // $stateProvider.state('Login', {
            // url: '/',
            // templateUrl: './Views/Login.html',
            // controller: 'Login_controller',
            // controllerUrl: './controller/Login-controller.js'
      // });
  
      //个人信息页
      // $stateProvider.state('Login.Information', {
          // url: 'Information/:ID',
          // templateUrl: './Views/Info/Information.html',
          // controller: 'Information_controller',
          // controllerUrl: './controller/Info/Information-controller.js'
      // });
    }
  ]);
