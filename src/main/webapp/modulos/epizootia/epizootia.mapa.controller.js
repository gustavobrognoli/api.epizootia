angular.module("vigilantos").controller("EpizootiaMapaController",
		function($scope, api, utils, NgMap, $location) {

			$scope.mapa = {};
			$scope.isEdicao = false;
			$scope.epizootiaFiltro = {};
			$scope.isOpen = false;

			$scope.fichasMapa = [];

			$scope.openMenu = function() {
				$scope.isOpen = true;
			}

			NgMap.getMap().then(function(map) {
				$scope.googlemaps = map;
			});

			$scope.init = function() {
				var clsEmAnalise = 1; 
				var clsIgnorado = 2; 
				var clsDescardado = 3; 
				var clsConfirmado = 4; 
				
				api.epizootia.ficha.getAll().then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var icon;
						var ficha = $scope.fichas[i];
						
						if (ficha.classificacaoFA.id == clsEmAnalise) 
							icon = "./assets/imagens/icone_macaco_amarelo.png";
						else if (ficha.classificacaoFA.id == clsIgnorado) 
							icon = "./assets/imagens/icone_macaco_verde.png";
						else if (ficha.classificacaoFA.id == clsDescardado)
							icon = "./assets/imagens/icone_macaco_verde.png";
						else if (ficha.classificacaoFA.id == clsConfirmado) 
							icon = "./assets/imagens/icone_macaco_vermelho.png";	
						
						ficha['posicao'] = [ficha.localidade.longitude, ficha.localidade.latitude];
						ficha['icon'] = icon;

						$scope.fichasMapa.push(ficha);
					}
				});

				$scope.abrirInfo = function(event, ficha) {
					$scope.selecionado = ficha;
					$scope.googlemaps.showInfoWindow("epizootia-info", this);
				}

				$scope.mapa.posicao = [ -27.4658, -50.7504 ];
				$scope.mapa.zoom = 8;

				$scope.epizootiaFiltro.dtColetaFim = new Date();
				$scope.epizootiaFiltro.dtColetaInicio = utils.dateUtils
						.getInicioAno();

			}
			
			$scope.init();

		});