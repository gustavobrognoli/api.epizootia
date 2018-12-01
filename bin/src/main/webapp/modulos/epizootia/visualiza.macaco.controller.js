angular.module("vigilantos").controller("ModalVisualizaAnimalController", function($scope, api, $uibModalInstance, animal ){
	
	$scope.animal = animal;
	
	api.epizootia.viscera.getAll().then(function( response ) {
		$scope.visceras = response.data.data;
	});

	api.epizootia.anormalidade.getAll().then(function( response ) {
		$scope.anormalidades = response.data.data;
	});
	
	$scope.close = function(){
		$uibModalInstance.dismiss();
	}
	
});