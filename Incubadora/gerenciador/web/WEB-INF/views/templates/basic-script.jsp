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
<script src="<c:url value="/js/painel-administrativo/app.js"/>"></script>


<!-- // SIDE MENU // -->
<script src="<c:url value="/js/side-menu-app/side-menu-app.js"/>"></script>
<!-- ////////////// --->


<!-- // Values // -->
<script src="<c:url value="/js/painel-administrativo/values/duvida-values.js"/>"></script>
<!-- // Services // -->

<%--<script src="<c:url value="/js/painel-administrativo/service/duvida-websocket-service.js"/>"></script> --%>
<script src="<c:url value="/js/painel-administrativo/service/duvida-service.js"/>"></script>
<!-- // Filters // -->
<script src="<c:url value="/js/painel-administrativo/filter/painel-admin-filters.js"/>"></script>

<!-- // Controllers // -->
<script src="<c:url value="/js/painel-administrativo/controller/duvida-controller.js"/>"></script>

<!-- // Configs // -->
<script src="<c:url value="/js/painel-administrativo/config/painel-admin-config.js"/>"></script>



<script>
   $(document).ready(function () {    

      $('.dropdown-button').dropdown({
         inDuration: 300,
         outDuration: 225,
         constrain_width: false, // Does not change width of dropdown to that of the activator
         hover: true, // Activate on hover
         gutter: 0, // Spacing from edge
         belowOrigin: false, // Displays dropdown below the button
         alignment: 'left' // Displays dropdown with edge aligned to the left of button
      });

      $(".button-collapse").sideNav();
      $('.time').mask('00:00');
      
       var configModal = {
              dismissible: false, // Modal can be dismissed by clicking outside of the modal
              opacity: .5, // Opacity of modal background
              in_duration: 1, // Transition in duration
              out_duration: 1, // Transition out duration
              ready: function () {
                 return;
              }, // Callback for Modal open
              complete: function () {

              } // Ca
           };
      
      $(".modal-trigger").leanModal(configModal);

      /*
       $(document).on('click', 'a.red', function () {
       $('#alert').openModal();
       });*/


      $('select').material_select();
      $('input#input-edit').characterCounter();

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

      
     $('.tooltipped').tooltip({delay: 50});

   });
</script>