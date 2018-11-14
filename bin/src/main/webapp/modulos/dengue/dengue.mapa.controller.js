angular.module("vigilantos").controller("DengueMapaController", 
		function($scope, api, NgMap, $routeParams, toastr){
	
	var idFoco = $routeParams.id;
	var isUpdate = $routeParams.isUpdate;
	
	$scope.mapa = {};
	$scope.isEdicao = isUpdate;
	
	if( $scope.isEdicao ){
		$scope.hideShape = true;
	}
	
	//armazena no escopo
	NgMap.getMap().then(function(map) {
	    $scope.googlemaps = map;
	});
	
	$scope.init = function(){
		$scope.mapa.posicao = [-27.4658, -50.7504];
		$scope.mapa.zoom = 8;
	}
	
	$scope.showPoint = function(){

		if(idFoco && idFoco != "new"){
			api.dengue.getDtoMapaById(idFoco).then(function(response){
				$scope.mapa.posicao = response.data[0].posicao;
				$scope.mapa.zoom = 12;
				$scope.focos = response.data;
			});
		}
	}
	
	$scope.abrirInfo = function(event, $index){
		$scope.selecionado = $scope.focos[$index];
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	$scope.onDragendMarker = function(){
		 
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();
		
		api.dengue.updateLatLonFoco( idFoco, latitude, longitude ).then(function(response) {
			toastr.success("Posição alterada com sucesso");
		});
	}
	
	
	$scope.mover = function( event ){
		var latitude = this.getPosition().lat();
		var longitude = this.getPosition().lng();

		console.info( latitude + ", "+ longitude );
	
		$scope.googlemaps.showInfoWindow("dengue-info", this);
	}
	
	$scope.showPoint();
});