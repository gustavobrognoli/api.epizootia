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

	api.epizootia.vidaLivre.getAll().then(function( response ) {
		$scope.vidasLivre = response.data.data;
	});
	
	api.epizootia.apreensao.getAll().then(function( response ) {
		$scope.apreensoes = response.data.data;
	});
	
	api.epizootia.cativeiro.getAll().then(function( response ) {
		$scope.cativeiros = response.data.data;
	});
	
	api.epizootia.viscera.getAll().then(function( response ) {
		$scope.visceras = response.data.data;
	});

	api.epizootia.anormalidade.getAll().then(function( response ) {
		$scope.anormalidades = response.data.data;
	});
	
	$scope.salvar = function(animal){
		
		// Visceras
		var selecionadosViscera = $scope.visceras;
	        for (var i = 0; i < $scope.visceras.length; i++) {
	            if ($scope.visceras[i].selected) {
	            	var visceras = {'id': $scope.visceras[i].id, 'viscera':$scope.visceras[i].viscera };
	            	key.viscera.push(viscera);
	            }
	        }
		        
		// Anormalidades
		var selecionadosAnormalidade = $scope.anormalidades;
	        for (var i = 0; i < $scope.anormalidades.length; i++) {
	            if ($scope.anormalidades[i].selected) {
	            	var anormalidades = {'id': $scope.anormalidades[i].id, 'sintoma':$scope.anormalidades[i].sintoma };
	            	key.anormalidade.push(anormalidade);
	            }
	        }
		
		api.epizootia.animal.insert( animal ).then( function(response){
			$scope.animal.id = response.data;
			toastr.success("Animal salvo com sucesso");
			$uibModalInstance.close( $scope.animal );
		});
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
	
	$scope.addEspecie = function(){
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/epizootia/cadastro.especie.html", 
			controller: "CadastroEspecieController",
			backdrop: 'static', 
			keyboard: false,
			resolve: {
				amostra: function(){
					return null;
				}
			}
		});
		
		modalInstance.result.then(function ( animalEspecie ){
			if(animalEspecie != null) {
				$scope.especies.push( animalEspecie );
			}
		});
	}
	
});