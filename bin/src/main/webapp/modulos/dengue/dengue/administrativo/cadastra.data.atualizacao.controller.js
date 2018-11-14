angular.module("vigilantos").controller("CadastroDtAtualizcaoDengueController", function($scope, $uibModalInstance, dataAtualizacao ){
	
	$scope.dtAtualizacao = dataAtualizacao;
	
	$scope.cancelar = function(){
		$uibModalInstance.dismiss();
	}
	
	$scope.salvar = function(){
		$uibModalInstance.close( $scope.dtAtualizacao );
	}
	
});