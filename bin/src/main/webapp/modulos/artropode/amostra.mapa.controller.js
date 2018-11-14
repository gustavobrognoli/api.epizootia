angular.module("vigilantos").controller("AmostraMapaController", 
		function($scope, api, utils, NgMap, $location){
	
	$scope.mapa = {};
	$scope.isEdicao = false;
	$scope.filtro = {};
	
	$scope.isOpen = false;
	
	$scope.isPermissaoAdmin = false;
	$scope.isPermissaoRegional = false;
	$scope.isPermissaoMunicipio = false;
	
    //Focus Ficha
    angular.element('#btfiltro').on('click', function() {
    	angular.element("[name='nficha']").focus();
    });
	
	$scope.openMenu = function(){
		$scope.isOpen = true;
	}
	
	$scope.getPermissoes = function(){
		var possuiPermissao = false;
	
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];

			if( role.modulo.nome == "artropodes" ){
				$scope.role = role; 
				possuiPermissao = true;
				
				if( role.descricao == "admin" ){
					$scope.isPermissaoAdmin = true;
					
					break;
				}
				else if( role.descricao == "regional" ){
					$scope.isPermissaoRegional = true;
					
					break;
				}
				else if( role.descricao == "municipio" ){
					$scope.isPermissaoMunicipio = true;

					break;
				}
				else{
					possuiPermissao = false;
				}
			}
		}
		
		if( !possuiPermissao ){
			$location.path("/error403");
			return;
		}
		
		montaFiltros();
				
	}

	//Localidades
	$scope.getByMunicipio = function(){
		$scope.filtro.idMunicipio = -1;
		
        if( $scope.municipio != null ){
        	$scope.filtro.idMunicipio = $scope.municipio.id;
        	
        	api.artropodes.amostraMapa.getLocalidades($scope.municipio).then(function(response){
        		$scope.localidades = response.data;
        	});
        }
        else{
        	$scope.localidades = [];
        }
    }
	
	$scope.getRegionalByMunicipio = function( idMunicipio ){
		api.regional.getByMunicipio( idMunicipio ).then(function(response){
			$scope.regional = response.data;
			
			$scope.regionais = [];
			$scope.regionais.push($scope.regional);
			
			$scope.filtro.idRegional = $scope.regional.id;
			$scope.$broadcast('buscaMapa', undefined);
		});
	}
	
	
	$scope.getRegionalByMunicipioRegional = function( idMunicipio ){
		api.regional.getByMunicipio( idMunicipio ).then(function(response){
			$scope.regional = response.data;
			
			$scope.regionais = [];
			$scope.regionais.push($scope.regional);
			
			$scope.filtro.idRegional = $scope.regional.id;
			
			$scope.selectMunicipioRegional();
		});
	}	
	
	$scope.getRegionalByMunicipioMunicipal = function( idMunicipio ){
		api.regional.getByMunicipio( idMunicipio ).then(function(response){
			$scope.regional = response.data;
			
			$scope.regionais = [];
			$scope.regionais.push($scope.regional);
			
			$scope.filtro.idRegional = $scope.regional.id;
			
			$scope.selectMunicipioMunicipal();
		});
	}	
	
	$scope.getRegionais = function(){
		api.regional.getAll().then(function(response){
			$scope.regionais = response.data;
			$scope.$broadcast('buscaMapa', undefined);
		});	
	}
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;

		$scope.filtro.dtFim = new Date();
		$scope.filtro.dtInicio = utils.dateUtils.getInicioMes();			
	}
	
	$scope.buscarMapa = function(){
		
		$scope.filtro.idMunicipio = utils.inputUtils.isVazioNullUndefined($scope.municipio) ? -1 : $scope.municipio.id;
		$scope.filtro.idRegional = utils.inputUtils.isVazioNullUndefined($scope.regional) ? -1 : $scope.regional.id;
		
		
		api.artropodes.amostraMapa.getMapa($scope.filtro).then(function(response){
			$scope.lista = response.data;
		});
	}
	
	$scope.abrirInfo = function(event, $index){
		if( ! $scope.isTodoEstado ){
			$scope.selecionado = $scope.lista[$index];
			$scope.googlemaps.showInfoWindow("amostra-info", this);			
		}
	}
	
	
	$scope.mover = function( event ){
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();

		$scope.googlemaps.showInfoWindow("amostra-info", this);
	}
	
	$scope.openRelatorio = function(){
	
		$scope.filtro.type = "xls";
		
		
		$scope.filtro.idMunicipio = utils.inputUtils.isVazioNullUndefined($scope.municipio) ? -1 : $scope.municipio.id ;

		$scope.filtro.idFicha = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idFicha) ? -1 : $scope.filtro.idFicha );
		$scope.filtro.idMunicipio = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idMunicipio) ? -1 : $scope.filtro.idMunicipio ); 
		$scope.filtro.idRegional = ( utils.inputUtils.isVazioNullUndefined($scope.regional) ? -1 : $scope.regional.id ); 
		$scope.filtro.idLocalidade = ( utils.inputUtils.isVazioNullUndefined($scope.municipio) ? -1 : $scope.municipio.id ); 
		$scope.filtro.laudo = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.laudo) ? -1 : $scope.filtro.laudo );		
		$scope.filtro.idTipoAnimal = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idTipoAnimal) ? -1 : $scope.filtro.idTipoAnimal ); 
		//$scope.filtro.idAnimal = ( typeof $scope.filtro.idAnimal == 'undefined' ? -1 : $scope.filtro.idAnimal ); 
		$scope.filtro.idEspecie = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idEspecie) ? -1 : $scope.filtro.idEspecie ); 
		$scope.filtro.idGenero = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idGenero) ? -1 : $scope.filtro.idGenero ); 
		$scope.filtro.idFamilia = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.idFamilia) ? -1 : $scope.filtro.idFamilia ); 
		
		$scope.filtro.dtInicio = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.dtInicio) ? null : $scope.filtro.dtInicio );
		$scope.filtro.dtFim = ( utils.inputUtils.isVazioNullUndefined($scope.filtro.dtFim) ? null : $scope.filtro.dtFim );			
		
		api.artropodes.amostraMapa.getRelatorioMapa($scope.filtro);
		
		if(!utils.inputUtils.isVazioNullUndefined($scope.filtro.idFicha) 
				&& $scope.filtro.idFicha === -1) {
			$scope.filtro.idFicha = undefined;//Limpa campo na tela			
		}
		
	}

	
	$scope.init();
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		$scope.getPermissoes();
	});
	
	/**
	 * Popula filtros utilzados na tela
	 */
	montaFiltros = function() {
		selectGersa();
	};
	
	/**
	 * Monta select GERSA
	 */
	selectGersa = function() {
		if( $scope.role.descricao == "admin" ) {
			$scope.getRegionais();
		}
		if( $scope.role.descricao == "regional" ) {
			$scope.getRegionalByMunicipioRegional(  $scope.usuarioOnline.municipio.id );
			
		}
		if( $scope.role.descricao == "municipio" ) {
			$scope.municipio = $scope.usuarioOnline.municipio;			
			$scope.getRegionalByMunicipioMunicipal(  $scope.usuarioOnline.municipio.id );
		}
	};
	
	
	$scope.selectMunicipio = function() {
		
		if(utils.inputUtils.isVazioNullUndefined($scope.regional) ) {
			$scope.municipios = [];
			$scope.localidades = [];
			return;
		}
		
		api.municipio.getByRegionalId($scope.regional.id).then(function(response){
			$scope.municipios = response.data;
		});		
	};	
	
	$scope.selectMunicipioRegional = function() {
		
		if(utils.inputUtils.isVazioNullUndefined($scope.regional) ) {
			$scope.municipios = [];
			$scope.localidades = [];
			return;
		}
		
		api.municipio.getByRegionalId($scope.regional.id).then(function(response){
			$scope.municipios = response.data;
			$scope.$broadcast('buscaMapa', undefined);
		});
	};	
	
	$scope.selectMunicipioMunicipal = function() {
		
		if(utils.inputUtils.isVazioNullUndefined($scope.regional) ) {
			$scope.municipios = [];
			$scope.localidades = [];
			return;
		}
		
		api.municipio.getByRegionalId($scope.regional.id).then(function(response){
			$scope.municipios = response.data;
			
			$scope.selectLocalidadesMunicipal();
		});
	};		
	
	$scope.selectLocalidades = function() {
		if(utils.inputUtils.isVazioNullUndefined($scope.municipio) ) {
			$scope.localidades = [];
			return;
		}
		
    	api.artropodes.amostraMapa.getLocalidades($scope.municipio).then(function(response){
    		$scope.localidades = response.data;
    	});
	};
	
	
	$scope.selectLocalidadesMunicipal = function() {
		if(utils.inputUtils.isVazioNullUndefined($scope.municipio) ) {
			$scope.localidades = [];
			return;
		}
		
    	api.artropodes.amostraMapa.getLocalidades($scope.municipio).then(function(response){
    		$scope.localidades = response.data;
    		$scope.$broadcast('buscaMapa', undefined);
    	});
	};
	
	//TipoAnimal
	api.artropodes.animais.getTipos().then( function(response){
		$scope.tiposAnimais = response.data;
	});
	
	//Familia
	api.artropodes.animalFamilia.getAll().then( function(response){
		$scope.familias = response.data;
	});
	
	$scope.selectGenero = function() {
		if(utils.inputUtils.isVazioNullUndefined($scope.filtro.idFamilia) ) {
			$scope.generos = [];
			$scope.especies = [];
			return;
		}
		
		api.artropodes.animalGenero.getGenerosByFamilia($scope.filtro.idFamilia).then( function(response){
			$scope.generos = response.data;
		});	
	};
	
	$scope.selectEspecie = function() {
		if(utils.inputUtils.isVazioNullUndefined($scope.filtro.idGenero) ) {
			$scope.especies = [];
			return;
		}
		
		api.artropodes.animalEspecie.getEspeciesByGenero($scope.filtro.idGenero).then( function(response){
			$scope.especies = response.data;
		});
	};
	
	//Carrega mapa ao carregar pagina
	$scope.$on('buscaMapa', function(event, data) {
		  $scope.buscarMapa();
	});
	
});