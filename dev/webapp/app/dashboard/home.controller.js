(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'AuthenticationService', 'FlashService', '$q'];
    function HomeController($location, AuthenticationService, FlashService, $q) {
        var vm = this;


        (function initController() {
            var deferred = $q.defer();
            var promise = deferred.promise;

            AuthenticationService.ValidCredentials(deferred);

            promise.then(function(result){
                if(!result){
                    $location.path('/');
                }
            });
           
        })();

    }

})();