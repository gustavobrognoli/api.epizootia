angular.module("vigilantos").controller('EpizootiaNotificacaoController', 
		function ($scope, api, $uibModal, toastr, $routeParams, $location) {
	
	// $scope.impactosSelecionados = [];

	api.epizootia.morador.getAll().then(function( response ) {
		$scope.moradores = response.data.data;
	});
	
	api.epizootia.animal.getAll().then(function( response ) {
		$scope.ficha.animais = response.data.data;
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
	
// $scope.GetValue = function () {
// for (var i = 0; i < $scope.impactos.length; i++) {
// if ($scope.impactos[i].selected) {
// var impacto = {'id': $scope.impactos[i].id, 'impacto':
// $scope.impactos[i].impacto };
// $scope.impactosSelecionados.push(impacto);
// }
// }
// toastr.success($scope.impactosSelecionados);
// }

	$scope.salvarFicha = function( key ){
		
		if( $scope.form.$valid){
			
			if( key == 'ficha_aba1'){
				api.epizootia.ficha.insert( ficha ).then( function(response){
					$scope.ficha.id = response.data;
					toastr.success("Dados do Animal salvos com sucesso");
				});
			}else if(key == 'ficha_aba2'){
				api.epizootia.ficha.update( ficha ).then( function(response){
					$scope.ficha.id = response.data;
					toastr.success("Dados do Local salvos com sucesso");
				});
			}else if(key == 'ficha_aba3'){
				api.epizootia.ficha.update( ficha ).then( function(response){
					$scope.ficha.id = response.data;
					toastr.success("Dados do Registro Entomológico salvos com sucesso");
				});
			}
		
		};
/* //////////////////////////////////////LOCALIDADE\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\ */
		
		
		// Impactos
		var selecionadosImpactos = $scope.impactos;
	        for (var i = 0; i < $scope.impactos.length; i++) {
	            if ($scope.impactos[i].selected) {
	            	var impacto = {'id': $scope.impactos[i].id, 'impacto': $scope.impactos[i].impacto };
	            	key.impactos.push(impacto);
	            }
	        }
            // Outros
	        if ($scope.impactos[i].selected) {
	        	var impacto = {'id': 99, 'impacto': ngmodel-outros };
	        }
	        
		// Caracteristica
		var selecionadosCaracteristica = $scope.caracteristicas;
	        for (var i = 0; i < $scope.caracteristicas.length; i++) {
	            if ($scope.caracteristicas[i].selected) {
	            	var caracteristicas = {'id': $scope.caracteristicas[i].id, 'caracteristica':$scope.caracteristicas[i].caracteristica };
	            	key.caracteristicas.push(caracteristica);
	            }
	        }
	        
/*
 * //////////////////////////////////////REGISTRO
 * ENTOMOLÓGICO\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
 */
	        
		// Equipamentos
		var selecionadosEquipamento = $scope.equipamentos;
	        for (var i = 0; i < $scope.equipamentos.length; i++) {
	            if ($scope.equipamentos[i].selected) {
	            	var equipamentos = {'id': $scope.equipamentos[i].id, 'equipamento':$scope.equipamentos[i].equipamento };
	            	key.equipamentos.push(equipamento);
	            }
	        }
		        
		// Espécies do Vetor
		var selecionadosGenero = $scope.generos;
	        for (var i = 0; i < $scope.generos.length; i++) {
	            if ($scope.generos[i].selected) {
	            	var generos = {'id': $scope.generos[i].id, 'genero':$scope.generos[i].genero };
	            	key.genero.push(genero);
	            }
	        }
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
/*
 * } else if($scope.form.$valid && $scope.epizootia.animais.length == 0){ var
 * msg; key == 'localidade' ? msg = "Inclua os animais da coleta para salvar." :
 * msg = "Inclua os animais da coleta em 'Dados do Animal' para salvar."; var
 * modalInstance = $uibModal.open({ templateUrl:
 * "modulos/template/dialog.mensagem.html", controller:
 * "ModalMensagemDialogController",
 * 
 * backdrop: 'static', keyboard: false,
 * 
 * resolve: { mensagem: function(){ return msg; }, titulo: function(){ return
 * "Aviso"; } } }); } else{}
 */
	}
	
});