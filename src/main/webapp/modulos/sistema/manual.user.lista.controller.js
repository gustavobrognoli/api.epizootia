angular.module("vigilantos").controller("ManualUserListaController", 
		function($scope, $rootScope, api, utils, $uibModal, toastr, $filter, $location){
	
    $scope.paginaAtual = 1;
    $scope.limite = 10;
	
	api.usuario.getUserOnline().then( function(response) {
		$scope.usuarioOnline = response.data;
		$scope.roles = response.data.roles;
		
		$scope.getPermissoes();
	});
	
	$scope.getPermissoes = function(){
		$scope.pesquisar();		
	}
	
	
	$scope.pesquisar = function(){
//		$scope.mensagemFiltro.idUser = $scope.usuarioOnline.id;
//		
//		$scope.mensagemFiltro.limite = $scope.limite;
//		$scope.mensagemFiltro.pagina = $scope.paginaAtual;
		
		api.manualUsuario.getManuais().then( function(response){
			$scope.manuais = response.data;
//			$scope.total = response.headers("x-count");
		});	
	}
	
	$scope.visualizar = function( chave ){
		api.manualUsuario.getOpenManual( chave );
	}
	
	$scope.responder = function( id ){
		
		api.mensagem.updateLida( id ).then( function(response){
			$scope.pesquisar();
		});
		
		$location.path("/mensagens/"+ id );
	}
	
	$scope.remover = function(id){
		api.mensagem.excluir(id).success(function(data){
			$scope.pesquisar();
			toastr.success('Mensagem removido com sucesso');
		});
	}
	
	$scope.confirmaExclusao = function( selecionado ){
		var mensagem = "Confirma exclusão da mensagem com assunto "+ selecionado.assunto +" com data: "+ $filter('date')( selecionado.data , 'dd/MM/yyyy') +"?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        id: function(){
		        	return selecionado.id;
		        }
		      }
		    });
		
		modalInstance.result.then(function (id){
			$scope.remover( id );
		});
	}
	
});