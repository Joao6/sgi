
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>

<!-- CSS  -->
<link rel="icon" type="image/png" sizes="32x20" href="<c:url value="/img/favicon-32x32.png"/>">

<link href="<c:url value="/css/materialize.css"/>" type="text/css" rel="stylesheet" media="screen,projection" />
<link href="<c:url value="/css/style.css"/>" type="text/css" rel="stylesheet" media="screen,projection" /> 
<link rel="stylesheet" href="<c:url value="/css/login-app/login.css"/>"/>
<!-- // Scripts // -->
<script src="<c:url value="/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/js/jquery.mask.js"/>"></script>
<script src="<c:url value="/js/materialize.js"/>"></script>
<script src="<c:url value="/js/md5.min.js"/>"></script>

<script>
   $(document).ready(function () {
      $('.slider').slider({full_width: true});
      // Pause slider
      $('.slider').slider('pause');
      // Start slider
      $('.slider').slider('start');


      $('.parallax').parallax();
   });

</script>

<!-- //Angular // -->  
<script src="<c:url value="/js/angular.js"/>"></script>
<!-- // App // -->
<script src="<c:url value="/js/login-app/app/app.js"/>"></script>
<!-- // Values // -->
<script src="<c:url value="/js/login-app/values/login-value.js"/>"></script>
<!-- // Services// -->
<script src="<c:url value="/js/login-app/services/login-service.js"/>"></script>
<!-- // Controllers // -->
<script src="<c:url value="/js/login-app/controllers/login-controller.js"/>"></script>
<!-- // Login // -->
<script src="<c:url value="/js/login-app/login.js"/>"></script>
<!-- // Directives // -->
<script src="<c:url value="/js/login-app/directives/modalLogin.js"/>"></script>
