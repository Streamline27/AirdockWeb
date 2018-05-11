app.controller('EditWorkOrderController', function($scope, $http, $stateParams, $state) {
	$scope.workOrder = {
		title: '',
		description: ''
	}

	var id = $stateParams.workOrderId;

	$http.get("/api/workorders/" + id).then(function(response) {
		$scope.workOrder = response.data;
	});

	$scope.submit = function() {
		$http.put("/api/workorders/" + id, $scope.workOrder).then(function() {
			$state.go('workorders');
		});
	};
});