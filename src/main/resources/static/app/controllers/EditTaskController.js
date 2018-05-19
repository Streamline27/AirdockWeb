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

	$http.get("/api/task/" + id).then(function(response) {
		var task = response.data;
		$scope.task = {
			title: task.title,
			assignee: task.assignee,
			from: task.from ? new Date(task.from) : null,
			to: task.to ? new Date(task.to) : null,
			created: task.created ? new Date(task.created) : null,
			description: task.description,
			workOrder: task.workOrder
		};
		console.log($scope.task);
	});

	$scope.submit = function() {
		$http.put("/api/tasks/" + id, $scope.task);
	};

});