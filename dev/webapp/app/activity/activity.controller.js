(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['Flash', '$location','$window', '$q', 'ActivityService', 'StateService'];
    function ActivityController(Flash, $location, $window, $q, ActivityService, StateService) {
        
        var deffered = $q.defer();
        var promise = deffered.promise;
        //create activity
    	var vm = this;
    	vm.user = {};
    	vm.room = {};
        vm.activity = {};
        vm.room = StateService.room.selectedRoom;
        StateService.room.selectedRoom = null;
        
        vm.register = register;
        vm.dataLoading = false;
        vm.getActivityList = getActivityList;
        vm.startActivity = startActivity;
        
        (function init(){ 
            var initdef = $q.defer();
            var initpromise = initdef.promise;
            if(vm.room == null || vm.room == undefined){
                StateService.room.initdef = initdef;    
                $location.path("/home/room");
            }
            else{
                initdef.resolve();
            }
            initpromise.then(function(){
                getActivityList();
            });

        })();

      
        function register() {
            vm.dataLoading = true;
			
            ActivityService.CreateActivity(vm.user.CurrentUid，vm.room.CurrentRid, vm.activity)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('home/createActivity');
                    } else {
                        vm.dataLoading = false;
                    }
                    vm.activity = null;
                });
        }

        function getActivityList() {
            ActivityService.getActivityByRoom(vm.room.rid)
            .then(function(response){
                if (response.status == 200 && response.data != null && response.data.length > 0){
                    vm.activity = response.data;
                    StateService.activity.ActivityList = vm.activity;
                    deffered.resolve(response);
                }
                else{
                    FlashService.Error("Cannot find any activity for this room");
                }
            });
        }

        function startActivity(status, id) {
        	if (status == true){
        		 ActivityService.StartActivity(id);
        	}
        	else {
        		ActivityService.EndActivity(id);
        	}
        };

        promise.then(function(response){
            if(response.status == 200 && response.data != null && response.data.length > 0) {
                vm.activity = response.data;
                if(vm.activity.length > 0)
                    attachAdmin();
                StateService.activity.ActivityList = vm.activity;
            }
            else{
                FlashService.Error("Cannot find any room");
            }
        });
        
        function attachAdmin(){
            for(var i = 0; i<vm.admittedActivity.length;i++){
                for(var j=0; j<vm.activity.length;j++){
                    if(vm.activity[j].isAdmin)
                        continue;

                    if(vm.admittedActivity[i].rid == vm.activity[j].rid){
                        vm.activity[j].isAdmin = true;
                        break;
                    }
                    else if(vm.activity[j].isAdmin == undefined || vm.activity[j].isAdmin == null)
                        vm.activity[j].isAdmin = false;
                }
            }
        }
    }
})();