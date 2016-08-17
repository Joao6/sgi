/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

      $('#cpf').mask('000.000.000-00');

    var id = $("#incubadora-new").val();
    if (id !== null && id !== "") {
        $("#avaliador-nome").attr({'data-toggle': 'tooltip', 'title': 'Nome do Empreendedor'});
        $("#avaliador-sobrenome").attr({'data-toggle': 'tooltip', 'title': 'Sobrenome do Empreendedor'});
        $("#avaliador-email").attr({'data-toggle': 'tooltip', 'title': 'Email'});
        $("#avaliador-senha").attr({'data-toggle': 'tooltip', 'title': 'Senha'});
        $("#cpf").attr({'data-toggle': 'tooltip', 'title': 'CPF'});

        //destroi tooltip
        $(".input-item").hover(function () {
            $(this).tooltip('show');
        });
    }


    //Quando digitar algo dentro do input 'nome' a borda voltará a cor normal (#AAA)
    $("value").keypress(function () {
        if ($(this).val() !== "" && $(this).val() !== null) {
            $(this).css({'border': '1px solid #AAA'});
        }
    });

    //Quando houver foco sobre o input 'nome' o tooltip desaparecerá 
    $(this).focus(function () {
        $(this).css({'outline': 'transparent'});
        $(this).tooltip('destroy');
    });

    //Destrói tooltip
    $(this).hover(function () {
        $(this).tooltip('destroy');
        $(this).tooltip('hide');
    });

    //Checa se houve preencimento correto para submeter o formulário
    $(document).on('submit', '#form-avaliador', function (e) {
        $(".value,#cpf").attr({'data-toggle': 'tooltip', 'data-placement': 'bottom',
            'title': 'campo Obrigatório!'});
        //Caso o campo esteja em branco a mensagem de erro é exibida
        var cpf = $("#cpf").val().toString();

        var cpfReplaced = cpf.substr(0, 3);
        cpfReplaced += cpf.substr(4, 3);
        cpfReplaced += cpf.substr(8, 3);
        cpfReplaced += cpf.substr(12, 2);

        $(this).find('.value').each(function () {
            if ($(this).val() === "" || $(this).val() === null || $(this).val() === '0') {
                e.preventDefault();
                $(this).tooltip('show');
                $(this).css({'border': '1px solid red'});
                $(this).attr({'data-toggle': 'tooltip', 'data-placement': 'bottom',
                    'title': 'Campo Obrigatório!'});
                $(this).tooltip('show');
            } else {
                $(this).tooltip('destroy');
                $(this).tooltip('hide');
                $(this).css({'border': '1px solid #AAA'});
            }
        });

        //TODO NÚMERO MÍNIMO DE CARACTERES PARA A SENHA


        //Validar CPF
        if (!validaCPF(cpfReplaced)) {
            e.preventDefault();
            $("#cpf").tooltip('show');
            $("#cpf").css({'border': '1px solid red'});
        } else {
            $("#cpf").tooltip('destroy');
            $("#cpf").tooltip('hide');
            $("#cpf").css({'border': '1px solid #AAA'});
        }
    });


    function validaCPF(cpf) {
        var soma;
        var resto;
        var i;

        if ((cpf.length !== 11) ||
                (cpf === "00000000000") || (cpf === "11111111111") ||
                (cpf === "22222222222") || (cpf === "33333333333") ||
                (cpf === "44444444444") || (cpf === "55555555555") ||
                (cpf === "66666666666") || (cpf === "77777777777") ||
                (cpf === "88888888888") || (cpf === "99999999999")) {
            return false;
        }

        soma = 0;

        for (i = 1; i <= 9; i++) {
            soma += Math.floor(cpf.charAt(i - 1)) * (11 - i);
        }

        resto = 11 - (soma - (Math.floor(soma / 11) * 11));

        if ((resto === 10) || (resto === 11)) {
            resto = 0;
        }

        if (resto !== Math.floor(cpf.charAt(9))) {
            return false;
        }

        soma = 0;

        for (i = 1; i <= 10; i++) {
            soma += cpf.charAt(i - 1) * (12 - i);
        }

        resto = 11 - (soma - (Math.floor(soma / 11) * 11));

        if ((resto === 10) || (resto === 11)) {
            resto = 0;
        }

        if (resto !== Math.floor(cpf.charAt(10))) {
            return false;
        }

        return true;
    }


});
