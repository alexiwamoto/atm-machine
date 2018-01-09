/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Controller responsável pelas operações
 */

(function () {
    'use strict';

    angular.module('app')
        .controller('AtmController',
            ['atmService', '$scope', 'loginService', 'SweetAlert',
                function (atmService, $scope, loginService, SweetAlert) {

                    $scope.able = true;
                    $scope.localUser = {};
                    $scope.numberOfBills = [];
                    $scope.balance = {};

                    function objToStrMap(obj) {
                        for (var k in obj) {
                            $scope.typeOfBill = {};
                            if(obj[k] != 0) {
                                $scope.typeOfBill.bill = k;
                                $scope.typeOfBill.amount= obj[k];
                                $scope.numberOfBills.push($scope.typeOfBill);
                            }
                        }
                    }

                    function cleanForm() {
                        $scope.form.$setPristine();
                        $scope.form.withdraw = '';
                        $scope.numberOfBills = [];
                    }


                    function fillForm() {
                        $scope.localUser = loginService.getUser();
                        $scope.balance = $scope.localUser.bankBalance;
                        $scope.numberOfBills = [];
                    }
                    fillForm();

                    $scope.withdraw = function () {
                        $scope.localUser.withdrawCash = $scope.form.withdraw;
                        atmService.getCash($scope.localUser)
                            .then(function (response) {
                                if(response.status === 200) {
                                    $scope.balance = response.data.user.bankBalance;
                                    loginService.setUser(response.data.user);
                                    objToStrMap(response.data.bills);
                                    SweetAlert.success({
                                        text: "Operação realizada com sucesso"
                                    });
                                } else {
                                    SweetAlert.error({
                                        text: "Saldo insuficiente"
                                    });
                                }
                                $scope.able = false;
                            }).catch(function () {
                            SweetAlert.error({
                                text: "Erro na operação"
                            });
                        });
                    };

                    $scope.getBills = function () {
                        $scope.able = true;
                        cleanForm();
                    }
                }]);
}());
