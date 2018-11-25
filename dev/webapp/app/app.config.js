'use strict';

angular.
  module('attendU').
  config(['$locationProvider' ,'$stateProvider','$urlRouterProvider',
    function config($locationProvider, $stateProvider, $urlRouterProvider) {
      //$locationProvider.hashPrefix('!');
      $urlRouterProvider.when('', '/index');
      $urlRouterProvider.when('/', '/index');

      $stateProvider.state('index',{
        url: '/index',
        controller: 'IndexController',
        controllerAs: 'vm',
        templateUrl: 'dashboard/index.html'
      }).
      state('login',{
        controller: 'LoginController',
        controllerAs: 'vm',
        url: '/login',
        templateUrl: 'login/login.view.html'
      }).
      state('register',{
        controller: 'RegisterController',
        controllerAs: 'vm',
        url: '/register',
        templateUrl: 'login/register.view.html'
      }).
      state('home',{
        url: '/home',
        controller: 'HomeController',
        controllerAs: 'vm',
        templateUrl: 'dashboard/home.html'
      }).
      state('home.room',{
        url: '/home/room',
        templateUrl: 'login/register.view.html'
      }).
      state('home.createRoom',{
        url: '/home/createRoom',
        templateUrl: 'login/register.view.html'
      }).
      state('home.configRoom',{
        url: '/home/configRoom',
        templateUrl: 'login/register.view.html'
      }).
      state('home.activity',{
        url: '/home/activity',
        templateUrl: 'login/login.view.html'
      }).
      state('home.createActivity',{
        url: '/home/createActivity',
        templateUrl: 'login/register.view.html'
      }).
      state('home.configActivity',{
        url: '/home/configActivity',
        templateUrl: 'login/register.view.html'
      })
    
    }
  ]);
