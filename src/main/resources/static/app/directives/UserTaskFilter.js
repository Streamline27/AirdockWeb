app.directive('userTaskFilter', ['$http', function($http){
    return{
        restrict: 'E',
        scope: {
        	onSearch: '&'
        },
        templateUrl: 'app/directives/UserTaskFilter.html',
        link: function($scope, element, attr){
        	$scope.filter = {};
			var baseFilter = Object.assign({}, $scope.filter);

			function onChangeEvent() {
				if (angular.equals($scope.filter, baseFilter))
					return;
				$scope.onSearch({filter: $scope.filter});
				baseFilter = Object.assign({}, $scope.filter);
				$scope.onChange = function() {}
				setTimeout(function() {
					$scope.onChange = onChangeEvent;
					$scope.onChange();
				}, 1000)
			};
			$scope.onChange = onChangeEvent;
        }
    }

}]);