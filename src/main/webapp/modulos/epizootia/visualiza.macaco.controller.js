angular.module("vigilantos").controller("ModalVisualizaAnimalController", function($scope, $uibModalInstance, animal ){
	
	$scope.animal = animal;
	
	$scope.close = function(){
		$uibModalInstance.dismiss();
	}
	
});