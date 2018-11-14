angular.module("vigilantos").controller("DengueCasosMapaGeralController", 
		function($scope, api, utils, NgMap){
	
	$scope.mapa = {};
	$scope.isEdicao = false;
	$scope.dengueFiltro = {};
	
	$scope.isOpen = false;
	
	$scope.openMenu = function(){
		$scope.isOpen = true;
	}
	
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		
		$scope.getPermissoes();
		$scope.buscarCasos();
	});
	
	$scope.getPermissoes = function(){
		var possuiPermissao = false;
		
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];

			if( role.modulo.nome == "dengue" ){
				possuiPermissao = true;
				
				if( role.descricao == "municipio" || role.descricao == "municipio_caso" ){
					$scope.isPermissaoMunicipio = true;
					
					$scope.municipio = $scope.usuarioOnline.municipio;
					
					$scope.getByMunicipio();
					$scope.buscarCasos();
				}
			}
		}
		
		if( !possuiPermissao ){
			$location.path("/error403");
		}
	}
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;

		$scope.dengueFiltro.dtColetaInicio = utils.dateUtils.getInicioAno();
		$scope.dengueFiltro.dtColetaFim = new Date();
	}
	
	$scope.getClassificacoes = function(){
		api.dengueCaso.getClassificacoes().then(function(response){
			$scope.classificacoes = response.data;
		});
	}

	$scope.getMunicipiosSC = function(){
		api.municipio.getByUF('SC').then(function(response){
			$scope.municipios = response.data;
		});
	}
	
	$scope.getByMunicipio = function(){
		$scope.dengueFiltro.idMunicipio = -1;
		
        if( $scope.municipio != null ){
        	$scope.dengueFiltro.idMunicipio = $scope.municipio.id;
        	
        	api.dengue.getLocalidades( $scope.municipio ).then(function(response){
        		$scope.localidades = response.data;
        	});
        }
        else{
        	$scope.localidades = [];
        }
    }
	
	$scope.buscarCasos = function(){
		api.dengueCaso.getMapaCasos($scope.dengueFiltro).then(function(response){
			$scope.hashMarkers = response.data;
			$scope.casos = $scope.hashMarkers.casos;
			$scope.focos = $scope.hashMarkers.focos;
		});
	}
	
	$scope.abrirInfo = function(event, $index, isCasos){
		if( isCasos ){
			$scope.selecionado = $scope.casos[$index];	
		}
		else{
			$scope.selecionado = $scope.focos[$index];
		}
		
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	
	$scope.mover = function( event ){
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();

		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	$scope.init();
	$scope.getClassificacoes();
	$scope.getMunicipiosSC();
});