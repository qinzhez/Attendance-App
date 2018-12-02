(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('CheckinService', CheckinService);

    CheckinService.$inject = ['$http', '$timeout', '$q','StateService'];
    function CheckinService($http, $timeout, $q, StateService) {
        var backend = StateService.server.backend;
        var checkinPort = StateService.server.checkinPort;
        var service = {}

        service.checkin = checkin;

        return service;

        function checkin(rid, aid, uid){
            return $http.post('http://'+backend+':'+checkinPort+'/checkin/'+rid+'/'+aid+'/'+uid).then(handleReponse);
        }

        // private functions

        function handleReponse(res) {
            return res;
        }
    }
})();
