angular.module("vigilantos").controller('TaxonomiaCadastroController', 
		function ($scope, api, $uibModal, $uibModalInstance, toastr, amostra){
	
	$scope.taxonomia = {};
	
	if( amostra && amostra != null ){
		$scope.taxonomia.amostraAnimal = amostra;
	}
	
	api.artropodes.animalEvolucao.getAll().then( function(response){
		$scope.evolucoes = response.data;
	});
	
	api.artropodes.animalFamilia.getAll().then( function(response){
		$scope.familias = response.data;
	});
	
	api.artropodes.importanciaMedica.getAll().then( function (response) {
		$scope.lsImportanciaMedica = response.data;
	});
	
	$scope.getGenerosByFamilia = function(){
		api.artropodes.animalGenero.getGenerosByFamilia( $scope.familia.id ).then( function(response){
			$scope.generos = response.data;
		});
	}
	
	$scope.getEspeciesByGenero = function(){
		if ($scope.genero !== undefined && $scope.genero !== null){
			api.artropodes.animalEspecie.getEspeciesByGenero( $scope.genero.id ).then( function(response){
				$scope.especies = response.data;
			});
		}
		
	}
	
	$scope.salvarEvolucao = function( evolucao ){
		api.artropodes.animalEvolucao.insert( evolucao ).then( function(response){
			$scope.evolucao.id = response.data;
			toastr.success("Evolução salva com sucesso");
			
			$scope.evolucoes.push($scope.evolucao);
		});
	}
	
	$scope.salvarFamilia = function( familia ){
		api.artropodes.animalFamilia.insert( familia ).then( function(response){
			$scope.familia.id = response.data;
			toastr.success("Família salva com sucesso");
			
			$scope.familias.push($scope.familia);

			
			$scope.getGenerosByFamilia();
		});
	}
	
	$scope.salvarGenero = function( genero ){
		api.artropodes.animalGenero.insert( genero ).then( function(response){
			$scope.genero.id = response.data;
			toastr.success("Gênero salvo com sucesso");
			
			$scope.generos.push($scope.genero);
			$scope.getEspeciesByGenero();
		});
	}
	
	$scope.salvarEspecie = function( especie ){
		api.artropodes.animalEspecie.insert( especie ).then( function(response){
			$scope.especie.id = response.data;
			toastr.success("Espécie salvo com sucesso");
			
			$scope.especies.push($scope.especie);
			$scope.getEspeciesByGenero($scope.especie.id);
		});
	}
	
	$scope.cadastrarEvolucao = function(){
		var mensagem = "Informe o nome da nova evolução";
		var tamanho = 45;
		
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
			var evolucao = {};
			evolucao.nome = nome;
			$scope.salvarEvolucao( evolucao );
			//$scope.evolucoes.push( evolucao );
			$scope.evolucao = evolucao;
		});
	}
	
	
	
	$scope.cadastrarFamilia = function(){
		var mensagem = "Informe o nome da nova familia";
		var tamanho = 45;
		
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
			var familia = {};
			familia.nome = nome;
			
			if( ! $scope.tiposAnimais ){
				$scope.tiposAnimais = [];
			}
			$scope.salvarFamilia( familia );
			//$scope.familias.push( familia );
			$scope.familia = familia;
			$scope.familia
			
		});
	}
	
	$scope.cadastrarGenero = function(){
		var mensagem = "Informe o nome do novo gênero";
		var tamanho = 45;
		
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
			var genero = {};
			genero.nome = nome;
			genero.familia = $scope.familia;
			
			if( ! $scope.tiposAnimais ){
				$scope.tiposAnimais = [];
			}
			
			$scope.salvarGenero(genero);
			//$scope.generos.push( genero );
			
			$scope.genero = genero;
		});
	}
	
	$scope.cadastrarEspecie = function(){
		var mensagem = "Informe o nome da nova espécie";
		var tamanho = 45;
		
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
			var especie = {};
			especie.nome = nome;
			especie.genero = $scope.genero;
			
			if( ! $scope.tiposAnimais ){
				$scope.tiposAnimais = [];
			}
			$scope.salvarEspecie(especie);
			//$scope.especies.push( especie );
			$scope.especie = especie;
			//$scope.getEspeciesByGenero($scope.especie.id);
		});
	}
	
	$scope.cancelar = function(){
		$uibModalInstance.close();	
	}
			
	$scope.salvar = function(){
		if( $scope.form.$valid ){
			$scope.taxonomia.evolucao = $scope.evolucao;
			$scope.taxonomia.quantidade = $scope.quantidade;
			$scope.taxonomia.especie = $scope.especie;
			$scope.taxonomia.importanciaMedica = $scope.importanciaMedica;
			$scope.taxonomia.familia = $scope.familia;
			$scope.taxonomia.genero = $scope.genero;
			
			$uibModalInstance.close( $scope.taxonomia );	
		}
	}
	
});