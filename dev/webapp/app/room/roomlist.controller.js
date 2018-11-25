(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('RoomListController', RoomListController);
    RoomListController.$inject = ['RoomService', 'AuthenticationService', 'FlashService', '$scope', 'StateService'];
    function RoomListController(RoomService, AuthenticationService, FlashService, $scope, StateService) {
        
        var vm = this;
        vm.roomOwned = false;
        vm.getRoomsOwned = getRoomsOwned;

        function getRoomsOwned(){
            RoomService.getRoomByAdmin(StateService.user.CurrentUid)
            .then(function (response){
                if(response.status == 200 && response.data == true) {
                    //FlashService.Success("Rooms found", true);
                    $scope.rooms = response.records;
                    vm.roomOwned = true;
                    $location.path('/home');
                }
                else{
                    //FlashService.Error("Cannot find any room");
                    vm.roomOwned = false;
                }
        });
        	
        }
    }
})();