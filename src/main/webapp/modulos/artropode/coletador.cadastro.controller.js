angular.module("vigilantos").controller('ColetadorCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, coletador){
	
	$scope.coletador = coletador;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(coletador){
		if( $scope.form.$valid ){
			$scope.coletador = coletador;
			
			if($scope.coletador.id){
				api.artropodes.coletador.update( coletador ).then( function(response){
					$scope.coletador.id = response.data;
					toastr.success("Coletador atualizado com sucesso");
					$uibModalInstance.close( $scope.coletador );
				});
			} else {
				delete $scope.coletador.id;
				api.artropodes.coletador.insert( coletador ).then( function(response){
					$scope.coletador.id = response.data;
					toastr.success("Coletador salvo com sucesso");
					$uibModalInstance.close( $scope.coletador );
				});
			}
		}
	}
	
});