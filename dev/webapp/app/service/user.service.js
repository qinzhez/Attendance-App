(function () {
    'use strict';

    angular
        .module('attendU')
        .factory('UserService', UserService);

    UserService.$inject = ['$http', '$timeout', '$q'];
    function UserService($http, $timeout, $q) {
		var backend = "localhost";
		var userPort = "8004";
		
        var service = {};

        service.GetAll = GetAll;
        service.GetById = GetById;
        service.GetByUsername = GetByUsername;
        service.Update = Update;
        service.Delete = Delete;

		service.Registration = Registration;
        service.Login = Login;
        service.ValidLogin = ValidLogin;
		
        return service;

		function Registration(info) {
			return $http.post('http://'+backend+':'+userPort+'/user/registration', info).then(handleSuccess, handleError('Error register with server'));		
		}

        function Login(info) {
            return $http.post('http://'+backend+':'+userPort+'/user/login', info).then(handleSuccess, handleError('Error register with server'));        
        }

        function ValidLogin(uid, token) {
            var info = {uid:uid, token:token};
            return $http.post('http://'+backend+':'+userPort+'/user/validToken', info).then(handleSuccess, handleError('Login validation failed'));
        }

        function GetByUsername(username) {
            return $http.get('http://'+backend+':'+userPort+'/user/username/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }
		
        function GetAll() {
            return $http.get('/api/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        
        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return { success: false, message: error };
            };
        }
    }  

})();
