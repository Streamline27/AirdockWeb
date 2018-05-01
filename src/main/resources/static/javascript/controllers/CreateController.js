app.controller('CreateController', function($scope, $http) {
	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: ''

	}

	$scope.assignees = [];

	$scope.submit = function() {
		$http.post("/api/tasks", $scope.task)
	}

	$http.get("/api/users/workers").then(function(response) {
		$scope.assignees = response.data;
	})

});