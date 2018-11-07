//angular.module("vigilantos").controller('CadastroAuxiliarTipoAnimalListaController',
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
//        		$scope.cadastrarTipoAnimal = function(){
//        			$scope.tipoAnimal = {};
//        			var modalInstance = $uibModal.open({ 
//        				templateUrl: "modulos/artropode/cadastro.auxiliar.tipoAnimal.cadastro.html", 
//        				controller: "CadastroAuxiliarTipoAnimalCadastroController",
//        				
//        				backdrop: 'static', 
//        				keyboard: false,
//        				
//        				resolve: {
//        					amostra: function(){
//        						return null;
//        					},
//        					tipoAnimal: $scope.tipoAnimal
//        				}
//        			});
//        			
//        			modalInstance.result.then(function (tipoAnimal){
//        				$scope.pesquisar();
//        			});
//        		}
//        		
//        		$scope.editarTipoAnimal = function(tipoAnimal){
//        			$scope.tipoAnimal = tipoAnimal;
//        				var modalInstance = $uibModal.open({ 
//        					templateUrl: "modulos/artropode/cadastro.auxiliar.tipoAnimal.cadastro.html", 
//        					controller: "CadastroAuxiliarTipoAnimalCadastroController",
//        					
//        					backdrop: 'static', 
//        					keyboard: false,
//        					
//        					resolve: {
//        						amostra: function(){
//        							return null;
//        						}, tipoAnimal : tipoAnimal
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
//	            	$scope.filtro.id = $scope.tipoAnimalCodigo;
//	                $scope.filtro.limite = 10;
//	                $scope.filtro.pagina = $scope.paginaAtual;
//	                
//	                $scope.filtro.nome = $scope.tipoAnimalNome;
//	                $scope.filtro.idMensagem = $scope.tipoAnimalMensagem;
//	                $scope.filtro.indefinido = $scope.tipoAnimalIndefinido;
//	                
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
//	        				if( role.modulo.nome == "artropodes" ){
//	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
//	        					//$scope.filtro.tipoPermissao = 'asdf';
//	        					break;
//	        				}
//	        		}
//	                
//	                api.artropodes.cadastroAuxiliarTipoAnimal.getTipoAnimaisDto($scope.filtro)
//	                    .then(function(response) {
//	                        $scope.lista = response.data;
//	                        $scope.total = response.headers("x-total");
//	                    });
//                
//            	 });
//            }
//
//            $scope.confirmarRemover = function(tipoAnimal) {
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
//            				if( role.modulo.nome == "artropodes" && role.admin ){
//            					$scope.possuiPermissaoAdmin = true;
//            					break;
//            				}
//            		}
//            		
//           		
//                    
//                    if ($scope.possuiPermissaoAdmin) {
//                        var mensagem = "Confirma a exclusão do Tipo de Animal?";
//                        var modalInstance = $uibModal.open({
//                            templateUrl: "modulos/template/confirma.exclusao.html",
//                            controller: "ModalConfirmaExclusaoController",
//                            resolve: {
//                                mensagem: function() {
//                                    return mensagem;
//                                },
//                                id: function() {
//                                    return tipoAnimal.id;
//                                }
//                            }
//                        });
//
//                        modalInstance.result
//                            .then(function(tipoAnimal) {
//                                $scope.remover(tipoAnimal);
//                            });
//                    } else {
//                        toastr.error('Usuário sem permissão para Excluir');
//                    }
//
//                });
//            }
//
//            $scope.remover = function(id) {
//                api.artropodes.tipoAnimal.excluir(id)
//                    .then(
//                        function(response) {
//                            $scope.pesquisar();
//                            toastr
//                                .success('Tipo de Animal removido com sucesso');
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
//            $scope.pesquisar();
//            
//            api.artropodes.cadastroAuxiliarTipoAnimal.getMensagens().then( function(response){
//        		$scope.mensagens = response.data;
//        	});
//
//        });