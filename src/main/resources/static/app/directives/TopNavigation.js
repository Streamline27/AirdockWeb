app.directive('topNavigation', ['$route', 'AuthService', '$window', function($route, authService, $window){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'app/directives/TopNavigation.html',
        link: function($scope, element, attr) {

            $scope.logout = function() {
                authService.logout()
            }

        }
    }
}]);