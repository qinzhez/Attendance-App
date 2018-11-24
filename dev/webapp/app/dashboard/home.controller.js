(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'AuthenticationService', 'FlashService'];
    function HomeController($location, AuthenticationService, FlashService) {
        var vm = this;


        (function initController() {
            AuthenticationService.ValidCredentials(function(result){
                if(!result){
                    $location.path('/');
                }
            });
           
        })();

    }

})();