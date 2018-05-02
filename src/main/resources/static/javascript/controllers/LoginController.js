
app.controller('LoginController', ['$scope', 'AuthService', '$location',
    function($scope, authService, $location){

    var user = {};

    $scope.login = function() {
        onSuccess = function(){
            $location.path('/')

        };
        onError = function(){
            alert("Error")
        };
        authService.Login($scope.user.username, $scope.user.password, onSuccess, onError);
    }
    
}]);