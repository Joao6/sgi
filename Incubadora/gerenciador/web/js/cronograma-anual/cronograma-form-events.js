
/**  
 * @default Scripts Responsáveis por inserir e remover elementos dinamicamente
 * 
 * @version 1.0 - 2015
 * @author William Daniel de Oliveira
 **/

$(document).ready(function () {

    //Flag para saber se o formulário está sendo usado para UPDATE
    var id = $("#cronograma-id").val();

    //Inicia o formulário eliminando do Select os nomes dos usuários já selecionados
    $(".clone-container").find('input[value]').each(function () {
        var selecionado = $(this).val();

        $("#responsavel").find('option[value]').each(function () {

            if (selecionado === $(this).val()) {
                $(this).remove();
            }

        });

    });

    //Inicia o formulário eliminando do Select os nomes dos usuários já selecionados
    $(".clone-container-pratica").find('input[value]').each(function () {
        var selecionado = $(this).val();
        $("#cronograma-pratica").find('option[value]').each(function () {

            if (selecionado === $(this).val()) {
                $(this).remove();
            }

        });

    });

    //Quando select 'responsável' perde o foco, é adicionada a opção escolhida
    $('#responsavel').change(function () {
        var value = $(this).val(); //Valor do option selecionado 

        //Exclui opção escolhida do Select para que não possa ser adicionada repetidamente
        $(this).find('option[value]').each(function () {
            if ($(this).val() === value && $(this).val() !== '0') {
                $(this).remove();
            }
        });

        //Adiciona 'responsável' escolhido
        if (value !== '0') {
            $("#incubadora").attr({value: value});
            var inputClone = $(".clone-container").html();
            $('#input-dinamico').append(inputClone);
        }

        //Ajax Incluir opção na base de dados
        if (id !== null && id !== "") {
            var url = "/gerenciador/cronograma-anual/" + id + "/manager/" + value + "/inserir";
            $.ajax({
                url: url,
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
            });
        }

    });

    //Exclui 'Responsável' adicionado
    $(document).on('click', '.close.close-incubadora', function () {
        //Retorna a opção para o Select
        var value = $(this).parents(".input-group").find('#incubadora[value]').val();
        $("#responsavel").append("<option class='option' value='" + value + "'>" + value + "</option>");

        if (id !== null && id !== "") {
            //Ajax Excluir opção na base de dados

            var url = "/gerenciador/cronograma-anual/" + id + "/manager/" + value + "/excluir";
            $.ajax({
                url: url,
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
            });
        }
        //Remove a opção escolhida (nome do participante do cronograma)
        $(this).parents('.input-group').remove();
    });

    //EVENTOS PARA PRÁTICA DINÂMICA


    //Inicia o formulário eliminando do Select os nomes dos usuários já selecionados
    $(".clone-container").find('input[value]').each(function () {
        var selecionado = $(this).val();

        $("#cronograma-pratica").find('option[value]').each(function () {

            if (selecionado === $(this).val()) {
                $(this).remove();
            }
        });
    });

    //Quando select 'responsável' perde o foco, é adicionada a opção escolhida
    $('#cronograma-pratica').change(function () {
        var value = $(this).val(); //Valor do option selecionado 

        //Exclui opção escolhida do Select para que não possa ser adicionada repetidamente
        $(this).find('option[value]').each(function () {
            if ($(this).val() === value && $(this).val() !== '0') {
                $(this).remove();
            }
        });

        //Adiciona 'responsável' escolhido
        if (value !== '0') {
            $("#pratica-chave").attr({value: value});
            var inputClone = $(".clone-container-pratica").html();
            $('#input-dinamico-pratica').append(inputClone);
        }

        //Ajax Incluir opção na base de dados
        if (id !== null && id !== "") {
            var url = "/gerenciador/cronograma-anual/" + id + "/manager-pratica/" + value + "/inserir";
            $.ajax({
                url: url,
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
            });
        }

    });

    //Exclui 'Responsável' adicionado
    $(document).on('click', '.close.close-pratica', function () {

        //Retorna a opção para o Select
        var value = $(this).parents(".teste").find('#pratica-chave[value]').val();
        $("#cronograma-pratica").append("<option class='option' value='" + value + "'>" + value + "</option>");

        if (id !== null && id !== "") {
            //Ajax Excluir opção na base de dados

            var url = "/gerenciador/cronograma-anual/" + id + "/manager-pratica/" + value + "/excluir";
            $.ajax({
                url: url,
                method: "GET",
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("Accept", "application/json");
                    xhr.setRequestHeader("Content-Type", "application/json");
                }
            });
        }
        //Remove a opção escolhida (nome do participante do cronograma)
        $(this).parents('.teste').remove();
    });

});