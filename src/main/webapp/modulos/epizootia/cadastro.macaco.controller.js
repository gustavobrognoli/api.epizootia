angular.module("vigilantos").controller('CadastroMacacoController', 
function ($scope, api, $uibModal, $uibModalInstance, toastr, animal){
	
	$scope.animal = {};
	
	api.artropodes.especie.getAll().then(function( response ) {
		$scope.especie = response.data;
	});
	
	$scope.salvar = function(animal){
		api.epizootia.animail.insert( animal ).then( function(response){
			$scope.animal.id = response.data;
			toastr.success("Animal salvo com sucesso");
			$uibModalInstance.close( $scope.animal );
		});
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	
});