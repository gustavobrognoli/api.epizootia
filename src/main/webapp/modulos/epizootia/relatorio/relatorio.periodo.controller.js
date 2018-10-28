angular.module("vigilantos").controller("RelatorioPeriodoController", function($scope, api, utils, $routeParams ) {
	
	
	var tipo = $routeParams.tipo;

	$scope.init = function(){
		
		$scope.dtInicio = utils.dateUtils.getInicioAno();
		$scope.dtFim = new Date();
		
		
		if( tipo == "rel_focos" ){
			$scope.titulo = "Relatório de Focos por período";
		}
		else if( tipo == "pro_dengue" ){
			$scope.titulo = "Relatório Programa Dengue";
		}
	}
	
	$scope.visualizar = function(){
		if( tipo == "rel_focos" ){
			api.dengue.getRelatorioFocos( $scope.dtInicio, $scope.dtFim);
		}
		else if( tipo == "pro_dengue" ){
			api.dengue.getRelatorioGeralFocos( $scope.dtInicio, $scope.dtFim);
		}
		else if( tipo == "map_focos" ){
			api.dengue.getRelatorioFocos( $scope.dtInicio, $scope.dtFim);
		}
	}
	
	$scope.init();
	
});