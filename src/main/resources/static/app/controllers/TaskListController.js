app.controller('TaskListController', function($scope, $http, $location) {
	$http.get("/api/tasks").then(function(response) {
		$scope.tasks = response.data;
	})

	$scope.redirectToEdit = function(id) {
		$location.path('/edit/' + id);
	}

	$scope.deleteTask = function(id) {
		$http.delete("/api/tasks/" + id).then(function(response) {
			$scope.tasks = $scope.tasks.filter(function(task) { return task.id !== id});
		})
	}

	$scope.parseDate = function(date) {
		return date ? new Date(date).toString() : 'empty'
	}
});