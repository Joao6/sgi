/**
 * @default Script para a validação do formulário do cadastro de Atividades do
 * Cronograma Anual 
 * 
 * @author William Daniel de Oliveira
 */

$(document).ready(function () {

   /*********************************************
    * VALIDAÇÃO DA DATA INICIAL E DA DATA FINAL *
    *********************************************/
  
   //Evento para controlar a alteração dos valores da Data Inicial
   $(document).on("change", "#dataInicio", function () {
      var segundo = 1000;
      var minuto = segundo * 60;
      var hora = minuto * 60;
      var dia = hora * 24;

      var timeDataInicial = Date.parse($(this).val().toString());
      var timeDataFinal = Date.parse($("#dataFim").val().toString());

      var dataInicialEmDias = timeDataInicial / dia;
      var dataFinalEmDias = timeDataFinal / dia;
      var totalDias = dataFinalEmDias - dataInicialEmDias + 1;

      if (totalDias < 0) {
         $("#totalDias").val(null);
         $("#dataFim").val(null);

         $("#dataFim").css({'border-bottom': '1px solid red'});

      } else {
         $("#totalDias").val(totalDias);
         $("#dataFim").css({'border-bottom': '1px solid #AAA'});
         $("#dataInicio").css({'border-bottom': '1px solid #AAA'});
      }

   });

   //Evento para controlar a alteração dos valores da Data Inicial
   $(document).on("change", "#dataFim", function () {
      var segundo = 1000;
      var minuto = segundo * 60;
      var hora = minuto * 60;
      var dia = hora * 24;

      var timeDataFinal = Date.parse($(this).val().toString());
      var timeDataInicial = Date.parse($("#dataInicio").val().toString());

      var dataFinalEmDias = timeDataFinal / dia;
      var dataInicialEmDias = timeDataInicial / dia;
      var totalDias = dataFinalEmDias - dataInicialEmDias + 1;

      if (totalDias < 0) {
         $("#totalDias").val(null);
         $("#dataInicio").val(null);

      } else {
         $("#totalDias").val(totalDias);
         $("#dataFim").css({'border-bottom': '1px solid #AAA'});
         $("#dataInicio").css({'border-bottom': '1px solid #AAA'});
      }

   });


});