(function () {
    'use strict';

    angular
        .module('attendU')
        .controller('DashboardController', DashboardController);

    DashboardController.$inject = ['$location', 'AuthenticationService', 'Flash', '$q', 'UserService', 'StateService'];
    function DashboardController($location, AuthenticationService, Flash, $q, UserService, StateService) {
        var vm = this;
        vm.userLoaded = false;
        vm.showName = null;
        vm.dashboardActivity = {};

        

    }

})();