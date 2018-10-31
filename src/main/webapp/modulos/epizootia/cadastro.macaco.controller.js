app.controller('CadastroMacacoController', 
function ($scope, api, $uibModal, $uibModalInstance, toastr){
	
	
	api.epizootia.especie.getAll().then(function( response ) {
		$scope.especies = response.data.data;
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