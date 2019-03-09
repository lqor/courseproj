$(document).ready(function() {
    $('#btn').click(function() {

        var textValue = $('#textField').val();
        $.ajax({
            url: '/classHandler',  /* zu dieser Adresse soll einen Servlet gemappt werden. Er ist fürBearbeitung eines Requestes zuständig*/
            method: 'POST',
            data: {"textFieldParam": textValue}, /* "textFieldParam" wird als Parameter in Servlet ansprechbar sein*/

            success: function resp(data) {
                document.getElementById('foo').innerHTML = data;
            }
        });
    });
});
