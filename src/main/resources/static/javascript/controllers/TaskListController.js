app.controller('TaskListController', function($scope, $http) {

	$http.get("/api/tasks").then(function(response) {
		$scope.tasks = response.data;
	})

});