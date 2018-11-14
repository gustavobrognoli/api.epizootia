angular.module("vigilantos").controller('CadastroAuxiliarLogradouroCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra, logradouro){
	
	$scope.logradouro = logradouro;
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
	
			
	$scope.salvar = function(logradouro){
		if( $scope.form.$valid ){
			$scope.logradouro = logradouro;
			
			if($scope.logradouro.id){
				
				
				api.artropodes.cadastroAuxiliarLogradouro.update( logradouro ).then( function(response){
					$scope.logradouro.id = response.data;
					toastr.success("Logradouro atualizado com sucesso");
					$uibModalInstance.close( $scope.logradouro );
				});
			} else {
				api.artropodes.cadastroAuxiliarLogradouro.insert( logradouro ).then( function(response){
					$scope.logradouro.id = response.data;
					toastr.success("Logradouro salvo com sucesso");
					$uibModalInstance.close( $scope.logradouro );
				});
			}
		}
	}
	
	api.municipio.getAll().then( function(response){
		$scope.municipios = response.data;
	});

	$scope.getLocalidadesByMunicipio = function(){
		if ($scope.logradouro.localidade !== undefined){
			api.localidade.getByMunicipio( $scope.logradouro.localidade.municipio.id ).then( function(response){
				$scope.localidades = response.data;
			});
		}
	}
	
	$scope.getLocalidadesByMunicipio();
	
});