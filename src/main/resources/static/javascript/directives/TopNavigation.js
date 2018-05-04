app.directive('topNavigation', ['$route', 'AuthService', function($route, authService){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'javascript/directives/TopNavigation.html',
        link: function($scope, element, attr){
            $scope.logout = function() {
                authService.Logout()
            }
        }
    }
}]);