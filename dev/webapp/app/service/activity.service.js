(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('ActivityService', ActivityService);

    ActivityService.$inject = ['$http', '$timeout', '$q','StateService'];
    function ActivityService($http, $timeout, $q, StateService) {
    	var backend = StateService.server.backend;
		var activityPort = StateService.server.activityPort;
		
        var service = {};

        service.CreateActivity = CreateActivity;
        service.getActivityByRoom = getActivityByRoom;
        service.StartActivity = StartActivity;
       	
        return service;

		function CreateActivity(info) {
			return $http.post('http://'+backend+':'+activityPort+'/activity/createActivity', info).then(handleReponse);		
		}
        
        function StartActivity(status, id){
            if (status == true)
                return $http.post('http://'+backend+':'+activityPort+'/activity/startActivity/'+id).then(handleReponse);
            else
                return $http.post('http://'+backend+':'+activityPort+'/activity/endActivity/'+id).then(handleReponse);
        }

        function getActivityByRoom(id){
            return $http.get('http://'+backend+':'+activityPort+'/activity/getActivityList/'+id).then(handleReponse);
        }

        // private functions

        function handleReponse(res) {
            return res;
        }
    }  

})();
