angular.module("vigilantos").controller('CadastroAuxiliarEspecieCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, especie){
	
	$scope.especie = especie;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
	
	$scope.salvar = function(especie){
		if( $scope.form.$valid ){
			$scope.especie = especie;
			
			if($scope.especie.id){
				
				api.artropodes.animalEspecie.update( especie ).then( function(response){
					$scope.especie.id = response.data;
					toastr.success("Espécie atualizada com sucesso");
					$uibModalInstance.close( $scope.especie );
				});
			} else {
				//delete $scope.tipoAnimal.id;
				api.artropodes.animalEspecie.insert( especie ).then( function(response){
					$scope.especie.id = response.data;
					toastr.success("Espécie salva com sucesso");
					$uibModalInstance.close( $scope.especie );
				});
			}
		}
	}
	
	api.artropodes.animalFamilia.getAll().then( function(response){
		$scope.familias = response.data;
	});
	
	api.artropodes.animalGenero.getAll().then( function(response){
		$scope.generos = response.data;
	});
	
	$scope.getGenerosByFamilia = function(){
		api.artropodes.animalGenero.getGenerosByFamilia( $scope.especie.genero.familia.id ).then( function(response){
			$scope.generos = response.data;
		});
	}
	
	
});