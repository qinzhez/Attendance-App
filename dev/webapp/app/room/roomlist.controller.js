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
        vm.getRoomsOwned = getRoomsOwned;
        vm.roomChosen = roomChosen;
        vm.getRoomsParticipant = getRoomsParticipant;

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
            if(response.status == 200 && response.data != null) {
                //FlashService.Success("Rooms found", true);
                vm.rooms = response.data;
                vm.roomOwned = true;
                $location.path('/home/room');
            }
            else{
                //FlashService.Error("Cannot find any room");
                vm.roomOwned = false;
            }
        });
    }
})();