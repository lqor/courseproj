<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Level 1 </title>
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<%--

Liste von behandelten Themen:
1) Klassenentwurf
2) getters-setters
3) Konstruktoren
4) Methodenentwurf
5) toString()
6) Datum-Klasse


Hier ist eine gute Highlighing für
<script src="https://cdn.jsdelivr.net/gh/google/code-prettify@master/loader/run_prettify.js"></script>
<pre class="prettyprint">class Voila {
public:
  // Voila
  static const string VOILA = "Voila";

  // will not interfere with embedded <a href="#voila2">tags</a>.
}</pre>

--%>
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
    <div class="main">
        <a href="/Level1/lectures/vo1"><div class="box">Vorlesung 1</div></a>
        <a href="/Level1/exercises/ex1"><div class="box">Aufgabe 1</div></a>
        <a href="/Level1/lectures/vo2"><div class="box">Vorlesung 2</div></a>
        <a href="/Level1/exercises/ex2"><div class="box">Aufgabe 2</div></a>
        <a href="/Level1/lectures/vo3"><div class="box">Vorlesung 3</div></a>
        <a href="/Level1/exercises/ex3"><div class="box">Aufgabe 3</div></a>
        <a href="/Level1/lectures/vo4"><div class="box">Vorlesung 4</div></a>
        <a href="/Level1/exercises/ex4"><div class="box">Aufgabe 4</div></a>
    </div>
</div>
</body>
</html>