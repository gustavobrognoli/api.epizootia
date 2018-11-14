//angular.module("vigilantos").controller('FichaAmostraVisualizacaoController', 
//		function ($scope, api, $uibModal, toastr, $routeParams, $location) {
//
//	var id = $routeParams.id;
//	
//	api.usuario.getUserOnline().then( function(response){
//		$scope.usuarioOnline = response.data;
//		$scope.roles = $scope.usuarioOnline.roles;
//		
//		$scope.getPermissoes();
//	});
//
//	$scope.getPermissoes = function(){
//		var possuiPermissao = false;
//		
//		for (i = 0; i < $scope.roles.length; i++) {
//			var role = $scope.roles[i];
//			possuiPermissao = false;
//			
//			if( role.modulo.nome == "artropodes" ){
//				possuiPermissao = true;
//				
//				if( role.descricao == "admin" ){
//					$scope.isPermissaoAdmin = true;
//					break;
//				}
//				else{
//					possuiPermissao = false;
//				}
//			}
//		}
//		
////		if( !possuiPermissao ){
////			$location.path("/error403");				
////		}
//	}
//	
//	if( id && id != "new" ){
//		api.usuario.getUserOnline().then( function(response){
//		api.artropodes.fichaAmostra.getFicha( id, response.data.id ).then( function( response ){
//			
//			if(response.data.id == 0){
//				$location.path("/error403");
//				return;
//			}
//			
//			$scope.artropodes = response.data;
//			$scope.municipio = $scope.artropodes.endereco.localidade.municipio;		
//			
//			if( $scope.artropodes.numNotificacaoSinan ){
//				$scope.ocorrenciaAcidente = true;
//			}
//			
//			
//			api.artropodes.fichaAmostra.getAnimaisByFicha( id ).then( function( response ){
//				$scope.artropodes.animais = response.data;
//			});
//			
//			$scope.getLocalidadesByMunicipio( $scope.municipio );
//			//$scope.getEnderecosByLocalidade( $scope.artropodes.endereco.localidade.id );
//			$scope.getByLocalidade();
//		});
//		
//		});
//	}
//	else{
//		$scope.artropodes = {};
//		$scope.artropodes.animais = [];
//	}
//	
//	// Alteração Julio
//	$scope.getByLocalidade = function(){
//
//		if( $scope.artropodes.endereco.localidade != null && $scope.artropodes.endereco.localidade.id > 0 ){
////			api.dengue.getEnderecos( $scope.artropodes.endereco.localidade ).then(function(response){		
////		debugger;		
////				$scope.enderecos = response.data;
////			});
//			
//			
//			api.artropodes.getEnderecos( $scope.artropodes.endereco.localidade ).then(function(response){		
//						$scope.enderecos = response.data;
//					});
//		}
//		else{
//			$scope.enderecos = [];
//		}
//    }
//	
//	api.municipio.getByUF( 'SC' ).then(function( response ) {
//		$scope.municipios = response.data;
//	});	
//
//// Removendo lista de endereços da tabela mod_artropodes_endereco e usando a sis_endereco
////	$scope.getEnderecosByLocalidade = function( idLocalidade ){
////		api.artropodes.fichaAmostra.getEnderecosByLocalidade( idLocalidade ).then( function( response ){
////			$scope.enderecos = response.data;
////		});
////	}
//	
//	$scope.salvarFicha = function( key ){
//		if( !$scope.form.$valid && ( key == 'tipo_animal' || key == 'finalizar' )){
//			var modalInstance = $uibModal.open({ 
//				templateUrl: "modulos/template/dialog.mensagem.html", 
//				controller: "ModalMensagemDialogController",
//				
//				backdrop: 'static', 
//				keyboard: false,
//				
//				resolve: {
//					mensagem: function(){
//						return "Preencha os campos da etapa 'Dados da Coleta' para salvar esta etapa.";
//					},
//					titulo: function(){
//						return "Aviso";
//					}
//				}
//			});
//		}
//		else{
//			
//			if($scope.form.$valid){
//				if( !$scope.ocorrenciaAcidente ){
//					$scope.artropodes.numNotificacaoSinan = null;
//				}
//				
//				
//				$scope.artropodes.usuario = $scope.usuarioOnline;
//				
//				if( !$scope.artropodes.latitude )
//					$scope.artropodes.latitude = 0;
//				if( !$scope.artropodes.longitude )
//					$scope.artropodes.longitude = 0;
//				
//				if( $scope.artropodes.id > 0 ){
//					for( i=0; i < $scope.artropodes.animais.length; i++){
//						$scope.artropodes.animais[i].fichaAmostra = null;
//					}
//					
//					api.artropodes.fichaAmostra.update( $scope.artropodes ).then( function(response){
//						toastr.success("Ficha atualizada com sucesso")
//					});	
//				}
//				else{
//					api.artropodes.fichaAmostra.insert( $scope.artropodes ).then( function(response){
//						$scope.artropodes.id = response.data;
//						
//						toastr.success("Ficha salva com sucesso")
//					});					
//				}
//			}
//		}
//	}
//	
//	$scope.visualizacaoAnimal = function( amostraAnimal ){
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/artropode/amostra.animal.visualizacao.html", 
//			controller: "AmostraAnimalVisualizacaocontroller",
//			
//			backdrop: 'static', 
//			keyboard: false,
//			
//			resolve: {
//				amostra: function(){
//					return amostraAnimal;
//				}
//			}
//		});
//		
//		modalInstance.result.then(function ( edit ){
////			$scope.artropodes.animais.push( amostraAnimal );
//		});
//		
//	}
//	
//	$scope.removerAnimal = function(amostraAnimal){
//		
//		var mensagem = "Confirma a exclusão?";
//		
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/template/confirma.exclusao.html", 
//			controller: "ModalConfirmaExclusaoController",
//			resolve: { 
//				mensagem: function () {
//					return mensagem;
//				},
//				id: function(){
//					return amostraAnimal.id;
//				}
//			}
//		});
//	
//		modalInstance.result.then(function (id){
//			for( i=0; i < $scope.artropodes.animais.length; i++ ){
//				var amostraIndex = $scope.artropodes.animais[ i ];
//
//				if( amostraAnimal.id ){
//					if( amostraAnimal.id == amostraIndex.id ){
//						$scope.artropodes.animais.splice(i, 1);
//						break;
//					}
//				}
//				else{
//					if( amostraAnimal.animal.tipo.id == amostraIndex.animal.tipo.id 
//							&& amostraAnimal.animal.id  == amostraAnimal.animal.id 
//							&& amostraAnimal.quantidade == amostraAnimal.quantidade ){
//						$scope.artropodes.animais.splice(i, 1);
//						break;
//					}
//				}
//			}
//			
//		});
//	}
//	
////	$scope.removerAnimal = function( amostraAnimal ){
////		for( i=0; i < $scope.artropodes.animais.length; i++ ){
////			var amostraIndex = $scope.artropodes.animais[ i ];
////
////			if( amostraAnimal.id ){
////				if( amostraAnimal.id == amostraIndex.id ){
////					$scope.artropodes.animais.splice(i, 1);
////					break;
////				}
////			}
////			else{
////				if( amostraAnimal.animal.tipo.id == amostraIndex.animal.tipo.id 
////						&& amostraAnimal.animal.id  == amostraAnimal.animal.id 
////						&& amostraAnimal.quantidade == amostraAnimal.quantidade ){
////					$scope.artropodes.animais.splice(i, 1);
////					break;
////				}
////			}
////		}
////	}
//	
//	$scope.getLocalidadesByMunicipio = function( municipio ){
//		api.localidade.getByMunicipio( municipio.id ).then( function( response ){
//			$scope.localidades = response.data;
//		});
//	}
//	
//	$scope.cadastraEndereco = function(){
//		var mensagem = "Informe o nome do novo endereço da localidade "+ $scope.artropodes.endereco.localidade.nome;
//		var tamanho = 255;
//		
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/template/cadastra.objeto.html", 
//			controller: "ModalCadastraObjetoController",
//			resolve: { 
//		    	mensagem: function () {
//		    		return mensagem;
//		        },
//				tamanho: function () {
//					return tamanho;
//        		}
//		      }
//		});
//		
//		modalInstance.result.then(function (nome){
//			var endereco = {};
//			endereco.localidade = $scope.artropodes.endereco.localidade;
//			endereco.descricao = nome;
//			
//			if( ! $scope.enderecos ){
//				$scope.enderecos = [];
//			}
//			
//			$scope.enderecos.push( endereco );
//			$scope.artropodes.endereco = endereco;
//		});
//	}
//	
//	$scope.addAnimal = function(){
//		var modalInstance = $uibModal.open({ 
//			templateUrl: "modulos/artropode/amostra.animal.cadastro.html", 
//			controller: "CadastroController",
//			
//			backdrop: 'static', 
//			keyboard: false,
//			
//			resolve: {
//				amostra: function(){
//					return null;
//				}
//			}
//		});
//		
//		modalInstance.result.then(function ( amostraAnimal ){
//			$scope.artropodes.animais.push( amostraAnimal );
//		});
//	}
//	
//	$scope.filtro = {};
//	$scope.gerarEtiqueta = function(amostraAnimal){
//		
//		if(amostraAnimal.id === undefined || $routeParams.id === undefined){
//			var modalInstance = $uibModal.open({ 
//				templateUrl: "modulos/template/dialog.mensagem.html", 
//				controller: "ModalMensagemDialogController",
//				
//				backdrop: 'static', 
//				keyboard: false,
//				
//				resolve: {
//					mensagem: function(){
//						return "Os dados de coleta e características do animal precisam ser salvos para possibilitar gerar etiqueta.";
//					},
//					titulo: function(){
//						return "Aviso";
//					}
//				}
//			});
//		} else {
//			$scope.filtro.type = "pdf";
//			$scope.filtro.id = $routeParams.id;
//			$scope.filtro.idAmostra = amostraAnimal.id;
//			
//			api.artropodes.fichaAmostra.geraEtiqueta($scope.filtro);
//		}
//		
//}
//	
//});