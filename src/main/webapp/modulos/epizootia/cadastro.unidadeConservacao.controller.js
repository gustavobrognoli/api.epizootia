angular.module("vigilantos").controller('CadastroConservacaoController', 
function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra){
	
	
	$scope.salvarAnimal = function( animal ){
		api.artropodes.animais.insert( animal ).then( function(response){
			$scope.animal.id = response.data;

			toastr.success("Animal salvo com sucesso");
			//$scope.getAnimaisByTipo(animal.tipo.id);

		});
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close( null );	
	}
			
	$scope.salvar = function(){
		if( $scope.form.$valid){
			
			if( $scope.amostra.metodologia === false ){
				$scope.amostra.tipoArmadilha = null;
			}

			if( !$scope.amostra.tipoArmadilha || $scope.amostra.tipoArmadilha.descricao != "Outros" ){
				$scope.amostra.outroTipo = null;
			}
			
			if($scope.amostra.ambiente.fgOrigem === false) {
				$scope.amostra.coletaOrigem = null;
			}
			
			if($scope.amostra.tipoAnimal.nome){
				$uibModalInstance.close( $scope.amostra );	
			}else{
				toastr.error("Selecione um animal");
			}
		}
	}

	
    $scope.visceras= [{
        titulo: "Figado"
    }, {
        titulo: "Rim"
    }, {
        titulo: "Cérebro"
    },{
        titulo: "Baço"
    },{
        titulo: "Pulmão"
    },{
        titulo: "Coração"
    },{
        titulo: "Sangue"
    },{
        titulo: "Soro"
    }];
});