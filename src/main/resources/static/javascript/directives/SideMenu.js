app.directive('sideMenu', ['$route', function($route){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'javascript/directives/SideMenu.html',
        link: function($scope, element, attr){
        }
    }
}]);