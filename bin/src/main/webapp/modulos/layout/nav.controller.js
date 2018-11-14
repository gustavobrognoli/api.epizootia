angular.module("vigilantos").controller('NavbarController', 
		function ($scope, api, $uibModal, toastr) {
    $scope.oneAtATime = false;

    $scope.status = {
      isFirstOpen: true,
      isSecondOpen: true,
      isThirdOpen: true
    };

	$scope.isPermissaoDengue = true;
//	
//	api.usuario.getUserOnline().then( function(response) {
//		$scope.usuarioOnline = response.data;
//		$scope.getPermissoes();
//	});
//    
 /*   $scope.getPermissoes = function(){

		for (i = 0; i < $scope.usuarioOnline.roles.length; i++) { 
			var role = $scope.usuarioOnline.roles[i];

			if( role.modulo.nome == "dengue" ){
				$scope.isPermissaoDengue = true;
				
				$scope.isDengueAdmin = role.admin;
				
				$scope.isDengueMunicipio = false;
				$scope.isDengueRegional = false;
				$scope.isDengueMunicipioCaso = false;
				$scope.isDengueLaboratorio = false;
				$scope.isDengueVisitanteMun = false;
				$scope.isDengueVisitanteReg = false;
				$scope.isDengueVisitante = false;
				
				// Permissão com acesso aos focos do seu municipio
				if( role.descricao == "municipio" ){
					$scope.isDengueMunicipio = true;
				}
				if( role.descricao == "regional" ){
					$scope.isDengueRegional = true;
				}
				if( role.descricao == "municipio_caso" ){
					$scope.isDengueMunicipioCaso = true;
				}
				if( role.descricao == "laboratorio" ){
					$scope.isDengueLaboratorio = true;
				}
				if( role.descricao == "municipio_visitante" || role.descricao == "regional_visitante" ||  role.descricao == "visitante" ){
					$scope.isDengueVisitante = true;
				}
			}
			if( role.modulo.nome == "pnem" ){
				$scope.isPermissaoPnem = true;
			}
			if( role.modulo.nome == "artropodes"){
				$scope.isPermissaoArtropodes = true;
			}
			if( role.modulo.nome == "imunobiologicos" ){
				$scope.isPermissaoSialie = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "formulainfantil" ){
				$scope.isPermissaoFormulaInfantil = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "pcdsc" ){
				$scope.isPermissaoPcdsc = true
		    	$scope.isSistemaAntigo = true;;
			}
			if( role.modulo.nome == "cancer" ){
				$scope.isPermissaoCancer = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "saladeparto" ){
				$scope.isPermissaoSalaParto = true;
				$scope.isSistemaAntigo = false;
			}
			if( role.modulo.nome == "tuberculose" ){
				$scope.isPermissaoIltb = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "influenza" ){
				$scope.isPermissaoInfluenza = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "tabagismo" ){
				$scope.isPermissaoTabagismo = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "testerapido" ){
				$scope.isPermissaoTesteRapido = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "sobreaviso" ){
				$scope.isPermissaoSobreavio = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "livropreto" ){
				$scope.isPermissaoLivroPreto = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "capacitacao" ){
				$scope.isPermissaoCapacitacao = true;
		    	$scope.isSistemaAntigo = true;
			}
			if( role.modulo.nome == "supervisao" ){
				$scope.isPermissaoSupervisao = true;
				
				if(role.descricao === "tecagricola") {
					$scope.isPermissaoTecAgricola = true;
				}
				
			}
			
		}
		
		$scope.roles = $scope.usuarioOnline.roles;
	}
*/
    $scope.redirecionaVigilantosAntigo = function(){
    	api.usuario.redirectOld();
    }
    
    $scope.OpenModalAlertasSialie = function(){		
		var modalInstance = $uibModal.open({ 
		templateUrl: "modulos/sialie/alertas.lista.html", 
		controller: "SialieAlertasListaController",
		
		backdrop: 'static', 
		keyboard: false
	
	    });
	};
	
 /*   $scope.openAlertas = function(){
    	api.sialieAlertas.getAlertasByUsuario($scope.usuarioOnline.id).then(function(response){
    		
    		for (i = 0; i < $scope.usuarioOnline.roles.length; i++) { 
    			var role = $scope.usuarioOnline.roles[i];
    			var showAlertas = false;
    	
    			if( role.modulo.nome == "imunobiologicos" ){
    				if(role.descricao == "municipio"){
    					showAlertas = true;
    				}
    				else if(role.descricao == "regional"){
    					showAlertas = true;
    				}
    			}
    		}
    		
    		if(response.data.length > 0 && showAlertas){
    			
    			$scope.OpenModalAlertasSialie();
    		}
    	});
    	
    }
*/    
    $scope.atualizaLatLongFocosDengue = function(){
    	toastr.warning("Atualização sendo efetuada em background");
    	
    	api.dengue.updateGeodata().then( function( response ){
    		toastr.success("Atualização lat/lon focos efetuada");	
    	});
    }
    
    $scope.atualizaLatLongCasosDengue = function(){
    	toastr.warning("Atualização sendo efetuada em background");
    	
    	api.dengueCaso.updateGeodata().then( function( response ){
    		toastr.success("Atualização lat/lon casos efetuada");	
    	});
    }
    
    $scope.openModalDengueDtAtualizacao = function(){
    	
    	var key = "dengue_ultima_atualizacao";
    	
    	api.sistema.getParametro( key ).then( function( response ){
    		var value = response.data;
    		
    		var dia =  value.slice(0, 2);
    		var mes = value.slice(3, 5);
    		var ano = value.slice(6, 10);
    		
    		var dtAtualizacao = new Date();
    		dtAtualizacao.setDate( dia );
    		dtAtualizacao.setMonth( (mes - 1) );
    		dtAtualizacao.setFullYear( ano );
    		
        	var modalInstance = $uibModal.open({ 
        		templateUrl: "modulos/dengue/administrativo/cadastra.data.atualizacao.html", 
        		controller: "CadastroDtAtualizcaoDengueController",

        		backdrop: 'static', 
        		keyboard: false,
        		
        		resolve: {
        			dataAtualizacao: function () {
    		    		return dtAtualizacao;
        			}
        		}
        	});
        	
        	modalInstance.result.then(function (date){
        		var strDate = date.toLocaleDateString();
        		
        		api.sistema.setParametro(key , strDate ).then( function( response ){
        			toastr.success( "Data atualizada", "Aviso" );
        		});
    		});
    		
    	});
    	
    }
    
   
    
 });
