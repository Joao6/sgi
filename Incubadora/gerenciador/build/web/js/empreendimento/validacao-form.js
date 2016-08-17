  /**
   * Script para validação do formulário de Empreendedor
   * 
   * @author William Daniel de Oliveira
   * @default 12/07/2015
   */

  $(document).ready(function () {
     //Status
     var UPDATE = "update";
     var CREATE = "create";
     var STATUS = $("#status-empreendimento").val();


     //Configurações iniciais ao carregar a página
     $('#cnpj').mask('00.000.000/0000-00');
     $("#dataAbertura").attr({'data-toggle': 'tooltip', 'title': 'Data de Abertura'});
     $("#dataIngresso").attr({'data-toggle': 'tooltip', 'title': 'Data de Ingresso'});
     $("#dataPrevisaoGraduacao").attr({'data-toggle': 'tooltip', 'title': 'Data de Previsão da Graduação'});

     //Não deixa que escolha uma data de previsão de graduação menor que a data atual
     $(document).on("change", "#dataPrevisaoGraduacao", function () {
        var segundo = 1000;
        var minuto = 60 * segundo;
        var hora = 60 * minuto;
        var dia = 24 * hora;

        var dataEscolhida = $(this).val().toString();
        var dataFinal = Date.parse(dataEscolhida) / dia;

        var dataIngresso = $("#dataIngresso").val().toString();
        var dataInicial = Date.parse(dataIngresso) / dia;

        dataEscolhida = Math.floor(dataEscolhida);
        dataFinal = Math.floor(dataFinal);

        if (dataInicial !== null && dataInicial !== "" && dataFinal !== null && dataFinal !== "") {
           if (dataFinal < dataInicial) {
              $(this).val(null);
              $(this).tooltip('show');
           }
        }
     });

     //Adiciona tooltips quando o formulário está em modo UPDATE

     if (STATUS === UPDATE) {
        $("#nome").attr({'data-toggle': 'tooltip', 'title': 'Nome do Empreendimento'});
        $("#razaoSocial").attr({'data-toggle': 'tooltip', 'title': 'Razão Social'});
        $("#cnpj").attr({'data-toggle': 'tooltip', 'title': 'CNPJ'});
        $("#ramoAtividade").attr({'data-toggle': 'tooltip', 'title': 'Ramo de Atividade'});
        $("#empreendedor").attr({'data-toggle': 'tooltip', 'title': 'Empreendedor'});

        //destroi tooltip
        $(".input-item").hover(function () {
           $(this).tooltip('show');
        });
     }

     $("input[type='date']").hover(function () {
        $(this).tooltip('show');
     });

     $(document).on('change', 'select', function () {
        var val = $(this).val();
        if (val !== "" && val !== null) {
           $(this).css({'border': '1px solid #AAA'});
        }
     });

     //Quando digitar algo dentro do input 'cnpj' a borda voltará a cor normal (#AAA)
     $("input").keypress(function () {
        if ($(this).val() !== "" && $(this).val() !== null) {
           $(this).css({'border': '1px solid #AAA'});
        }
     });

     //Quando houver foco sobre o input 'cnpj' o tooltip desaparecerá 
     $("input,select").focus(function () {
        $(this).css({'outline': 'transparent'});
        $(this).tooltip('destroy');
        $(this).tooltip('hide');
     });

     //Destrói tooltip
     $("input[text],select").hover(function () {
        var val = $(this).val();
        if (val !== "" && val !== null && val !== "0") {
           $(this).tooltip('destroy');
           $(this).tooltip('hide');
        }
     });

     //Checa se houve preencimento correto para submeter o formulário
     $(document).on('submit', '#form-empreendimento', function (e) {
        var nome = $("#nome").val();
        var ramoAtividade = $("#ramoAtividade").val();
        var empreendedor = $("#default").val();
        var dataAbertura = $("#dataAbertura").val();
        var dataIngresso = $("#dataIngresso").val();
        var dataPrevisaoGraduacao = $("#dataPrevisaoGraduacao").val();
        var cnpj = $("#cnpj").val().toString();


        //Validação dos demais campos
        $(this).find('.value').each(function () {

           if ($(this).val() === "" || $(this).val() === null ||
               $(this).val() === '0') {
              e.preventDefault();
              alertTooltip($(this));
           } else {
              $(this).css({'border': '1px solid #AAA'});
           }

           if ($("#default").val() === "" || $("#default").val() === null) {
              e.preventDefault();
              alertTooltip($("#empreendedor"));
           } else {
              $("#empreendedor").css({'border': '1px solid #AAA'});
           }
        });

        //Checa se todos os campos foram preenchidos antes de testar as datas
        if (nome !== "" && nome !== null &&
            ramoAtividade !== "0" && ramoAtividade !== null &&
            empreendedor !== "0" && empreendedor !== null) {
           if (cnpj === "" || cnpj === null) {
              if (dataAbertura === null || dataAbertura === "") {
                 $("#dataAbertura").attr({'disabled': 'true'});
              }

              if (dataIngresso === null || dataIngresso === "") {
                 $("#dataIngresso").attr({'disabled': 'true'});
              }

              if (dataPrevisaoGraduacao === null || dataPrevisaoGraduacao === "") {
                 $("#dataPrevisaoGraduacao").attr({'disabled': 'true'});
              }

           } else if (cnpj !== "" && cnpj !== null) { //Valida o cnpj caso o campo cnpj esteja preenchido
              var cnpjReplaced = cnpj.substr(0, 2);
              cnpjReplaced += cnpj.substr(3, 3);
              cnpjReplaced += cnpj.substr(7, 3);
              cnpjReplaced += cnpj.substr(11, 4);
              cnpjReplaced += cnpj.substr(16, 2);

              if (!validarCNPJ(cnpjReplaced)) {
                 e.preventDefault();
                 alertTooltip($("#cnpj"));
              } else {
                 if (dataAbertura === null || dataAbertura === "") {
                    $("#dataAbertura").attr({'disabled': 'true'});
                 }

                 if (dataIngresso === null || dataIngresso === "") {
                    $("#dataIngresso").attr({'disabled': 'true'});
                 }

                 if (dataPrevisaoGraduacao === null || dataPrevisaoGraduacao === "") {
                    $("#dataPrevisaoGraduacao").attr({'disabled': 'true'});
                 }

              }
           }
        }

     });


     function alertTooltip(campo) {
        $(campo).attr({'data-toggle': 'tooltip'});

        var id = $(campo).attr('id');
        $(campo).attr({'title': 'Campo Obrigatório!'});

        $("#" + id).tooltip('show');
        $(campo).css({'border': '1px solid red'});
        $(campo).tooltip('show');
     }

     function validarCNPJ(cnpj) {
        var DV1;
        var DV2;
        var soma;
        var resto;
        var i;

        if ((cnpj.length !== 14) ||
            (cnpj === "00000000000000" || cnpj === "11111111111111" ||
                cnpj === "22222222222222" || cnpj === "33333333333333" ||
                cnpj === "44444444444444" || cnpj === "55555555555555" ||
                cnpj === "66666666666666" || cnpj === "77777777777777" ||
                cnpj === "88888888888888" || cnpj === "99999999999999")) {
           return false;
        }

        soma = 0;

        for (i = 1; i <= 4; i++) {
           soma += Math.floor(cnpj.charAt(i - 1)) * (6 - i);
        }


        for (i; i <= 12; i++) {
           soma += Math.floor(cnpj.charAt(i - 1) * (14 - i));
        }


        resto = (soma - (Math.floor(soma / 11) * 11));

        if (resto < 2) {
           DV1 = 0;
        } else {
           DV1 = 11 - resto;
           cnpj += DV1;
        }

        soma = 0;

        for (i = 1; i <= 5; i++) {
           soma += Math.floor(cnpj.charAt(i - 1)) * (7 - i);
        }


        for (i; i <= 13; i++) {
           soma += Math.floor(cnpj.charAt(i - 1) * (15 - i));
        }

        resto = (soma - (Math.floor(soma / 11) * 11));
        if (resto < 2) {
           DV2 = 0;
        } else {
           DV2 = 11 - resto;
           cnpj += DV2;
        }

        if (DV1 !== Math.floor(cnpj.charAt(12))) {
           return false;
        }


        if (DV2 !== Math.floor(cnpj.charAt(13))) {
           return false;
        }

        return true;
     }

  });

























