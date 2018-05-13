app.controller('TaskListController', function($scope, $http, $state, $httpParamSerializer) {
	$http.get("/api/tasks/search").then(function(response) {
		$scope.tasks = response.data;
	});

	$scope.redirectToEdit = function(id) {
		$state.go('edit/task', {taskId : id});
	};

	$scope.deleteTask = function(id) {
		$http.delete("/api/task/" + id).then(function(response) {
			$scope.tasks = $scope.tasks.filter(function(task) { return task.id !== id});
		})
	};

	$scope.parseDate = function(date) {
		return date ? new Date(date).toString() : 'empty'
	};

	$scope.search = function(filter) {
		var filterParams = $httpParamSerializer(filter);
		$http.get("/api/tasks/search?" + filterParams).then(function(response) {
			$scope.tasks = response.data;
		})
	}
});