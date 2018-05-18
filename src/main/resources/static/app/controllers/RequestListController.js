app.controller('RequestListController', function($scope, $http, $state) {

	$scope.requests = {
		PENDING: [],
		CANCELLED: [],
		ACCEPTED: []
	}

	$scope.status = "PENDING"
	$scope.statuses = []
	$http.get("/api/request/statuses").then(function(response) {
		$scope.statuses = response.data;
	});


	$http.get("/api/requests?status=PENDING").then(function(response) {
		$scope.requests.PENDING = response.data;
	});

	$http.get("/api/requests?status=CANCELLED").then(function(response) {
		$scope.requests.CANCELLED = response.data;
	});

	$http.get("/api/requests?status=ACCEPTED").then(function(response) {
		$scope.requests.ACCEPTED = response.data;
	});

	$scope.cancel = function(id) {
		$http.put("/api/request/" + id + "/status/CANCELLED").then(function() {
			$scope.requests.CANCELLED = [$scope.requests.PENDING.find(function(request) { return request.id === id}), ...$scope.requests.CANCELLED]
			$scope.requests.PENDING = $scope.requests.PENDING.filter(function(request) { return request.id !== id});
		});
	};

	$scope.accept = function(id) {
		$http.put("/api/request/" + id + "/status/ACCEPTED").then(function() {
			$scope.requests.ACCEPTED = [$scope.requests.PENDING.find(function(request) { return request.id === id}), ...$scope.requests.ACCEPTED]
			$scope.requests.PENDING = $scope.requests.PENDING.filter(function(request) { return request.id !== id});
		});
	};

	$scope.isPending = function() {
		return $scope.status === "PENDING"
	}

});