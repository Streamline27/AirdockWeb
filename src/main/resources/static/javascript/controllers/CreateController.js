app.controller('CreateController', function($scope, $http) {
//	$('#toInput').datepicker({uiLibrary: 'bootstrap4'});
//	$('#fromInput').datepicker({uiLibrary: 'bootstrap4'});


	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: ''

	}

	$scope.assignees = [];

	$scope.submit = function() {
		$http.post("/tasks", $scope.task)
	}

	$http.get("/users/workers").then(function(response) {
		console.log(response.data);
		$scope.assignees = response.data;
	})

});