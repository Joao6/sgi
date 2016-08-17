/* global angular */

(function(){
   angular.module('painelAdmin').value('AvaliadorValues', {
      url_obter_avaliadores: '/gerenciador/incubadora/all/avaliadores',
      url_remover_avaliadore: '/gerenciador/avaliador/remover/',
      url_add_avaliador: '/gerenciador/add/avaliador',
      url_get_avaliador:'/gerenciador/avaliador/',
      url_update: '/gerenciador/avaliador/atualizar',
      url_check_email:'/gerenciador/avaliador/email',
      url_check_cpf:'/gerenciador/avaliador/check/cpf'
   });
})();