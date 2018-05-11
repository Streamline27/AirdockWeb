app.controller('CreateTaskController', function($scope, $http) {
	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: '',
		workOrder: ''

	};

	$scope.submit = function() {
		$http.post("/api/tasks/task", $scope.task);
	};
});