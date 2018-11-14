angular.module("vigilantos").controller('CadastroAuxiliarFamiliaCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, familia){
	
	$scope.familia = familia;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(familia){
		if( $scope.form.$valid ){
			$scope.familia = familia;
			
			if($scope.familia.id){
				
				api.artropodes.animalFamilia.update( familia ).then( function(response){
					$scope.familia.id = response.data;
					toastr.success("Família atualizada com sucesso");
					$uibModalInstance.close( $scope.familia );
				});
			} else {
				//delete $scope.tipoAnimal.id;
				api.artropodes.animalFamilia.insert( familia ).then( function(response){
					$scope.familia.id = response.data;
					toastr.success("Família salva com sucesso");
					$uibModalInstance.close( $scope.familia );
				});
			}
		}
	}
	
});