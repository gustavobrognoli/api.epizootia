
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
//		DENGUE														                           //
//=============================================================================================//
        .when('/dengue/focos/:id', {
        	templateUrl: "modulos/dengue/dengue.focos.cadastro.html",
        	controller: "DengueFocosCadastroController"
        })
        .when('/dengue/focos', {
        	templateUrl: "modulos/dengue/dengue.focos.lista.html",
        	controller: "DengueFocosListaController"
        })
        .when('/dengue/mapa/:id/:isUpdate', {
        	templateUrl: "modulos/dengue/dengue.mapa.html",
        	controller: "DengueMapaController"
        })
        .when("/dengue/mapa-focos", {
        	templateUrl: "modulos/dengue/dengue.focos.mapa.geral.html",
        	controller: "DengueMapaGeralController"
        })
        .when("/dengue/casos/importacao",{
        	templateUrl: "modulos/dengue/dengue.casos.importacao.html",
        	controller: "DengueCasosImportacaoController"
        })
        .when("/dengue/casos",{
          	templateUrl: "modulos/dengue/dengue.casos.lista.html",
        	controller: "DengueCasosListaController"
        })
        .when("/dengue/mapa-casos",{
        	templateUrl: "modulos/dengue/dengue.casos.mapa.geral.html",
        	controller: "DengueCasosMapaGeralController"
        })
        .when("/dengue/casos/mapa/:id",{
        	templateUrl: "modulos/dengue/dengue.casos.mapa.html",
        	controller: "DengueCasoMapaController"
        })
        .when("/dengue/relatorio/periodo/:tipo",{
        	templateUrl: "modulos/dengue/relatorios/relatorio.periodo.html",
        	controller: "RelatorioPeriodoController"
        })
        .when("/dengue/total-municipio/mapa",{
        	templateUrl: "modulos/dengue/relatorios/mapa.municipio.periodo.html",
        	controller: "MapaTotalMunicipioController"
        })
        .when("/dengue/grafico-focos",{
        	templateUrl: "modulos/dengue/graficos/grafico.focos.html",
        	controller: "GraficoFocosController"
        })
        .when("/dengue/grafico-casos",{
        	templateUrl: "modulos/dengue/graficos/grafico.casos.html",
        	controller: "GraficoCasosController"
        })
        .when("/dengue/admin/user-regional", {
        	templateUrl: "modulos/dengue/administrativo/vinculo.usuario.regional.html",
        	controller: "VinculoUsuarioRegionalController"
        })
        .when("/dengue/grafico-anual", {
        	templateUrl: "modulos/dengue/graficos/grafico.comparativo.anual.html",
        	controller: "GraficoComparativoAnualController"
        })
//===========================================================================================//
//		ANIMAIS PEÇONHENTOS - Artrópodes
//===========================================================================================//
        .when("/artropodes/fichas/:id", {
        	templateUrl: "modulos/artropode/ficha.amostra.cadastro.html",
        	controller: "FichaAmostraCadastroController"
        })
        .when("/artropodes/visualizacao/:id", {
        	templateUrl: "modulos/artropode/ficha.amostra.visualizacao.html",
        	controller: "FichaAmostraVisualizacaoController"
        })
        .when("/artropodes/fichas", {
        	templateUrl: "modulos/artropode/ficha.amostra.lista.html",
        	controller: "FichaAmostraListaController"
        })
        .when("/artropodes/cadastro-auxiliar-coletadores", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.coletador.lista.html",
        	controller: "CadastroAuxiliarColetadorListaController"
        })
        .when("/artropodes/cadastro-auxiliar-tipoAnimais", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.tipoAnimal.lista.html",
        	controller: "CadastroAuxiliarTipoAnimalListaController"
        })
        
        .when("/artropodes/cadastro-auxiliar-evolucoes", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.evolucao.lista.html",
        	controller: "CadastroAuxiliarEvolucaoListaController"
        })
        .when("/artropodes/cadastro-auxiliar-familias", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.familia.lista.html",
        	controller: "CadastroAuxiliarFamiliaListaController"
        })
        .when("/artropodes/cadastro-auxiliar-generos", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.genero.lista.html",
        	controller: "CadastroAuxiliarGeneroListaController"
        })
        .when("/artropodes/cadastro-auxiliar-especies", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.especie.lista.html",
        	controller: "CadastroAuxiliarEspecieListaController"
        })
        .when("/artropodes/cadastro-auxiliar-logradouros", {
        	templateUrl: "modulos/artropode/cadastro.auxiliar.logradouro.lista.html",
        	controller: "CadastroAuxiliarLogradouroListaController"
        })        
        .when("/artropodes/laudos", {
        	templateUrl: "modulos/artropode/laudo.laboratorio.lista.html",
        	controller: "LaudoLaboratorioListaController"
        })
        .when("/artropodes/laudo/visualizacao/:idFicha/:idLaudo", {
        	templateUrl: "modulos/artropode/laudo.laboratorio.visualizacao.html",
        	controller: "LaudoLaboratorioVisualizacaoController"
        })
        .when("/artropodes/laudo/:idFicha/:idLaudo", {
        	templateUrl: "modulos/artropode/laudo.laboratorio.cadastro.html",
        	controller: "LaudoLaboratorioCadastroController"
        })
        .when("/artropodes/mapa", {
        	templateUrl: "modulos/artropode/amostra.mapa.html",
        	controller: "AmostraMapaController"
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

