app.directive('taskForm', ['$http', function($http){
    return{
        restrict: 'E',
        scope: {
        	task: '=',
        	onSubmit: '&'
        },
        templateUrl: 'app/directives/TaskForm.html',
        link: function($scope, element, attr){
			$scope.assignees = [{id: '', name: 'None'}];
			$http.get("/api/users/workers").then(function(response) {
				$scope.assignees = [{id: '', name: 'None'}, ...response.data];
			});
        }
    }
}]);