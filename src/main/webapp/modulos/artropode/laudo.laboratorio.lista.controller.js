angular
    .module("vigilantos")
    .controller(
        'LaudoLaboratorioListaController',
        function($scope, api, $uibModal, toastr, $routeParams, $location) {

            $scope.isOpen = false;
            $scope.filtro = {};
            $scope.paginaAtual = 1;
            $scope.limite = 10;

            $scope.isPermissaoAdmin = false;
            $scope.isPermissaoRegional = false;
            $scope.isPermissaoMunicipio = false;
            
            //Focus ficha
            angular.element('#btfiltro').on('click', function() {
            	angular.element("[name='nficha']").focus();
            });

            api.usuario.getUserOnline().then(function(response) {
                $scope.usuarioOnline = response.data;
                $scope.roles = $scope.usuarioOnline.roles;

                $scope.getPermissoes();
            });

            $scope.getPermissoes = function() {
                var possuiPermissao = false;

                for (i = 0; i < $scope.roles.length; i++) {
                    var role = $scope.roles[i];
                    possuiPermissao = false;

                    if (role.modulo.nome == "artropodes") {
                        possuiPermissao = true;

                        $scope.getRegionais();

                        if (role.descricao == "admin") {
                            $scope.isPermissaoAdmin = true;
                            $scope.getMunicipiosSC();

                            break;
                        } else if (role.descricao == "regional") {
                            $scope.isPermissaoRegional = true;
                            $scope
                                .getRegionalByMunicipio($scope.usuarioOnline.municipio.id);

                            break;
                        } else if (role.descricao == "municipio") {
                            $scope.isPermissaoMunicipio = true;

                            $scope
                                .getRegionalByMunicipio($scope.usuarioOnline.municipio.id);
                            $scope.municipio = $scope.usuarioOnline.municipio;

                            break;
                        } else {
                            possuiPermissao = false;
                        }
                    }
                }

                if (!possuiPermissao) {
                    $location.path("/error403");
                }

                $scope.pesquisar();
            }

            $scope.pesquisar = function() {
            	
            	api.usuario.getUserOnline().then(function(response) {
            		 
	        		$scope.usuarioLogado = response.data;
	            		 
	        		$scope.filtro.idFicha = $scope.idFicha;	 
	                $scope.filtro.limite = 10;
	                $scope.filtro.pagina = $scope.paginaAtual;
	                $scope.filtro.dtInicio = $scope.dtInicial;
	                $scope.filtro.dtFim = $scope.dtFinal;
	                
	                $scope.filtro.idMunicipioUsuario = $scope.usuarioLogado.municipio.id;
	                $scope.filtro.idUsuario = $scope.usuarioLogado.id;
	                
	                for (i = 0; i < response.data.roles.length; i++) {
	        			var role = response.data.roles[i];
	        				if( role.modulo.nome == "artropodes" ){
	        					$scope.filtro.tipoPermissaoUsuario = role.descricao;
	        					//$scope.filtro.tipoPermissao = 'asdf';
	        					break;
	        				}
	        		}
	                
	                api.artropodes.laudo.getLaudosDto($scope.filtro).then(
	                    function(response) {
	                        $scope.lista = response.data;
	                        $scope.total = response.headers("x-total");
                    });
                
            	   });
            }

            $scope.openMenu = function() {
                $scope.isOpen = true;
            }

            $scope.getByRegional = function() {
                if ($scope.regional) {
                    $scope.filtro.idRegional = $scope.regional.id;
                    $scope.municipios = $scope.regional.municipios;
                }
            }

            $scope.getMunicipiosSC = function() {
                api.municipio.getByUF('SC').then(function(response) {
                    $scope.municipios = response.data;
                });
            }

            $scope.getRegionais = function() {
                api.regional.getAll().then(function(response) {
                    $scope.regionais = response.data;
                });
            }

            $scope.getRegionalByMunicipio = function(idMunicipio) {
                api.regional.getByMunicipio(idMunicipio).then(
                    function(response) {
                        $scope.regional = response.data;
                        $scope.getByRegional();
                    });
            }

            $scope.confirmarRemover = function(laudo) {
                api.usuario
                    .getUserOnline()
                    .then(
                        function(response) {
                            $scope.possuiPermissaoAdmin = false;

                            for (i = 0; i < response.data.roles.length; i++) {
                                var role = response.data.roles[i];
                                if ( role.modulo.nome == "artropodes" && role.admin) {
                                    $scope.possuiPermissaoAdmin = true;
                                    break;
                                }
                            }

                            if ($scope.possuiPermissaoAdmin) {
                                var mensagem = "Confirma a exclusão do laudo?";

                                var modalInstance = $uibModal
                                    .open({
                                        templateUrl: "modulos/template/confirma.exclusao.html",
                                        controller: "ModalConfirmaExclusaoController",
                                        resolve: {
                                            mensagem: function() {
                                                return mensagem;
                                            },
                                            id: function() {
                                                return laudo.id;
                                            }
                                        }
                                    });

                                modalInstance.result
                                    .then(function(id) {
                                        $scope.remover(id);
                                    });
                            } else {
                                toastr.error('Usuário sem permissão para Excluir');
                            }

                        });
            }

            $scope.remover = function(id) {
                api.artropodes.laudo
                    .excluir(id)
                    .then(
                        function(response) {
                            $scope.pesquisar();
                            toastr.success('Laudo removido com sucesso');
                        });
            }

            $scope.pesquisar();
            
		   $scope.editarLaudo = function(laudo) {
			   $location.path( "/artropodes/laudo/"+laudo.numeroControle+"/"+laudo.id );
		   };
		   
		   $scope.visualizarLaudo = function(laudo) {
			   $location.path( "/artropodes/laudo/visualizacao/"+laudo.numeroControle+"/"+laudo.id );
		   }
		
});