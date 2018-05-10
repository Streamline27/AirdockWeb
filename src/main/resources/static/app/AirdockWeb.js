
var app = angular.module('AirdockWeb', ['ui.router', 'ngCookies', 'angular-jwt']);

app.config(['$stateProvider', '$urlRouterProvider', '$httpProvider', 'jwtOptionsProvider', function( $stateProvider, $urlRouterProvider, $httpProvider, jwtOptionsProvider){

    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('hello', {
            url: '/',
            controller: 'HelloController',
            templateUrl: 'app/views/hello.html',
            data: {
                requiresLogin: true
            }
        })
        .state('tasks', {
            url: '/tasks',
            controller: 'TaskListController',
            templateUrl: 'app/views/tasklist.html',
            data: {
                requiresLogin: true
            }
        })
        .state('users', {
            url: '/users',
            controller: 'UsersController',
            templateUrl: 'app/views/users.html',
            data: {
                requiresLogin: true
            }
        })
        .state('create', {
            url: '/task/create',
            controller: 'CreateController',
            templateUrl: 'app/views/create.html',
            data: {
                requiresLogin: true
            }
        })
        .state('edit', {
            url: '/task/{taskId}/edit/',
            controller: 'EditController',
            templateUrl: 'app/views/edit.html',
            data: {
                requiresLogin: true
            }
        })
        .state('login', {
            url: '/login',
            controller: 'LoginController',
            templateUrl: 'app/views/login.html',
            data: {
                requiresLogin: false
            }
        });



    jwtOptionsProvider.config({
        tokenGetter: ['options', 'AuthService', function (options, authService) {
            // Skip authentication for any requests ending in .html
            if (options == null && options.url.substr(options.url.length - 5) === '.html') {
                return null;
            }
            return authService.getToken()
        }]
    });
    $httpProvider.interceptors.push('jwtInterceptor');
    $httpProvider.interceptors.push('accessDeniedInterceptor');

}]);

app.run(['$rootScope', '$state', '$transitions', 'AuthService', function ($rootScope, $state, $transitions, authService) {

    $transitions.onStart({ to: '**' },function (trans) {
        to = trans.$to();
        if (to.data && to.data.requiresLogin) {
            if (!authService.isLoggedIn()) {
                return trans.router.stateService.target('login');
            }
        }
    });
}]);

app.factory('accessDeniedInterceptor', function ($state, $q) {
    return {
        responseError: function (response) {
            if(response.status === 403) {
                $state.go('login');
            }
            else {
                return $q.reject(response);
            }
        }
    }
})