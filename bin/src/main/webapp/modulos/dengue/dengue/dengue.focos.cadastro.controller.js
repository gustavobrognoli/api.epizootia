angular.module("vigilantos").controller("DengueFocosCadastroController", 
			function($scope, $location, api, $routeParams, $uibModal, toastr, $filter){

	var id = $routeParams.id;
	
	$scope.dengue = {};
	$scope.municipio = null;
	$scope.localidade = null;
	
	$scope.isPermissaoAdmin = false;
	$scope.isPermissaoRegional = false;
	$scope.isPermissaoMunicipio = false;
	
	api.usuario.getUserOnline().then(function(response){
		$scope.usuarioOnline = response.data;
		$scope.roles = $scope.usuarioOnline.roles;
		$scope.getPermissoes();
	});
	
	$scope.getPermissoes = function(){
		var possuiPermissao = false;
		
		for (i = 0; i < $scope.roles.length; i++) {
			var role = $scope.roles[i];
			possuiPermissao = false;
			
			if( role.modulo.nome == "dengue" ){
				possuiPermissao = true;
				
				
				if( role.descricao == "admin" || role.descricao == "laboratorio" ){
					$scope.isPermissaoAdmin = true;
					$scope.getMunicipiosSC();
					
					break;
				}
				else if( role.descricao == "regional" ){
					$scope.getRegionalByUser();
					
					break;
				}
				else if( role.descricao == "municipio" ){
					$scope.isPermissaoMunicipio = true;
					
					$scope.municipio = $scope.usuarioOnline.municipio;
					$scope.municipios = [$scope.usuarioOnline.municipio];
					
					$scope.getByMunicipio();
					
					break;
				}
				else{
					possuiPermissao = false;
				}
			}
		}
		
		if( !possuiPermissao ){
			$location.path("/error403");				
		}
	}
	
	
	if(id && id != "new"){
		api.dengue.get(id).then(function(response){
			$scope.dengue = response.data;
			
			if( ! $scope.dengue.latitude ){
				$scope.dengue.latitude = 0;
			}
			if( ! $scope.dengue.longitude ){
				$scope.dengue.longitude = 0;
			}
			
			$scope.update = true;
			
			$scope.municipio = $scope.dengue.endereco.localidade.municipio;
			$scope.getByMunicipio();
				
			$scope.localidade = $scope.dengue.endereco.localidade;
			$scope.getByLocalidade();				
		});
	} 
	else {
		$scope.dengue = {};
	}
	
	api.dengue.getTipoImovel().then(function(response){
		$scope.tiposImoveis = response.data;
	});
	
	api.dengue.getTipoDeposito().then(function(response){
		$scope.tiposDepositos = response.data;
	});
	
	api.dengue.getAtividades().then(function(response){
		$scope.atividades = response.data;
	});
	
	$scope.getRegionalByUser = function(){
		api.dengueUsuario.getRegionaisByUser( $scope.usuarioOnline.id ).then(function(response){
			if( response.data.length > 0 ){
				var municipios = response.data[0].municipios;
				
				for( i=1; i < response.data.length; i++ ){
					municipios = municipios.concat( response.data[i].municipios );
				}
				
				$scope.municipios = municipios;
			}
		});
	}

	$scope.getByMunicipio = function(){
        if( $scope.municipio != null ){
        	api.dengue.getLocalidades( $scope.municipio ).then(function(response){
        		$scope.localidades = response.data;
        	});
        }
        else{
        	$scope.localidades = [];
        }
    }
	
	$scope.getByLocalidade = function(){
        if( $scope.localidade != null && $scope.localidade.id > 0 ){
			api.dengue.getEnderecos( $scope.localidade ).then(function(response){
        		$scope.enderecos = response.data;
        	});
        }
        else{
        	$scope.enderecos = [];
        }
    }
	
	$scope.getMunicipiosSC = function(){
		api.municipio.getByUF('SC').then(function(response){
			$scope.municipios = response.data;
		});
	}
	
	$scope.salvar = function(){
    	if($scope.form.$valid){

    		if( typeof $scope.dengue.latitude == 'undefined' || $scope.dengue.latitude.length < 1 )
    			$scope.dengue.latitude = 0;

    		if( typeof $scope.dengue.longitude == 'undefined' || $scope.dengue.longitude.length < 1 )
    			$scope.dengue.longitude = 0;
    		
    		$scope.dengue.idUsuario = $scope.usuarioOnline.id;
    		
    		if($scope.update){
        		api.dengue.update($scope.dengue).then(function(response){
        			toastr.success("Foco alterado com sucesso");
            		$location.path("/dengue/focos");
        		});
        	} 
        	else {
                api.dengue.insert($scope.dengue).then(function(response){
                    toastr.success("Foco salvo com sucesso, número de controle: "+ response.data );   
                    $location.path("/dengue/focos");
                });
        	}
    	}
	}
	
	$scope.cadastraLocalidade = function(){
		var mensagem = "Informe o nome da nova localidade de "+ $scope.municipio.nome;
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
			var loc = {};
			loc.municipio = $scope.municipio;
			loc.nome = nome;
			
			$scope.localidades.push( loc );
			$scope.localidade = loc;
		});
	}
	
	$scope.cadastraEndereco = function(){
		var mensagem = "Informe o nome do novo endereço da localidade "+ $scope.localidade.nome;
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
			var endereco = {};
			endereco.localidade = $scope.localidade;
			endereco.nome = nome;
			
			if( ! $scope.enderecos ){
				$scope.enderecos = [];
			}
			
			$scope.enderecos.push( endereco );
			$scope.dengue.endereco = endereco;
		});
	}
	
});