(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('RoomListController', RoomListController);

    RoomListController.$inject = ['$RoomService', 'AuthenticationService', 'FlashService', '$rootScope'];
    function RoomListController($RoomService, AuthenticationService, FlashService, $rootScope) {
        var vm = this;
        vm.roomOwned = false;
        vm.checkRoomOwned = checkRoomOwned;
        vm.getRoomsOwned = getRoomsOwned;

        function checkRoomOwned(){
            RoomService.getRoomByAdmin(vm.user.uid)
            .then(function (response){
                if(response.status == 200 && response.data == true) {
                    vm.roomOwned = true;
                }
                else{
                    vm.roomOwned = false;
                }
            });
        }
        function getRoomsOwned(){
            RoomService.getRoomByAdmin(vm.user.uid)
            .then(function (response){
                if(response.status == 200 && response.data == true) {
                    FlashService.Success("Rooms found", true);
                    $location.path();
                }
                else{
                    FlashService.Error("Cannot find any room");
                }
        });
        	
        }
    }
});