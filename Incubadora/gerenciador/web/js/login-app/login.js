  (function () {
     $(document).ready(function () {
        $('.modal-trigger').leanModal({
           dismissible: false, // Modal can be dismissed by clicking outside of the modal
           opacity: .5, // Opacity of modal background
           in_duration: 70, // Transition in duration
           out_duration: 60, // Transition out duration
           ready: function () {
              return;
           }, // Callback for Modal open
           complete: function () {
              //location.reload();
           } // Callback for Modal close
        });
     });

     $("form").css({'overflow': 'none'});

     $('select').material_select();
     $('.dropdown-button').dropdown({
        inDuration: 300,
        outDuration: 225,
        constrain_width: false, // Does not change width of dropdown to that of the activator
        hover: true, // Activate on hover
        gutter: 0, // Spacing from edge
        belowOrigin: false, // Displays dropdown below the button
        alignment: 'left' // Displays dropdown with edge aligned to the left of button
     }
     );

  })();