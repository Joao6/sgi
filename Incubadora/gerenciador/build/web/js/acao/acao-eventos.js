
  $(document).ready(function () {
     var msgDefault = 'Escolha um tipo de plano para seu planejamento';
     
     var STATUS = $("#status").val().trim();
     if(STATUS !== "update" || STATUS === ""){
        $("#orientacoes-popover").attr({'data-content': msgDefault});        
     }
     
     $('[data-toggle="popover"]').popover('show');

     $("[data-toggle='tooltip']").hover(function () {
        $(this).tooltip('show');
     });


     $(this).on("change", "#categoria", function () {
        var idCategoria = $(this).val().toString();
        if (idCategoria !== "0") {
           findOrientacoesById(idCategoria);
        } else {
           $("#orientacoes-popover").attr({'data-content': msgDefault});
           $('[data-toggle="popover"]').popover('show');
        }

     });

     function findOrientacoesById(idCategoria) {

        var url = "/gerenciador/orientacoes/" + idCategoria;
        $.ajax(
            {
               url: url,
               method: 'GET',
               dataType: 'text',
               beforeSend: function (xhr) {
                  xhr.setRequestHeader("Accept", "application/text");
                  xhr.setRequestHeader("Content-Type", "text/plain");
                  xhr.overrideMimeType('text/html;charset=iso-8859-1');
               }
            }
        ).done(function (orientacoes) {
           if (orientacoes !== null && orientacoes !== "") {
              orientacoes = decodeURIComponent(orientacoes);
              $("#orientacoes-popover").attr({'data-content': orientacoes});
              $('[data-toggle="popover"]').popover('show');
           } else {
              $('[data-toggle="popover"]').popover('hide');
           }
        }).fail(function () {
           $('[data-toggle="popover"]').popover('hide');
           alert("Ops! Houver um Erro ao tentar ler a mensagem de orientação.");
        });

     }

  });
