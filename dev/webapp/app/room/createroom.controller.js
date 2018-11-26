(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('CreateroomController', CreateroomController);

    CreateroomController.$inject = ['RoomService', '$location', '$rootScope'];
    function CreateroomController(RoomService, $location, $rootScope) {
        var wrx = this;
        wrx.room={};
        wrx.register = register;
        wrx.dataLoading=false;

        function register() {
            wrx.dataLoading = true;
			
            RoomService.Registration(wrx.room)
                .then(function (response) {
                    if (response.status == 200 && response.data == true) {
                        $location.path('/home/createRoom');
                    } else {
                        wrx.dataLoading = false;
                    }
                });
        }
    }

})();
