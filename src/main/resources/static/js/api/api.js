/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Serviço responsável pelas chamadas rest
 */

(function () {
    'use strict';

    angular.module('app')
        .service('api', ['$http',
            function ($http) {

                this.getUser = function () {
                    return $http.get('/api/user', entry)
                        .then(function (response) {
                            return response.data;
                        });
                };

                this.saveUser = function (entry) {
                    return $http.post('/api/save/user', entry)
                        .then(function (response) {
                            return response;
                        });
                };

                this.updateUser = function (entry) {
                    return $http.put('/api/update/user', entry)
                        .then(function (response) {
                            return response;
                        });
                };

                this.deleteUser = function (id) {
                    return $http.delete('/api/delete/user/' + id)
                        .then(function (response) {
                            return response;
                        });
                };

                this.getCash = function (entry) {
                    return $http.post('/api/withdrawcash/', entry)
                        .then(function (response) {
                            return response;
                        });
                };

                this.login = function (entry) {
                    return $http.post('/api/login', entry)
                        .then(function (response) {
                            return response;
                        });
                };

                this.logout = function (entry) {
                    return $http.post('/api/logout', entry)
                        .then(function (response) {
                            return response;
                        });
                };
            }
        ]);
}());
