app.controller('CadastroEspecieController', function($scope, api, $uibModal,
		$uibModalInstance, toastr) {

	$scope.salvar = function(especie) {
		api.epizootia.especie.insert(especie).then(function(response) {
			$scope.especie.id = response.data;
			toastr.success("Espécie salva com sucesso");
			$uibModalInstance.close($scope.especie);
		});
	}

	$scope.cancelar = function() {
		$uibModalInstance.close(null);
	}

});