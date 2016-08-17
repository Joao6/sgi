
$(document).ready(function () {

  $('.input-data').mask("00r00r0000", {
    translation: {
      'r': {
        pattern: /[\/]/,
        fallback: '/'
      },
      placeholder: "__/__/____"
    }
  });
  
  
  $(".input-cpf").mask("999.999.999-99");
  $(".input-cnpj").mask("99.999.999/9999-99");
});