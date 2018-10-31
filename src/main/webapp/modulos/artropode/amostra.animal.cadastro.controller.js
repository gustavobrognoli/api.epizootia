app.controller('CadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra){
	
	$scope.amostra = {};
//	$scope.amostra.animal = {};
//	$scope.amostra.animal.tipo = {};
	
	api.artropodes.tipoArmadilha.getAll().then( function(response){
		$scope.tiposArmadilhas = response.data;
	});
	
	api.artropodes.tipoColeta.getAll().then( function(response){
		$scope.tiposColeta = response.data;
	});
	
	api.artropodes.ambienteColeta.getAll().then( function(response){
		$scope.ambientesColeta = response.data;
	});
	
	api.artropodes.animais.getTipos().then( function(response){
		$scope.tiposAnimais = response.data;
	});

//	$scope.getAnimaisByTipo = function( idTipo ){
//		if( idTipo ){
//			api.artropodes.animais.getAnimaisByTipo( idTipo ).then( function(response){
//				$scope.animais = response.data;
//			});
//		}
//	}
	
	$scope.salvarAnimal = function( animal ){
		api.artropodes.animais.insert( animal ).then( function(response){
			$scope.animal.id = response.data;

			toastr.success("Animal salvo com sucesso");
			//$scope.getAnimaisByTipo(animal.tipo.id);

		});
	}
	
	$scope.salvarTipoAnimal = function( tipoAnimal ){
		api.artropodes.tipoAnimal.insert( tipoAnimal ).then( function(response){
			$scope.tipoAnimal.id = response.data;
			$scope.tipoAnimal.nome = tipoAnimal.nome;
			
			toastr.success("Tipo de animal salvo com sucesso");
			
			$scope.tiposAnimais.push($scope.tipoAnimal);
			//$scope.animais = {};
			
//			$scope.getAnimaisByTipo($scope.tipoAnimal.id);			
			
		});
	}
	
	$scope.cadastrarTipoAnimal = function(){
		var mensagem = "Informe o nome do novo tipo de animal";
		var tamanho = 255;
		
		var modalInstance = $uibModal.open({ 
			templateUrl: "modulos/template/cadastra.objeto.html", 
			controller: "ModalCadastraObjetoController",
			resolve: { 
		    	mensagem: function () {
		    		return mensagem;
		        },
				tamanho: function () {
					return tamanho;
        		}
		      }
		});
		
		modalInstance.result.then(function (nome){
			var tipoAnimal = {};
			tipoAnimal.nome = nome;
			
			if( ! $scope.tiposAnimais ){
				$scope.tiposAnimais = [];
			}
			$scope.salvarTipoAnimal(tipoAnimal);//teste salva
			//$scope.tiposAnimais.push( tipoAnimal );
			//$scope.amostra.animal = {};
			$scope.amostra.tipoAnimal = tipoAnimal;
			$scope.tipoAnimal = tipoAnimal;
		});
	}
	
	$scope.cadastrarAnimal = function(){
		
		if($scope.amostra.animal) {
			var mensagem = "Informe o nome do novo animal";
			var tamanho = 255;
			
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/cadastra.objeto.html", 
				controller: "ModalCadastraObjetoController",
				resolve: { 
					mensagem: function () {
						return mensagem;
					},
					tamanho: function () {
						return tamanho;
	        		}
				}
			});
			
			modalInstance.result.then(function (nome){
				var animal = {};
				animal.nome = nome;
				animal.tipo = $scope.amostra.animal.tipo;
				
				if( ! $scope.animais ){
					$scope.animais = [];
				}
				
				$scope.salvarAnimal(animal);//teste salva
				//$scope.animais.push( animal );
				$scope.amostra.animal = animal;
				$scope.animal = animal;
			});
		} else {
			var modalInstance = $uibModal.open({ 
				templateUrl: "modulos/template/dialog.mensagem.html", 
				controller: "ModalMensagemDialogController",
				
				backdrop: 'static', 
				keyboard: false,
				
				resolve: {
					mensagem: function(){
						return "Selecione o Tipo Animal para cadastrar novo animal.";
					},
					titulo: function(){
						return "Aviso";
					}
				}
			});
		}
		
	}
	
	if( amostra && amostra != null ){
		$scope.amostra = amostra;
		
		$scope.tipoAnimal = $scope.amostra.tipoAnimal;
		
		if(!amostra.metodologia) {
			$scope.amostra.metodologia = false;
		} else {
			$scope.amostra.metodologia = amostra.metodologia;			
		}
		//$scope.getAnimaisByTipo( $scope.tipoAnimal.id );
	} else {
		$scope.amostra.metodologia = false;
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

});