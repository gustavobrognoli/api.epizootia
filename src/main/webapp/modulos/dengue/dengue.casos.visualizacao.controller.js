angular.module("vigilantos").controller("ModalVisualizaCasosController", function($scope, $uibModalInstance, caso ){
	
	$scope.caso = caso;
	
	$scope.close = function(){
		$uibModalInstance.dismiss();
	}
	
});