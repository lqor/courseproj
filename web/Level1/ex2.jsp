<%@ page import="Logic.l1.Ex2" %>
<%@ page import="Logic.PatternClasses.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Aufgabe 2 </title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">
        <%@include file="../js/sendDataScript.js" %>
    </script>
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
                <h3>In dieser Aufgabe sollen Sie eine public-Klasse schreiben, die private Attribute "name" und "age" enthält.</h3>
            </td>
        </tr>

        <tr>
            <td class="inputTD">
                <textarea id="textField" name="text"></textarea> <br/><br/>
                <input type="button" id="btn" value="Code ausführen" onclick="send('/ex2Handler');"/><br/>
            </td>

            <td class="outputTD">
                <div id="foo"></div>
            </td>
        </tr>
    </table>

</div>

</body>
</html>
