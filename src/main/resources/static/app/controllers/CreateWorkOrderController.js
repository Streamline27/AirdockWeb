app.controller('CreateWorkOrderController', function($scope, $http, $state) {
	$scope.workOrder = {
		title: '',
		description: ''

	}
	$scope.submit = function() {
		$http.post("/api/workorders/workorder", $scope.workOrder).then(function() {
			$state.go('workorders');
		});
	}
});