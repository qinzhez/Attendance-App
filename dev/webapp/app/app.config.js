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
        controller: 'RoomListController',
        controllerAs: 'vm',
        url: '/room',
        templateUrl: 'room/roomList.view.html'
      }).
      state('home.createRoom',{
        url: '/room/createRoom',
        controller: 'RoomListController',
        controllerAs: 'vm',
        templateUrl: 'room/createroom.view.html'
      }).

      state('home.configRoom',{
      	controller: 'RoomListController',  
      	controllerAs: 'vm',  
        url: '/room/configRoom',
        templateUrl: 'room/configRoom.view.html'
      }).
      state('home.activity',{
        url: '/activity?enterRID&enterAID',
        controller: 'ActivityController',
        controllerAs: 'vm',
        templateUrl: 'activity/activity.view.html'
      }).
      state('home.activitysetting',{
          url: '/activity/setting',
          controller: 'ActivityController',
          controllerAs: 'vm',
          templateUrl: 'activity/activitysetting.view.html'
        }).
      state('home.createActivity',{
        url: '/activity/create',
        controller: 'ActivityController',
        controllerAs: 'vm',
        templateUrl: 'activity/createactivity.view.html'
      }).
      state('home.configActivity',{
        url: '/configActivity',
        templateUrl: 'login/register.view.html'
      }).
      state('home.withRID', {
        url: '/home/room/withId/:rid'
      })
    
    }
  ]);