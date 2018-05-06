app.directive('topNavigation', ['$route', 'AuthService', '$location', function($route, authService, $location){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'app/directives/TopNavigation.html',
        link: function($scope, element, attr){
            $scope.logout = function() {
                authService.Logout(function () {
                    $location.path('/login');
                })
            }
        }
    }
}]);