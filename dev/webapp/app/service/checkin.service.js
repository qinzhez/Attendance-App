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
        service.getCheckinInfo = getCheckinInfo;

        return service;

        function checkin(uid, rid, aid){
            return $http.post('http://'+backend+':'+checkinPort+'/activity/'+aid+'/'+rid+'/checkin/'+uid).then(handleReponse, function(){
                return {status: 200, data:false};
            });
        }
        
        function getCheckinInfo(uid, rid, aid){
            return $http.get('http://'+backend+':'+checkinPort+'/activity/getCheckinInfo/'+rid+'/'+aid+'/'+uid).then(function(response){
                if(response.data != null && response.status==200)
                    return true;
                return false;
            }, function(){
                return false;
            });
        }

        // private functions

        function handleReponse(res) {
            return res;
        }
    }
})();
