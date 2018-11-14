angular.module("vigilantos").controller("DengueCasoMapaController", 
		function($scope, api, NgMap, $routeParams){
	
	var id = $routeParams.id;
	
	$scope.mapa = {};
	$scope.isEdicao = false;
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;
	}
	
	$scope.showPoint = function(){

		if(id && id != "new"){
			api.dengueCaso.getDtoMapaById(id).then(function(response){
				$scope.mapa.posicao = response.data[0].posicao;
				$scope.mapa.zoom = 12;
				$scope.casos = response.data;
			});
		}
	}
	
	$scope.abrirInfo = function(event, $index){
		$scope.selecionado = $scope.casos[$index];
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	
	$scope.mover = function( event ){
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();

		console.info( latitude + ", "+ longitude );
	
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	$scope.showPoint();
});