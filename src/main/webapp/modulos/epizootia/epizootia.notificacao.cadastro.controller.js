angular.module("vigilantos").controller('EpizootiaNotificacaoController', 
		function ($scope, api, $uibModal, toastr, $routeParams, $location) {

	api.epizootia.morador.getAll().then(function( response ) {
		$scope.moradores = response.data.data;
	});
	
	api.epizootia.animal.getAll().then(function( response ) {
		$scope.animais = response.data.data;
	});

	api.epizootia.situacaoFundiaria.getAll().then(function( response ) {
		$scope.situacoesFundiarias = response.data.data;
	});
	
	api.epizootia.unidadeConservacao.getAll().then(function( response ) {
		$scope.unidadesConservacao = response.data.data;
	});
	
	api.epizootia.registroEntomologico.getAll().then(function( response ) {
		$scope.registrosEntomologicos = response.data.data;
	});
	
	api.epizootia.corposAgua.getAll().then(function( response ) {
		$scope.corposAguas = response.data.data;
	});
	
	api.epizootia.localidade.getAll().then(function( response ) {
		$scope.localidades = response.data.data;
	});
	
	api.epizootia.caracteristica.getAll().then(function( response ) {
		$scope.caracteristicas = response.data.data;
	});
	
	api.epizootia.impacto.getAll().then(function( response ) {
		$scope.impactos = response.data.data;
	});
	
	api.epizootia.metodoCaptura.getAll().then(function( response ) {
		$scope.metodosCaptura = response.data.data;
	});	

	api.epizootia.resultado.getAll().then(function( response ) {
		$scope.resultados = response.data.data;
	});	
	
	$scope.municipios = [ {
		nome : ["Palhoça"]
	}, {
		nome : [ "São José" ]
	}, {
		nome : [ "Florianópolis" ]
	} ];
	
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
		
		modalInstance.result.then(function ( fichaAnimal ){
			if(fichaAnimal != null) {
				$scope.animais.push( fichaAnimal );
			}
		});
	}
	
	$scope.remover = function(fichaAnimal){
		api.epizootia.animal.excluir(fichaAnimal.id).then(function(response){
			toastr.success('Animal removido com sucesso');
			$scope.animais.splice(animais.indexOf(animal), 1);
		});
	}
	
	
	$scope.removerAnimal = function(fichaAnimal){
		
		var mensagem = "Confirma a exclusão do animal ?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
				mensagem: function () {
					return mensagem;
				},
				fichaAnimal: function(){
					return fichaAnimal;
				}
			}
		});
	
		modalInstance.result.then(function (id){
			$scope.remover( id );
		});
	}
			
	$scope.visualizaAnimal = function( id ){
		
		api.epizootia.animal.get( id ).then(function(response) {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/epizootia/visualiza.macaco.html", 
				controller: "ModalVisualizaAnimalController",
				backdrop: 'static', 
				keyboard: false,
				
				resolve: { 
			    	animal: function () {
			    		return response.data;
			        }
			      }
			});
		}, function(error) {
			
		}, function(value) {
			
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
				$scope.moradores.push( localMorador );
			}
		});
	}
	
	$scope.addCorpoAgua = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.corpoAgua.html", 
			controller: "CorpoAguaController",
			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( localCorpoAgua ){
			if(localCorpoAgua != null) {
				$scope.corposAguas.push( localCorpoAgua );
			}
		});
	}

	$scope.addSituacaoFundiaria = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.situacaoFundiaria.html", 
			controller: "SituacaoFundiariaController",
			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( localSituacaoFundiaria ){
			if(localSituacaoFundiaria != null) {
				$scope.situacoesFundiarias.push( localSituacaoFundiaria );
			}
		});
	}
	
	$scope.addUnidade = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.unidadeConservacao.html", 
			controller: "UnidadeConservacaoController",

			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( unidadesConservacao ){
			if(unidadesConservacao != null) {
				$scope.unidadesConservacao.push( unidadesConservacao );
			}
		});
	} 

	$scope.salvarFicha = function( key ){
		if( !$scope.form.$valid && ( key == 'localidade' || key == 'finalizar' )){
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Preencha os campos da etapa 'Dados do Animal' para salvar esta etapa.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		} else if($scope.form.$valid && $scope.epizootia.animais.length == 0){
			var msg;
			key == 'localidade' ? msg = "Inclua os animais da coleta para salvar." : msg = "Inclua os animais da coleta em 'Dados do Animal' para salvar."; 
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return msg;
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		}
		else{}
	}
	
});