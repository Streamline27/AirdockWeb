
var app = angular.module('AirdockWeb', ['ngRoute']);

app.config(function($routeProvider){
    $routeProvider
        .when('/', {
            controller: 'HelloController',
            templateUrl: 'views/hello.html'
        });
});