angular.module("vigilantos").controller("ModalVisualizaFocosController", function($scope, $uibModalInstance, dengue ){
	
	$scope.dengue = dengue;
	
	$scope.close = function(){
		$uibModalInstance.dismiss();
	}
	
});