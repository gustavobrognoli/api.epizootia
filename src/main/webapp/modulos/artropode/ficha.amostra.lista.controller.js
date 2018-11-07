//angular
//    .module("vigilantos")
//    .controller(
//        'FichaAmostraListaController',
//        function($scope, api, $uibModal, toastr, $routeParams, $location) {
//
//            $scope.filtro = {};
//            $scope.paginaAtual = 1;
//            $scope.limite = 10;
//            $scope.isOpen = false;
//
//            $scope.exportarFichaPdf = function(ficha){
//        			api.artropodes.fichaAmostra.geraFichaPfd( ficha );
//            }
//            
//            
//            
//            //Focus Ficha
//            angular.element('#btfiltro').on('click', function() {
//            	angular.element("[name='nficha']").focus();
//            });
//            
//            $scope.openMenu = function() {
//            		$("[name='nficha']").focus();
//            		$scope.isOpen = true;
//            }
//
//            api.municipio.getByUF("SC").then(function(response) {
//                $scope.municipios = response.data;
//            });
//            
//        	api.usuario.getUserOnline().then(function(response){
//        		$scope.usuarioOnline = response.data;
//        		$scope.roles = $scope.usuarioOnline.roles;
//        		
//        	});
//
//            // api.artropodes.fichaAmostra.getFichasDto().then(
//            // function(response){
//            // $scope.lista = response.data;
//            // });
//
//            $scope.pesquisar = function() {
//
//            	api.usuario.getUserOnline().then(function(response) {
//            		 
//	            	$scope.usuarioLogado = response.data;
//	            	
//	            	$scope.filtro.idFicha = $scope.idFicha;
//	                $scope.filtro.limite = 10;
//	                $scope.filtro.pagina = $scope.paginaAtual;
//	                $scope.filtro.dtInicio = $scope.dtInicial;
//	                $scope.filtro.dtFim = $scope.dtFinal;
//	                
//	                
//	                
//	                $scope.filtro.idMunicipioUsuario = $scope.usuarioLogado.municipio.id;
//	                
//	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
//	                
//	
//	                
//	                for (i = 0; i < response.data.roles.length; i++) {
//	        			var role = response.data.roles[i];
//	        			
//	        			if( role.modulo.nome == "artropodes" ){
//	        				$scope.filtro.tipoPermissaoUsuario = role.descricao;
//	        				break;
//	        			}
//	        		}
//	                
//	
//	                api.artropodes.fichaAmostra.getFichasDto($scope.filtro)
//	                    .then(function(response) {
//	                        $scope.lista = response.data;
//	                        $scope.total = response.headers("x-total");
//	                    });
//                
//            	 });
//            }
//            $scope.emitirLaudo = function(ficha) {
//            	
//        		for (i = 0; i < $scope.roles.length; i++) {
//        			var role = $scope.roles[i];
//        			if(role.modulo.nome === "artropodes") {
//        				if(!role.admin) {
//        					var msg = "Apenas perfil Adiministrador pode emitir laudos!"
//        						$scope.mensagemAlerta(msg);
//        					return;
//        				} else {
//        					
//        					api.artropodes.fichaAmostra.getAnimaisByFicha(ficha.id).then( function( response ){
//        						var lsAmostra = response.data; //Amostra de animais
//        						
//            	        		for (var i = 0; i < lsAmostra.length; i++) {
//            	        			var indefinido = lsAmostra[i].tipoAnimal.indefinido;
//            	        			
//            	        			if(indefinido === true) {
//            	        				$scope.mensagem = "Animal deve ser definido para realizar a emissão do laudo!";
//            	        				toastr.error($scope.mensagem);
//            	        				return;
//            	        			}
//            	        		}
//       
//            					$location.path( "/artropodes/laudo/"+ficha.id+"/"+ficha.idLaudo );
//        					});
//        	        		
//
//        				}
//        			}
//        		}
//        		
//            };
//            
//            $scope.confirmarRemover = function(ficha) {
//                api.usuario.getUserOnline().then(function(response) {
//                	 
//                	$scope.usuarioLogado = response.data;
//                	
//                	$scope.usuarioLogado = response.data.id;
//                    $scope.autor = ficha.usuario;
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
//            		// permite excluir se {'é autor', 'é admin do grupo', 'não tem laudo emitido'}
//                    if ($scope.autor == $scope.usuarioLogado || $scope.possuiPermissaoAdmin) {
//                        var mensagem = "Confirma a exclusão da ficha?";
//                        var modalInstance = $uibModal.open({
//                            templateUrl: "modulos/template/confirma.exclusao.html",
//                            controller: "ModalConfirmaExclusaoController",
//                            resolve: {
//                                mensagem: function() {
//                                    return mensagem;
//                                },
//                                id: function() {
//                                    return ficha.id;
//                                }
//                            }
//                        });
//
//                        modalInstance.result
//                            .then(function(id) {
//                                $scope.remover(id);
//                            });
//                    } else {
//                        toastr.error('Usuário sem permissão para Excluir');
//                    }
//
//                });
//            }
//
//            $scope.remover = function(id) {
//                api.artropodes.fichaAmostra
//                    .excluir(id)
//                    .then(
//                        function(response) {
//                            $scope.pesquisar();
//                            toastr
//                                .success('Ficha removida com sucesso');
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
//        });