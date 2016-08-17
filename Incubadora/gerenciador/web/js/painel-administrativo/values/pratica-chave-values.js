/* global angular */
/* Materialize */

angular.module('painelAdmin')
        .value('PraticaChaveValue', {
           url_get_processo_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/pratica-chave/processo-chave/api",
           url_get_pratica_chave_save: window.location.protocol + "//" + window.location.host + "/gerenciador/pratica-chave/save/api",
           url_view_pratica_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/view/praticas-chave/from/processo-chave",
           url_delete_pratica_chave: window.location.protocol + "//" + window.location.host + "/gerenciador/pratica-chave/delete/api/",
           url_get_estagios_evolucao: window.location.protocol + "//" + window.location.host + "/gerenciador/pratica-chave/estagio-evolucao/api",
           url_delete_anexo: window.location.protocol + "//" + window.location.host + "/gerenciador/excluir/anexo/",
           url_get_all_praticas: window.location.protocol + "//" + window.location.host + "/gerenciador/praticas-chave/all/api"
        });