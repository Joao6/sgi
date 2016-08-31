/* global angular */

(function () {
   angular.module('painelEmpreendedor').value('EmpreendimentoValues', {
      url_get_empreendimeto_by_emp: '/gerenciador/empreendimentos/empreendedor',
      url_get_ramo_atividade: '/gerenciador/ramo/atividade/all',
      url_get_editais: '/gerenciador/edital/aberto/all',
      url_save_empreendimento: '/gerenciador/empreendimento/novo',
      url_remove_empreendimento: '/gerenciador/remover/empreendimento/',
      url_get_empreendimento_by_id: '/gerenciador/empreendimento/',
      url_update_empreendimento:'/gerenciador/empreendimento/update',
      url_add_ap_negocio:'/gerenciador/empreendimento/add/ap-negocio',
      url_obter_empreendedores:'/gerenciador/incubadora/all/empreendedores',
      url_add_empreen: '/gerenciador/empreendimento/add/empreendedores'
   });
})();

