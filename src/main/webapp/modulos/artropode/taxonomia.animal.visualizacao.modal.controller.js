angular.module("vigilantos").controller('TaxonomiaVisualizacaoController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, taxonomia, utils){
	
	$scope.taxonomia = taxonomia;
	$scope.evolucao = taxonomia.evolucao;
	$scope.quantidade = taxonomia.quantidade;
	$scope.especie = taxonomia.especie;
	$scope.familia = taxonomia.familia;
	$scope.genero = taxonomia.genero;

	$scope.importanciaMedica = taxonomia.importanciaMedica;
	
	$scope.cancelar = function(){
		$uibModalInstance.close();	
	}
	
});