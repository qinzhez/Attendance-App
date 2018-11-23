(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('IndexController', IndexController);

    IndexController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function IndexController($location, AuthenticationService, FlashService) {
        var vm = this;

        

        (function initController() {
            var hasCookies = AuthenticationService.HasCredentials();

            if(hasCookies){
                $location.path('/home');
            }
        })();


    }

})();