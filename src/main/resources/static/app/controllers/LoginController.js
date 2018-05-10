
app.controller('LoginController', ['$scope', 'AuthService', '$state',
    function($scope, authService, $state){

    var user = {};

    $scope.login = function() {
        onSuccess = function(){
            $state.go('hello')

        };
        onError = function(){
            alert("Error")
        };
        authService.Login($scope.user.username, $scope.user.password, onSuccess, onError);
    }
    
}]);