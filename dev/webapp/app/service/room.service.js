(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('RoomService', RoomService);

    RoomService.$inject = ['$http', '$timeout', '$q','StateService'];
    function RoomService($http, $timeout, $q, StateService) {
    	var backend = StateService.server.backend;
		var roomPort = StateService.server.roomPort;
		
        var service = {};

        service.Registration = Registration;
        service.getRoomByAdmin = getRoomByAdmin;

		
        return service;

		function Registration(info) {
			return $http.post('http://'+backend+':'+roomPort+'/room/createRoom', info).then(handleReponse);		
		}
		
    	function getRoomByAdmin(id){
    		return $http.get('http://'+backend+':'+roomPort+'/room/getRoomByAdmin/'+id).then(handleReponse);
        }
        
        // private functions

        function handleReponse(res) {
            return res;
        }
    }  

})();
