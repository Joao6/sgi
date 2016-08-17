/* global angular */  
  (function(){
     angular.module('painelAdmin').value('DuvidaValues', {
        
        url_obter_duvidas: '/gerenciador/gestor/duvidas/all',
        url_remover_duvida: '/gerenciador/remover/duvida/',
        url_remover_resposta: '/gerenciador/remover/resposta',
        url_atualizar_resposta: '/gerenciador/atualizar/resposta',
        url_responder_duvida: '/gerenciador/responder/duvida',
        url_get_connection:'/gerenciador/duvida/connection'
     });
     
  })();