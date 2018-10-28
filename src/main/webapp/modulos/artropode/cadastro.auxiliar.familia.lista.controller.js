angular.module("vigilantos").controller('CadastroAuxiliarFamiliaListaController',
        function($scope, api, $uibModal, toastr, $routeParams, $location) {

            $scope.filtro = {};
            $scope.paginaAtual = 1;
            $scope.limite = 10;
            $scope.isOpen = false;

            $scope.openMenu = function() {
            		$("[name='nficha']").focus();
            		$scope.isOpen = true;
            }

        	api.usuario.getUserOnline().then(function(response){
        		$scope.usuarioOnline = response.data;
        		$scope.roles = $scope.usuarioOnline.roles;
        		
        	});

       
//
//        	api.artropodes.coletadores.getAll().then(function( response ) {
//        			$scope.coletadores = response.data;
//        		});



        		$scope.cadastrarFamilia = function(){
        			$scope.familia = {};
        			var modalInstance = $uibModal.open({ 
        				templateUrl: "modulos/artropode/cadastro.auxiliar.familia.cadastro.html", 
        				controller: "CadastroAuxiliarFamiliaCadastroController",
        				
        				backdrop: 'static', 
        				keyboard: false,
        				
        				resolve: {
        					amostra: function(){
        						return null;
        					},
        					familia: $scope.familia
        				}
        			});
        			
        			modalInstance.result.then(function (familia){
        				$scope.pesquisar();
        			});
        		}
        		
        		$scope.editarFamilia = function(familia){
        			$scope.familia = familia;
        				var modalInstance = $uibModal.open({ 
        					templateUrl: "modulos/artropode/cadastro.auxiliar.familia.cadastro.html", 
        					controller: "CadastroAuxiliarFamiliaCadastroController",
        					
        					backdrop: 'static', 
        					keyboard: false,
        					
        					resolve: {
        						amostra: function(){
        							return null;
        						}, familia : familia
        					}
        				});
        		}
        	
        	
        	
        	

            $scope.pesquisar = function() {

            	api.usuario.getUserOnline().then(function(response) {
            		 
	            	$scope.usuarioLogado = response.data;
	            	
	            	$scope.filtro.id = $scope.familiaCodigo;
	                $scope.filtro.limite = 10;
	                $scope.filtro.pagina = $scope.paginaAtual;
	                
	                $scope.filtro.nome = $scope.familiaNome;
                
//	                $scope.filtro.idMunicipioUsuario = $scope.usuarioLogado.municipio.id;
                
	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
	                
	
	                
	                for (i = 0; i < response.data.roles.length; i++) {
	        			var role = response.data.roles[i];
	        				if( role.modulo.nome == "artropodes" ){
	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
	        					//$scope.filtro.tipoPermissao = 'asdf';
	        					break;
	        				}
	        		}
	                
	
	                
	                
	                
	
	                api.artropodes.cadastroAuxiliarFamilia.getFamiliasDto($scope.filtro)
	                    .then(function(response) {
	                    	
	                        $scope.lista = response.data;
	                        
	                        $scope.total = response.headers("x-total");
	                    });
                
            	 });
            }

            $scope.confirmarRemover = function(familia) {
                api.usuario.getUserOnline().then(function(response) {
                	
                	$scope.usuarioLogado = response.data;
                	
//                	$scope.usuarioLogado = response.data.id;
//                    $scope.autor = ficha.usuario;
                    $scope.possuiPermissaoAdmin = false;
                	
            		for (i = 0; i < response.data.roles.length; i++) {
            			var role = response.data.roles[i];
            				if( role.modulo.nome == "artropodes" && role.admin ){
            					$scope.possuiPermissaoAdmin = true;
            					break;
            				}
            		}
            		
           		
                    
                    if ($scope.possuiPermissaoAdmin) {
                        var mensagem = "Confirma a exclusão da família?";
                        var modalInstance = $uibModal.open({
                            templateUrl: "modulos/template/confirma.exclusao.html",
                            controller: "ModalConfirmaExclusaoController",
                            resolve: {
                                mensagem: function() {
                                    return mensagem;
                                },
                                id: function() {
                                    return familia.id;
                                }
                            }
                        });

                        modalInstance.result
                            .then(function(familia) {
                                $scope.remover(familia);
                            });
                    } else {
                        toastr.error('Usuário sem permissão para Excluir');
                    }

                });
            }

            $scope.remover = function(id) {
                api.artropodes.animalFamilia.excluir(id)
                    .then(
                        function(response) {
                            $scope.pesquisar();
                            toastr
                                .success('Família removida com sucesso');
                        });

            }
            
        	$scope.mensagemAlerta = function( mensagem ){
        		var modalInstance = $uibModal.open({ 
        			templateUrl: "modulos/template/dialog.mensagem.html", 
        			controller: "ModalMensagemDialogController",
        			
        			resolve: { 
        				mensagem: function () {
        		    		return mensagem;
        		        },
        		        titulo: function (){
        		        	return "Aviso";
        		        }
        		      }
        		});
        	}

            $scope.pesquisar();

        });