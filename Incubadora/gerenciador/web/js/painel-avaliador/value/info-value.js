
  /* global angular */
  angular.module('painelAvaliador').value('AvaliadorValue', {
     url_getInfoAv:  window.location.protocol + "//" + window.location.host + "/gerenciador/avaliador/info/api",
     url_update_infoAv: window.location.protocol + "//" + window.location.host + "/gerenciador/avaliador/update/info/api"
  });
