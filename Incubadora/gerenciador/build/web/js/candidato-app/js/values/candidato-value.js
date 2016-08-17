
/* global angular */

angular.module('candidatoApp').value('CandidatoValue', {
   url_check_email: window.location.protocol + "//" + window.location.host + '/gerenciador/candidato/email',
   url_create_candidato: window.location.protocol + "//" + window.location.host + '/gerenciador/candidato/novo/api'
});