angular.module("vigilantos").controller("DengueCasosListaController", 
	function($scope, api, utils, toastr, $uibModal, $filter, $location) {

	$scope.isOpen = false;
    
	$scope.paginaAtual = 1;
    $scope.limite = 10;
	
    $scope.isPermissaoAdmin = false;
    $scope.isPermissaoRegional = false;
    $scope.isPermissaoMunicipio = false;
    
    $scope.isMunicipio = false;
    $scope.isLocalidade= false;
    $scope.isDtColeta = false;
    
    $scope.regional = {};
    $scope.dengueFiltro = {};
    
    $scope.opcao = 'importadosSINAN';
    
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		$scope.getPermissoes();
	});
    
    $scope.getPermissoes = function(){
    	var possuiPermissao = false;
		
    	for(i=0; i < $scope.roles.length; i++ ){
			var role = $scope.roles[i];
	
			if( role.modulo.nome == "dengue" ){
				possuiPermissao = true;
				
				if( role.descricao == "admin" ){
					$scope.isPermissaoAdmin = true;
					
					api.regional.getAll().then(function(response){
						$scope.regionais = response.data;
					});
					
					$scope.getMunicipipoSC();
					
					$scope.pesquisar();
					break;
				}
				else if( role.descricao == "regional" ){
					$scope.isPermissaoRegional = true;
					
				}
				else if( role.descricao == "municipio_caso" ){
					$scope.isPermissaoMunicipio = true;
				    
					api.regional.getByMunicipio( $scope.usuarioOnline.municipio.id ).then(function(response){
						$scope.regionais = [response.data];
						$scope.regional = response.data;
						$scope.municipios = response.data.municipios;
					});
					
					$scope.municipio = $scope.usuarioOnline.municipio;
					$scope.pesquisar();
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
    
	$scope.openMenu = function(){
		$scope.isOpen = true;
	}

	$scope.init = function(){
		$scope.dengueFiltro.dtSintomasInicio = utils.dateUtils.getInicioAno();
		$scope.dengueFiltro.dtSintomasFim = new Date();
	}
	
	$scope.getMunicipipoSC = function(){
		api.municipio.getByUF('SC').then(function(response){
			$scope.municipios = response.data;
		});
	}
	
	$scope.getResultados = function(){
		api.resultado.getAll().then(function(response){
			$scope.resultados = response.data;
		});
	}
	
	$scope.getClassificacoes = function(){
		api.dengueCaso.getClassificacoes().then(function(response){
			$scope.classificacoes = response.data;
		});
	}
	
	$scope.trocaOpcao = function(){
		$scope.casos = [];
	}
	
	$scope.pesquisar = function(){
		$scope.dengueFiltro.limite = 10;
		$scope.dengueFiltro.pagina = $scope.paginaAtual;
		
    	if( $scope.municipio ) $scope.dengueFiltro.idMunicipio = $scope.municipio.id;	
		else $scope.dengueFiltro.idMunicipio = -1;
    	
    	if( $scope.regional ) $scope.dengueFiltro.idRegional = $scope.regional.id;	
		else $scope.dengueFiltro.idRegional = -1;
    	
		if( $scope.isPermissaoRegional && ( $scope.regional == null || typeof $scope.regional.id == 'undefined' || $scope.regional.id < 0 )){
        	var msgn = "Selecione uma regional";
        	$scope.mensagemAlerta( msgn );
        }
        else{
        	if( $scope.opcao == 'vigilantos' ){
    			api.dengueCaso.getAll( $scope.dengueFiltro ).then(function(response){
    				$scope.casos = response.data;
    	        	$scope.total = response.headers("x-count");
    			});			
    		}
    		else{
    			api.dengueCaso.getAllImportados( $scope.dengueFiltro ).then(function(response){
    				$scope.casos = response.data;
    	        	$scope.total = response.headers("x-count");
    			});	
    		}
        }
		
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
	
	$scope.openRelatorio = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/opcao.relatorio.html", 
			controller: "ModalOpcaoRelatorioController"
		});
		
		modalInstance.result.then(function ( type ){
			$scope.dengueFiltro.type = type;
			
			if( $scope.municipio ) $scope.dengueFiltro.idMunicipio = $scope.municipio.id;	
			else $scope.dengueFiltro.idMunicipio = -1;
	    	
	    	if( $scope.regional ) $scope.dengueFiltro.idRegional = $scope.regional.id;	
			else $scope.dengueFiltro.idRegional = -1;
			
			$scope.dengueFiltro.paciente = ( typeof $scope.dengueFiltro.paciente == 'undefined' ? null : $scope.dengueFiltro.paciente ); 
			$scope.dengueFiltro.idResultado = ( typeof $scope.dengueFiltro.idResultado == 'undefined' ? -1 : $scope.dengueFiltro.idResultado ); 
			$scope.dengueFiltro.dtColetaInicio = ( typeof $scope.dengueFiltro.dtColetaInicio == 'undefined' ? null : $scope.dengueFiltro.dtColetaInicio );
			$scope.dengueFiltro.dtColetaFim = ( typeof $scope.dengueFiltro.dtColetaFim == 'undefined' ? null : $scope.dengueFiltro.dtColetaFim );
			$scope.dengueFiltro.idMunicipioNotificante = ( typeof $scope.dengueFiltro.idMunicipioNotificante == 'undefined' ? -1 : $scope.dengueFiltro.idMunicipioNotificante ); 
			
			api.dengueCaso.getRelatorioVigilantos( $scope.dengueFiltro );
		});
	}
	
	$scope.confirmarRemover = function(caso){
		var mensagem = "Confirma exclusão do caso "+ caso.nomePaciente +" com data dos sintomas: "+ $filter('date')( caso.dataSintomas , 'dd/MM/yyyy') +"?";
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/confirma.exclusao.html", 
			controller: "ModalConfirmaExclusaoController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
		        id: function(){
		        	return caso.id;
		        }
		      }
		    });
		
		modalInstance.result.then(function (id){
			$scope.remover( id );
		});
	}
	
	$scope.remover = function(id){
		if( $scope.opcao == 'vigilantos' ){
			api.dengueCaso.deleteCaso(id).then(function(response){
				$scope.pesquisar();
				toastr.success('Caso dengue removido com sucesso');
			});
		}
		else{
			api.dengueCaso.deleteImportado(id).then(function(response){
				$scope.pesquisar();
				toastr.success('Caso dengue removido com sucesso');
			});			
		}
	}
	
	$scope.visualizaCaso = function( id ){
		api.dengueCaso.getSinanById( id ).then(function(response){
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/dengue/dengue.casos.visualizacao.html", 
				controller: "ModalVisualizaCasosController",
				resolve: { 
					caso: function(){
			        	return response.data;
			        }
			    }
			});
		});
	}
	
	$scope.init();
	$scope.getResultados();
	$scope.getClassificacoes();
	
});