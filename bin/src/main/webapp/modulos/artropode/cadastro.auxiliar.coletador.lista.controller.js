//angular.module("vigilantos").controller('CadastroAuxiliarColetadorListaController',
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
///*        	api.usuario.getUserOnline().then(function(response){
//        		$scope.usuarioOnline = response.data;
//        		$scope.roles = $scope.usuarioOnline.roles;
//        		
//        	});*/
//
//       
//        		$scope.cadastrarColetador = function(){
//        			$scope.coletador = {};
//        			var modalInstance = $uibModal.open({ 
//        				templateUrl: "modulos/artropode/coletador.cadastro.html", 
//        				controller: "ColetadorCadastroController",
//        				
//        				backdrop: 'static', 
//        				keyboard: false,
//        				
//        				resolve: {
//        					amostra: function(){
//        						return null;
//        					}, coletador: $scope.coletador
//        				}
//        			});
//        			
//        			modalInstance.result.then(function (coletador){
//        				$scope.pesquisar();
//        			});
//        			
//        		}
//        		
//        		$scope.editarColetador = function(coletador){
//        			$scope.coletador = coletador;
//        			if(!$scope.coletador || !$scope.coletador.id || $scope.coletador.id == "") {
//        				var mensagem = "Selecione um coletador para edição.";
//        				var modalInstance = $uibModal.open({ 
//        					templateUrl: "modulos/template/dialog.mensagem.html", 
//        					controller: "ModalMensagemDialogController",
//        					resolve: { 
//        				    	mensagem: function () {
//        				    		return mensagem;
//        				        },
//        				        titulo: function () {
//        				    		return null;
//        				        }
//        				      }
//        				});
//        			} else {
//        				var modalInstance = $uibModal.open({ 
//        					templateUrl: "modulos/artropode/coletador.cadastro.html", 
//        					controller: "ColetadorCadastroController",
//        					
//        					backdrop: 'static', 
//        					keyboard: false,
//        					
//        					resolve: {
//        						amostra: function(){
//        							return null;
//        						}, coletador: coletador
//        					}
//        				});
//        			}
//        		}
//        	
//        	
//        	
//        	
//
// /*           $scope.pesquisar = function() {
//
//            	api.usuario.getUserOnline().then(function(response) {
//            		 
//	            	$scope.usuarioLogado = response.data;
//	            	
//	            	$scope.filtro.id = $scope.coletadorCodigo;
//	                $scope.filtro.limite = 10;
//	                $scope.filtro.pagina = $scope.paginaAtual;
//	                
//	                $scope.filtro.nome = $scope.coletadorNome;
//	                $scope.filtro.email = $scope.coletadorEmail;
//	                $scope.filtro.telefone = $scope.coletadorTelefone;
//	                
//	                
//	                
////	                $scope.filtro.idMunicipioUsuario = $scope.usuarioLogado.municipio.id;
//                
//	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
//	                
//	
//	                
//	                for (i = 0; i < response.data.roles.length; i++) {
//	        			var role = response.data.roles[i];
//	        				if( role.modulo.nome == 'artropodes'){// valida permissão admin no modulo (artropodes)
//	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
//	        					break;
//	        				}
//	        		}
//	                
//	
//	                
//	                
//	                
//	
//	                api.artropodes.cadastroAuxiliarColetador.getColetadoresDto($scope.filtro)
//	                    .then(function(response) {
//	                        $scope.lista = response.data;
//	                        $scope.total = response.headers("x-total");
//	                    });
//                
//            	 });
//            }
//
//            $scope.confirmarRemover = function(coletador) {
//                api.usuario.getUserOnline().then(function(response) {
//                	
//                	$scope.usuarioLogado = response.data;
//                	
////                	$scope.usuarioLogado = response.data.id;
////                    $scope.autor = ficha.usuario;
//                    $scope.possuiPermissaoAdmin = false;
//                	
//            		for (i = 0; i < response.data.roles.length; i++) {
//            			var role = response.data.roles[i];
//            				if( role.modulo.nome == "artropodes" ){
//            					$scope.possuiPermissaoAdmin = true;
//            					break;
//            				}
//            		}
//            		
//           		
//                    
//                    if ($scope.possuiPermissaoAdmin) {
//                        var mensagem = "Confirma a exclusão do coletador?";
//                        var modalInstance = $uibModal.open({
//                            templateUrl: "modulos/template/confirma.exclusao.html",
//                            controller: "ModalConfirmaExclusaoController",
//                            resolve: {
//                                mensagem: function() {
//                                    return mensagem;
//                                },
//                                id: function() {
//                                    return coletador.id;
//                                }
//                            }
//                        });
//
//                        modalInstance.result
//                            .then(function(coletador) {
//                                $scope.remover(coletador);
//                            });
//                    } else {
//                        toastr.error('Usuário sem permissão para Excluir');
//                    }
//
//                });
//            }
//
//            $scope.remover = function(id) {
//                api.artropodes.coletador.excluir(id)
//                    .then(
//                        function(response) {
//                            $scope.pesquisar();
//                            toastr
//                                .success('Coletador removido com sucesso');
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
//        	}*/
//
//            $scope.pesquisar();
//
//        });