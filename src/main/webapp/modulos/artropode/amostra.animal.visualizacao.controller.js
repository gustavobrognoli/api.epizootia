angular.module("vigilantos").controller('AmostraAnimalVisualizacaocontroller', 
		function ($scope, api, $uibModalInstance, amostra){
			
	api.artropodes.tipoArmadilha.getAll().then( function(response){
		$scope.tiposArmadilhas = response.data;
	});
	
	api.artropodes.tipoColeta.getAll().then( function(response){
		$scope.tiposColeta = response.data;
	});
	
	api.artropodes.ambienteColeta.getAll().then( function(response){
		$scope.ambientesColeta = response.data;
	});
	
	api.artropodes.animais.getTipos().then( function(response){
		$scope.tiposAnimais = response.data;
	});

//	$scope.getAnimaisByTipo = function( idTipo ){
//		if( idTipo ){
//			api.artropodes.animais.getAnimaisByTipo( idTipo ).then( function(response){
//				$scope.animais = response.data;
//			});
//		}
//	}

//	if( amostra && amostra != null ){
//		$scope.amostra = amostra;
//		
//		$scope.tipoAnimal = $scope.amostra.animal.tipo;
//		$scope.getAnimaisByTipo( $scope.tipoAnimal.id );
//	}
	
	if( amostra && amostra != null ){
		$scope.amostra = amostra;
		
		$scope.tipoAnimal = $scope.amostra.tipoAnimal;
		
		if(!amostra.metodologia) {
			$scope.amostra.metodologia = false;
		} else {
			$scope.amostra.metodologia = amostra.metodologia;			
		}
	} else {
		$scope.amostra.metodologia = false;
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(){
		if( $scope.form.$valid ){
			$uibModalInstance.close( $scope.amostra );	
		}
	}
	
});