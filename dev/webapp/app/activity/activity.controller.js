(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['$window', '$q', 'ActivityService', 'StateService'];
    function ActivityController($window, $q, ActivityService, StateService) {
    	
        //create activity
    	var wrx=this;
        wrx.activity={};
        wrx.register = register;
        wrx.dataLoading=false;

        function register() {
            wrx.dataLoading = true;
			
            ActivityService.Registration(wrx.activity)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('home/room');
                    } else {
                        wrx.dataLoading = false;
                    }
                });
        }
    }

})();