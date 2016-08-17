  
  (function(){
     angular.module('painelEmpreendedor').value('DuvidaValues', {
        url_obter_duvidas: '/gerenciador/gestor/duvidas/all',
        url_remover_duvida: '/gerenciador/remover/duvida/',
        url_responder_duvida: '/gerenciador/responder/duvida',
        url_add_duvida: '/gerenciador/add/duvida'
     });
  })();