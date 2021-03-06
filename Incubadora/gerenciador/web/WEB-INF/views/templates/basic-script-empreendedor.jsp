<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<script src="<c:url value="/js/painel-empreendedor/app.js"/>"></script>

<script src="<c:url value="/js/painel-empreendedor/values/duvida-values.js"/>"></script>
<script src="<c:url value="/js/painel-empreendedor/services/duvida-service.js"/>"></script>
<script src="<c:url value="/js/painel-empreendedor/controllers/duvida-controller.js"/>"></script>

<!-- // SIDE MENU // -->
<!-- // Values // -->

<script src="<c:url value="/js/painel-empreendedor/values/info-value.js"/>"></script>
<!-- // Services // -->

<script src="<c:url value="/js/painel-empreendedor/services/info-service.js"/>"></script>
<!-- // Controllers // -->

<script src="<c:url value="/js/painel-empreendedor/controllers/info-controller.js"/>"></script>

<!-- // Values // -->


<script>
   $(document).ready(function () {
      $(".button-collapse").sideNav();
      $('.time').mask('00:00');
      $("#cpf").mask('000.000.000-00');
      $("#data").mask('99/99/9999');
      $("#telefone").mask('(00)0 0000-00000');
      /*
       $(document).on('click', 'a.red', function () {
       $('#alert').openModal();
       });*/
        
        $('.dropdown-button').dropdown({
         inDuration: 300,
         outDuration: 225,
         constrain_width: false, // Does not change width of dropdown to that of the activator
         hover: false, // Activate on hover
         gutter: 0, // Spacing from edge
         belowOrigin: true, // Displays dropdown below the button
         alignment: 'left' // Displays dropdown with edge aligned to the left of button
      });
     
      $('select').material_select();
      /**
       * Configura��o do Datepicker
       * */
      $('.datepicker').pickadate({
         selectMonths: true, // Creates a dropdown to control month
         selectYears: 15, // Creates a dropdown of 15 years to control year
         // The title label to use for the month nav buttons
         labelMonthNext: 'Pr�ximo M�s',
         labelMonthPrev: 'M�s Anterior',
         // The title label to use for the dropdown selectors
         labelMonthSelect: 'Selecione o M�s',
         labelYearSelect: 'Selecione o ano',
         // Months and weekdays
         monthsFull: [
            'Janeiro', 'Fevereiro', 'Mar�o', 'Abril',
            'Maio', 'Junho', 'Julho', 'Agosto',
            'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
         monthsShort: [
            'Jan', 'Fev', 'Mar', 'Abr',
            'Mai', 'Jun', 'Jul', 'Ago',
            'Set', 'Out', 'Nov', 'Dez'],
         weekdaysFull: ['Domingo', 'Segunda', 'Ter�a', 'Quarta', 'Quinta', 'Sexta', 'S�bado'],
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