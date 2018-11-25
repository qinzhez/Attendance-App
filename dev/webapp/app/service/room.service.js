(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('RoomService', RoomService);

    RoomService.$inject = ['$http', '$timeout', '$q', 'StateService'];
    function RoomService($http, $timeout, $q, StateService) {
		var backend = StateService.server.backend;
		var roomPort = StateService.server.roomPort;
		
        var service = {};

        service.UpdateRoomInfo = UpdateRoomInfo;
		
        return service;

		function UpdateRoomInfo(rid, roomname, participationnum,roomdescription) {
			var info = { rid:rid, name:roomname, participationNum:participationnum, description:roomdescription };
			return $http.post('http://'+backend+':'+roomPort+'/updateRoom', info).then(handleReponse);		
		}

        // private functions
        function handleReponse(res) {
            return res;
        }
    }  

})();
