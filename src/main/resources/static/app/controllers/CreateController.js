app.controller('CreateController', function($scope, $http) {
	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: ''

	}
	$scope.submit = function() {
		$http.post("/api/tasks/task", $scope.task);
	}
});