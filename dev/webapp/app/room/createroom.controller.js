(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('CreateroomController', CreateroomController);

    CreateroomController.$inject = ['RoomService', '$location', '$rootScope', 'FlashService'];
    function CreateroomController(RoomService, $location, $rootScope, FlashService) {
        var wrx = this;
        wrx.availableRoomname = false;
        wrx.checkNewRoomname = checkNewRoomname;
        wrx.register = register;

        function register() {
            wrx.dataLoading = true;
			
            RoomService.createRoom(wrx.room)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/home/createRoom');
                    } else {
                        FlashService.Error(response.message);
                        wrx.dataLoading = false;
                    }
                });
        }

        function checkNewRoomname() {
            RoomService.getRoomByName(wrx.room.name)
                .then(function (response) {
                    if(response.status == 200 && response.data == true) {
                        wrx.availableRoomname = false;
                    } else {
                        wrx.availableRoomname = true;
                    }
                });
        }
    }

})();
