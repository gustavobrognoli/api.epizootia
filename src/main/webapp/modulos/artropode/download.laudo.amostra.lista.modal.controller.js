angular.module("vigilantos").controller('DownloadLaudoAmostraModalController', function ($scope, $location, api, utils, $routeParams, toastr, $uibModal, $uibModalInstance, amostra) {

	$scope.amostra = amostra;
	
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		//$scope.getPermissoes();
	});
	
    $scope.onClickDownload = function(arquivo){
		var id = arquivo.idArquivo;
		var nome = arquivo.nome;
		
		window.location =  "./rest/file-laudo/download/"+id+"/"+nome; 
		
	}	
    
	$scope.confirmarRemover = function(arquivo){
			
		var mensagem = "Confirma a exclusão do arquivo " + arquivo.nome + " ?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
				mensagem: function () {
					return mensagem;
				},
				id: function(){
					return arquivo.idArquivo;
				}
			}
		});
		
		modalInstance.result.then(function (id){
			$scope.remover( id, $scope.amostra.id );
		});
		
	}
	
//    $scope.onCliclExcluir = function(arquivo){
//    	api.artropodes.arquivoAmostra.excluir(arquivo.idArquivo).then(function(response){
//    		
//    	});
//
//    }
    
	$scope.remover = function(idArquivo, idAmostra){
		api.artropodes.arquivoLaudo.excluir(idArquivo, idAmostra).then(function(response){
			$scope.pesquisar();
			toastr.success('Arquivo removido com sucesso');
    	});
	}
	
	$scope.pesquisar = function(){
		
		api.artropodes.arquivoLaudo.getArquivos(amostra.id).then(function(response){
			$scope.listaArquivos = response.data;
		});
	}

	
	$scope.fechar = function(){
		$uibModalInstance.close();
	}
	
	$scope.pesquisar();
	
});