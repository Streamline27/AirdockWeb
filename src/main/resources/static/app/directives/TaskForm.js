app.directive('taskForm', ['$http', function($http){
    return{
        restrict: 'E',
        scope: {
        	task: '=',
        	onSubmit: '&'
        },
        templateUrl: 'app/directives/TaskForm.html',
        link: function($scope, element, attr){
			$scope.assignees = [];
			$http.get("/api/users/workers").then(function(response) {
				$scope.assignees = response.data;
			});
        }
    }
}]);