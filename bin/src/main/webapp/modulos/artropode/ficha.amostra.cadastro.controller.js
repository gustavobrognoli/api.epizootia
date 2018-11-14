angular.module("vigilantos").controller('FichaAmostraCadastroController', 
		function ($scope, api, $uibModal, toastr, $routeParams, $location) {

	var id = $routeParams.id;
	$scope.idFicha = $routeParams.id;
	
	angular.element('#idColetador').focus();
	
	$scope.getMunicipiosPorPerfil = function() {
		
		for (var i = 0; i < $scope.roles.length; i++){
			var role = $scope.roles[i];
			
			if( role.modulo.nome == "artropodes" ){				
				if( role.descricao == "admin" ){
					api.municipio.getByUF( 'SC' ).then(function( response ) {
						$scope.municipios = response.data;
					});
				}
				
				if( role.descricao == "regional" ){
					api.municipio.getByUserId( $scope.usuarioOnline.id ).then(function( response ) {
						$scope.municipios = response.data;
					});
					
				}
				
				if( role.descricao == "municipio" ){
					$scope.municipio = $scope.usuarioOnline.municipio;
					$scope.municipios = [];
					$scope.municipios.push($scope.usuarioOnline.municipio);
					
					$scope.getByMunicipio($scope.usuarioOnline.municipio);
				}
			}
		}
	};

	$scope.getPermissoes = function(){
		var possuiPermissao = false;
		
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];
			possuiPermissao = false;
			
			if( role.modulo.nome == "artropodes" ){
				possuiPermissao = true;
				
				if( role.descricao == "admin" ){
					$scope.isPermissaoAdmin = true;
					break;
				}else if( role.descricao == "municipio" ){
					$scope.isPermissaoMunicipio = true;
					break;
				}else if( role.descricao == "regional" ){
					$scope.isPermissaoRegional = true;
					break;
				}
				else{
					possuiPermissao = false;
				}
			}
		}
		
		if( !possuiPermissao ){
			$location.path("/error403");				
		}
	}
	
	api.usuario.getUserOnline().then( function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		$scope.getPermissoes();
		$scope.getMunicipiosPorPerfil();
	
	if( id && id != "new" ){
		api.artropodes.fichaAmostra.getFicha( id, $scope.usuarioOnline.id).then( function( response ){
			$scope.artropodes = response.data;
			
			if($scope.artropodes.id == 0){
				$location.path("/error403");
				return;
			}
						
			$scope.municipio = $scope.artropodes.endereco.localidade.municipio;
			
			api.artropodes.fichaAmostra.getAnimaisByFicha( id ).then( function( response ){
				$scope.artropodes.animais = response.data; Amostras
			});
			
			$scope.getLocalidadesByMunicipio( $scope.municipio );
			$scope.getByLocalidade();
			
			$scope.getEnderecosByLocalidade( $scope.artropodes.endereco.localidade.id );
		});
	} else{
		$scope.artropodes = {};
		$scope.artropodes.animais = [];
	}
	
	});
	
	api.artropodes.coletadores.getAll().then(function( response ) {
		$scope.coletadores = response.data;
	});
	

	
	
	// 1 -Metodo antigo para busca de endereços, mantido para averiguação futura se necessário
	$scope.getEnderecosByLocalidade = function( idLocalidade ){
		api.artropodes.fichaAmostra.getEnderecosByLocalidade( idLocalidade ).then( function( response ){
			$scope.enderecos = response.data;
		});
	}
	 1 - Fim
	
	 //Alteração Julio
	$scope.getByLocalidade = function(){

		if( $scope.artropodes.endereco.localidade != null && $scope.artropodes.endereco.localidade.id > 0 ){
			api.dengue.getEnderecos( $scope.artropodes.endereco.localidade ).then(function(response){		
		debugger;		
				$scope.enderecos = response.data;
			});
			
			
			api.artropodes.getEnderecos( $scope.artropodes.endereco.localidade ).then(function(response){		
						$scope.enderecos = response.data;
					});
		}
		else{
			$scope.enderecos = [];
		}
    }
	 //Fim Alteração Julio
	
	$scope.salvarFicha = function( key ){
		if( !$scope.form.$valid && ( key == 'tipo_animal' || key == 'finalizar' )){
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Preencha os campos da etapa 'Dados da Coleta' para salvar esta etapa.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		} else if($scope.form.$valid && $scope.artropodes.animais.length == 0){
			var msg;
			key == 'tipo_animal' ? msg = "Inclua os animais da coleta para salvar." : msg = "Inclua os animais da coleta em 'Caracteristicas do Animal' para salvar."; 
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
		else{
			if($scope.form.$valid){
				
				if($scope.artropodes.latitude !== undefined
						&& (parseFloat($scope.artropodes.latitude) > 0  
							   || ((parseFloat($scope.artropodes.latitude) > parseFloat(-25.844319)) && !(parseFloat($scope.artropodes.latitude) <  parseFloat(-29.374493)))
							   || (!(parseFloat($scope.artropodes.latitude) > parseFloat(-25.844319)) && (parseFloat($scope.artropodes.latitude) <  parseFloat(-29.374493)))
						    )
				   ) {
					$scope.mensagemLatitudeLimites();
					return;
				}
				
				if($scope.artropodes.longitude !== undefined						
						&& (parseFloat($scope.artropodes.longitude) > 0 
								|| ((parseFloat($scope.artropodes.longitude) > parseFloat(-48.276716))  && !(parseFloat($scope.artropodes.longitude) <  parseFloat(-53.952621)))
								|| (!(parseFloat($scope.artropodes.longitude) > parseFloat(-48.276716))  && (parseFloat($scope.artropodes.longitude) <  parseFloat(-53.952621)))
							) 
					) {
					$scope.mensagemLongitudeLimites();
					return;
				}
				
				
				$scope.artropodes.usuario = $scope.usuarioOnline;
				
				if( !$scope.artropodes.latitude )
					$scope.artropodes.latitude = 0;
				if( !$scope.artropodes.longitude )
					$scope.artropodes.longitude = 0;
				
				if( $scope.artropodes.id > 0 ){
					for( i=0; i < $scope.artropodes.animais.length; i++){
						$scope.artropodes.animais[i].fichaAmostra = null;
					}
					
					api.artropodes.fichaAmostra.update( $scope.artropodes ).then( function(response){
						api.artropodes.fichaAmostra.getById(response.data).then(function (response ) {
							$scope.artropodes = response.data;
							$routeParams.id = $scope.artropodes.id
						});
						
						toastr.success("Ficha atualizada com sucesso")
						if(key == 'finalizar') {
							$location.path("/artropodes/fichas")
						}
					});	
				}
				else{
					api.artropodes.fichaAmostra.insert( $scope.artropodes ).then( function(response){
						$scope.artropodes.id = response.data;
						toastr.success("Ficha salva com sucesso");
						api.artropodes.fichaAmostra.getById($scope.artropodes.id).then(function (response ) {
							$scope.artropodes = response.data;
							$routeParams.id = $scope.artropodes.id
						});
						$location.path("/artropodes/fichas/"+$scope.artropodes.id);
					});					
				}
			}else{
				if($scope.artropodes.coletador == undefined){
					toastr.error("Coletador deve ser preenchido.")
				}
			}
		}
	}
	
	$scope.mensagemLatitudeLimites = function (){
		
		var mensagem = "Valores para latidude devem estár dentro deste intervalo (-25.844319, -29.374493 )";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/dialog.mensagem.html", 
			controller: "ModalMensagemDialogController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        titulo: function () {
		    		return null;
		        }
		      }
		});
	}
	
	$scope.mensagemLongitudeLimites = function (){
		
		var mensagem = "Valores para longitude devem estár dentro deste intervalo ( -48.276716 = -53.952621 )";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/dialog.mensagem.html", 
			controller: "ModalMensagemDialogController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        titulo: function () {
		    		return null;
		        }
		      }
		});
	}

	
	$scope.checkAcidente = function(){
		var checkAcidente = $('#ch-aciente').val();
		if(checkAcidente === undefined) {
			$scope.ocorrenciaAcidente = false;
		}else {
			$scope.ocorrenciaAcidente = true;
		}
	};
	
	$scope.updateAnimal = function( amostraAnimal ){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/artropode/amostra.animal.cadastro.html", 
			controller: "CadastroController",
			
			backdrop: 'static', 
			keyboard: false,
			
			resolve: {
				amostra: function(){
					return amostraAnimal;
				}
			}
		});
		
		modalInstance.result.then(function ( edit ){
			$scope.artropodes.animais.push( amostraAnimal );
		});
		
	}
	
	$scope.onClickUpload = function(amostraAnimal){
			
		
		if(amostraAnimal.id) {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/artropode/upload.amostra.modal.html", 
				controller: "UploadAmostraModalController",
				resolve: { 
					amostra: function () {
						return amostraAnimal;
					}
				}
			});
		} else {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Para anexar uma imagem é necessário salvar o cadastro.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		}
	}
	$scope.filtro = {};
	$scope.gerarEtiqueta = function(amostraAnimal){
		
			if(amostraAnimal.id > 0){
				$scope.filtro.type = "pdf";
				$scope.filtro.id = $routeParams.id;
				$scope.filtro.idAmostra = amostraAnimal.id;
				
				$scope.filtro.id = (typeof $scope.filtro.id == 'undefined' ? -1 : ($scope.filtro.id == null ? -1 : $scope.filtro.id));	
				$scope.filtro.idAmostra = (typeof $scope.filtro.idAmostra == 'undefined' ? -1 : ($scope.filtro.idAmostra == null ? -1 : $scope.filtro.idAmostra));
			}
			
			if(amostraAnimal.id === undefined || $routeParams.id === undefined){
				var modalInstance = $uibModal.open({ 
					templateUrl: "modulos/template/dialog.mensagem.html", 
					controller: "ModalMensagemDialogController",
					
					backdrop: 'static', 
					keyboard: false,
					
					resolve: {
						mensagem: function(){
							return "Os dados de coleta e características do animal precisam ser salvos para possibilitar gerar etiqueta.";
						},
						titulo: function(){
							return "Aviso";
						}
					}
				});
			} else {
				$scope.filtro.type = "pdf";
				$scope.filtro.id = $routeParams.id;
				$scope.filtro.idAmostra = amostraAnimal.id;
				
				api.artropodes.fichaAmostra.geraEtiqueta($scope.filtro);
			}
			
	}
	
	$scope.gerarTodasEtiquetas = function(){
		if($routeParams.id === undefined || $routeParams.id == "new"){
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Os dados de coleta e características do animal precisam ser salvos para possibilitar gerar etiqueta.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		} else {
			$scope.filtro.id = $routeParams.id;
			$scope.filtro.idAmostra = -1;
			$scope.filtro.id = (typeof $scope.filtro.id == 'undefined' ? -1 : ($scope.filtro.id == null ? -1 : $scope.filtro.id));	
			$scope.filtro.idAmostra = (typeof $scope.filtro.idAmostra == 'undefined' ? -1 : ($scope.filtro.idAmostra == null ? -1 : $scope.filtro.idAmostra));
			
			$scope.filtro.type = "pdf";
			
			api.artropodes.fichaAmostra.geraEtiqueta($scope.filtro);
		} 
	}
	
	$scope.onClickDownload = function(amostraAnimal){
		if(amostraAnimal.id) {
			$scope.amostraAnimal = {};
			api.artropodes.arquivoAmostra.getArquivos(amostraAnimal.id).then(function(response){
				$scope.lista = response.data;
				if($scope.lista.length > 0){
					
					var modalInstance = $uibModal.open({ 
						templateUrl: "modulos/artropode/download.amostra.lista.modal.html", 
						controller: "DownloadAmostraModalController",
						resolve: { 
							amostra: function () {
								return amostraAnimal;
							}
						}
					});
				}else{
					$scope.mensagem();
				}
			});
		} else {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Para realizar download de uma imagem é necessário salvar o cadastro da ficha.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		}
	}
		
	$scope.mensagem = function (){
		
		var mensagem = "Esta amostra não possui anexos.";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/dialog.mensagem.html", 
			controller: "ModalMensagemDialogController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        titulo: function () {
		    		return null;
		        }
		      }
		});
	}
	
	$scope.removerAnimal = function(amostraAnimal){
		
		var mensagem = "Confirma a exclusão do(a) " + amostraAnimal.tipoAnimal.nome + "?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
				mensagem: function () {
					return mensagem;
				},
				id: function(){
					return amostraAnimal.id;
				}
			}
		});
	
		modalInstance.result.then(function (id){
			for( i=0; i < $scope.artropodes.animais.length; i++ ){
				var amostraIndex = $scope.artropodes.animais[ i ];

				if( amostraAnimal.id ){
					if( amostraAnimal.id == amostraIndex.id ){
						$scope.artropodes.animais.splice(i, 1);
						break;
					}
				}
				else{
					if( amostraAnimal.tipoAnimal.id == amostraIndex.tipoAnimal.id 
							&& amostraAnimal.quantidade == amostraAnimal.quantidade ){
						$scope.artropodes.animais.splice(i, 1);
						break;
					}
				}
			}
			
		});
	}
	
	
	$scope.getByMunicipio = function(municipio){

        if( municipio != null ){
        	api.dengue.getLocalidades( municipio ).then(function(response){        		
        		$scope.localidades = response.data;
        	});
/*        	
        	alterado por Julio 8-12-2017*/
        	api.artropodes.getLocalidades( municipio ).then(function(response){        		
        		$scope.localidades = response.data;
        	});
        	
        }
        else{
        	$scope.localidades = [];
        }
    }
	
	$scope.getLocalidadesByMunicipio = function( municipio ){
		api.localidade.getByMunicipio( municipio.id ).then( function( response ){
			$scope.localidades = response.data;
		});
	}
	
	$scope.cadastraEndereco = function(){
		var mensagem = "Informe o nome do novo endereço da localidade "+ $scope.artropodes.endereco.localidade.nome;
		var tamanho = 255;
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/cadastra.objeto.html", 
			controller: "ModalCadastraObjetoController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
				tamanho: function () {
					return tamanho;
        		}
		      }
		});
		
		modalInstance.result.then(function (nome){
			var endereco = {};
			endereco.localidade = $scope.artropodes.endereco.localidade;
			endereco.nome = nome;
			
			if( ! $scope.enderecos ){
				$scope.enderecos = [];
			}
			$scope.enderecos.push( endereco );
			$scope.artropodes.endereco = endereco;
		});
	}
	
	$scope.addAnimal = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/artropode/amostra.animal.cadastro.html", 
			controller: "CadastroController",
			
			backdrop: 'static', 
			keyboard: false,
			
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( amostraAnimal ){
			if(amostraAnimal != null) {
				$scope.artropodes.animais.push( amostraAnimal );
			}
		});
	}
	
	$scope.contemAnimais = function(){
		return $scope.artropodes === undefined ? false : $scope.artropodes.animais.length > 0;
	}
	
	
	
	$scope.cadastrarColetador = function(){
		$scope.coletador = {};
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/artropode/coletador.cadastro.html", 
			controller: "ColetadorCadastroController",
			
			backdrop: 'static', 
			keyboard: false,
			
			resolve: {
				amostra: function(){
					return null;
				}, coletador: $scope.coletador
			}
		});
		
		modalInstance.result.then(function ( coletador ){
			if(coletador != null) {
				$scope.coletadores.push(coletador);
			}
		});
	}
	
	$scope.editarColetador = function(coletador){
		$scope.coletador = coletador;
		if(!$scope.coletador || !$scope.coletador.id || $scope.coletador.id == "") {
			var mensagem = "Selecione um coletador para edição.";
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				resolve: { 
			    	mensagem: function () {
			    		return mensagem;
			        },
			        titulo: function () {
			    		return null;
			        }
			      }
			});
		} else {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/artropode/coletador.cadastro.html", 
				controller: "ColetadorCadastroController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					amostra: function(){
						return null;
					}, coletador: coletador
				}
			});
		}
	}

});