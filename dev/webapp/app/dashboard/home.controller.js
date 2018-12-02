(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'AuthenticationService', 'Flash', '$q', 'UserService', 'StateService'];
    function HomeController($location, AuthenticationService, Flash, $q, UserService, StateService) {
        var vm = this;
        vm.userLoaded = false;
        vm.showName = null;


        vm.onloadFun = (function initController() {
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
                                vm.userLoaded = true;
                                vm.showName = StateService.user.CurrentUser.firstName;

                                vm.showName = "Logout";

                                if(StateService.room.initdef != undefined ||
                                    StateService.room.initdef != null)
                                    StateService.room.initdef.resolve();
                                if(StateService.activity.initdef != undefined ||
                                    StateService.activity.initdef != null)
                                    StateService.activity.initdef.resolve();
                        });
                }
            });
           
        });

        vm.test =1;
            
        vm.Logout = function(){
            AuthenticationService.ClearCredentials();
             $location.path('/');
        };

    }

})();