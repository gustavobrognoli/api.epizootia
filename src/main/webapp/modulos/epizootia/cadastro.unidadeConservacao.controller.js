app.controller('UnidadeConservacaoController', function($scope, api, $uibModal,
		$uibModalInstance, toastr) {

	$scope.salvar = function(unidadeConservacao) {
		api.epizootia.unidadeConservacao.insert(unidadeConservacao).then(function(response) {
			$scope.unidadeConservacao.id = response.data;
			toastr.success("Unidade de Conservação salva com sucesso");
			$uibModalInstance.close($scope.unidadeConservacao);
		});
	}

	$scope.cancelar = function() {
		$uibModalInstance.close(null);
	}

});