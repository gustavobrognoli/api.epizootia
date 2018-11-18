angular.module("vigilantos").controller("EpizootiaNotificacaoListaController", 
		function($scope, api, utils, $uibModal, toastr, $filter, $location){

	$scope.dengueFiltro = {};
    $scope.paginaAtual = 1;
    $scope.limite = 10;
    
    $scope.municipio = {};
    $scope.regional = {};
    
    $scope.isPermissaoAdmin = false;
    $scope.isPermissaoRegional = false;
    $scope.isPermissaoMunicipio = false;
	$scope.isPermissaoVisitante = false;
	$scope.isPermissaoMunicipioCaso = false;
	
    $scope.isMunicipio = false;
    $scope.isLocalidade= false;
    $scope.isDtColeta = false;
    
	$scope.isOpen = false;
	
	$scope.openMenu = function(){
		$scope.isOpen = true;
	}
	
	api.epizootia.ficha.getAll().then(function( response ) {
		$scope.fichas = response.data.data;
	});
	
	$scope.getMunicipipoSC = function(){
		api.municipio.getByUF('SC').then(function(response){
			$scope.municipios = response.data;
		});
	}

	$scope.getEspecies = function(){
		var aegypti = new Object;
		aegypti.id = 1;
		aegypti.nome = 'A. aegypti';
		
		var albo = new Object;
		albo.id = 2;
		albo.nome = 'A. albopictus';
		
		var lista = [aegypti, albo];
		
		$scope.especies = lista;
	}
	
	$scope.getByMunicipio = function(){
		$scope.dengueFiltro.idMunicipio = -1;
		
        if( $scope.municipio != null ){
        	$scope.dengueFiltro.idMunicipio = $scope.municipio.id;
        	
        	api.dengue.getLocalidades( $scope.municipio ).then(function(response){
        		var registro = {};
        		registro.id = -1;
        		registro.nome = "Todas...";
        		
        		$scope.localidades = [];
        		$scope.localidades.push( registro );
        		
        		$scope.localidades = $scope.localidades.concat( response.data );
        	});
        }
        else{
        	$scope.dengueFiltro.idLocalidade = -1;
        	$scope.localidades = [];
        }
    }
	
	$scope.getByRegional = function(){
		$scope.dengueFiltro.idRegional = -1;
		
		if( $scope.regional != null ){
        	$scope.dengueFiltro.idRegional = $scope.regional.id;
        	
        	$scope.municipios = this.regional.municipios;
        }
		else{
        	if( $scope.isPermissaoRegional ){
        		$scope.municipios = [];
        	}
        	else{
        		$scope.getMunicipipoSC();
        	}
        }
    }
	
	$scope.getDataInicio = function(){
		this.dengueFiltro.dtColetaInicio = utils.dateUtils.getInicioAno();
		this.dengueFiltro.dtColetaFim = utils.dateUtils.getInicioProxMes();
	}
	
	$scope.pesquisar = function(){
		$scope.dengueFiltro.limite = $scope.limite;
		$scope.dengueFiltro.pagina = $scope.paginaAtual;
        
		$scope.dengueFiltro.ordenacao = [];
        
        if( $scope.isMunicipio ){
        	$scope.dengueFiltro.ordenacao.push( 1 );
        }
        if( $scope.isLocalidade ){
        	$scope.dengueFiltro.ordenacao.push( 2 );        	
        }
        if( $scope.isDtColeta ){
        	$scope.dengueFiltro.ordenacao.push( 3 );
        }

        
        if( $scope.isPermissaoRegional && ( $scope.regional == null || typeof $scope.regional.id == 'undefined' || $scope.regional.id < 0 )){
        	var msgn = "Selecione uma regional";
        	$scope.mensagemAlerta( msgn );
        }
        else{
        	api.dengue.getDto( this.dengueFiltro ).then(function(response){
            	$scope.lista = response.data;
            	$scope.total = response.headers("x-count");
            });              	        	
        }
	}

	$scope.visualizaRegistro = function( id ){
		
		api.epizootia.ficha.get( id ).then(function(response) {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/dengue/dengue.focos.visualizacao.html", 
				controller: "ModalVisualizaFocosController",
				backdrop: 'static', 
				keyboard: false,
				
				resolve: { 
			    	dengue: function () {
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
			$scope.pesquisar();
			toastr.success('Registro removido com sucesso');
		});
	}
	
	$scope.confirmarRemover = function( ficha ){
		var mensagem = "Confirma exclusão deste Registro"+"?";
		
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
	
	$scope.openRelatorio = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/opcao.relatorio.html", 
			controller: "ModalOpcaoRelatorioController"
		});
		
		modalInstance.result.then(function ( type ){
			$scope.dengueFiltro.type = type;
			
			$scope.dengueFiltro.idRegional = ( typeof $scope.dengueFiltro.idRegional == 'undefined' ? -1 : $scope.dengueFiltro.idRegional ); 
			$scope.dengueFiltro.idMunicipio = ( typeof $scope.dengueFiltro.idMunicipio == 'undefined' ? -1 : $scope.dengueFiltro.idMunicipio ); 
			$scope.dengueFiltro.idLocalidade = ( typeof $scope.dengueFiltro.idLocalidade == 'undefined' ? -1 : $scope.dengueFiltro.idLocalidade ); 
			$scope.dengueFiltro.especie = ( typeof $scope.dengueFiltro.especie == 'undefined' ? -1 : $scope.dengueFiltro.especie ); 
			$scope.dengueFiltro.imovel = ( typeof $scope.dengueFiltro.imovel == 'undefined' ? -1 : $scope.dengueFiltro.imovel ); 
			$scope.dengueFiltro.deposito = ( typeof $scope.dengueFiltro.deposito == 'undefined' ? -1 : $scope.dengueFiltro.deposito ); 
			$scope.dengueFiltro.idAtividade = ( typeof $scope.dengueFiltro.idAtividade == 'undefined' ? -1 : $scope.dengueFiltro.idAtividade ); 
			$scope.dengueFiltro.isExistente = ( typeof $scope.dengueFiltro.idRegional == 'undefined' ? false : $scope.dengueFiltro.isExistente ); 
			
			api.dengue.getRelatorio( $scope.dengueFiltro );
		});
	}
	
	$scope.getDataInicio();
	$scope.getEspecies();
	
	
});