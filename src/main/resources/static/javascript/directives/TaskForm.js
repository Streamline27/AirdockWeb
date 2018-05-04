app.directive('taskForm', ['$route', '$http', function($route, $http){
    return{
        restrict: 'E',
        scope: {
        	task: '=',
        	onSubmit: '&'
        },
        templateUrl: 'javascript/directives/TaskForm.html',
        link: function($scope, element, attr){
			$scope.assignees = [];
			$http.get("/api/users/workers").then(function(response) {
				$scope.assignees = response.data;
			});
        }
    }
}]);