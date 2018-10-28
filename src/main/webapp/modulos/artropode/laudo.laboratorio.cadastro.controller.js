angular.module("vigilantos").controller('LaudoLaboratorioCadastroController', 
		function ($scope, api, $uibModal, $log, toastr, $routeParams, $location, utils, $timeout, $filter) {

	//Variaveis
	var idFicha = $routeParams.idFicha;
	var idLaudo = $routeParams.idLaudo;
	
	$scope.ficha = {};
	$scope.laudo = {};
	
	
	
	//Funcoes
	
	$scope.getPermissoes = function() {
		var possuiPermissao = false;
		
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];
			//possuiPermissao = false;
			
			if( role.modulo.nome == "artropodes" ){
				//possuiPermissao = true;
				
				if( role.descricao == "admin" || role.descricao == "regional" ){// adicionado regional para emitir laudo.
					$scope.isPermissaoAdmin = true;
					if(role.descricao == "admin") {
						possuiPermissao = true;						
					}
					break;
				}
			}
		}
		
		if( !possuiPermissao ) {
			$location.path("/error403");				
		}

		api.artropodes.fichaAmostra.getFicha( idFicha, $scope.usuarioOnline.id ).then( function( response ) {
			if(response.data.id == 0){
				$location.path("/error403");
				return;
			}
			
			$scope.ficha = response.data;
			
			if(idLaudo) {
				getLaudo(idLaudo, idFicha);
			}
		});
		
	}
	
	/**
	 * Atualiza Laudo ao atualizar tela
	 */
	getLaudo = function(idLaudo, idFicha) {
		api.artropodes.laudo.getById( idLaudo, idFicha, $scope.usuarioOnline.id ).then( function( response ) {
			$scope.laudo = response.data;

			if($scope.laudo.dtAlteracao !== undefined) {
				$scope.laudo.dtAlteracao = $scope.laudo.dtCadastro;
			}

			if($scope.laudo.dtRecebimento) {				
			    var data = new Date($scope.laudo.dtRecebimento);
			    data.setDate(data.getDate() + 2);
			    $scope.laudo.dtRecebimento = utils.formatUtils.dateToString(data);
			}

			if($scope.laudo.id > 0) {

				api.artropodes.laudo.getAnimaisTaxonomiaByLaudo(idLaudo).then( function( response ) {
					$scope.laudo.lsTaxonomia = response.data;
					$scope.lsTaxonomiaAux = $scope.laudo.lsTaxonomia; //Copia
					
					//Seta taxonomias para não excluir
					var size = $scope.laudo.lsTaxonomia.length;
					for(var i=0; i < size; i++) {
						$scope.laudo.lsTaxonomia[i].fgExcluir = false;
						$scope.lsTaxonomiaAux[i].fgExcluir = false;
					}
				});
				
				
			} else {
				//Carregamento para inserção
				$scope.laudo.lsTaxonomia = [];
				$scope.lsTaxonomiaAux = [];
			}
		});
	}
	
	/**
	 * Carrega dados iniciais da tela e realiza funcoes
	 */
	init = function() {
		//Focus ficha
		$timeout( function(){
			$("#aba2").parent().parent().click(function() {
				  $("[name='dtRecebimento']").focus();
				});
	    }, 1000 );

		
		api.artropodes.condicaoAmostra.getAll().then( function(response){
			$scope.condicoes = response.data;
		});
		
		api.artropodes.destinoAmostra.getAll().then( function(response){
			$scope.destinos = response.data;
		});
		
		api.usuario.getUserOnline().then( function(response){
			$scope.usuarioOnline = response.data;			
			$scope.roles = $scope.usuarioOnline.roles;
			
			$scope.getPermissoes();
		});

	};
	init();
	

	/**
	 * Lista Taxonomias por amostra
	 */	
	getListaTaxonomias = function(idAmostra) {
		var lsTaxonomias = [];
		
		var lsTaxonomiasAux = $scope.lsTaxonomiaAux;
		
		for (var i = 0; i < lsTaxonomiasAux.length; i++) {
			var taxonomia = lsTaxonomiasAux[i];
			if(idAmostra === taxonomia.amostraAnimal.id) {
				lsTaxonomias.push(taxonomia);				
			}
		}
		
		return lsTaxonomias;
	};
	
	
	
	
	
	
	
	
	
	
	//Carrega ficha
