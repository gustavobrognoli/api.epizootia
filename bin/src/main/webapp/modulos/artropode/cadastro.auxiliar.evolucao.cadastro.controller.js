angular.module("vigilantos").controller('CadastroAuxiliarEvolucaoCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, evolucao){
	
	$scope.evolucao = evolucao;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(evolucao){
		if( $scope.form.$valid ){
			$scope.evolucao = evolucao;
			
			if($scope.evolucao.id){
				
				api.artropodes.animalEvolucao.update( evolucao ).then( function(response){
					$scope.evolucao.id = response.data;
					toastr.success("Evolução atualizada com sucesso");
					$uibModalInstance.close( $scope.evolucao );
				});
			} else {
				api.artropodes.animalEvolucao.insert( evolucao ).then( function(response){
					$scope.evolucao.id = response.data;
					toastr.success("Evolução salva com sucesso");
					$uibModalInstance.close( $scope.evolucao );
				});
			}
		}
	}
	
});