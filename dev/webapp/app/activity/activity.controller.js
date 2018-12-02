(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['$stateParams', 'Flash', '$location','$window', '$q', 'ActivityService', 'StateService'];
    function ActivityController($stateParams, Flash, $location, $window, $q, ActivityService, StateService) {
        
        var deffered = $q.defer();
        var promise = deffered.promise;
        //create activity
    	var vm = this;
        vm.enteredRID = $stateParams.enterRID;
        vm.currentUrl = $location.url();
    	vm.user = {};
    	vm.room = {};
        vm.activity = {};
        vm.room = StateService.room.selectedRoom;
        StateService.room.selectedRoom = null;
        if(vm.room != undefined && vm.room != null)
            vm.activity = StateService.activity.selectedActivity;
        
        vm.register = register;
        vm.dataLoading = false;
        vm.getActivityList = getActivityList;
        vm.startActivity = startActivity;
        vm.goCreate = goCreate;

        (function init(){ 
            var initdeffered = $q.defer();
            var initpromise = initdeffered.promise;
            var userdef = $q.defer();
            var userpromise = userdef.promise;

            if(StateService.user.CurrentUid == undefined||StateService.user.CurrentUid == null)
                StateService.activity.initdef = userdef;
            else{
                userdef.resolve();
            }


            if(vm.enteredRID != undefined && vm.enteredRID != null && vm.enteredRID>0){
                ActivityService.getRoomByRid(vm.enteredRID)
                    .then(function(response){
                        
                        if(response.status == 200 && response.data != null){
                            vm.room = response.data;
                            initdeffered.resolve();
                        }

                    });
            }
            else{
                if(vm.room == null || vm.room == undefined){  
                    $location.path("/home/room");
                }else{
                    getActivityList();
                }
            }
            
            userpromise.then(function(){
                vm.user = StateService.user;
                initpromise.then(function(){
                    if(vm.room == null || vm.room == undefined){
                        $location.url("/home/room");
                        //$location.path("/home/room");
                    }else{
                        ActivityService.isAdmin(vm.user.CurrentUid, vm.room.rid)
                            .then(function(response){
                                vm.room.isAdmin = response;
                                getActivityList();
                            });
                    }
                });
            });

        })();

      
        function register() {
            vm.dataLoading = true;
			
            ActivityService.CreateActivity(vm.user.CurrentUid, vm.room.CurrentRid, vm.activity)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                    } else {
                        vm.dataLoading = false;
                    }
                });
        }

        function goCreate(){
            StateService.room.selectedRoom = vm.room;
            StateService.activity.selectedActivity = vm.activity;
            $location.path("/home/activity/create");
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
                    //FlashService.Error("Cannot find any activity for this room");
                }
            });
        }

        function startActivity(status, id) {
            ActivityService.StartActivity(status, id);
        };

        promise.then(function(response){
            if(response.status == 200 && response.data != null && response.data.length > 0) {
                vm.activity = response.data;
                StateService.activity.ActivityList = vm.activity;
            }
            else{
                //FlashService.Error("Cannot find any room");
            }
        });
        
    }
})();