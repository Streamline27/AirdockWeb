app.controller('EditController', function($scope, $http, $routeParams) {
	$scope.task = {
		title: '',
		assignee: null,
		from: '',
		to: '',
		description: ''

	}
	var id = $routeParams.taskId;

	$scope.assignees = [];
	$http.get("/api/users/workers").then(function(response) {
		$scope.assignees = response.data;
		console.log($scope.assignees);
	});

	$http.get("/api/tasks/" + id).then(function(response) {
		var task = response.data;
		console.log(task);
		$scope.task = {
			title: task.title,
			assignee: "" + task.user.id,
			from: new Date(task.start),
			to: new Date(task.end),
			description: task.description
		};
		console.log($scope.task);
	});


	$scope.submit = function() {
		$http.put("/api/tasks/" + id, $scope.task)
	};

});