
$(document).ready(function () {

  var LIMITE_UPLOAD = 26214400; //25MB
  $('#file').bind('change', function () {

    //this.files[0].size gets the size of your file.
    if (this.files[0].size > LIMITE_UPLOAD) {
      alert("Tamanho de arquivo nao suportado!");
      $("#btn-submit").css(
              {"border": "1px solid #CCC"},
      {"color": "#CCC"});
    } else {
      $("#btn-submit").addClass('.btn.botao');
    }
  });

  $('#btn-submit').click(function (e) {
    if (this.falie[0].size <= LIMITE_UPLOAD) {
      return true;
    }
    else {
      e.preventDefault();
      $(this).addClass('.btn.botao');
    }
  });
});