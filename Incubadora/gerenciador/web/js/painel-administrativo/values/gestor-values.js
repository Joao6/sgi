/* global angular */
  
(function(){
   angular.module('painelAdmin').value('GestorValues', {
      url_obter_gestores:'/gerenciador/incubadora/gestores',
      url_remove_gestor:'/gerenciador/incubadora/remove/gestor/',
      url_add_gestor:'/gerenciador/incubadora/add/gestor',
      url_get_gestor_by_id:'/gerenciador/incubadora/edit/gestor/',
      url_edit_gestor:'/gerenciador/incubadora/edit/gestor',
      url_check_email:'/gerenciador/incubadora/gestor/email',
      url_check_cpf:'/gerenciador/incubadora/gestor/check/cpf'      
   });
})();
