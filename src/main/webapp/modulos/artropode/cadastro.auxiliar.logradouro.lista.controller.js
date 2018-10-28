angular.module("vigilantos").controller('CadastroAuxiliarLogradouroListaController',
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

        		$scope.cadastrarLogradouro = function(){
        			$scope.logradouro = {};
        			var modalInstance = $uibModal.open({ 
        				templateUrl: "modulos/artropode/cadastro.auxiliar.logradouro.cadastro.html", 
        				controller: "CadastroAuxiliarLogradouroCadastroController",
        				
        				backdrop: 'static', 
        				keyboard: false,
        				
        				resolve: {
        					amostra: function(){
        						return null;
        					},
        					logradouro: $scope.logradouro
        				}
        			});
        			
        			modalInstance.result.then(function (logradouro){
        				$scope.pesquisar();
        			});
        		}
        		
        		$scope.editarLogradouro = function(logradouro){
        			$scope.logradouro = logradouro;
        				var modalInstance = $uibModal.open({ 
        					templateUrl: "modulos/artropode/cadastro.auxiliar.logradouro.cadastro.html", 
        					controller: "CadastroAuxiliarLogradouroCadastroController",
        					
        					backdrop: 'static', 
        					keyboard: false,
        					
        					resolve: {
        						amostra: function(){
        							return null;
        						}, logradouro : logradouro
        					}
        				});
        		}
        	
        	
        	
        	

            $scope.pesquisar = function() {

            	api.usuario.getUserOnline().then(function(response) {
            		 
	            	$scope.usuarioLogado = response.data;
	            	
	            	$scope.filtro.id = $scope.logradouroCodigo;
	                $scope.filtro.limite = 10;
	                $scope.filtro.pagina = $scope.paginaAtual;
	                
	                $scope.filtro.nome = $scope.logradouroNome;
	                $scope.filtro.idLocalidade = $scope.logradouroLocalidade;
	                $scope.filtro.idMunicipio = $scope.logradouroMunicipio;

	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
	                
	
	                
	                for (i = 0; i < response.data.roles.length; i++) {
	        			var role = response.data.roles[i];
	        				if( role.modulo.nome == "artropodes" ){
	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
	        					//$scope.filtro.tipoPermissao = 'asdf';
	        					break;
	        				}
	        		}
	                
	                api.artropodes.cadastroAuxiliarLogradouro.getLogradourosDto($scope.filtro)
	                    .then(function(response) {
	                    	
	                        $scope.lista = response.data;
	                        
	                        $scope.total = response.headers("x-total");
	                    });
                
            	 });
            }

            $scope.confirmarRemover = function(logradouro) {
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
                        var mensagem = "Confirma a exclusão do logradouro?";
                        var modalInstance = $uibModal.open({
                            templateUrl: "modulos/template/confirma.exclusao.html",
                            controller: "ModalConfirmaExclusaoController",
                            resolve: {
                                mensagem: function() {
                                    return mensagem;
                                },
                                id: function() {
                                    return logradouro.id;
                                }
                            }
                        });

                        modalInstance.result
                            .then(function(logradouro) {
                                $scope.remover(logradouro);
                            });
                    } else {
                        toastr.error('Usuário sem permissão para Excluir');
                    }

                });
            }

            $scope.remover = function(id) {
                api.artropodes.cadastroAuxiliarLogradouro.excluir(id)
                    .then(
                        function(response) {
                            $scope.pesquisar();
                            toastr
                                .success('Logradouro removido com sucesso');
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
            
        	api.municipio.getAll().then( function(response){
        		$scope.municipios = response.data;
        	});

        	$scope.getLocalidadesByMunicipio = function(){
        		if ($scope.logradouroMunicipio !== undefined){
        			api.localidade.getByMunicipio( $scope.logradouroMunicipio ).then( function(response){
        				$scope.localidades = response.data;
        			});
        		}
        	}
        	
//        	$scope.getLocalidadesByMunicipio();
            

        });