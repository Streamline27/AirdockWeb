
var app = angular.module('AirdockWeb', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
        	controller: 'HelloController',
            templateUrl: 'views/hello.html'
        })
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'views/login.html'
        }).when('/create', {
            controller: 'CreateController',
            templateUrl: 'views/create.html'
        }).when('/list', {
			controller: 'TaskListController',
			templateUrl: 'views/tasklist.html'
		});
});