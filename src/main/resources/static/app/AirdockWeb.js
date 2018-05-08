
var app = angular.module('AirdockWeb', ['ngRoute', 'ngCookies', 'angular-jwt']);

app.config(['$routeProvider', '$httpProvider', 'jwtOptionsProvider', function($routeProvider, $httpProvider, jwtOptionsProvider){

    $routeProvider
        .when('/', {
        	controller: 'HelloController',
            templateUrl: 'app/views/hello.html',
            data: { requiresLogin: true }
        }).when('/create', {
            controller: 'CreateController',
            templateUrl: 'app/views/create.html',
            data: { requiresLogin: true }
        }).when('/list', {
			controller: 'TaskListController',
			templateUrl: 'app/views/tasklist.html',
            data: { requiresLogin: true }
        }).when('/users', {
            controller: 'UsersController',
            templateUrl: 'app/views/users.html',
            data: { requiresLogin: true }
		}).when('/edit/:taskId', {
			controller: 'EditController',
			templateUrl: 'app/views/edit.html',
            data: { requiresLogin: true }
		}).when('/login', {
            controller: 'LoginController',
            templateUrl: 'app/views/login.html'
        });


    jwtOptionsProvider.config({

        tokenGetter: ['AuthService', function (authService) { return authService.getToken() }],
        unauthenticatedRedirectPath: '/login'
    });
    $httpProvider.interceptors.push('jwtInterceptor');

}]);

app.run(['$rootScope', '$location', 'AuthService', '$interval', function ($rootScope, $location, authService, $interval) {

    var canLogin = authService.loginFromCookies();

    if (canLogin && authService.tokenIsExpired()) {
        authService.clearCookies();
        canLogin = false
    }

    $rootScope.$on('$routeChangeStart', function (event) {

        if (!authService.isLoggedIn() && $location.path() !== '/login' && !canLogin) {
            console.log('DENY : Redirecting to Login');
            event.preventDefault();
            $location.path('/login');
        }
        else {
            console.log('ALLOW');
        }

        if (authService.isLoggedIn() && authService.tokenIsExpired()) {
            authService.logout(function () {
                $location.path('/login');
            })
        }
    });

}]);