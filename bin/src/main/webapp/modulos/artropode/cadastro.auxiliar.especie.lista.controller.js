//angular.module("vigilantos").controller('CadastroAuxiliarEspecieListaController',
//        function($scope, api, $uibModal, toastr, $routeParams, $location) {
//
//            $scope.filtro = {};
//            $scope.paginaAtual = 1;
//            $scope.limite = 10;
//            $scope.isOpen = false;
//
//            $scope.openMenu = function() {
//            		$("[name='nficha']").focus();
//            		$scope.isOpen = true;
//            }
//
//        	api.usuario.getUserOnline().then(function(response){
//        		$scope.usuarioOnline = response.data;
//        		$scope.roles = $scope.usuarioOnline.roles;
//        		
//        	});
//
//        		$scope.cadastrarEspecie = function(){
//        			$scope.especie = {};
//        			var modalInstance = $uibModal.open({ 
//        				templateUrl: "modulos/artropode/cadastro.auxiliar.especie.cadastro.html", 
//        				controller: "CadastroAuxiliarEspecieCadastroController",
//        				
//        				backdrop: 'static', 
//        				keyboard: false,
//        				
//        				resolve: {
//        					amostra: function(){
//        						return null;
//        					},
//        					especie: $scope.especie
//        				}
//        			});
//        			
//        			modalInstance.result.then(function (especie){
//        				$scope.pesquisar();
//        			});
//        			
//        		}
//        		
//        		$scope.editarEspecie = function(especie){
//        			$scope.especie = especie;
//        				var modalInstance = $uibModal.open({ 
//        					templateUrl: "modulos/artropode/cadastro.auxiliar.especie.cadastro.html", 
//        					controller: "CadastroAuxiliarEspecieCadastroController",
//        					
//        					backdrop: 'static', 
//        					keyboard: false,
//        					
//        					resolve: {
//        						amostra: function(){
//        							return null;
//        						}, especie : especie
//        					}
//        				});
//        		}
//        	
//        	
//        	
//        	
//
//            $scope.pesquisar = function() {
//
//            	api.usuario.getUserOnline().then(function(response) {
//            		 
//	            	$scope.usuarioLogado = response.data;
//	            	
//	            	$scope.filtro.id = $scope.especieCodigo;
//	                $scope.filtro.limite = 10;
//	                $scope.filtro.pagina = $scope.paginaAtual;
//	                
//	                $scope.filtro.nome = $scope.especieNome;
//	                $scope.filtro.idGenero = $scope.especieGenero;
//	                $scope.filtro.idFamilia = $scope.especieFamilia;
//	                
////	                if ($scope.especieGenero){
////	                	$scope.filtro.genero = $scope.especieGenero;	
////	                }else{
////	                	$scope.filtro.genero.id = 0;
////	                }
////	                
////	                if ($scope.especieFamilia){
////	                	$scope.filtro.familia = $scope.especieFamilia;	
////	                }else{
////	                	$scope.filtro.familia.id = 0;
////	                }
//                
//	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
//	                
//	
//	                
//	                for (i = 0; i < response.data.roles.length; i++) {
//	        			var role = response.data.roles[i];
//	        				if( role.modulo.nome == "artropodes" ){
//	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
//	        					//$scope.filtro.tipoPermissao = 'asdf';
//	        					break;
//	        				}
//	        		}
//	                
//	                api.artropodes.cadastroAuxiliarEspecie.getEspeciesDto($scope.filtro)
//	                    .then(function(response) {
//	                    	
//	                        $scope.lista = response.data;
//	                        $scope.total = response.headers("x-total");
//	                    });
//                
//            	 });
//            }
//
//            $scope.confirmarRemover = function(especie) {
//                api.usuario.getUserOnline().then(function(response) {
//                	
//                	$scope.usuarioLogado = response.data;
//                	
//                    $scope.possuiPermissaoAdmin = false;
//                	
//            		for (i = 0; i < response.data.roles.length; i++) {
//            			var role = response.data.roles[i];
//            			if( role.modulo.nome == "artropodes" ){
//            					$scope.possuiPermissaoAdmin = true;
//            					break;
//            				}
//            		}
//            		
//           		
//                    
//                    if ($scope.possuiPermissaoAdmin) {
//                        var mensagem = "Confirma a exclusão da espécie?";
//                        var modalInstance = $uibModal.open({
//                            templateUrl: "modulos/template/confirma.exclusao.html",
//                            controller: "ModalConfirmaExclusaoController",
//                            resolve: {
//                                mensagem: function() {
//                                    return mensagem;
//                                },
//                                id: function() {
//                                    return especie.id;
//                                }
//                            }
//                        });
//
//                        modalInstance.result
//                            .then(function(especie) {
//                                $scope.remover(especie);
//                            });
//                    } else {
//                        toastr.error('Usuário sem permissão para Excluir');
//                    }
//
//                });
//            }
//
//            $scope.remover = function(id) {
//                api.artropodes.animalEspecie.excluir(id)
//                    .then(
//                        function(response) {
//                            $scope.pesquisar();
//                            toastr
//                                .success('Espécie removida com sucesso');
//                        });
//
//            }
//            
//        	$scope.mensagemAlerta = function( mensagem ){
//        		var modalInstance = $uibModal.open({ 
//        			templateUrl: "modulos/template/dialog.mensagem.html", 
//        			controller: "ModalMensagemDialogController",
//        			
//        			resolve: { 
//        				mensagem: function () {
//        		    		return mensagem;
//        		        },
//        		        titulo: function (){
//        		        	return "Aviso";
//        		        }
//        		      }
//        		});
//        	}
//
//           
//            
//            api.artropodes.animalFamilia.getAll().then( function(response){
//        		$scope.familias = response.data;
//        	});
//        	
//        	api.artropodes.animalGenero.getAll().then( function(response){
//        		$scope.generos = response.data;
//        	});
//        	
//        	 $scope.pesquisar();
//            
//
//        });