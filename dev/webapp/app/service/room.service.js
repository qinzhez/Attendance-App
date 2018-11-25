(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('RoomService', RoomService);

    RoomService.$inject = ['$http', '$cookies', '$rootScope'];
    function RoomService($http, $cookies, $rootScope){
    	var backend = "localhost";
    	var userPort = "8002";

        var service = {};
        service.getRoomByAdmin = getRoomByAdmin;

    	return service;

    	function getRoomByAdmin(id){
    		return $http.get('/api/room/getRoomByAdmin' + id).then(handleReponse);
    	}
    }
    	
    });