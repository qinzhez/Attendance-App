(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('RoomListController', RoomListController);
    RoomListController.$inject = ['$location','$q','RoomService', 'AuthenticationService', 'FlashService', '$scope', 'StateService'];
    function RoomListController($location, $q, RoomService, AuthenticationService, FlashService, $scope, StateService) {
        var deffered = $q.defer();
        var promise = deffered.promise;
        var vm = this;
        vm.roomOwned = false;
        vm.rooms = {};
        vm.newRoom = {};
        vm.createLoading=false;

        // functions
        vm.getRoomsOwned = getRoomsOwned;
        vm.roomChosen = roomChosen;
        vm.getRoomsParticipant = getRoomsParticipant;
        vm.register = register;
        

        function register() {
            vm.createLoading = true;
            
            RoomService.Registration(vm.newRoom, StateService.user.CurrentUid)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('/home/room');
                    } else {
                        vm.createLoading = false;
                    }
                });
        }

        function roomChosen(rid){
            alert("hello world");
            StateService.room.selectedRid = rid;
        }

        function getRoomsParticipant(){
            
        }
        
        function getRoomsOwned(){
            RoomService.getRoomByAdmin(StateService.user.CurrentUid)
            .then(function (response){
                deffered.resolve(response);
        });
        	
        }

        promise.then(function(response){
            if(response.status == 200 && response.data != null && response.data.length > 0) {
                //FlashService.Success("Rooms found", true);
                vm.rooms = response.data;
                vm.roomOwned = true;
            }
            else{
                FlashService.Error("Cannot find any room");
                vm.roomOwned = false;
            }
        });
    }
})();