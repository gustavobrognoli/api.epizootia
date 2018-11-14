angular.module("vigilantos").controller('ClassificacaoTaxonomicaCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, utils, $window, toastr, amostra, ficha, laudo, lsTaxonomias, $filter, $http){
	
	//Variaveis
	$scope.amostra = amostra;
	$scope.ficha = ficha;
	$scope.laudo = laudo;
	$scope.laudoExterno = amostra.laudoExterno;
	//$scope.taxonomia = {};
	$scope.listaTaxonomia = lsTaxonomias;
	
	$scope.mensagem = $scope.amostra.tipoAnimal.mensagem.mensagem;
	
	//var taxonomias = [];
	
	
	/**
	 * Carregamento Inicial da tela
	 */
	init = function() {
		api.artropodes.tipoArmadilha.getAll().then( function(response){
			$scope.tiposArmadilhas = response.data;
		});
		
		api.artropodes.tipoColeta.getAll().then( function(response){
			$scope.tiposColeta = response.data;
		});
		
		api.artropodes.ambienteColeta.getAll().then( function(response){
			$scope.ambientesColeta = response.data;
		});
		
		api.artropodes.animais.getTipos().then( function(response){
			$scope.tiposAnimais = response.data;
		});
	}
	init();
	
	
	//Funções
	$scope.cancelar = function() {
		var totalTaxonomias = ftotalTaxonomias();			
		
		if($scope.amostra.quantidade == totalTaxonomias) {
			$uibModalInstance.close( { 'listaTaxonomia': $scope.listaTaxonomia, 'amostra': $scope.amostra } );
		} else {
			var msg = "Quantidade de itens classificados não conferem com a quantidade da amostra."
				$scope.mensagemAlerta(msg);
		}
	}

	$scope.salvar = function() {
		if( $scope.form.$valid ) {
			
			
			var totalTaxonomias = ftotalTaxonomias();			
			
			if($scope.amostra.quantidade == totalTaxonomias) {
				$uibModalInstance.close( { 'listaTaxonomia': $scope.listaTaxonomia, 'amostra': $scope.amostra } );
			} else {
				var msg = "Quantidade de itens classificados não conferem com a quantidade da amostra."
					$scope.mensagemAlerta(msg);
			}
		}
	}
	
	function ftotalTaxonomias() {
		var totalTaxonomias = 0;
		
		for(var i=0; i<$scope.listaTaxonomia.length; i++){
			if($scope.listaTaxonomia[i].fgExcluir === false) {
				totalTaxonomias += $scope.listaTaxonomia[i].quantidade;		
			}
		}
		return totalTaxonomias;
	};
	
	$scope.addTaxonomia = function( amostra ) {
		var modalInstance = $uibModal.open({
			templateUrl: "modulos/artropode/taxonomia.animal.cadastro.modal.html", 
			controller: "TaxonomiaCadastroController",
			
			resolve: {
				amostra: function(){
					return $scope.amostra;
				}
			}
		});
		
		modalInstance.result.then(function ( taxonomiaAnimal ) {//alterar ainda
			if(taxonomiaAnimal !== undefined) {
				taxonomiaAnimal.fgExcluir = false;
				$scope.listaTaxonomia.push(taxonomiaAnimal);
			}
//			var totalTaxonomias = 0;
//			
//			for(i=0; i<$scope.listaTaxonomia.length; i++){
//				totalTaxonomias += $scope.listaTaxonomia[i].quantidade;
//			}
//			console.info("KKK "+$scope.botaoDesabilitado);
//			
//			if($scope.taxonomia.amostraAnimal.quantidade == totalTaxonomias){
//				$scope.botaoDesabilitado = false;
//			} else {
//				var msg = "Quantidade de itens classificados não conferem com a quantidade da amostra."
//					$scope.mensagemAlerta(msg);
//			}
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
	
	
	$scope.onClickUpload = function(amostra, laudoExterno) {
		
		var modalInstance = $uibModal.open({
			templateUrl: "modulos/artropode/upload.laudo.modal.html", 
			controller: "UploadLaudoModalController",
			resolve: { 
				amostra: function () {
		    		return amostra;
		        }
			}
		}).result.then(function(result){
			
			if(!result.length) {
				return;
			}
			
			$scope.flaudoExterno = false;
			$scope.fuploadError = false;
			result.forEach(function(status) {
				if(status == 200)
					$scope.flaudoExterno = true;
				if(status == 500) 
					$scope.fuploadError = true;				
				
			});
			
			if($scope.flaudoExterno
					&& $scope.fuploadError) {
				$scope.mensagemAlerta("Ao menos um dos arquivos não foi salvo.");
			} 
			
			if(result.length > 0 
					&& $scope.fuploadError) {
				$scope.mensagemAlerta("Erro ao salvar arquivo(s).");
//				$scope.taxonomia.amostraAnimal.laudoExterno = laudoExterno;
//				if($scope.amostraAnimal)
//					$scope.amostraAnimal.laudoExterno = laudoExterno
//				$scope.laudoExterno = laudoExterno;
			}
			
//			if(result.length == 0) {
//				if($scope.amostra)
//					$scope.amostra.laudoExterno = laudoExterno;
//			}
			
			
			$scope.amostra.laudoExterno = true;
			api.artropodes.amostra.updateLaudoExterno($scope.amostra).then( function( response ) {});
			
		});
		
	}
	
	$scope.visualizarTaxonomia = function(taxonomia) {


		if(taxonomia.id) { //Taxonomia banco
				api.artropodes.laudo.getTaxonomiaByFichaId(taxonomia.id).then( function( response ){
					var taxonomia = response.data;
					var modalInstance = $uibModal.open({ 
						templateUrl: "modulos/artropode/taxonomia.animal.visualizacao.modal.html", 
						controller: "TaxonomiaVisualizacaoController",
						
						resolve: {
							taxonomia: function(){
								return taxonomia;
							}
						}
					});
				});
		} else { //Taxonomia tela
			var modalInstance = $uibModal.open({
				templateUrl: "modulos/artropode/taxonomia.animal.visualizacao.modal.html", 
				controller: "TaxonomiaVisualizacaoController",
				
				resolve: {
					taxonomia: function(){
						return taxonomia;
					}
				}
			});
		}
	}
	
	$scope.excluirTaxonomia = function(taxonomiaId) {
		
		
		var mensagem = "Confirma a exclusão da Taxonomia ?";
		
		var modalInstance = $uibModal.open({
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
				mensagem: function () {
					return mensagem;
				},
				id: function(){
					return taxonomiaId;
				}
			}
		});
	
		modalInstance.result.then(function (){
			var size = $scope.listaTaxonomia.length;
			for (var i = 0; i < size; i++) {
				var id = $scope.listaTaxonomia[i].id;
				if(id === taxonomiaId && taxonomiaId !== undefined) {
					//$scope.listaTaxonomia.splice(i,1);		
					$scope.listaTaxonomia[i].fgExcluir = true;
					toastr.success("Taxonomia removida com sucesso");
					return;
				}
				
//				if(taxonomiaId === undefined) {
//					if(i === index) {//Caso a taxonomia não tenha sido salva
//						$scope.listaTaxonomia[i].fgExcluir = true;
//						toastr.success("Taxonomia removida com sucesso");
//						return;						
//					}
//				}
				
				
			}
			
			toastr.error("Falha ao remover Taxonomia");			
			
		});
		
	};
	
	$scope.filtroExcluidos = function(item) {
		return item.fgExcluir === false;
	};
	
	$scope.gerarRelatorioLaudoTaxonomia = function(taxonomia) {
		
		var relatorioLaudoTaxonomia = {
				taxonomia
		};
		
//		var generoId;
//		var especieId;
//		
//		if (!utils.inputUtils.isVazioNullUndefined(relatorioLaudoTaxonomia.taxonomia.genero) && !utils.inputUtils.isVazioNullUndefined(relatorioLaudoTaxonomia.taxonomia.genero.id)) {
//			generoId = taxonomia.genero.id;
//		}		
//		if (!utils.inputUtils.isVazioNullUndefined(relatorioLaudoTaxonomia.taxonomia.especie) && !utils.inputUtils.isVazioNullUndefined(relatorioLaudoTaxonomia.taxonomia.especie.id)) {
//			especieId = taxonomia.especie.id;
//		}
//		
//		if (generoId || especieId) {
			//buscar nome do jasper vinculado a genero/especie caso exista - relatorio específico
			api.artropodes.laudo.getRelatorioByGeneroEspecie(relatorioLaudoTaxonomia).then(function(response){
				var nmGenero = "";
				if(!utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.genero) 
						&& !utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.genero.nome)) {
					nmGenero = response.data.taxonomia.genero.nome;
				}
				var nmEspecie = "";
				if(!utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.especie) 
						&& !utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.especie.nome)) {
					nmEspecie = response.data.taxonomia.especie.nome;
				}
				var nmFamilia = "";
				if(!utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.familia) 
						&& !utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.familia.nome)) {
					nmFamilia = response.data.taxonomia.familia.nome;
				}
				var nmTipoAnimal = "";
				if(!utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.amostraAnimal.tipoAnimal) 
						&& !utils.inputUtils.isVazioNullUndefined(response.data.taxonomia.amostraAnimal.tipoAnimal.nome)) {
					nmTipoAnimal = response.data.taxonomia.amostraAnimal.tipoAnimal.nome;
				}
				
				$scope.relatorioEspecificoLaudoTaxonomia = {
					ficha: $scope.ficha,
					laudo: $scope.laudo,
					nomeRelatorioJasper: response.data.nomeRelatorioJasper,
					caracteristicasGerais: response.data.caracteristicasGerais,
					dtIdentificacao: response.data.dtIdentificacao,					
					fichaAnimaisQuantidade: response.data.taxonomia.quantidade,
					animalNome: nmTipoAnimal,
					interesseMedico: response.data.interesseMedico,
					interesseMedicoParte1: response.data.interesseMedicoParte1,
					interesseMedicoParte2: response.data.interesseMedicoParte2,
					observacoes: response.data.observacoes,
					genero: nmGenero,
					especie: nmEspecie,
					familia: nmFamilia,
					fichaId: $scope.ficha.id,
					taxonomiaAmostraAnimalTipoAnimalNome: nmTipoAnimal,
					emailResponsavelColeta: $scope.ficha.coletador.email,
					responsavelColeta: $scope.ficha.coletador.nome,
					telefoneResponsavelColeta: $scope.ficha.coletador.telefone,
					municipioRequisitante: $scope.ficha.endereco.localidade.municipio.nome,
					idResponsavelEnvio: $scope.ficha.usuario.id,
					dtEmissao: $filter('date')($scope.laudo.dtAlteracao, 'dd/MM/yyyy'),
					observacaoLaudo: $scope.laudo.dsObservacao,
					idResponsavelIdentificacao: $scope.laudo.cdUsuCadastro
				};
				
				if (!utils.inputUtils.isVazioNullUndefined($scope.relatorioEspecificoLaudoTaxonomia.nomeRelatorioJasper) && (($scope.relatorioEspecificoLaudoTaxonomia.nomeRelatorioJasper.toUpperCase() == 'TITYUS_COSTATUS') || 
						($scope.relatorioEspecificoLaudoTaxonomia.nomeRelatorioJasper.toUpperCase().indexOf('TITYUS_SERRULATUS')) >= 0 ||
						($scope.relatorioEspecificoLaudoTaxonomia.nomeRelatorioJasper.toUpperCase().indexOf('LOXOSCELES')) >= 0 ||
						($scope.relatorioEspecificoLaudoTaxonomia.nomeRelatorioJasper.toUpperCase().indexOf('LONOMIA')) >= 0)) {
			
					var modalInstance = $uibModal.open({ 
						templateUrl: "modulos/artropode/laudo.laboratorio.relatorio.especifico.modal.html", 
						controller: "LaudoRelatorioEspecificoController",
						
						backdrop: 'static', 
						keyboard: false,
						
						resolve: {
							relatorioEspecificoLaudoTaxonomia: function(){
								return $scope.relatorioEspecificoLaudoTaxonomia;
							},
							ficha: function(){
								return $scope.ficha;
							},
							laudo: function(){
								return $scope.laudo;
							}
						}
					});
					 
				} else {
					//EXPORTAÇÃO GERAL
					//NÃO ABRE MODAL E GERA DIRETO O RELATORIO					
					var relatorioLaudoTaxonomiaDTO = $scope.relatorioEspecificoLaudoTaxonomia;					
					$http.post('./rest/artropodes/laudo/relatorio_laudo_taxonomia.pdf',{relatorioLaudoTaxonomiaDTO}, {responseType: 'blob'})
	         		   .then(function (response) {
	         			//Solução para formatar data com $.datepicker.formatDate.
            			   var formatedFileName = relatorioLaudoTaxonomiaDTO.nomeRelatorioJasper != null ? 'relatorio_taxonomia_' + relatorioLaudoTaxonomiaDTO.nomeRelatorioJasper + '_'+ $.datepicker.formatDate('dd/mm/yy', new Date()) : 'relatorio_taxonomia_geral_' + $.datepicker.formatDate('dd/mm/yy', new Date());            			   
            			   var a = document.createElement("a");
            			   document.body.appendChild(a);
            			   var file = new Blob([response.data], {type: 'application/pdf'});
                           var fileURL = window.URL.createObjectURL(file);
                           a.href = fileURL;
                           a.download = formatedFileName;
                           a.click();
                           
                           //Modo para abrir o arquivo PDF em outra aba no Chrome
	        			   //var fileURL = URL.createObjectURL(response.data);
	        			   //window.open(fileURL);
	         		});
				}
			});
		//}
		
		//$scope.relatorioEspecificoLaudoTaxonomia;
	};	

	$scope.openFile = function(amostraAnimal) {
		
		api.artropodes.arquivoLaudo.getArquivos(amostraAnimal.id).then(function(response){
			$scope.amostraAnimal = {};
			$scope.listaArquivos = response.data;
			if($scope.listaArquivos.length > 0){
				
				var modalInstance = $uibModal.open({ 
					templateUrl: "modulos/artropode/download.laudo.amostra.lista.modal.html", 
					controller: "DownloadLaudoAmostraModalController",
					resolve: { 
						amostra: function () {
							return amostraAnimal;
						},
						listaArquivos: function() {
							return response.data;
						} 
					}
				});
			}else{
				$scope.mensagem();
			}
			
		});
	}
	
});