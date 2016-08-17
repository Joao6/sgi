  /* 
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */

  $(document).ready(function () {

     $(document).on("submit", "#form-planejamento", function (e) {
        $(this).find('.required').each(function () {
           var value = $(this).val();
           if (value === "" || value === null || value === "0") {
              e.preventDefault();
              $(this).attr({
                 'data-toggle': 'tooltip',
                 'data-placement': 'top',
                 'title': 'Campo Obrigatório'
              });
              $(this).tooltip('show');
           }
        });

        //Validação das datas
        var dataInicial = $("#dataInicial").val();
        var dataFinal = $("#dataFinal").val();
        if (dataFinal !== null && dataInicial !== null) {
           var timeInicio = Date.parse(dataInicial);
           var timeTermino = Date.parse(dataFinal);

           var periodo = timeTermino - timeInicio;
           if (periodo < 0) {
              e.preventDefault();
              $("#dataFinal").attr({
                 'data-toggle': 'tooltip',
                 'data-placement': 'top',
                 'title': 'Data Final menor que Data Inicial'
              });
              $("#dataFinal").tooltip('show');
              $("#dataFinal").val(null);
           }
        }
     });

  });
