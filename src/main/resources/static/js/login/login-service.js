/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Serviço responsável pelo gerenciamento de usuário na sessão
 */

(function() {
    'use strict';

    angular.module('app')
        .service('loginService', ['api', 'ipCookie', function (api, ipCookie) {

            this.setUser = function (user) {
                ipCookie('user', user);
                console.log('ipCookie:',  ipCookie);
            };

            this.getUser = function () {
                return ipCookie('user');
            };

            this.removeUser = function () {
                ipCookie.remove('user');
            };
        }]);

}());
