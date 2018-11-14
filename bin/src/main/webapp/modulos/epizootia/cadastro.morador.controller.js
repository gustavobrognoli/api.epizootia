app.controller('CadastroMoradorController', function($scope, api, $uibModal,
		$uibModalInstance, toastr) {

	$scope.salvar = function(morador) {
		api.epizootia.morador.insert(morador).then(function(response) {
			$scope.morador.id = response.data;
			toastr.success("Morador salvo com sucesso");
			$uibModalInstance.close($scope.morador);
		});
	}

	$scope.cancelar = function() {
		$uibModalInstance.close(null);
	}

});