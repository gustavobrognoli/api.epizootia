angular.module("vigilantos").controller("MapaTotalMunicipioController", function($scope, api, utils, NgMap){
	
	$scope.mapa = {};
	$scope.dtInicio = {};
	$scope.dtFim = {};
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;

		$scope.dtFim = new Date();
		$scope.dtInicio = utils.dateUtils.getInicioAno();	
		
		$scope.buscarFocos();
	}
	
	$scope.buscarFocos = function(){
		api.dengue.getMapaMunicipioFocos($scope.dtInicio, $scope.dtFim, -1).then(function(response){
			$scope.focos = response.data;
		});
	}
	
	$scope.abrirInfo = function(event, $index){
		$scope.selecionado = $scope.focos[$index];
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	
	$scope.init();
	
});