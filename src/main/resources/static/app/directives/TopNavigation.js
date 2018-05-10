app.directive('topNavigation', ['AuthService', '$state', function(authService, $state){
    return{
        restrict: 'E',
        scope: {},
        templateUrl: 'app/directives/TopNavigation.html',
        link: function($scope, element, attr) {

            $scope.logout = function() {
                authService.logout(function () {
                    $state.go('login');
                })
            }

        }
    }
}]);