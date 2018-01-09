/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Controller responsável pela barra de navegação
 */

(function() {
    'use strict';

    angular.module('app')
        .controller('NavigationController', ['$rootScope', '$scope', 'SweetAlert', '$location', 'loginService', 'api',
            function ($rootScope, $scope, SweetAlert, $location, loginService, api) {

                $scope.logged = false;

                function fillAuthUser() {
                    $scope.authUser = loginService.getUser();
                    if($scope.authUser == null) {
                        $scope.logged = false;
                    }
                    else {
                        $scope.logged = true;
                    }
                }
                fillAuthUser();

                $rootScope.$on('authUser', function(event, data) {
                    $scope.authUser = data;
                    $scope.logged = true;
                });

                $rootScope.$on('changedUser', function(event, data) {
                    $scope.authUser = data;
                    $scope.logged = false;
                });

                $scope.logout = function () {
                    api.logout(loginService.getUser())
                        .then(function(response) {
                            SweetAlert.success({
                                text: 'Sessão terminada'
                            });
                            loginService.removeUser();
                            fillAuthUser();
                            $location.path('/');
                        })
                        .catch(function() {
                            SweetAlert.error({
                                text: "Não foi possível realizar a operação"
                            });
                        });
                }
            }]);
}());
