angular.module("vigilantos").controller('EpizootiaNotificacaoController', 
		function ($scope, api, $uibModal, toastr, $routeParams, $location) {

	$scope.addMacaco = function(size){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.macaco.html", 
			controller: "CadastroMacacoController",

			backdrop: 'static', 
			keyboard: false,
			size:size,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( amostraAnimal ){
			if(amostraAnimal != null) {
				$scope.epizootia.animais.push( amostraAnimal );
			}
		});
	}

	$scope.addMorador = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.morador.html", 
			controller: "CadastroMoradorController",

			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( localMorador ){
			if(localMorador != null) {
				$scope.epizootia.morador.push( localMorador );
			}
		});
	}

	$scope.addUnidade = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.unidadeConservacao.html", 
			controller: "CadastroConservacaoController",

			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( regEpidemiologicoUnidade ){
			if(regEpidemiologicoUnidade != null) {
				$scope.epizootia.unidade.push( localUnidade );
			}
		});
	}

	
});