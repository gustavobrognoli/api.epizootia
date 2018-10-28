angular.module("vigilantos").controller("ModalCadastraObjetoController", function($scope, $uibModalInstance, mensagem, tamanho){
	
	$scope.mensagem = mensagem;
	$scope.tamanho = tamanho;
	
	$scope.nome = null;
	
	$scope.cancelar = function(){
		$uibModalInstance.dismiss();
	}
	
	$scope.salvar = function(){
		$uibModalInstance.close( $scope.nome );
	}
	
});