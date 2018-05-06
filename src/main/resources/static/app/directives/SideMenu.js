app.directive('sideMenu', ['$route', function($route){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'app/directives/SideMenu.html',
        link: function($scope, element, attr){
        }
    }
}]);