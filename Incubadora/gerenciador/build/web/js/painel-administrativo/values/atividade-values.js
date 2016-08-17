/* global angular */
  (function () {
     angular.module('painelAdmin').value('values', {
        url_atividades: "/gerenciador/cronograma-anual/atividades",
        url_atividade: "/gerenciador/cronograma-anual/novo",
        url_remove_atividade: "/gerenciador/remover/atividade/",
        url_get_atividade_by_id:'/gerenciador/cronograma-anual/atividade/',
        url_update_atividade: '/gerenciador/cronograma-anual/alterar'
     });
  })();