angular.module("vigilantos").controller('CadastroAuxiliarGeneroListaController',
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



        		$scope.cadastrarGenero = function(){
        			$scope.genero = {};
        			var modalInstance = $uibModal.open({ 
        				templateUrl: "modulos/artropode/cadastro.auxiliar.genero.cadastro.html", 
        				controller: "CadastroAuxiliarGeneroCadastroController",
        				
        				backdrop: 'static', 
        				keyboard: false,
        				
        				resolve: {
        					amostra: function(){
        						return null;
        					},
        					genero: $scope.genero
        				}
        			});
        			
        			modalInstance.result.then(function (genero){
        				$scope.pesquisar();
        			});
        		}
        		
        		$scope.editarGenero = function(genero){
        			$scope.genero = genero;
        				var modalInstance = $uibModal.open({ 
        					templateUrl: "modulos/artropode/cadastro.auxiliar.genero.cadastro.html", 
        					controller: "CadastroAuxiliarGeneroCadastroController",
        					
        					backdrop: 'static', 
        					keyboard: false,
        					
        					resolve: {
        						amostra: function(){
        							return null;
        						}, genero : genero
        					}
        				});
        				
            			modalInstance.result.then(function (genero){
            				$scope.pesquisar();
            			});
        		}
        	
        	
        	
        	

            $scope.pesquisar = function() {

            	api.usuario.getUserOnline().then(function(response) {
            		 
	            	$scope.usuarioLogado = response.data;
	            	
	            	$scope.filtro.id = $scope.generoCodigo;
	                $scope.filtro.limite = 10;
	                $scope.filtro.pagina = $scope.paginaAtual;
	                
	                $scope.filtro.nome = $scope.generoNome;
	                $scope.filtro.idFamilia = $scope.generoFamilia;
                
	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
	                
	
	                
	                for (i = 0; i < response.data.roles.length; i++) {
	        			var role = response.data.roles[i];
	        				if( role.modulo.nome == "artropodes" ){
	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
	        					//$scope.filtro.tipoPermissao = 'asdf';
	        					break;
	        				}
	        		}
	                
	
	                
	                
	                
	
	                api.artropodes.cadastroAuxiliarGenero.getGenerosDto($scope.filtro)
	                    .then(function(response) {
	                    	
	                        $scope.lista = response.data;
	                        
	                        $scope.total = response.headers("x-total");
	                    });
                
            	 });
            }

            $scope.confirmarRemover = function(genero) {
                api.usuario.getUserOnline().then(function(response) {
                	
                	$scope.usuarioLogado = response.data;
                	
                    $scope.possuiPermissaoAdmin = false;
                	
            		for (i = 0; i < response.data.roles.length; i++) {
            			var role = response.data.roles[i];
            				if( role.modulo.nome == "artropodes" && role.admin ){
            					$scope.possuiPermissaoAdmin = true;
            					break;
            				}
            		}
            		
           		
                    
                    if ($scope.possuiPermissaoAdmin) {
                        var mensagem = "Confirma a exclusão do gênero?";
                        var modalInstance = $uibModal.open({
                            templateUrl: "modulos/template/confirma.exclusao.html",
                            controller: "ModalConfirmaExclusaoController",
                            resolve: {
                                mensagem: function() {
                                    return mensagem;
                                },
                                id: function() {
                                    return genero.id;
                                }
                            }
                        });

                        modalInstance.result
                            .then(function(genero) {
                                $scope.remover(genero);
                            });
                    } else {
                        toastr.error('Usuário sem permissão para Excluir');
                    }

                });
            }

            $scope.remover = function(id) {
                api.artropodes.animalGenero.excluir(id)
                    .then(
                        function(response) {
                            $scope.pesquisar();
                            toastr
                                .success('Gênero removido com sucesso');
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
            
            api.artropodes.animalFamilia.getAll().then( function(response){
        		$scope.familias = response.data;
        	});
        	

        });