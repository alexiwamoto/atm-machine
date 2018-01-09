/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Controller responsável pelo CRUD de usuário
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('UserController',
            ['api', '$scope','SweetAlert', '$location', 'loginService',
                function (api, $scope, SweetAlert, $location, loginService) {

                    $scope.isEdit = false;
                    $scope.user = {};
                    $scope.originalUser = angular.copy($scope.user);

                    function fillUser() {
                        $scope.user = loginService.getUser();
                    }
                    fillUser();

                    function cleanRegister() {
                        $scope.registerForm.$setPristine();
                    }

                    function sendChangedUser() {
                        var user = loginService.getUser();
                        $scope.$emit('changedUser', user);
                    }

                    function cleanProfile() {
                        $scope.user = angular.copy($scope.originalUser);
                    }

                    function cleanEditUser() {
                        $scope.userEditForm.$setPristine();
                        $scope.editUser = {};
                    }

                    function cleanAll() {
                        loginService.removeUser();
                        cleanProfile();
                        cleanEditUser();
                        sendChangedUser();
                        $location.path('/');
                    }

                    $scope.register = function () {
                        $scope.user.authStatus = false;
                        $scope.user.withdrawCash = 0;
                        api.saveUser($scope.user)
                            .then(function (response) {
                                if(response.status === 201) {
                                    SweetAlert.success({
                                        text: "Usuário salvo com sucesso, faça o login"
                                    });
                                    cleanRegister();
                                    $location.path('/');
                                }
                            })
                            .catch(function () {
                                SweetAlert.error({
                                    text: "Algum erro aconteceu, tente novamente"
                                });
                                cleanRegister();
                                $location.path('/');
                            });
                    };

                    $scope.toEdit = function () {
                        $scope.isEdit = true;
                        $scope.editUser = loginService.getUser();
                    };

                    $scope.edit = function () {
                        api.updateUser($scope.editUser)
                            .then(function (response) {
                                if(response.status === 200) {
                                    SweetAlert.success({
                                        text: "Usuário alterado com sucesso"
                                    });
                                    cleanAll();
                                }
                            })
                            .catch(function () {
                                SweetAlert.error({
                                    text: "Algum erro aconteceu, tente novamente mais tarde"
                                })
                            });
                    };

                    $scope.remove = function () {
                        SweetAlert.warning({
                            text: "Você realmente deseja excluir usuário?",
                            showCancelButton: true,
                            cancelButtonText: "Cancelar?",
                            confirmButtonColor: "#DD6B55",
                            confirmButtonText: "Sim, desejo excluir"
                        }).then(function () {
                            var deleteUser = loginService.getUser();

                            api.deleteUser(deleteUser.id)
                                .then(function (response) {
                                    if(response.status === 200) {
                                        SweetAlert.success({
                                            text: "Excluido com sucesso"
                                        });
                                        cleanAll();
                                    }
                                })
                                .catch();
                        }, function () {
                            return;
                        });
                    }
                }]);
}());
