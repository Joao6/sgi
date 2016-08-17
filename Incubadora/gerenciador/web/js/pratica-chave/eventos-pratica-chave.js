
$(document).ready(function () {

    var UPDATE = "update";
    var CREATE = "create";

    var status = $("#status").val();
    if (status === UPDATE) {
        $("#btn-add").hide();
        $("#btn-sub").hide();
    }

    $("#dataCertificacao").attr({'data-toggle': 'tooltip', 'title': 'Data da Certificação'});
    $("#dataEvolucao").attr({'data-toggle': 'tooltip', 'title': 'Campo Obrigatório'});
    $('input[type="date"]').tooltip();

    //Remove todas as Práticas
    $(document).on('click', '#btn-sub', function () {
        $('.row-2').parents('.delete').remove();
        return false;
    });

    //Remove a prática selecionada
    $(document).on('click', '.btn.btn-danger', function (e) {
        e.preventDefault();
        $(this).parents('.input-default').remove();
    });

    $("#btn-add").click(function (e) {
        e.preventDefault();
        var input = $(".input-default#principal").html();
        $('.group-input').append("<span class='input-default delete'>" + input + "</span>");
        $('.delete').append("<button class='btn btn-danger'><i class='glyphicon glyphicon-minus'></i></button>");
        $('input').tooltip();
        $('.input-default').find('[name]').each(function () {
            limparCampo($(this));
        });

    });

    /**
     * Recupera dados do formulário para enviar pro Controller
     * @param e (Evento)
     **/


    $('#btn-submit').click(function (e) {
        if (status === CREATE) {
            e.preventDefault();
            var index = 0;
            var praticas = [];
            var json = [];
            var ok = true;

            $("#form-pratica-chave").find("input[id='input-data-certificacao']").each(function () {
                var data = $(this).val();
                if (data === null || data === "") {
                    $(this).val("0001-01-01");
                }
            });

            //Form-pratica-chave  - obtendo dados de cada formulário           
            $("#form-pratica-chave").find('.value').each(function () {

                if (campoIsValid($(this))) {
                    praticas[index] = $(this).val().toString();
                    index++;
                } else {
                    e.preventDefault();
                    if ($("#input-data-certificacao").val() === "0001-01-01") {
                        $("#input-data-certificacao").val(null);
                    }
                    ok = false;
                }

            });

            if (ok) {
                //Preparar JSON
                makeJSON(json, praticas);
                //Enviar JSON
                enviarJSON(json);
            }

        } else if (status === UPDATE) {
            //Se o campo Data da Certificação estiver vazio, seta esse campo como null    
            $("#form-pratica-chave").find('#input-data-certificacao').each(function () {
                if ($(this).val() === "" || $(this).val() === null) {
                    $(this).attr({'disabled': 'true'});
                    $(this).removeClass("value");
                }
            });

            $("#form-pratica-chave").find('.value').each(function () {
                if (!campoIsValid($(this))) {
                    e.preventDefault();
                }
            });
        }
    });

    function campoIsValid(campo) {
        var value = $(campo).val();
        if (value === "" || value === null || value === "0") {

            if ($(campo).attr("id") !== "input-data-certificacao") {
                $(campo).attr({'data-toggle': 'tooltip', 'title': 'Campo Obrigatório!'});
                $(campo).css({'border': '1px solid red'});
                $(campo).tooltip('show');
                return false;
            }
            return true;
        } else {
            $(campo).css({'border': '1px solid #AAA'});
            $(campo).tooltip('destroy');
            $(campo).tooltip('hide');
            return true;
        }
    }

    function limparCampo(campo) {
        $(campo).css({'border': '1px solid #AAA'});
        $(campo).tooltip('destroy');
        $(campo).tooltip('hide');
    }

    //Gerar JSON
    function makeJSON(json, praticas) {
        //Gerando JSON 
        var pos = 0;
        for (var j = 0; j < praticas.length; ++j) {
            json[pos] = {
                nome: praticas[j],
                estagioEvolucao: {
                    id: praticas[++j]
                },
                descricao: praticas[++j],
                dataEvolucao: praticas[++j],
                dataCertificacao: praticas[++j],
                processoChave: {
                    id: $("#processo").val().toString()
                }
            };
            pos++;
        }

    }
    //Envia para o controller em formato JSON
    function enviarJSON(json) {

        $.ajax({
            url: '/gerenciador/processo-chave/' + $('#processo').val() + '/pratica-chave/nova',
            dataType: 'json',
            method: "POST",
            data: JSON.stringify(json),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (data) {
                if (data !== null) {
                    location.reload();
                }
            }
        }).done(function (data) {
            console.log(data);
        }).complete(function () {
            window.location = "/gerenciador/processo-chave/" + $('#processo').val() + "/pratica-chave";
        });
    }

});