/* global angular */
(function(){
   angular.module('painelAdmin').value('EmpreendedorValues', {
      url_get_empreendedores: '/gerenciador/empreendedor/',
      url_add_empreendedor: '/gerenciador/add/empreendedor',
      url_update: '/gerenciador/empreendedor/atualizar',
      url_remover_empreendedor: '/gerenciador/empreendedor/remover/',  
      url_get_empreendimento :'/gerenciador/empreendimento/by/empreendedor/',      
      url_obter_empreendedores: '/gerenciador/incubadora/all/empreendedores'      
   });
})();