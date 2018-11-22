(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('RegisterController', RegisterController);

    RegisterController.$inject = ['UserService', '$location', '$rootScope', 'FlashService'];
    function RegisterController(UserService, $location, $rootScope, FlashService) {
        var vm = this;
        vm.availableUsername = true;
        vm.checkNewUsername = checkNewUsername;
        vm.register = register;

        function register() {
            vm.dataLoading = true;
			
            UserService.Registration(vm.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        vm.dataLoading = false;
                    }
                });
        }

        function checkNewUsername() {
            UserService.GetByUsername(vm.user.username)
                .then(function (response) {
                    if(response.success) {
                        vm.availableUsername = false;
                    } else {
                        vm.availableUsername = true;
                    }
                });
        }
    }

})();
