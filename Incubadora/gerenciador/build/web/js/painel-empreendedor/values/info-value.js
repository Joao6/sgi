
  /* global angular */
  angular.module('painelEmpreendedor').value('InfoValue', {
     url_getInfo: window.location.protocol + "//" + window.location.host + "/gerenciador/empreendedor/info/api",
     url_update_info: window.location.protocol + "//" + window.location.host + "/gerenciador/empreendedor/update/info/api"
  });
