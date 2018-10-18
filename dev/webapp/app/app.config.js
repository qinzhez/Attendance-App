'use strict';

angular.
  module('attendU').
  config(['$locationProvider' ,'$routeProvider','$stateProvider',
    function config($locationProvider, $routeProvider, $stateProvider) {
      $locationProvider.hashPrefix('!');

      $routeProvider.
        when('/phones', {
          template: '<phone-list></phone-list>'
        }).
        when('/phones/:phoneId', {
          template: '<phone-detail></phone-detail>'
        }).
        otherwise('/phones');
		
	  //默认跳转首页
      $urlRouterProvider.otherwise('/');
  
      //首页
      $stateProvider.state('Login', {
            url: '/',
            templateUrl: './Views/Login.html',
            controller: 'Login_controller',
            controllerUrl: './controller/Login-controller.js'
      });
  
      //个人信息页
      $stateProvider.state('Login.Information', {
          url: 'Information/:ID',
          templateUrl: './Views/Info/Information.html',
          controller: 'Information_controller',
          controllerUrl: './controller/Info/Information-controller.js'
      });
    }
  ]);
