angular.module("vigilantos").controller('CadastroAuxiliarTipoAnimalCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, tipoAnimal){
	
	$scope.tipoAnimal = tipoAnimal;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(tipoAnimal){
		if( $scope.form.$valid ){
			$scope.tipoAnimal = tipoAnimal;
			
			if($scope.tipoAnimal.id){
				
				api.artropodes.tipoAnimal.update( tipoAnimal ).then( function(response){
					$scope.tipoAnimal.id = response.data;
					toastr.success("Tipo Animal atualizado com sucesso");
					$uibModalInstance.close( $scope.tipoAnimal );
				});
			} else {
				api.artropodes.tipoAnimal.insert( tipoAnimal ).then( function(response){
					$scope.tipoAnimal.id = response.data;
					toastr.success("Tipo Animal salvo com sucesso");
					$uibModalInstance.close( $scope.tipoAnimal );
				});
			}
		}
	}
	
	api.artropodes.cadastroAuxiliarTipoAnimal.getMensagens().then( function(response){
		$scope.mensagens = response.data;
	});
	
	
});