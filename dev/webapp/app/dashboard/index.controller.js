(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('IndexController', IndexController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;

        

        (function initController() {
            var hasCookies = AuthenticationService.HasCredentials();

            if(hasCookies){
                $location.path('/home');
            }
        })();


    }

})();