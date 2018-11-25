(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'AuthenticationService', 'FlashService', '$q', 'UserService', 'StateService'];
    function HomeController($location, AuthenticationService, FlashService, $q, UserService, StateService) {
        var vm = this;
        vm.showName = null;


        (function initController() {
            var deferred = $q.defer();
            var promise = deferred.promise;

            AuthenticationService.ValidCredentials(deferred);

            promise.then(function(result){
                if(!result){
                    $location.path('/');
                }
                else{
                    UserService.GetUserByUid(StateService.user.CurrentUid).then(
                        function(reponse){
                            if(reponse.status == 200 && reponse.data.uid>0)
                                StateService.user.CurrentUser = reponse.data;
                                vm.showName = StateService.user.CurrentUser.firstName;

                                vm.showName = "Logout";
                        });
                }
            });
           
        })();

        vm.test =1;
            
        vm.Logout = function(){
            AuthenticationService.ClearCredentials();
             $location.path('/');
        };

    }

})();