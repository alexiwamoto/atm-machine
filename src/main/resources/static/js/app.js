/**
 * Created by alexandre on 08/01/18.
 */

/**
 * Configuração e rotas do módulo
 */

angular.module('app', ['ngRoute', 'ui.utils.masks', 'ipCookie', 'jutaz.ngSweetAlertAsPromised'])
    .config(function ($routeProvider, $locationProvider) {

        $locationProvider.html5Mode(true);

        $routeProvider.when('/', {
            templateUrl : 'pages/login.html',
            controller : 'LoginController'
        });
        $routeProvider.when('/register', {
            templateUrl : 'pages/register.html',
            controller : 'UserController'
        });
        $routeProvider.when('/transaction', {
            templateUrl : 'pages/transaction.html',
            controller : 'AtmController'
        });
        $routeProvider.when('/profile', {
            templateUrl : 'pages/profile.html',
            controller : 'UserController'
        });
        $routeProvider.otherwise({redirectTo: '/'});
    });