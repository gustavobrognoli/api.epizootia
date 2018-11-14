angular.module("vigilantos").controller("GraficoFocosController", function($scope, api) {
	
	$scope.options = {legend: {display: true}};
	$scope.options.legend.position = "bottom";
	
	api.dengueDashboard.getFocosAno().then(function(response){
		$scope.focosAnoData = response.data.data;
		$scope.focosAnoLabels = response.data.labels;
	});
	
	$scope.openGraficoPorAno = function(){
		if( $scope.ano ){
			$scope.municipio = null;
			
			api.dengueDashboard.getMunicipioFocos( $scope.ano ).then(function(response){
				$scope.municipioFocosData = response.data.data;
				$scope.municipioFocosLabels = response.data.labels;
			});			
		}
	}
	
	$scope.openGraficosByMunicipio = function(){
		if( $scope.municipio && $scope.municipio != null ){
			
			api.dengueDashboard.getDepositos( $scope.ano, $scope.municipio ).then(function(response){
				$scope.depositosData = response.data.data;
				$scope.depositosLabels = response.data.legends;
			});		
			
			api.dengueDashboard.getImoveis( $scope.ano, $scope.municipio ).then(function(response){
				$scope.imoveisData = response.data.data;
				$scope.imoveisLabels = response.data.labels;
			});		
			
		}
	}
	
});