app.controller('EditTaskController', function($scope, $http, $stateParams) {

	var id = $stateParams.taskId;

    $http.get("/api/task/" + id).then(function (response) {
        var task = response.data;
        $scope.task = {
            title       : task.title,
            assignee    : task.assignee,
            from        : task.from ? new Date(task.from) : null,
            to          : task.to ? new Date(task.to) : null,
            created     : task.created ? new Date(task.created) : null,
            description : task.description,
            workOrder   : task.workOrder,
            status      : task.status,
            formattedStatus : formatTaskStatus(task.status)
        };

        console.log($scope.task);
        refreshStatus(task.status)
    });

    $scope.assignees = [{id: '', name: 'None'}];
    $http.get("/api/users/workers").then(function(response) {
        $scope.assignees = [{id: '', name: 'None'}].concat(response.data);
    });

    $scope.workOrders = [{id: '', title: 'None'}];
    $http.get("/api/workorders").then(function(response) {
        $scope.workOrders = [{id: '', title: 'None'}].concat(response.data);
    });

	$scope.submit = function() {
		$http.put("/api/task/" + id, $scope.task);
	};

	$scope.setStatusDone = function(){
        $scope.setTaskStatus("DONE")
	};
    $scope.setStatusInProgress = function(){
        $scope.setTaskStatus("IN_PROGRESS")
    };
    $scope.setStatusCanceled = function(){
        $scope.setTaskStatus("CANCELED")
    };
    $scope.setStatusTodo = function(){
        $scope.setTaskStatus("TODO")
    };
    $scope.setTaskStatus = function setTaskStatus(status){
        $http.put("/api/task/"+id+"/status/" +status)
        refreshStatus(status);
    };

    function refreshStatus(status) {
        $scope.task.status = status;
        $scope.task.formattedStatus = formatTaskStatus(status);
        $scope.isTodo       =  $scope.task.status === 'TODO';
        $scope.isInProgress =  $scope.task.status === 'IN_PROGRESS';
        $scope.isCanceled   =  $scope.task.status === 'CANCELED';
        $scope.isDone       =  $scope.task.status === 'DONE';
    }

    /**
	 *  Downcase capitalize and replace _ with " "
     */
    function formatTaskStatus(status){
		var formattedStatus = status.charAt(0).toUpperCase() + status.slice(1).toLowerCase();
		return formattedStatus.split("_").join(" ")
    }

});