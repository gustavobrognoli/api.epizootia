angular.module("vigilantos").controller('ClassificacaoTaxonomicaVisualizacaoController', 
		function ($scope, api, $uibModal, $uibModalInstance, amostra){
	$scope.amostra = amostra;
	$scope.laudoExterno = amostra.laudoExterno;
	$scope.taxonomia = {};
	$scope.listaTaxonomia = [];
	var taxonomias = [];
	
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
	
	
	if( amostra && amostra != null ){
		$scope.taxonomia.amostraAnimal = amostra;
		
		$scope.mensagem = $scope.taxonomia.amostraAnimal.tipoAnimal.mensagem.mensagem;
		
		api.artropodes.laudo.getAnimaisTaxonomiaByAmostra(amostra.id).then( function( response ){
			$scope.listaTaxonomia = response.data;
		});
		
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close();	
	}
			
	$scope.visualizarTaxonomia = function(taxonomiaId) {
		api.artropodes.laudo.getTaxonomiaByFichaId(taxonomiaId).then( function( response ){
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
	}
	
	$scope.mensagemAlerta = function( mensagem ){
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
	
	$scope.openFile = function(amostraAnimal) {
		
//		window.location =  "./rest/file-laudo/download/"+amostraAnimal.id; 
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
			
		});;
	}
	
});