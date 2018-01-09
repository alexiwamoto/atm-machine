/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Serviço responsável por acessar a api para efetuar saque
 */

(function () {
    'use strict';

    angular.module('app')
        .service('atmService', ['api', function (api) {

            this.getCash = function (entry){
                return api.getCash(entry)
                    .then(function(apiResponse) {
                        return apiResponse;
                    });
            };
        }]);
}());
