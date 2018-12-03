(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['$rootScope','$stateParams', 'Flash', '$location','$window', '$q', 'ActivityService', 'StateService','CheckinService'];
    function ActivityController($rootScope, $stateParams, Flash, $location, $window, $q, ActivityService, StateService, CheckinService) {

        var deffered = $q.defer();
        var promise = deffered.promise;
        //create activity
    	var vm = this;
        vm.enteredRID = $stateParams.enterRID;
        vm.currentUrl = $location.url();
    	vm.user = {};
    	vm.room = {};
        vm.activity = {};
        vm.newActivity = {};
        vm.room = StateService.room.selectedRoom;
        vm.editActivity = StateService.activity.selectedActivity;
        
        vm.register = register;
        vm.dataLoading = false;
        vm.getActivityList = getActivityList;
        vm.startActivity = startActivity;
        vm.checkin = checkin;
        vm.getCheckinInfo = getCheckinInfo;
        vm.goCreate = goCreate;
        vm.goConfig = goConfig;
        vm.updateActivity = updateActivity;
        vm.removeActivity = removeActivity;

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
			
            ActivityService.CreateActivity(vm.user.CurrentUid, vm.room.rid, vm.newActivity)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        StateService.room.selectedRoom = vm.room;
                    
                    $location.path("/home/activity");
                    } else {
                       
                    }
                    vm.dataLoading = false
                });
        }

        function goCreate(){
            StateService.room.selectedRoom = vm.room;
            vm.activity = {};
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

        function startActivity(status, row) {
            ActivityService.StartActivity(status, row.aid)
                .then(function(response){
                    if(response.status == 200 && response.data == true){
                        row.started = status;
                    }
                })
        };
        function goConfig(selectedActivity){
            StateService.room.selectedRoom = vm.room;
            selectedActivity.date = new Date(selectedActivity.date);
            selectedActivity.due = new Date(selectedActivity.due);
            StateService.activity.selectedActivity = selectedActivity;
            $location.url("/home/activity/config?enterRID="+vm.room.rid+"&enterAID="+selectedActivity.aid);
        }

        function updateActivity(rid, activity){
            ActivityService.updateActivity(activity)
                .then(function(response){
                    if(response.status==200 && response.data == true){
                        $location.url("/home/activity?enterRID="+rid);
                    }
                    else{
                        //FlashService.Error("Cannot find any room");
                    }
                });
        }

        function removeActivity(activity){
            activity.acid = vm.user.CurrentUid;
            activity.name = $rootScope.globals.currentUser.token;
            ActivityService.removeActivity(activity)
                .then(function(response){
                    if(response.status==200 && response.data == true){
                        getActivityList();
                    }
                    else{
                        //FlashService.Error("Cannot find any room");
                    }
                });
        }

        function checkin(row){
            CheckinService.checkin(StateService.user.CurrentUid, vm.room.rid, row.aid)
                .then(function(response){
                    if(response.status == 200 && response.data == true){
                        row.attendance = 1;
                    }
                    getActivityList();
            });
            
        }

        function getCheckinInfo(row){
            CheckinService.getCheckinInfo(StateService.user.CurrentUid,
                                            vm.room.rid, row.aid).then(function(response){
                if(response.data != null && response.status == 200){
                    
                }
        });
        }

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