app.controller('EditTaskController', function($scope, $http, $stateParams) {
	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: '',
		workOrder: ''

	};

	var id = $stateParams.taskId;

	$http.get("/api/tasks/" + id).then(function(response) {
		var task = response.data;
		$scope.task = {
			title: task.title,
			assignee: "" + (task.user ? task.user.id : ""),
			from: task.start ? new Date(task.start) : null,
			to: task.end ? new Date(task.end) : null,
			description: task.description,
			workOrder: "" + (task.workOrder ? task.workOrder.id : "")
		};
	});


	$scope.submit = function() {
		$http.put("/api/tasks/" + id, $scope.task);
	};

});