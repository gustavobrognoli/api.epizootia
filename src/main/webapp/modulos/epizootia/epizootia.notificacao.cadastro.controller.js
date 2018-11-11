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

	
	api.epizootia.metodoCaptura.getAll().then(function( response ) {
		$scope.metodosCaptura = response.data.data;
	});	
	
	
	
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
	
	
	$scope.remover = function(id){
		api.epizootia.animal.excluir(id).then(function(response){
			toastr.success('Animal removido com sucesso');
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
				id: function(){
					return fichaAnimal.id;
				}
			}
		});
	
		modalInstance.result.then(function (id){
			$scope.remover( id );
		});
	}
			
	

/*	$scope.salvarFicha = function( key ){
		if( !$scope.form.$valid && ( key == 'localidade')){
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
			}   else if($scope.form.$valid && $scope.epizootia.animais.length == 0){
				var msg;
				key == 'dados_animal' ? msg = "Inclua os animais da coleta para salvar." : msg = "Inclua os animais da coleta em 'Dados do Animal' para salvar."; 
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


	
	$scope.salvarLocalidade = function(localidade){
		api.epizootia.localidade.insert( localidade ).then( function(response){
			$scope.localidade.id = response.data;
			toastr.success("Localidade salva com sucesso");
		});
	}*/
	
	
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


	
});