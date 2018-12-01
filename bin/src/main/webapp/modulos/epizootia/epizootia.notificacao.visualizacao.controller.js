angular.module("vigilantos").controller('ModalVisualizacaoController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, $routeParams, $location) {

	$scope.close = function(){
		$uibModalInstance.dismiss();
	}
	
	api.epizootia.animal.getAll().then(function( response ) {
		$scope.animais = response.data.data;
	});
	
	api.epizootia.localidade.getAll().then(function( response ) {
		$scope.localidades = response.data.data;
	});	
	
	api.epizootia.caracteristica.getAll().then(function( response ) {
		$scope.caracteristicas = response.data.data;
	});
	
	api.epizootia.equipamento.getAll().then(function( response ) {
		$scope.equipamentos = response.data.data;
	});
	
	api.epizootia.genero.getAll().then(function( response ) {
		$scope.generos = response.data.data;
	});
	
	api.epizootia.impacto.getAll().then(function( response ) {
		$scope.impactos = response.data.data;
	});
	
	
	$scope.visualizaAnimal = function(fichaAnimal, size){
		
		//api.epizootia.animal.get( id ).then(function(response) {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/epizootia/visualiza.macaco.html", 
				controller: "ModalVisualizaAnimalController",
				backdrop: 'static', 
				keyboard: false,
				size:size,
				resolve: { 
			    	animal: fichaAnimal
			      }
			});
		}, function(error) {
			
		}, function(value) {
			
		//});
	}
});