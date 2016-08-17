/* global angular */

(function(){
   angular.module('painelAvaliador').value('EmpValues',{
      url_get_empreendimeto_by_emp: '/gerenciador/empreendimento/by/avaliador/',
      url_get_ramo_atividade: '/gerenciador/ramo/atividade/all',
      url_get_editais: '/gerenciador/edital/aberto/all',
      url_get_eixos: '/gerenciador/eixo/api',
      url_get_criterios: '/gerenciador/criterio/api',
      url_save_empreendimento: '/gerenciador/empreendimento/novo',
      url_remove_empreendimento: '/gerenciador/remover/empreendimento/',
      url_get_empreendimento_by_id: '/gerenciador/empreendimento/',
      url_update_empreendimento:'/gerenciador/empreendimento/update',
      url_add_ap_negocio:'/gerenciador/empreendimento/add/ap-negocio',
      url_save_notas:'/gerenciador/empreendimento/avaliar'
   });
})();