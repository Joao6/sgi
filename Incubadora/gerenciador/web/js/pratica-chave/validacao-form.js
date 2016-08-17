/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

  var UPDATE = "update";
  var status = $("#status").val();
  if (status === UPDATE) {
    $("#input-nome").attr({'data-toggle': 'tooltip', 'title': 'Nome da Prática'});
    $("#input-estagio-evolucao").attr({'data-toggle': 'tooltip', 'title': 'Estágio de Evolução'});
    $("#text-descricao").attr({'data-toggle': 'tooltip', 'title': 'Descrição'});


    //destroi tooltip
    $(".input").hover(function () {
      $(this).tooltip('show');
    });
  }


  $(document).on('submit', "#form-pratica-chave", function (e) {

    $(this).find(".teste").each(function () {
      if ($(this).val() === "" || $(this).val() === null || $(this).val() === '0') {
        e.preventDefault();
        $(this).css({'border': '1px solid red'});
        $(this).attr({'data-toggle': 'tooltip', 'title': 'Campo Inválido!'});
        $(this).tooltip('show');
      } else {
        $(this).css({'border': '1px solid #AAA'});
        $(this).tooltip('destroy');
        $(this).tooltip('hide');
      }
    });

  });

});

