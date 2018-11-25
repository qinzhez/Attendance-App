(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('RoomService', RoomService);

    RoomService.$inject = ['$http', '$cookies', '$rootScope', 'StateService'];
    function RoomService($http, $cookies, $rootScope, StateService){
    	var backend = StateService.server.backend;
    	var userPort = StateService.server.roomPort;

        var service = {};
        service.getRoomByAdmin = getRoomByAdmin;

    	return service;

    	function getRoomByAdmin(id){
    		return $http.get('http://'+backend+':'+roomPort+'/room/getRoomByAdmin/'+id).then(handleReponse);
        }
        
        function handleReponse(res) {
            return res;
        }
    }
    	
    })();