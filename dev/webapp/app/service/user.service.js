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
			return $http.post('http://'+backend+':'+userPort+'/user/registration', info).then(handleReponse);		
		}

        function Login(info) {
            return $http.post('http://'+backend+':'+userPort+'/user/login', info).then(handleReponse);        
        }

        function ValidLogin(uid, token) {
            var info = {uid:uid, token:token};
            return $http.post('http://'+backend+':'+userPort+'/user/validToken', info).then(handleReponse);
        }

        function GetByUsername(username) {
            return $http.get('http://'+backend+':'+userPort+'/user/username/' + username).then(handleReponse);
        }
		
        function GetAll() {
            return $http.get('/api/users').then(handleReponse);
        }

        function GetById(id) {
            return $http.get('/api/users/' + id).then(handleReponse);
        }

        
        function Update(user) {
            return $http.put('/api/users/' + user.id, user).then(handleReponse);
        }

        function Delete(id) {
            return $http.delete('/api/users/' + id).then(handleReponse);
        }

        // private functions

        function handleReponse(res) {
            return res;
        }
    }  

})();
