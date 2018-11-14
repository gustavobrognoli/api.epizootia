angular.module("vigilantos").controller('LaudoRelatorioEspecificoController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, relatorioEspecificoLaudoTaxonomia, ficha, laudo, $window){
	
	$scope.relatorioEspecificoLaudoTaxonomia = relatorioEspecificoLaudoTaxonomia;
	
	$scope.gerarRelatorioEspecificoLaudoTaxonomia = function(relatorioLaudoTaxonomiaDTO) {		
		if($scope.formRelatorio.$valid) {			
			api.artropodes.laudo.gerarRelatorioEspecLaudoTaxonomia(relatorioLaudoTaxonomiaDTO);
			$uibModalInstance.close();
		}
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close();	
	}
	
});