//	$scope.$broadcast('carregaFicha', undefined);
//	$scope.$on('carregaFicha', function(event, data) {
//	});
				  
	
	
	
	
	
	
	
	
	
	
	
	


	
//	fatualizaTelaLaudo = function(idFicha) {
//
//		
//
//		api.usuario.getUserOnline().then( function(response){
//			$scope.idUser = response.data.id;
//			
//			if(idAux != undefined) {
//				
//			
//
//			
//			
//			api.artropodes.laudo.getById( idAux, $scope.idUser ).then( function( response ){
//
//			if(response.data.id) {
//				id = response.data.id;
//				
//				if( id && id != "new" ){
//					$scope.laudo = {};
//					
//					api.artropodes.laudo.getById( id, $scope.usuarioOnline.id ).then( function( response ){
//						$scope.laudo = response.data;
//						$scope.laudo.dtCadastro = new Date($scope.laudo.dtCadastro);
//						if(!$scope.laudo.dtAlteracao){
//							$scope.laudo.dtAlteracao = $scope.laudo.dtCadastro;
//						}
//						
//						$scope.laudo.dtRecebimento = new Date($scope.laudo.dtRecebimento);
//						$scope.laudo.dtRecebimento.setDate($scope.laudo.dtRecebimento.getDate() + 1);
//						
//						if($scope.laudo === undefined
//								|| $scope.laudo === ''){
//							return;
//						}
//						
//						api.artropodes.fichaAmostra.getById($scope.laudo.ficha.id).then( function( response ){
//							$scope.ficha = response.data;
//							$scope.identificacao.numero = $scope.laudo.ficha.id;
//							
//							if( $scope.ficha.numNotificacaoSinan ){
//								$scope.ocorrenciaAcidente = true;
//							}
//						});
//						
//						api.artropodes.fichaAmostra.getAnimaisByFicha($scope.laudo.ficha.id).then( function( response ){				
//							$scope.ficha.animais = response.data; //Amostra de animais
//						});
//						
//						api.artropodes.laudo.getAnimaisTaxonomiaByLaudo($scope.laudo.id).then( function( response ){				
//							$scope.laudo.animaisTaxonomia = response.data;
//
//						});
//						
//					});
//				}
//			}
//		});// fim getById
//			
//	}
//	});
//};
	
