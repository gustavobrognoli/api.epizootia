angular.module("vigilantos").controller('CadastroAuxiliarEvolucaoListaController',
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



        		$scope.cadastrarEvolucao = function(){
        			$scope.evolucao = {};
        			var modalInstance = $uibModal.open({ 
        				templateUrl: "modulos/artropode/cadastro.auxiliar.evolucao.cadastro.html", 
        				controller: "CadastroAuxiliarEvolucaoCadastroController",
        				
        				backdrop: 'static', 
        				keyboard: false,
        				
        				resolve: {
        					amostra: function(){
        						return null;
        					},
        					evolucao: $scope.evolucao
        				}
        			});
        			
        			modalInstance.result.then(function (evolucao){
        				$scope.pesquisar();
        			});
        		}
        		
        		$scope.editarEvolucao = function(evolucao){
        			$scope.evolucao = evolucao;
        				var modalInstance = $uibModal.open({ 
        					templateUrl: "modulos/artropode/cadastro.auxiliar.evolucao.cadastro.html", 
        					controller: "CadastroAuxiliarEvolucaoCadastroController",
        					
        					backdrop: 'static', 
        					keyboard: false,
        					
        					resolve: {
        						amostra: function(){
        							return null;
        						}, evolucao : evolucao
        					}
        				});
        		}
        	
        	
        	
        	

            $scope.pesquisar = function() {

            	api.usuario.getUserOnline().then(function(response) {
            		 
	            	$scope.usuarioLogado = response.data;
	            	
	            	$scope.filtro.id = $scope.evolucaoCodigo;
	                $scope.filtro.limite = 10;
	                $scope.filtro.pagina = $scope.paginaAtual;
	                
	                $scope.filtro.nome = $scope.evolucaoNome;
                
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
	                
	
	                
	                
	                
	
	                api.artropodes.cadastroAuxiliarEvolucao.getEvolucoesDto($scope.filtro)
	                    .then(function(response) {
	                    	
	                        $scope.lista = response.data;
	                        
	                        $scope.total = response.headers("x-total");
	                    });
                
            	 });
            }

            $scope.confirmarRemover = function(evolucao) {
                api.usuario.getUserOnline().then(function(response) {
                	
                	$scope.usuarioLogado = response.data;
                	
//                	$scope.usuarioLogado = response.data.id;
//                    $scope.autor = ficha.usuario;
                    $scope.possuiPermissaoAdmin = false;
                	
            		for (i = 0; i < response.data.roles.length; i++) {
            			var role = response.data.roles[i];
            				if( role.modulo.nome == "artropodes" && role.admin ){// valida permissão admin no modulo 30 (artropodes)
            					$scope.possuiPermissaoAdmin = true;
            					break;
            				}
            		}
            		
           		
                    
                    if ($scope.possuiPermissaoAdmin) {
                        var mensagem = "Confirma a exclusão da evolucao?";
                        var modalInstance = $uibModal.open({
                            templateUrl: "modulos/template/confirma.exclusao.html",
                            controller: "ModalConfirmaExclusaoController",
                            resolve: {
                                mensagem: function() {
                                    return mensagem;
                                },
                                id: function() {
                                    return evolucao.id;
                                }
                            }
                        });

                        modalInstance.result
                            .then(function(evolucao) {
                                $scope.remover(evolucao);
                            });
                    } else {
                        toastr.error('Usuário sem permissão para Excluir');
                    }

                });
            }

            $scope.remover = function(id) {
                api.artropodes.animalEvolucao.excluir(id)
                    .then(
                        function(response) {
                            $scope.pesquisar();
                            toastr
                                .success('Evolução removida com sucesso');
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