angular.module("vigilantos").controller("GraficoCasosController", function($scope, api) {
	
	$scope.options = {legend: {display: true}};
	$scope.options.legend.position = "top";
	
	
	$scope.getCasos = function(){
		api.dengueDashboard.getCasosAno().then(function(response){
			$scope.labels = response.data['suspeitos'].labels;
			$scope.series = ['Casos Suspeitos', 'Casos Confirmados'];
			$scope.data = [ response.data['suspeitos'].data, response.data['confirmados'].data ];
		});
	}
	
	$scope.isDistribuicaoMunicipio = function(){
		
		if( $scope.tipoGrafico && $scope.ano ){
			$scope.isDistribuicao = true;
		
			var confirmados = false;
			if( $scope.tipo == "confirmados" ){
				confirmados = true;
			}
			
			api.dengueDashboard.getCasosMunicipioAno( $scope.ano, confirmados).then(function(response){
				$scope.municipioLabels = response.data.labels;
				$scope.municipioData = response.data.data;
			});
			
		}
		else{
			$scope.isDistribuicao = false;
		}
		
	}
	
	$scope.getCasos();

});