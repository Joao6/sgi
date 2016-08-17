/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



$(document).ready(function () {


  var nome = $(".form-control").val();

  if (nome !== null && nome !== "") {
    $("#descricao").attr({'data-toggle': 'tooltip', 'title': 'Descrever Orientação'});
    $("#plano").attr({'data-toggle': 'tooltip', 'title': 'Selecione uma Categoria'});
    //Destrói tooltip
    $("#descricao").hover(function () {
      $(this).tooltip('show');
    });

  } else {
    //Quando digitar algo dentro do input 'nome' a borda voltará a cor normal (#AAA)
    $("#descricao").keypress(function () {
      if ($(this).val() !== "" && $(this).val() !== null) {
        $(this).css({'border': '1px solid #AAA'});
      }
    });

    //Quando houver foco sobre o input 'nome' o tooltip desaparecerá 
    $("#descricao").focus(function () {
      $(this).css({'outline': 'transparent'});
      $('[data-toggle="tooltip"]').tooltip('destroy');
    });

    //Destrói tooltip
    $("#descricao").hover(function () {
      $('[data-toggle="tooltip"]').tooltip('destroy');
      $('[data-toggle="tooltip"]').tooltip('hide');
    });
  }
  //Checa se houve preencimento correto para submeter o formulário
  $(document).on('submit', '#orientacoes-form', function (e) {
    //Caso o campo esteja em branco a mensagem de erro é exibida
    if ($("#descricao","#plano").val() === "" || $("#descricao","#plano").val() === null) {
      e.preventDefault();
      $('[data-toggle="tooltip"]').tooltip('show');
      $("#descricao","#plano").css({'border': '1px solid red'});
    }
  });

});