/* global angular */

(function () {
   angular.module('painelAdmin').value('EmpreendimentoValues', {
      url_get_empreendimetos: '/gerenciador/empreendimentos/api',
      url_get_ramo_atividade: '/gerenciador/ramo/atividade/all',
      url_get_editais: '/gerenciador/edital/aberto/all',
      url_save_empreendimento: '/gerenciador/empreendimento/novo',
      url_remove_empreendimento: '/gerenciador/remover/empreendimento/',
      url_get_empreendimento_by_id: '/gerenciador/empreendimento/',
      url_update_empreendimento:'/gerenciador/empreendimento/update',
      url_add_ap_negocio:'/gerenciador/empreendimento/add/ap-negocio',
      url_change_state:'/gerenciador/empreendimento/change-state',
      url_agendar_apresentacao: '/gerenciador/empreendimento/agendar-apresentacao',
      url_get_avaliadores:'/gerenciador/incubadora/all/avaliadores',
      url_add_avaliadores: '/gerenciador/empreendimento/add/avaliadores'
   });
})();

