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
        vm.user = {};
        vm.rooms = {};
        vm.admittedRooms = {};
        vm.newRoom = {};
        vm.createLoading=false;

        // functions
        vm.getRooms = getRooms;
        vm.roomChosen = roomChosen;
        vm.getRoomsParticipant = getRoomsParticipant;
        vm.register = register;
        vm.goCreate = goCreate;
        vm.getAdminList = getAdminList;

        (function init(){
            var initdef = $q.defer();
            var initpromise = initdef.promise;
            if(StateService.user.CurrentUid == null||StateService.user.CurrentUid == undefined)
                StateService.room.initdef = initdef;
            else
                getRooms();

            initpromise.then(function(){
                vm.user = StateService.user;
                getRooms();
                getAdminList();
            });
        })();
        

        function register() {
            vm.createLoading = true;
            
            RoomService.Registration(vm.newRoom, vm.user.CurrentUid)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('/home/room');
                    } else {
                        vm.createLoading = false;
                    }
                });
        }

        function roomChosen(selectedRoom){
            StateService.room.selectedRoom = selectedRoom;
            $location.path("/home/activity");
        }

        function getRoomsParticipant(){
            
        }
        
        function getRooms(){
            RoomService.getRoomByUid(StateService.user.CurrentUid)
            .then(function (response){
                deffered.resolve(response);
        });
        	
        }

        function goCreate(){
            $location.path("/home/room/createRoom");
        }

        function getAdminList(){
            RoomService.getRoomByAdmin(vm.user.CurrentUid)
                .then(function(response){
                    if(response.status == 200 && response.data != null && response.data.length > 0) {
                        vm.admittedRooms = response.data;
                        StateService.room.admittedList = vm.admittedRooms;
                        if(vm.rooms.length<vm.admittedRooms.length)
                            getRooms();
                        else{
                            attachAdmin();
                            StateService.room.RoomList = vm.rooms;
                        }
                    }
                    else{

                    }
                });
        }

        function attachAdmin(){
            for(var i = 0; i<vm.admittedRooms.length;i++){
                for(var j=0; j<vm.rooms.length;j++){
                    if(vm.rooms[j].isAdmin)
                        continue;

                    if(vm.admittedRooms[i].rid == vm.rooms[j].rid){
                        vm.rooms[j].isAdmin = true;
                        break;
                    }
                    else if(vm.rooms[j].isAdmin == null || vm.rooms[j].isAdmin == undefined)
                        vm.rooms[j].isAdmin = false;
                }
            }
        }

        promise.then(function(response){
            if(response.status == 200 && response.data != null && response.data.length > 0) {
                //FlashService.Success("Rooms found", true);
                vm.rooms = response.data;
                if(vm.admittedRooms.length > 0)
                    attachAdmin();
                StateService.room.RoomList = vm.rooms;
            }
            else{
                FlashService.Error("Cannot find any room");
            }
        });
    }
})();