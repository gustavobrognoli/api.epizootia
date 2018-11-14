angular.module("vigilantos").controller("MunicipioListaController", function($scope, api ){
	
	api.municipio.getSC('SC').success(function(data){
		$scope.municipios = data;
	});
	
});
	