

$( document ).ready(function() {
    // $('#table_state tbody tr').each(function (a,b) {
    //     $(b).click(function () {
    //         console.log("Ok")
    //         $('.table_state tbody tr').css('background', '#ffffff');
    //         $(this).css('background', '#ff0000');
    //     });
    // });

    $('table tr').each(function (a, b) {
        $(b).click(function () {
            // console.log("ok")
            $('table tr').css('background', '#ffffff');
            $(this).css('background', '#ececec');
        });
    });
  });