function send(servletUrl) {
    var textValue = $('#textField').val();
    $.ajax({
        url: servletUrl,
        method: 'POST',
        data: {"textFieldParam": textValue}, /* "textFieldParam" wird als Parameter in Servlet ansprechbar sein*/

        success: function resp(data) {
            document.getElementById('foo').innerHTML = data;
        }
    });
}