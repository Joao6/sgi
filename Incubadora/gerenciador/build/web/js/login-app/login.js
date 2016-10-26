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


})();