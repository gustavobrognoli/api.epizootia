app.controller('CadastroMacacoController', 
function ($scope, api, $uibModal, $uibModalInstance, toastr){
	
	
	api.epizootia.nomePopular.getAll().then(function( response ) {
		$scope.nomesPopulares = response.data.data;
	});
	
	api.epizootia.especie.getAll().then(function( response ) {
		$scope.especies = response.data.data;
	});
	
	api.epizootia.idade.getAll().then(function( response ) {
		$scope.idades = response.data.data;
	});
	
	api.epizootia.sexo.getAll().then(function( response ) {
		$scope.sexos = response.data.data;
	});
	
	api.epizootia.situacao.getAll().then(function( response ) {
		$scope.situacoes = response.data.data;
	});
	
	api.epizootia.tempoObito.getAll().then(function( response ) {
		$scope.temposObitos = response.data.data;
	});
	
	$scope.salvar = function(animal){
		api.epizootia.animal.insert( animal ).then( function(response){
			$scope.animal.id = response.data;
			toastr.success("Animal salvo com sucesso");
			$uibModalInstance.close( $scope.animal );
		});
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	
});