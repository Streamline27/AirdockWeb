app.controller('UsersController', function($scope, $http) {

    $scope.user = {
        name : '',
        login : '',
        password : ''
    };

    $http.get("/api/users/workers").then(function(response) {
        $scope.users = response.data;
    });

    $scope.registerWorker = function () {
        $http.post("/api/users/worker/register", $scope.user)
            .then(function (value) {
                $scope.users.unshift(value.data)
            });
    };



});