angular.module("vigilantos").controller("DengueMapaGeralController", 
		function($scope, api, utils, NgMap, $location){
	
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
		$scope.buscarFocos();
	});
	
	$scope.getPermissoes = function(){
		var possuiPermissao = false;
		
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];

			if( role.modulo.nome == "dengue" ){
				possuiPermissao = true;
				
				if( role.descricao == "municipio" || role.descricao == "municipio_caso"){
					$scope.isPermissaoMunicipio = true;
					
					$scope.municipio = $scope.usuarioOnline.municipio;
					
					$scope.getByMunicipio();
					$scope.buscarFocos();
				}
			}
		}
		
		if( !possuiPermissao ){
			$location.path("/error403");
		}
	}
	
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
        		$scope.localidades = response.data;
        	});
        }
        else{
        	$scope.localidades = [];
        }
    }
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;

		$scope.dengueFiltro.dtColetaFim = new Date();
		$scope.dengueFiltro.dtColetaInicio = utils.dateUtils.getInicioAno();			
	}
	
	$scope.buscarFocos = function(){
		
		if( $scope.isTodoEstado ){
			$scope.dengueFiltro.idMunicipio = -1;
		}
		else{
			$scope.dengueFiltro.idMunicipio = $scope.municipio != null ? $scope.municipio.id : -1;
		}
		
		api.dengue.getMapaFocos($scope.dengueFiltro).then(function(response){
			$scope.focos = response.data;
		});
	}
	
	$scope.abrirInfo = function(event, $index){
		if( ! $scope.isTodoEstado ){
			$scope.selecionado = $scope.focos[$index];
			$scope.googlemaps.showInfoWindow("dengue-info", this);			
		}
	}
	
	
	$scope.mover = function( event ){
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();

		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	$scope.init();
	$scope.getMunicipipoSC();
	$scope.getEspecies();
});