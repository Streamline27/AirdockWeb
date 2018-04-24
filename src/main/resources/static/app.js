'use.strict'

var app = angular.module('app', ['ngRoute']);
//app.config(function($routeProvider){
//  $routeProvider.when('/', {
//    controller: 'ctrl',
//    templateUrl: 'hello.html'
//  })
//})
app.controller('ctrl', function($scope) {
    $scope.message = "Hello World";
});