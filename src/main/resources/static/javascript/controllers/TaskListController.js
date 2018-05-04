app.controller('TaskListController', function($scope, $http, $location) {
	$http.get("/api/tasks").then(function(response) {
		$scope.tasks = response.data;
	})

	$scope.redirectToEdit = function(id) {
		$location.path('/edit/' + id);
	}

});