angular.module("vigilantos").controller(
		"EpizootiaMapaController",
		function($scope, api, utils, NgMap, $location) {

			$scope.mapa = {};
			$scope.isEdicao = false;
			$scope.epizootiaFiltro = {};
			$scope.isOpen = false;
			

			$scope.analises = [];
			$scope.ignorados = [];
			$scope.descartados = [];
			$scope.confirmados = [];
			
			$scope.fichasInfo = [];
			$scope.fichaMarcador = [];
			$scope.marcador = [];

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
						var ficha = $scope.fichas[i];
						if (ficha.classificacaoFA = clsEmAnalise) {
							var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
							$scope.fichaMarcador.push( posicao );
							$scope.marcador = "assets/imagens/icone_macaco_amarelo.png";
							$scope.fichasInfo.push( ficha );
							
							
						} else if (ficha.classificacaoFA = clsIgnorado) {
							var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
							$scope.fichaMarcador.push( posicao );
							$scope.marcador = "assets/imagens/assets/imagens/icone_macaco_verde.png";
							$scope.fichasInfo.push( ficha );
							
							
						} else if (ficha.classificacaoFA = clsDescardado) {
							var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
							$scope.fichaMarcador.push( posicao );
							$scope.marcador = "assets/imagens/assets/imagens/icone_macaco_verde.png";
							$scope.fichasInfo.push( ficha );

						} else if (ficha.classificacaoFA = clsConfirmado) {
							var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
							$scope.fichaMarcador.push( posicao );
							$scope.marcador = "assets/imagens/assets/imagens/assets/imagens/icone_macaco_vermelho.png";
							$scope.fichasInfo.push(ficha);
						}
					}
				});

				$scope.abrirInfo = function(event, $index) {
					$scope.fichasInfo = $scope.fichasInfo[$index];
					$scope.googlemaps.showInfoWindow("epizootia-info", this);
				}
/*
				
				// Amarelo
				api.epizootia.ficha.getClassificao( clsEmAnalise ).then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var ficha = $scope.fichas[i];
						var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
						$scope.analises.push( posicao );
						$scope.fichasInfo.push(ficha);
					}
				});
				
				// Verde
				api.epizootia.ficha.getClassificao( clsIgnorado ).then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var ficha = $scope.fichas[i];
						var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
						$scope.ignorados.push( posicao );
						$scope.fichasInfo.push(ficha);
					}
				});
				
				// Verde
				api.epizootia.ficha.getClassificao( clsDescardado ).then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var ficha = $scope.fichas[i];
						var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
						$scope.descartados.push( posicao );
						$scope.fichasInfo.push(ficha);
					}
				});
				
				// Vermelho
				api.epizootia.ficha.getClassificao( clsConfirmado ).then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var ficha = $scope.fichas[i];
						var posicao = {posicao: [ficha.localidade.longitude, ficha.localidade.latitude]};
						$scope.confirmados.push( posicao );
						$scope.fichasInfo.push(ficha);
					}
				});

*/				
				
				$scope.mapa.posicao = [ -27.4658, -50.7504 ];
				$scope.mapa.zoom = 8;

				$scope.epizootiaFiltro.dtColetaFim = new Date();
				$scope.epizootiaFiltro.dtColetaInicio = utils.dateUtils
						.getInicioAno();

			}
			
			$scope.init();

		});