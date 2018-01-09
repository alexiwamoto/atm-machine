/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Diretiva do componente de barra de navegação
 */

(function () {
    'use strict';

    angular.module('app')
        .directive('navigationHeader', [ function () {
            return {
                restrict: 'E',
                templateUrl: 'js/navigation/navigation-header.html',
                controller: 'NavigationController'
            }
        }]);
}());
