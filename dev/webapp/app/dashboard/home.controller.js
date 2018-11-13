(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('HomeController', HomeController);

    LoginController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function LoginController($location, AuthenticationService, FlashService) {
        var vm = this;


        (function initController() {
            var isLogin = AuthenticationService.ValidLogin();

            if(!isLogin){
                $location.path('/');
            }
        })();

    }

})();