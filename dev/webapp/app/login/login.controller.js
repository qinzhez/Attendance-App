(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        vm.login = login;

        (function initController() {
            // reset login status
            AuthenticationService.ClearCredentials();
        })();

        function login() {
            vm.dataLoading = true;
            //AuthenticationService.ClearCredentials();
            AuthenticationService.Login(vm.username, vm.password, function (response) {
                if (response.status == 200 && response.data['uid']>0) {
                    AuthenticationService.SetCredentials(response.data['uid'], response.data['token']);
                    $location.path('/');
                } else {
                    FlashService.Error("Login failed");
                    vm.dataLoading = false;
                }
            });
        };
    }

})();
