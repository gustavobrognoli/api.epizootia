app.controller('CorpoAguaController', function($scope, api, $uibModal,
		$uibModalInstance, toastr) {

	$scope.salvar = function(corpoAgua) {
		api.epizootia.corposAgua.insert(corpoAgua).then(function(response) {
			$scope.corpoAgua.id = response.data;
			toastr.success("Corpo d`Água salvo com sucesso");
			$uibModalInstance.close($scope.corpoAgua);
		});
	}

	$scope.cancelar = function() {
		$uibModalInstance.close(null);
	}

});