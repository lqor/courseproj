<%--
  Created by IntelliJ IDEA.
  User: igor
  Date: 03.03.19
  Time: 14:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="Logic.PostHandler" %>
<html>
<head>
    <title> Example 1 </title>
</head>
<body>
    <form action="" method="post">
        Printe "Hi, World!" auf Console:<br/>
        <textarea rows="10" cols="50" name="inputTextArea">public class test {
  public static void main(){
    //input your code here
  }
}</textarea><br/>
        <input type="submit" value="Go!" />
    </form>

    <div id="results" onclick="">
        <%
            String str = new PostHandler().doPost(request, response);
            /**Hier ist das Problem. Wenn der User noch nichts eingegeben hat, wird PostHandler() als body diese Dafault-Daten aus
             * Textarea erhalten. Danach wird überprüft, was der User aus Console printet und es is "null". Es wird deshalb null angezeigt
             *
             * Eine mögliche Lösung wäre, dass die Ergebnisse nur beim Clicken auf "Go!"-Button  gezeigt werden
             * */
            if(str.contains("null"))
                out.print("");
            else
                out.print(str);
        %>
    </div>

</body>
</html>
