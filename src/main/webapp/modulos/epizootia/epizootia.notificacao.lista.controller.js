angular.module("vigilantos").controller("EpizootiaNotificacaoListaController", 
		function($scope, api, utils, $uibModal, toastr, $filter, $location){

	$scope.epizootiaFiltro = {};
    $scope.paginaAtual = 1;
    $scope.limite = 10;
    
    $scope.isDtColeta = false;
    
	$scope.isOpen = false;
	
	$scope.openMenu = function(){
		$scope.isOpen = true;
	}
	
	api.epizootia.ficha.getAll().then(function( response ) {
		$scope.fichas = response.data.data;
	});
	
	$scope.getDataInicio = function(){
		this.epizootiaFiltro.dtColetaInicio = utils.dateUtils.getInicioAno();
		this.epizootiaFiltro.dtColetaFim = utils.dateUtils.getInicioProxMes();
	}

	$scope.visualizaRegistro = function(id, size){
		
		api.epizootia.ficha.get( id ).then(function(response) {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/epizootia/epizootia.notificacao.visualizacao.html", 
				controller: "ModalVisualizacaoController",
				
				backdrop: 'static', 
				keyboard: false,
				size:size,
				resolve: { 
			    	ficha: function () {
			    		return response.data;
			        }
			      }
			});
		}, function(error) {
			
		}, function(value) {
			
		});
	}
	
	$scope.remover = function(id){
		api.epizootia.ficha.excluir(id).then(function(response){
		toastr.success('Registro removido com sucesso');
		});
	}
	
	$scope.removerFicha = function( ficha ){
		var mensagem = "Confirma exclusão deste Registro ?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        id: function(){
		        	return ficha.id;
		        }
		      }
		    });
		
		modalInstance.result.then(function (id){
			$scope.remover( id );
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
				titulo: function(){
					return null;
				}
		      }
		});
	}
	
	$scope.getDataInicio();
	
	
});