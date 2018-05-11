app.controller('WorkOrderListController', function($scope, $http, $state) {
	$http.get("/api/workorders").then(function(response) {
		$scope.workOrders = response.data;
	});

	$scope.redirectToEdit = function(id) {
		$state.go('edit/workorder', {workOrderId : id});
	};

	$scope.redirectToCreate = function() {
		$state.go('create/workorder');
	};

	$scope.deleteTask = function(id) {
		$http.delete("/api/workorders/" + id).then(function(response) {
			$scope.workOrders = $scope.workOrders.filter(function(workorder) { return workorder.id !== id});
		})
	};
});