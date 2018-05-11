app.directive('workOrderForm', ['$http', function($http){
    return{
        restrict: 'E',
        scope: {
        	workOrder: '=',
        	onSubmit: '&'
        },
        templateUrl: 'app/directives/WorkOrderForm.html',
        link: function($scope, element, attr){
        }
    }
}]);