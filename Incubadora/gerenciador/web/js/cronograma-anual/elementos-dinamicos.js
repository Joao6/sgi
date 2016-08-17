
  $(document).ready(function () {

     //Adiciona pratica
     $("#select-pratica").change(function (e) {
        var option = $(this).val();
        $(this).find('option').each(function () {
           if ($(this).val() === option && option !== '0') {
              var id = $(this).attr("value");
              var nome = $(this).text().toString();
              var content = "<li>"
                  + "<div class='chip'>"
                  + "<span id='" + id + "'>" + nome + "</span>"
                  + "<input type='number' hidden='true' name='pratica' value='" + id + "'/>"
                  + "<button type='button' class=' pratica close' data-dismiss='alert' aria-label='Close'>"
                  + "<span aria-hidden='true'>&times;</span>"
                  + "</button>"
                  + "</div>"
                  + "</li>";

              $(".list-praticas").append($(content));
              $(this).remove();
           }
        });
     });

     //Remove Pratica-Chave adicionada
     $(document).on('click', "button.pratica.close", function (e) {
        var id = $(this).parent(".list-praticas .chip").find("span").attr("id");
        var text = $(this).parent().text().toString();
        var text = text.substring(0, text.length - 1);
        $("#select-pratica").append("<option value='" + id + "'>" + text + "</option>");
        $(this).parent().parent().remove();
     });

     //Adiciona responsáveis
     $("#select-responsavel").change(function (e) {
        var option = $(this).val();
        $(this).find('option').each(function () {
           if ($(this).val() === option && option !== '0') {
              var id = $(this).attr("value");
              var nome = $(this).text().toString();
              var content = "<li>"
                  + "<div class='chip'>"
                  + "<span id='" + id + "'>" + nome + "</span>"
                  + "<input type='number' hidden='true' name='responsavel' value='" + id + "'/>"
                  + "<button type='button' class='responsavel close' data-dismiss='alert' aria-label='Close'>"
                  + "<span aria-hidden='true'>&times;</span>"
                  + "</button>"
                  + "</div>"
                  + "</li>";

              $(".list-responsaveis").append($(content));

              $(this).remove();
           }
        });
     });

     //Remove Responsávela adicionados
     $(document).on('click', 'button.responsavel.close', function (e) {
        var id = $(this).parent(".list-responsaveis .chip").find("span").attr("id");
        var text = $(this).parent().text().toString();
        var text = text.substring(0, text.length - 1);
        $("#select-responsavel").append("<option value='" + id + "'>" + text + "</option>");
        $(this).parent().parent().remove();
     });


     //Validação de campos - borda vermelha para campos não preenchidos corretamente
     $(document).on('keypress', '[type=text],[type=number]', function (e) {
        $(this).css({'border': '1px solid #AAA'});
     });
     $(document).on('change', '[type=number],[type=date]', function (e) {
        $(this).css({'border': '1px solid #AAA'});
     });
//     $(document).on('submit', '#form-cronograma', function (e) {
//        //Validando campos vazios
//        $(this).find(".validate").each(function () {
//           if ($(this).val() === "" || $(this).val() === null || $(this).val() === '0') {
//              e.preventDefault();
//              $(this).css({'border': '1px solid red'});
//           } else {
//              $(this).css({'border': '1px solid #AAA'});
//           }
//        });
//     });


  });
