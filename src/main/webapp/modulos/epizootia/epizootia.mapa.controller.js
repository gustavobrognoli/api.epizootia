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

//			$scope.registros = [ {
//				posicao : [ -27.593854, -48.577817 ]
//			}, {
//				posicao : [ -27.595385, -48.577163 ]
//			}, {
//				posicao : [ -27.596483, -48.580190 ]
//			}, {
//				posicao : [ -27.598738, -48.571219 ]
//			}, {
//				posicao : [ -27.597326, -48.570806 ]
//			} ];

//			// Amarelo
//			$scope.analises = [ {
//				posicao : [ -27.592392, -48.581440 ]
//			}, {
//				posicao : [ -27.586005, -48.586490 ]
//			} ];
//			
//			
//			// Verde
//			$scope.ignorados = [ {
//				posicao : [ -27.598738, -48.571219 ]
//			}, {
//				posicao : [ -27.597326, -48.570806 ]
//			} ];
//			
//			// Verde
//			$scope.descatados = [ {
//				posicao : [ -27.587899, -48.586456 ]
//			} ];

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
						
				// Amarelo
				api.epizootia.ficha.getClassificao( clsEmAnalise ).then(function( response ) {
					$scope.fichas = response.data.data;
					for (var i = 0; i < $scope.fichas.length; i++) {
						var ficha = $scope.fichas[i];
						var posicao = [ficha.localidade.longitude, ficha.localidade.latitude];
						$scope.analises.push( posicao );
					}
				});
				
//				// Verde
//				api.epizootia.ficha.getClassificao( clsIgnorado ).then(function( response ) {
//					$scope.fichas = response.data.data;
//					for (var i = 0; i < $scope.fichas.length; i++) {
//						var ficha = $scope.fichas[i];
//						var posicao = [ficha.localidade.longitude, ficha.localidade.latitude];
//						$scope.ignorados.push( posicao );
//					}
//				});
//				
//				// Verde
//				api.epizootia.ficha.getClassificao( clsDescardado ).then(function( response ) {
//					$scope.fichas = response.data.data;
//					for (var i = 0; i < $scope.fichas.length; i++) {
//						var ficha = $scope.fichas[i];
//						var posicao = [ficha.localidade.longitude, ficha.localidade.latitude];
//						$scope.descartados.push( posicao );
//					}
//				});
//				
//				// Vermelho
//				api.epizootia.ficha.getClassificao( clsConfirmado ).then(function( response ) {
//					$scope.fichas = response.data.data;
//					for (var i = 0; i < $scope.fichas.length; i++) {
//						var ficha = $scope.fichas[i];
//						var posicao = [ficha.localidade.longitude, ficha.localidade.latitude];
//						$scope.confirmados.push( posicao );
//					}
//				});
				
				
				$scope.mapa.posicao = [ -27.4658, -50.7504 ];
				$scope.mapa.zoom = 8;

				$scope.epizootiaFiltro.dtColetaFim = new Date();
				$scope.epizootiaFiltro.dtColetaInicio = utils.dateUtils
						.getInicioAno();

			}

			$scope.abrirInfo = function(event, $index) {
				$scope.googlemaps.showInfoWindow("epizootia-info", this);
			}

			$scope.init();

		});