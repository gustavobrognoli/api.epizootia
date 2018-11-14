//angular.module("vigilantos").controller('LaudoLaboratorioVisualizacaoController', 
//		function ($scope, api, $uibModal, toastr, $routeParams, $location) {
//
//	//Variaveis
//	$scope.laudo = {};
//	$scope.identificacao = {};
//	$scope.identificacao.numero = "";
//	
//	$scope.arquivo = {};
//	
//	var idFicha = $routeParams.idFicha;
//	var idLaudo = $routeParams.idLaudo;
//	
//	//Funções 
//	init = function() {
//		api.artropodes.condicaoAmostra.getAll().then( function(response){
//			$scope.condicoes = response.data;
//		});
//		
//		api.artropodes.destinoAmostra.getAll().then( function(response){
//			$scope.destinos = response.data;
//		});
//		
//		api.usuario.getUserOnline().then( function(response){
//			$scope.usuarioOnline = response.data;
//			$scope.roles = $scope.usuarioOnline.roles;
//			
//			$scope.getPermissoes();
//		});
//		
//		$scope.getPermissoes = function() {
//			var possuiPermissao = false;
//			
//			for (i = 0; i < $scope.roles.length; i++) {
//				var role = $scope.roles[i];
//				possuiPermissao = false;
//				
//				if( role.modulo.nome == "artropodes" ){
//					possuiPermissao = true;
//					
//					if( role.descricao == "admin" ){
//						$scope.isPermissaoAdmin = true;
//						break;
//					}
//					else{
//						possuiPermissao = false;
//					}
//				}
//			}
//			
//			// comentado para permitir exibir o laudo
////			if( !possuiPermissao ){
////				$location.path("/error403");				
////			}
//		}
//		
//		if( idLaudo ) {
//			api.usuario.getUserOnline().then( function(response) {
//				api.artropodes.laudo.getById( idLaudo, idFicha, response.data.id ).then( function( response ){
//					if(response.data.id == 0){
//						$location.path("/error403");
//						return;
//					}
//					
//					$scope.laudo = response.data;
//					
//					$scope.laudo.dtCadastro = new Date($scope.laudo.dtCadastro);
//					if(!$scope.laudo.dtAlteracao){
//						$scope.laudo.dtAlteracao = $scope.laudo.dtCadastro;
//					}
//					
//					if($scope.laudo === undefined
//							|| $scope.laudo === ''){
//						return;
//					}
//					
//					if($scope.laudo.dtRecebimento !== undefined
//							&& $scope.laudo.dtRecebimento !== "") {					
//						$scope.laudo.dtRecebimento = new Date($scope.laudo.dtRecebimento);
//						$scope.laudo.dtRecebimento.setDate($scope.laudo.dtRecebimento.getDate() + 1);
//					}
//		
//					api.artropodes.fichaAmostra.getById( $scope.laudo.ficha.id ).then( function( response ){
//						$scope.ficha = response.data;
//						
//						$scope.identificacao.numero = $scope.laudo.ficha.id;
//		
//						
//						api.artropodes.fichaAmostra.getAnimaisByFicha( $scope.laudo.ficha.id ).then( function( response ){
//							$scope.ficha.animais = response.data;
//						});
//					});
//				});
//			
//			});
//		}
//	};
//	
//	init();
//	
//	$scope.emitirLaudo = function() {
//		if($scope.form.$valid){
//			$scope.laudo.ficha = $scope.ficha;
//			
//			if($scope.identificacao.numero == $scope.ficha.id){
//				
//				if( $scope.laudo.id > 0 ){
//					api.artropodes.laudo.update( $scope.laudo ).then( function(response){
//						toastr.success("Laudo atualizado com sucesso");
//					});	
//				}
//				
//				else{
//					api.artropodes.laudo.insert( $scope.laudo ).then( function( response ){
//						toastr.success("Laudo salvo com sucesso");
//						$location.path("/artropodes/laudos");
//					});
//				}
//				
//			} else {
//				var msg = "N° de Controle da Amostra não confere";
//				$scope.mensagemAlerta(msg);
//			}
//		}
//	}
//	
//	$scope.visualizarTaxonomia = function( amostra ){
//		
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/artropode/classificacao.taxonomica.animal.visualizacao.html", 
//			controller: "ClassificacaoTaxonomicaVisualizacaoController",
//			
//			backdrop: 'static', 
//			keyboard: false,
//			
//			resolve: {
//				amostra: function(){
//					return amostra;
//				}
//			}
//		});
//	}
//	
//	$scope.mensagemAlerta = function( mensagem ){
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/template/dialog.mensagem.html", 
//			controller: "ModalMensagemDialogController",
//			
//			resolve: { 
//				mensagem: function () {
//		    		return mensagem;
//		        },
//		        titulo: function (){
//		        	return "Aviso";
//		        }
//		      }
//		});
//	}
//	
//});