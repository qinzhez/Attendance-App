(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('ActivityController', ActivityController);

    ActivityController.$inject = ['$window', 'AuthenticationService', 'FlashService', '$q', 'UserService', 'StateService'];
    function ActivityController($window, AuthenticationService, FlashService, $q, UserService, StateService) {
        var vm = this;
        
        //if(StateService.room.SelectedRoom == null)
        //    $window.history.back();

        vm.room = StateService.room.SelectedRoom; //TODO: temp name





        

        vm.test =1;
            
        

    }

})();