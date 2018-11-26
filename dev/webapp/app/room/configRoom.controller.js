(function () {
    'use strict';
    angular
        .module('attendU')
        .controller('configRoomController', configRoomController);

    configRoomController.$inject = ['$location', 'AuthenticationService', 'FlashService','$q', 'StateService'];
    function configRoomController($location, AuthenticationService, FlashService, $q, StateService) {
        var vm = this;

        vm.congfigRoom = congfigRoom;

        (function initController() {
        })();

        function congfigRoom() {
            vm.dataLoading = true;

            var deferred = $q.defer();
            var promise = deferred.promise;
            var uid = StateService.user.CurrentUid;
            
            //CurrentRid is a temporary name
            var rid = StateService.room.CurrentRid;
            RoomService.UpdateRoomInfo(rid, vm.roomname, vm.roomdescription, vm.roomdescription, deferred);

            promise.then(function (response) {
                if (response.status == 200 && response.data['rid']>0) {
                    $location.path('/home/configRoom');
                } else {
                    vm.dataLoading = false;
                }
            });
        };
    }
})();
