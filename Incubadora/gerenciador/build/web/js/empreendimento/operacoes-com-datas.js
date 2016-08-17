/* *  
 * Script responsável por calcular o número de meses restantes entre a 
 * data de previsão de graduação até a data atual
 * 
 * Autor: William Daniel de Oliveira
 * v. 1.0.0 - 2015 
 */

$(document).ready(function () {

  var segundo = 1000;
  var minuto = segundo * 60;
  var hora = minuto * 60;
  var dia = hora * 24;

  $('#tabela-empreendimento').find('.dataPrevGraduacao').each(function () {
    var dataPrevGraduacao = $(this).text().trim();
    if (dataPrevGraduacao !== "") {

      var dataGraduacao = Date.parse(dataPrevGraduacao);
      var data = new Date();
      var dataHoje = data.getFullYear().toString() + "-"
              + (data.getMonth() + 1).toString() + "-"
              + data.getDate().toString();

      var dataAtual = Date.parse(dataHoje);
      var dataAtualEmDias = dataAtual / dia;
      var dataFinal = dataGraduacao / dia;

      var TEMPO = "tempo";
      if (Math.floor(dataFinal) < Math.floor(dataAtualEmDias)) {
        $(this).text("---");
      } else {
        var result = dataFinal - dataAtualEmDias;
        if (result > 30) {
          result = Math.floor(result / 30);
          TEMPO = result + " mes(es)";
        } else {
          if (result > 7) {
            result = Math.floor(result / 7);
            TEMPO = result + " semana(s)";
          } else {
            if (result > 1 && result < 2) {
              TEMPO = Math.floor(result) + " dia e algumas horas";
            }
            else if (result > 1) {
              TEMPO = Math.floor(result) + " dia(s)";
            } else if (result < 1) {
              TEMPO = " menos de 24 horas";
            }
          }

        }

        $(this).text(TEMPO);
      }
    }
  });
  

});
