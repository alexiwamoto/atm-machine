/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Controller responsável pela página de login
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('LoginController', ['api', 'loginService', '$scope','SweetAlert', '$location',
            function (api, loginService, $scope, SweetAlert, $location) {

                $scope.error = false;
                $scope.user = {};
                $scope.originalUser = angular.copy($scope.user);

                function cleanFields() {
                    $scope.user = angular.copy($scope.originalUser);
                    $scope.loginForm.$setPristine();
                }

                function sendAuthUser(user) {
                    $scope.$emit('authUser', user);
                }

                $scope.login = function () {
                    api.login($scope.user)
                        .then(function(response) {
                            loginService.setUser(response.data);
                            if(response.status === 200) {
                                SweetAlert.success({
                                    text: "Login feito com sucesso"
                                })
                            }
                            sendAuthUser(loginService.getUser());
                            cleanFields();
                            $location.path('/transaction');

                        })
                        .catch(function (response) {
                            if(response.status === 404) {
                                SweetAlert.error({
                                    text: "Usuário não registrado"
                                });
                            }
                            if(response.status === 409) {
                                SweetAlert.error({
                                    text: "Usuário já está logado"
                                });
                            }
                            if(response.status === 417) {
                                SweetAlert.error({
                                    text: "Senha incorreta"
                                });
                            }
                            if(response.status === 403) {
                                SweetAlert.error({
                                    text: "Excedeu o limite de usuários"
                                });
                            }
                        });
                };
            }]);
}());
