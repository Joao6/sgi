/* global angular */

(function () {
   angular.module('painelAdmin').value('EixoValues', {
      url_get_eixos: window.location.protocol + "//" + window.location.host + "/gerenciador/eixo/api",
      url_get_criterios: window.location.protocol + "//" + window.location.host + "/gerenciador/criterio/api",
      url_add_eixo: window.location.protocol + "//" + window.location.host + "/gerenciador/eixo/novo/api",
      url_add_criterio: window.location.protocol + "//" + window.location.host + "/gerenciador/eixo/add/criterio/api",
      url_update_eixo: window.location.protocol + "//" + window.location.host + "/gerenciador/eixo/update/api",
      url_delete_eixo: window.location.protocol + "//" + window.location.host + "/gerenciador/excluir/eixo/",
      url_delete_criterio: window.location.protocol + "//" + window.location.host + "/gerenciador/excluir/criterio/"
   });
})();


