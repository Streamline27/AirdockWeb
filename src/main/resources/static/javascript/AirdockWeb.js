
var app = angular.module('AirdockWeb', ['ngRoute', 'ngCookies']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
        	controller: 'HelloController',
            templateUrl: 'views/hello.html'
        }).when('/login', {
            controller: 'LoginController',
            templateUrl: 'views/login.html'
        }).when('/create', {
            controller: 'CreateController',
            templateUrl: 'views/create.html'
        }).when('/list', {
			controller: 'TaskListController',
			templateUrl: 'views/tasklist.html'
        }).when('/users', {
            controller: 'UsersController',
            templateUrl: 'views/users.html'
		}).when('/edit/:taskId', {
			controller: 'EditController',
			templateUrl: 'views/edit.html'
		});
});

app.run(['$rootScope', '$location', 'AuthService', function ($rootScope, $location, authService) {

        var canLogin = authService.loginFromCookies();

        $rootScope.$on('$routeChangeStart', function (event) {

            if (!authService.isLoggedIn() && $location.path() !== '/login' && !canLogin) {
                console.log('DENY : Redirecting to Login');
                event.preventDefault();
                $location.path('/login');
            }
            else {
                console.log('ALLOW');
            }
        });

    }]);