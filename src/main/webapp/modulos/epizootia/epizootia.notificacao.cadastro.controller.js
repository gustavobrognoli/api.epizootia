angular.module("vigilantos").controller('EpizootiaNotificacaoController', 
		function ($scope, api, $uibModal, toastr, $routeParams, $location) {
	
	// $scope.impactosSelecionados = [];

	/*$scope.fichas = [];*/
	$scope.ficha;

	$scope.municipios = [ {
		id: 1, 
		nome : "Palhoça"
	}, {
		id: 2, 
		nome : "São José" 
	}, {
		id: 3, 
		nome : "Florianópolis" 
	} ];
	
	// Buscar apenas se for Alteração
	api.epizootia.animal.getAll().then(function( response ) {
		$scope.animais = response.data.data;
	});

	api.epizootia.resultado.getAll().then(function( response ) {
		$scope.resultados = response.data.data;
	});	
	
	api.epizootia.morador.getAll().then(function( response ) {
		$scope.moradores = response.data.data;
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
	
	api.epizootia.equipamento.getAll().then(function( response ) {
		$scope.equipamentos = response.data.data;
	});
	
	api.epizootia.genero.getAll().then(function( response ) {
		$scope.generos = response.data.data;
	});
	
	api.epizootia.impacto.getAll().then(function( response ) {
		$scope.impactos = response.data.data;
	});
	
	api.epizootia.metodoCaptura.getAll().then(function( response ) {
		$scope.metodosCaptura = response.data.data;
	});	

	$scope.salvarFicha = function(ficha) {
		
			ficha['animais'] = $scope.animais;
			ficha['municipio'] = ficha.municipio.nome;
			
			// Impactos
			var impactos = [];
			var selecionadosImpactos = $scope.impactos;
			for (var i = 0; i < $scope.impactos.length; i++) {
		    	if ($scope.impactos[i].selected) {
		        	var impacto = {'id': $scope.impactos[i].id, 'impacto': $scope.impactos[i].impacto };
		            impactos.push(impacto);
		        }
	        }
/*            // Outros
	        if (impacto.outros.selected) {
	        	var impacto = {'id': 99, 'impacto': impacto.outros.descricao};
	            impactos.push(impacto);
	        }
			ficha['impactos'] = impactos;*/
		        
			// Caracteristica
			var caracteristicas = [];
			var selecionadosCaracteristica = $scope.caracteristicas;
	        for (var i = 0; i < $scope.caracteristicas.length; i++) {
	            if ($scope.caracteristicas[i].selected) {
	            	var caracteristica = {'id': $scope.caracteristicas[i].id, 'caracteristica':$scope.caracteristicas[i].caracteristica };
	            	caracteristicas.push(caracteristica);
	            }
	        }
			ficha['caracteristicas'] = caracteristicas;
			
			// Equipamentos
			var equipamentos = [];
			var selecionadosEquipamento = $scope.equipamentos;
	        for (var i = 0; i < $scope.equipamentos.length; i++) {
	            if ($scope.equipamentos[i].selected) {
	            	var equipamento = {'id': $scope.equipamentos[i].id, 'equipamento':$scope.equipamentos[i].equipamento };
	            	equipamentos.push(equipamento);
	            }
	        }
			ficha['equipamentos'] = caracteristicas;
			        
			// Espécies do Vetor
			var generos = [];
			var selecionadosGenero = $scope.generos;
	        for (var i = 0; i < $scope.generos.length; i++) {
	            if ($scope.generos[i].selected) {
	            	var genero = {'id': $scope.generos[i].id, 'genero':$scope.generos[i].genero };
	            	generos.push(genero);
	            }
	        }
			ficha['generos'] = generos;

			api.epizootia.ficha.insert( ficha ).then( function(response){
				$scope.ficha = response.data;
				toastr.success("Dados da Notificação salvos com sucesso");
			});
//	        api.epizootia.ficha.update( ficha ).then( function(response) {
//				$scope.ficha.id = response.data;
//				toastr.success("Dados do Registro Entomológico salvos com sucesso");
//			});
	}
		
	$scope.addMacaco = function(size){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.macaco.html", 
			controller: "CadastroMacacoController",
			backdrop: 'static', 
			keyboard: false,
			size:size,
			resolve: {
				animais: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( animais ){
			if(animais != null) {
				$scope.animais.push(animais);
				$scope.animais.push(animais);
			}
		});
	}
	
	$scope.remover = function(id) {
		api.epizootia.animal.excluir(id).then(function(response){
			$scope.animais.splice($scope.animais.indexOf(response.data.data), 1);
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
			
	$scope.visualizaAnimal = function(fichaAnimal, size){
		
		// api.epizootia.animal.get( id ).then(function(response) {
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
			
		// });
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
	
});