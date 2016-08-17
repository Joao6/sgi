  /**
   * Script responsável por inserir e membros do empreendimento dinamicamente.
   * 
   * v. 1.0.0 - 2015
   */

  $(document).ready(function () {

     var idEmpreendimento = $("#empreendimento-id").val();

     //Checar status
     var CREATE = "create";
     var UPDATE = "update";


     var STATUS = $("#status-empreendimento").val();

     if (STATUS === UPDATE) {
        //Inicia o formulário eliminando do Select os nomes dos usuários já selecionados
        $(".input-clone").find('input[value]').each(function () {
           var selecionado = $(this).val();

           $("#empreendedor").find('option[value]').each(function () {
              var id = $(this).val();
              if (selecionado === getNameById(id)) {
                 $(this).remove();
              }
           });

        });
     }

     //Encontra o nome do empreendedor (no <select> ) a partir do id

     $(document).on("change", "#empreendedor", function () {

        var idEmpreendedor = $(this).val().toString();

        if (STATUS === UPDATE && idEmpreendedor !== "0") {

           var url = "/gerenciador/empreendimento/" + idEmpreendimento + "/empreendedor/" + idEmpreendedor + "/add";
           $.ajax({
              url: url,
              method: 'GET',
              dataType: 'text',
              beforeSend: function (xhr) {
                 xhr.setRequestHeader("Accept", "application/text");
                 xhr.setRequestHeader("Content-Type", "text/plain");
                 xhr.overrideMimeType('text/html;charset=iso-8859-1');
              }
           }).done(function (result) {
              if (result !== null) {
                 addEmpreendedor(getNameById(idEmpreendedor), idEmpreendedor);
              }
           }).fail(function () {
              $("#empreendedor").attr({'data-toggle': 'tooltip', 'data-placement': 'top',
                 'title': 'Erro ao Tentar Adicionar ' + getNameById(idEmpreendedor) + '!'});
              $("#empreendedor").tooltip('show');
              alert("falha ao adicionar " + getNameById(idEmpreendedor));
           });

        } else if (STATUS === CREATE && idEmpreendedor !== "0") {
           //addEmpreendedor(findNameById(idEmpreendedor));
           var url = "/gerenciador/empreendedor/" + idEmpreendedor;

           $.ajax({
              url: url,
              method: 'POST',
              dataType: 'text',
              beforeSend: function (xhr) {
                 xhr.setRequestHeader("Accept", "application/text");
                 xhr.setRequestHeader("Content-Type", "text/plain");
                 xhr.overrideMimeType('text/html;charset=iso-8859-1');//Colocar para reconhecer acentos
              }
           }).done(function (nome) {
              if (nome !== null) {
                 nome = decodeURIComponent(nome);
                 addEmpreendedor(nome, idEmpreendedor);
              }
           }).fail(function () {
              $("#empreendedor").attr({'data-toggle': 'tooltip',
                 'title': 'Erro ao Tentar Adicionar ' + getNameById(idEmpreendedor) + '!'});
              $("#empreendedor").tooltip('show');
           });

        }

     });


     //Exclui 'Responsável' adicionado
     $(document).on('click', '.close', function () {

        //Retorna a opção para o Select
        var input = $(this).parents(".input-clone").find('[name]');
        var value = input.val();
        var id = input.attr('id');

        $("#empreendedor").append("<option class='option' value='" + id + "'>" + value + "</option>");

        if (idEmpreendimento !== null && value !== "" && STATUS === UPDATE) {
           //Ajax Excluir opção na base de dados

           var url = "/gerenciador/remover/empreendedor/" + id;
           $.ajax({
              url: url,
              method: "GET",
              beforeSend: function (xhr) {
                 xhr.setRequestHeader("Accept", "application/text");
                 xhr.setRequestHeader("Content-Type", "text");
              }
           }).done(function () {
              //Remove a opção escolhida (nome do participante do cronograma)       
           }).fail(function () {
              alert("erro ao tentar remover empreendedor");
           });
        }
        $(this).parents('.input-group').remove();
        $("#default").val(null);
     });


     function removeSelectById(id) {
        var nome;
        $("#empreendedor").find("option").each(function () {
           var value = $(this).val().toString();
           if (value === id.toString() && value !== '0' && value !== null) {
              nome = $(this).text().trim();
              $(this).remove(); //remove do select
           }
        });

        return nome;
     }

     function getNameById(id) {
        var nome;
        $("#empreendedor").find("option").each(function () {
           var value = $(this).val().toString();
           if (value === id.toString() && value !== '0' && value !== null) {
              nome = $(this).text().trim();
           }
        });

        return nome;
     }

     function addEmpreendedor(value, id) {

        //Adiciona 'responsável' escolhido
        if (value !== '0') {
           $(".default").find('input').attr({id: id});
           $("#" + id).attr({value: value});
           var inputClone = $(".clone-container").html();
           $('.clone-container.main').append(inputClone);
        }

        removeSelectById(id);
     }

  });
