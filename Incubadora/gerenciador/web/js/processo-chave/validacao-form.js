
$(document).ready(function () {


  var nome = $(".input-nome").val();

  if (nome !== null && nome !== "") {
    $("#nome").attr({'data-toggle': 'tooltip', 'title': 'Nome do Processo'});
    //Destrói tooltip
    $("#nome").hover(function () {
      $(this).tooltip('show');
    });

  } else {
    //Quando digitar algo dentro do input 'nome' a borda voltará a cor normal (#AAA)
    $("#nome").keypress(function () {
      if ($(this).val() !== "" && $(this).val() !== null) {
        $(this).css({'border': '1px solid #AAA'});
      }
    });

    //Quando houver foco sobre o input 'nome' o tooltip desaparecerá 
    $("#nome").focus(function () {
      $(this).css({'outline': 'transparent'});
      $('[data-toggle="tooltip"]').tooltip('destroy');
    });

    //Destrói tooltip
    $("#nome").hover(function () {
      $('[data-toggle="tooltip"]').tooltip('destroy');
      $('[data-toggle="tooltip"]').tooltip('hide');
    });
  }
  //Checa se houve preencimento correto para submeter o formulário
  $(document).on('submit', '#form-processo-chave', function (e) {
    //Caso o campo esteja em branco a mensagem de erro é exibida
    if ($("#nome").val() === "" || $("#nome").val() === null) {
      e.preventDefault();
      $('[data-toggle="tooltip"]').tooltip('show');
      $("#nome").css({'border': '1px solid red'});
    }
  });

});