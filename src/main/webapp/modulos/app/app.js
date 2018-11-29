var app = angular.module("vigilantos",
		[ 'ngRoute', 'ngSanitize', 'ui.bootstrap', 'toastr', 
		  'picardy.fontawesome', 'mgcrea.ngStrap' , 'ngMap', 
		  'angularFileUpload', 'chart.js', 'dndLists', 
		  'ui.utils.masks', 'brasil.filters', 'AxelSoft', 
		  'ngTagsInput', 'textAngular', 'ngWebSocket']);

app.config(function($routeProvider, toastrConfig, $qProvider){
	
//	$qProvider.errorOnUnhandledRejections(false)
	
	angular.extend(toastrConfig, {
	    positionClass: 'toast-bottom-right',
	    target: 'body'
	});
	
	
	/*
	 taOptions.toolbar = [
      ['h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'p', 'pre', 'quote'],
      ['bold', 'italics', 'underline', 'strikeThrough', 'ul', 'ol', 'redo', 'undo', 'clear'],
      ['justifyLeft', 'justifyCenter', 'justifyRight', 'indent', 'outdent'],
      ['html', 'insertImage','insertLink', 'insertVideo', 'wordcount', 'charcount']
  	];
	 */
	
   $routeProvider
//=============================================================================================//		
//	   SISTEMA														                           //
//=============================================================================================//
	   .when('/home', {
		   templateUrl: 'modulos/home/home.html', 
		   controller: 'HomeController'
	   })
	   .when('/about', {
           templateUrl: 'modulos/home/about.html', 
           controller: 'AboutController'
       })
       .when("/construcao",{
          	templateUrl: "default.desenvolvimento.html"
        })
       .when("/error403",{
          	templateUrl: "modulos/layout/http.error.403.html",
          	controller: "HttpError403Controller"
        })
       .when("/mensagens",{
          	templateUrl: "modulos/sistema/mensagem.lista.html",
          	controller: "MensagemListaController"
        })
        .when("/mensagens/:id",{
          	templateUrl: "modulos/sistema/mensagem.cadastro.html",
          	controller: "MensagemCadastroController"
        })
        .when("/manual-user",{
          	templateUrl: "modulos/sistema/manual.user.lista.html",
          	controller: "ManualUserListaController"
        })
        .when("/no-session",{
          	templateUrl: "modulos/sistema/sessao.expirada.html",
          	controller: "NoSessionController"
        })
//=============================================================================================//		
//		EPIZOOTIA														                           //
//=============================================================================================//
        .when('/epizootia/notificacao/:id', {
        	templateUrl: "modulos/epizootia/epizootia.notificacao.cadastro.html",
        	controller: "EpizootiaNotificacaoController"
        })
        .when('/epizootia/notificacao', {
        	templateUrl: "modulos/epizootia/epizootia.notificacao.lista.html",
        	controller: "EpizootiaNotificacaoListaController"
        })
        .when('/epizootia/mapa-notificacao', {
        	templateUrl: "modulos/epizootia/epizootia.mapa.html",
        	controller: "EpizootiaMapaController"
        })
        .when("/epizootia/relatorio/periodo/:tipo",{
        	templateUrl: "modulos/epizootia/relatorio/relatorio.periodo.html",
        	controller: "RelatorioPeriodoController"
        })
//===========================================================================================//
//		DEFAULT
//===========================================================================================//        
         .otherwise({redirectTo: '/home' })
});

app.run( function(uibPaginationConfig){
	uibPaginationConfig.firstText='<<';
	uibPaginationConfig.previousText='<';
	uibPaginationConfig.nextText='>';
	uibPaginationConfig.lastText='>>';
	
});

app.value('$strapConfig', {
	datepicker: {
		language: 'pt-BR',
		format: 'dd/MM/yyyy'
	}
});

