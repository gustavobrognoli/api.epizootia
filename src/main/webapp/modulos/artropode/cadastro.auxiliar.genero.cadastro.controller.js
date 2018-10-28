angular.module("vigilantos").controller('CadastroAuxiliarGeneroCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, genero){
	
	$scope.genero = genero;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
	
	
			
	$scope.salvar = function(genero){
		if( $scope.form.$valid ){
			$scope.genero = genero;
			
			if($scope.genero.id){
				
				api.artropodes.animalGenero.update( genero ).then( function(response){
					$scope.genero.id = response.data;
					toastr.success("Gênero atualizado com sucesso");
					$uibModalInstance.close( $scope.genero );
				});
			} else {
				api.artropodes.animalGenero.insert( genero ).then( function(response){
					$scope.genero.id = response.data;
					toastr.success("Gênero salvo com sucesso");
					$uibModalInstance.close( $scope.genero );
				});
			}
		}
	}
	
	api.artropodes.animalFamilia.getAll().then( function(response){
		$scope.familias = response.data;
	});
	
	
});