app.controller('SituacaoFundiariaController', function($scope, api, $uibModal,
		$uibModalInstance, toastr) {

	$scope.salvar = function(situacaoFundiaria) {
		api.epizootia.situacaoFundiaria.insert(situacaoFundiaria).then(function(response) {
			$scope.situacaoFundiaria.id = response.data;
			toastr.success("Situação Fundiária salva com sucesso");
			$uibModalInstance.close($scope.situacaoFundiaria);
		});
	}

	$scope.cancelar = function() {
		$uibModalInstance.close(null);
	}

});