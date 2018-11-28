(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['$location','$window', '$q', 'ActivityService', 'StateService'];
    function ActivityController($location, $window, $q, ActivityService, StateService) {
    	
        //create activity
    	var vm=this;
        vm.activity={};
        vm.room=StateService.room.selectedRoom;
        StateService.room.selectedRoom = null;
        vm.register = register;
        vm.dataLoading=false;

        (function init(){
            if(vm.room == null || vm.room == undefined)
                $location.path("/home/room");
            else{}

        })();

        function register() {
            vm.dataLoading = true;
			
            ActivityService.CreateActivity(vm.activity)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('home/createActivity');
                    } else {
                        vm.dataLoading = false;
                    }
                });
        }
    }

})();