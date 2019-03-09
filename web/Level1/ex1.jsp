<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="Logic.OutputHandler" %>
<html>
<head>
    <title> Aufgabe 1 </title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<div class="container">
    <div class="sidebar">
        <h4 class="sitename"><a href="../index.jsp">SITE NAME</a></h4>
        <ul>
            <li>
                <a href="/Level1/">Level 1</a>
            </li>
            <li>Level 2</li>
            <li>Level 3</li>
            <li>Level 4</li>
            <li>Level 5</li>
            <li>Level 6</li>
            <li>Level 7</li>
            <li>Level 8</li>
        </ul>
    </div>

    <table>
        <tr>
            <td colspan="2">
                <h3>Printe "Hi, World!" auf Console:</h3>
            </td>
        </tr>

        <tr>
            <td class="inputTD">
            <form method="post" >
        <textarea name="inputTextArea" ><%--
               --%><%="public class test {\n" +
                   "   public static void main() {\n" +
                   "       //input your code here\n" +
                   "   }\n" +
                   "}"
               %><%--
        --%></textarea><br/><br/>
                <input type="submit" value="Code ausführen" />
            </form>
            </td>

            <td class="outputTD">
        <%
            String str = new OutputHandler().doPost(request, "Hi, World!");
            if(str.contains("null"))
                out.print("");
            else
                out.print(str);
        %>
                </td>
        </tr>
    </table>

</div>

</body>
</html>
