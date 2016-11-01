<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Scripts -->
<!-- // JQuery v. 2 // -->
<script src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/js/jquery.mask.js"/>"></script>
<script src="<c:url value="/js/materialize.js"/>"></script>
<!-- // Angular CORE v. 5 // -->
<script src="<c:url value="/js/angular.js"/>"></script>
<script src="<c:url value="/js/angular-route.js"/>"></script>
<script src="<c:url value="/js/table-pagination-app/app/paginacao-app.js"/>"></script>
<!-- // Angular app //-->
<script src="<c:url value="/js/painel-avaliador/app.js"/>"></script>

<!-- JOAO PEDRO ###############-->
<!-- // SIDE MENU // -->
<!-- // Values // -->

<script src="<c:url value="/js/painel-avaliador/value/info-value.js"/>"></script>
<!-- // Services // -->

<script src="<c:url value="/js/painel-avaliador/service/info-service.js"/>"></script>
<!-- // Controllers // -->

<script src="<c:url value="/js/painel-avaliador/controller/info-controller.js"/>"></script>

<!-- // Values // -->


<script>
   $(document).ready(function () {
      $(".button-collapse").sideNav();
      $('.time').mask('00:00');
      $("#cpf").mask('000.000.000-00');
      $("#telefone").mask('(00)0 0000-0000');
      /*
       $(document).on('click', 'a.red', function () {
       $('#alert').openModal();
       });*/
     
      $('select').material_select();
      /**
       * Configuração do Datepicker
       * */
      $('.datepicker').pickadate({
         selectMonths: true, // Creates a dropdown to control month
         selectYears: 15, // Creates a dropdown of 15 years to control year
         // The title label to use for the month nav buttons
         labelMonthNext: 'Próximo Mês',
         labelMonthPrev: 'Mês Anterior',
         // The title label to use for the dropdown selectors
         labelMonthSelect: 'Selecione o Mês',
         labelYearSelect: 'Selecione o ano',
         // Months and weekdays
         monthsFull: [
            'Janeiro', 'Fevereiro', 'Março', 'Abril',
            'Maio', 'Junho', 'Julho', 'Agosto',
            'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
         monthsShort: [
            'Jan', 'Fev', 'Mar', 'Abr',
            'Mai', 'Jun', 'Jul', 'Ago',
            'Set', 'Out', 'Nov', 'Dez'],
         weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
         weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
         // Materialize modified
         weekdaysLetter: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
         // Today and clear
         today: 'Hoje',
         clear: 'Limpar',
         close: 'Fechar',
         // The format to show on the `input` element
         format: 'yyyy-mm-dd'
      });

   });
</script>