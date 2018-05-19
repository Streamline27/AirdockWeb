app.controller('CreateTaskController', ['$scope', '$http', function($scope, $http) {
	$scope.task = {
		title: '',
		assignee: '',
		from: '',
		to: '',
		description: '',
		workOrder: ''
	};

    $scope.assignees = [{id: '', name: 'None'}];
    $http.get("/api/users/workers").then(function(response) {
        $scope.assignees = [{id: '', name: 'None'}].concat(response.data);
    });

    $scope.workOrders = [{id: '', title: 'None'}];
    $http.get("/api/workorders").then(function(response) {
        $scope.workOrders = [{id: '', title: 'None'}].concat(response.data);
    });
	$scope.submit = function() {
		$http.post("/api/tas", $scope.task);
	};
}]);