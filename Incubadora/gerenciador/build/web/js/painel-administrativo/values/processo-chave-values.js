  /* global angular */

  angular.module('painelAdmin').value('ProcessoChaveValue', {
     url_get_processo_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/processo-chave/api",
     url_add_processo_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/processo-chave/add/api",
     url_view_pratica_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/view/praticas-chave/from/processo-chave",
     url_update_processo_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/processo-chave/update/api",
     url_delete_processo_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/processo-chave/delete/api/"
  });