//api.artropodes.laudo.getByFichaId( fichaId == undefined ? 0 : fichaId ).then( function( responseFicha ){
//	
//	if(responseFicha.data != ""){
//		$scope.laudo = responseFicha.data;
//	}
//
//	if(fichaId && responseFicha.data == ""){
//		fatualizaTelaLaudo(fichaId);
//	} else {
//		fatualizaTelaLaudo(fichaId);
//		if( id && id != "new" ){
//			$scope.laudo = {};
//			
//			api.usuario.getUserOnline().then( function(response){
//			api.artropodes.laudo.getById( id, response.data.id ).then( function( response ){
//				if(response.data.id == 0){
//					$location.path("/error403");
//					return;
//				}
//				$scope.laudo = response.data;
//				$scope.laudo.dtCadastro = new Date($scope.laudo.dtCadastro);
//				if(!$scope.laudo.dtAlteracao){
//					$scope.laudo.dtAlteracao = $scope.laudo.dtCadastro;
//				}
//				
//				if($scope.laudo.dtRecebimento !== undefined
//						&& $scope.laudo.dtRecebimento !== "") {					
//					$scope.laudo.dtRecebimento = new Date($scope.laudo.dtRecebimento);
//					$scope.laudo.dtRecebimento.setDate($scope.laudo.dtRecebimento.getDate() + 1);
//				}
//				
//				if($scope.laudo === undefined
//						|| $scope.laudo === ''){
//					return;
//				}
//				
//				api.artropodes.fichaAmostra.getById($scope.laudo.ficha.id).then( function( response ){
//					$scope.ficha = response.data;
//					$scope.identificacao.numero = $scope.laudo.ficha.id;
//				});
//				
//				api.artropodes.fichaAmostra.getAnimaisByFicha($scope.laudo.ficha.id).then( function( response ){				
//					$scope.ficha.animais = response.data; //Amostra de animais
//				});
//				
//				api.artropodes.laudo.getAnimaisTaxonomiaByLaudo($scope.laudo.id).then( function( response ){				
//					$scope.laudo.animaisTaxonomia = response.data;
//
//				});
//				
//			});
//			
//		   });
//			
//			
//			
//			
//		}
//	}
//	
//
//});
	
	validaAmostrasPendentes = function() {
		for (var i = 0; i < $scope.ficha.animais.length; i++) {
			if($scope.ficha.animais[i].taxonomiaRealizada === false) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * Atualiza Laudo ao inserir ou atualizar
	 */
	getLaudoInsertUpdate = function(idLaudo, idFicha) {
		api.artropodes.laudo.getById( idLaudo, idFicha, $scope.usuarioOnline.id ).then( function( response ) {
			$scope.laudo = response.data;	
				

			if($scope.laudo.dtAlteracao !== undefined) {
				$scope.laudo.dtAlteracao = $scope.laudo.dtCadastro;
			}
			
		    var data = new Date($scope.laudo.dtRecebimento);
		    data.setDate(data.getDate() + 2);
		    $scope.laudo.dtRecebimento = utils.formatUtils.dateToString(data);
			
			toastr.success("Laudo salvo com sucesso");
			
			var resultado = validaAmostrasPendentes();

			if (resultado){
				toastr.warning("Ainda existem taxonomias pendentes.");	
			}

			if($scope.laudo.id > 0) {

				api.artropodes.laudo.getAnimaisTaxonomiaByLaudo(idLaudo).then( function( response ) {
					$scope.laudo.lsTaxonomia = response.data;
					$scope.lsTaxonomiaAux = $scope.laudo.lsTaxonomia; //Copia 
					
					//Seta taxonomias para não excluir
					var size = $scope.laudo.lsTaxonomia.length;
					for(var i=0; i < size; i++) {
						$scope.laudo.lsTaxonomia[i].fgExcluir = false;
						$scope.lsTaxonomiaAux[i].fgExcluir = false;
					}
					
					
				});
				
			}
		});
	}
	
	
	$scope.emitirLaudo = function() {
//		$scope.laudo = laudo;
//		$scope.laudo.ficha = $scope.ficha;

		$scope.mensagem = "";
		
		if (!$scope.ficha.animais) {
			$scope.mensagem = "Necessário preencher ao menos 1 Taxonomia.";
		}else{
			if ($scope.ficha.animais.length == 0) {
				$scope.mensagem = "Necessário preencher ao menos 1 Taxonomia.";	
			}
		}
		
		if (!$scope.form.$valid) {
			$scope.mensagem = "Por favor preencha o formulário acima.";
		}
		
		
		
		if ($scope.laudo.dsObservacao) {
			if ($scope.laudo.dsObservacao.length > 3000) {
				$scope.mensagem = "Campo Observação está muito grande.";	
			}
		}
		
		var animalIndefinido = validaAmostraIndefinida();
		
		if(animalIndefinido) {
			$scope.mensagem = "Animal deve ser definido para realizar a emissão do laudo!";
		}

		if(new Date($scope.ficha.dtColeta) > new Date($scope.laudo.dtRecebimento)) {
			$scope.mensagem = "Data do Recebimento não pode ser menor que a Data da Coleta: " + $filter('date')(new Date($scope.ficha.dtColeta),'dd/MM/yyyy');
		}

		if($scope.mensagem != "") {
			toastr.error($scope.mensagem);
			return;
		}
		
		//Update
		if( $scope.laudo.id > 0 ) {
			var laudo = $scope.laudo;
			laudo.ficha = $scope.ficha;
			
			api.artropodes.laudo.update( laudo ).then( function(response) {
				if( response.status == 200 ) {
					
					getLaudoInsertUpdate($scope.laudo.id, $scope.ficha.id);
					
				} else {
					toastr.error("Erro ao atualizar laudo.");
				}
			});	
		} else {
			//Insert
			var laudo = $scope.laudo;
			laudo.ficha = $scope.ficha;
			api.artropodes.laudo.insert( laudo ).then( function( response ) {
				if( response.status == 200 ) {
					api.artropodes.laudo.getById(response.data, $scope.ficha.id, $scope.usuarioOnline.id).then(function (response ) {
						$scope.laudo = response.data;
						getLaudoInsertUpdate($scope.laudo.id, $scope.ficha.id);
					});
				} else {
					toastr.error("Erro ao atualizar laudo.");
				}
			});
		}
				
	}
	
	/**
	 * Valida se a amostra contém algum animal indefinido na lista ao tentar emitir o laudo
	 */
	validaAmostraIndefinida = function() {
		
		var lsAmostra = $scope.ficha.animais;
		for (var i = 0; i < lsAmostra.length; i++) {
			var indefinido = lsAmostra[i].tipoAnimal.fgIndefinido;
			
			if(indefinido === true) {
				return true;
			}
		}
		
		return false;
	};
	
	$scope.addTaxonomia = function(amostra) {
		
		var lsTaxonomias = getListaTaxonomias(amostra.id);
		
//		amostra.fichaAmostra = {};
//		amostra.fichaAmostra.id = $scope.ficha.id;
		
		//Lista adiciona valor de não excluidas a taxonomias cadastradas


		var modalInstance = $uibModal.open( {
			templateUrl: "modulos/artropode/classificacao.taxonomica.animal.cadastro.html", 
			controller: "ClassificacaoTaxonomicaCadastroController",
			
			backdrop: 'static', 
			keyboard: false,
			
			resolve: {
				amostra: function(){
					return amostra;
				},				
				ficha: function() {
					return $scope.ficha;
				},
				laudo: function() {
					return $scope.laudo;
				},
				lsTaxonomias: function() {
					return lsTaxonomias;
				}
			}
		});
		
		/**
		 * Retorna o número de animais de uma amostra
		 */		
		getNuAnimaisAmostra = function(idAmostra) {
			var lsAnimais = $scope.ficha.animais;
			
			var size = lsAnimais.length;
			
			var nuAnimais = 0;
			for (var i = 0; i < size; i++) {
				var amostra = lsAnimais[i];
				
				if(amostra.id === idAmostra) {
					nuAnimais+= amostra.quantidade;
					break;
				}
			}
			
			return nuAnimais;
		}
		
		/**
		 * Retorna o número de animais de uma lista de taxonomia
		 */		
		getNuAnimaisTaxonomia = function(lsTaxonomia) {
			
			var size = lsTaxonomia.length;
			
			var nuAnimais = 0;
			for (var i = 0; i < size; i++) {
					var taxonomia = lsTaxonomia[i];
				if(taxonomia.fgExcluir === false) {
					nuAnimais+= taxonomia.quantidade;					
				}
			}
			
			return nuAnimais;
		}
		
		
		setTaxonomiaRealizada = function(lsTaxonomia, amostraAux) {
			var nuAnimaisAmostra = getNuAnimaisAmostra(amostraAux.id);
			
			var nuAnimaisTaxonomia = getNuAnimaisTaxonomia(lsTaxonomia);
			
			if(nuAnimaisAmostra === nuAnimaisTaxonomia) {
				amostraAux.taxonomiaRealizada = true;
			} else {
				amostraAux.taxonomiaRealizada = false;
			}
			
		}
		
		atualizaTaxonomiasEmAmostra = function(lsTaxonomia, amostra) {
			if(angular.equals($scope.laudo, {}) ) {
				$scope.laudo.lsTaxonomia = [];
			}
			
			var size = $scope.laudo.lsTaxonomia.length;
			//Remove taxonomias
			for (var i = 0; i < size; i++) {
				var taxonomia = $scope.laudo.lsTaxonomia[i];
				if(taxonomia.amostraAnimal.id === amostra.id) {
					delete $scope.laudo.lsTaxonomia[i];
				}
			}
			$scope.laudo.lsTaxonomia.remove(null);
			
			//Adiciona Novos
			var size2 = lsTaxonomia.length;
			for (var i = 0; i < size2; i++) {
				$scope.laudo.lsTaxonomia.push(lsTaxonomia[i]); 
			}
			
		}
		
		/**
		 * Atualiza Amostra
		 */
		atualizaAmostra = function(amostra) {
			
			var size = $scope.ficha.animais.length;
			
			for (var i = 0; i < size; i++) {
				var amostraAux = $scope.ficha.animais[i];
				
				if(amostra.id === amostraAux.id) {
					$scope.ficha.animais[i] = amostra;
				}
			}
		}
		
        modalInstance.result.then(function (result) {
			var lsTaxonomia = result.listaTaxonomia;
			var amostra = result.amostra;
			amostra.taxonomiaRealizada = false;
			
			setTaxonomiaRealizada(lsTaxonomia, amostra);
			
			atualizaTaxonomiasEmAmostra(lsTaxonomia, amostra);
			
			atualizaAmostra(amostra);
          }, function (result) {
        	  var lsTaxonomia = result.listaTaxonomia;
  			  var amostra = result.amostra;
  			  
        	  setTaxonomiaRealizada(lsTaxonomia, amostra);
          });
	}
	
	$scope.mensagemAlerta = function( mensagem ) {
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/dialog.mensagem.html", 
			controller: "ModalMensagemDialogController",
			
			resolve: { 
				mensagem: function () {
		    		return mensagem;
		        },
		        titulo: function (){
		        	return "Aviso";
		        }
		      }
		});
	}

	
});