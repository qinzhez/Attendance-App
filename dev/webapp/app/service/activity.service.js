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
		
        return service;

		function CreateActivity(info) {
			return $http.post('http://'+backend+':'+activityPort+'/activity/createActivity', info).then(handleReponse);		
		}
		
        // private functions

        function handleReponse(res) {
            return res;
        }
    }  

})();
