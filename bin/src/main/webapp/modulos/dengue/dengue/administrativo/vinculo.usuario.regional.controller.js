angular.module("vigilantos").controller("VinculoUsuarioRegionalController", 
	function($scope, api, toastr) {
	
	
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
	});
	
	$scope.getRegionaisByUser = function(){
		api.dengueUsuario.getRegionaisByUser( $scope.usuario.id ).then(function(response){
			$scope.selecionados = response.data;
		});
	}
	
	$scope.getRegionais = function(){
		api.regional.getAll().then(function(response){
			$scope.regionais = response.data;
		});
	}
	
	$scope.getUsuarios = function(){
		api.dengueUsuario.getUsuariosRegionais().then(function(response){
			$scope.usuarios = response.data;
		});
	}
	
	$scope.salvar = function(){
		api.dengueUsuario.updateAll( $scope.usuario.id, $scope.selecionados ).then(function(response){
			if( response.data == 'true' ){
				toastr.success("Dados salvo com sucesso");
			}
			else{
				toastr.error("Problemas ao salvar");
			}
		});
	}
	
 	$scope.models = {
        selected: null,
    };
    
 	$scope.getUsuarios();
	$scope.getRegionais();
 	
});