app.controller('EditController', function($scope, $http, $routeParams) {
	$scope.task = {
		title: '',
		assignee: null,
		from: '',
		to: '',
		description: ''

	}
	var id = $routeParams.taskId;

	$http.get("/api/tasks/" + id).then(function(response) {
		var task = response.data;
		console.log(task);
		$scope.task = {
			title: task.title,
			assignee: "" + (task.user ? task.user.id : ""),
			from: task.start ? new Date(task.start) : null,
			to: task.end ? new Date(task.end) : null,
			description: task.description
		};
		console.log($scope.task);
	});


	$scope.submit = function() {
		$http.put("/api/tasks/" + id, $scope.task);
	};

});