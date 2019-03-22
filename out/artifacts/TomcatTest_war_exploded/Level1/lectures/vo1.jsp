<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Vorlesung 1 </title>
    <style>
        <%@include file='../../css/style.css' %>
        <%@include file='../../css/lectures.css' %>
    </style>
    <script src="https://cdn.jsdelivr.net/gh/google/code-prettify@master/loader/run_prettify.js"></script>
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
    <div class="main">
        <div class="lectureContent">
            <h3 style="color: #0f3b18">Objektbasiertes Programmieren</h3>
            <p>
                Java ist eine objektorientierte Programmier-Sprache, man soll deshalb zuerst die Grundkonzepte der objektorientierten
                Programmierung lernen. Wir fangen deshalb mit dem ersten Konzept und zwar wie werden die Klassen in Java erstellt und verwaltet
            </p>
            <p>
                Unsere Welt besteht aus Objekten. Die Idee ist jetzt, dass man diese Objekte auch im Computer spiegeln kann. Die Grundidee
                der Objektorientierung ist, dass diese Objekte miteinander kommunizieren und Daten austauschen. Der Sicht lautet also <i>"<span class="text-red">Wer</span> macht <span class="text-lightgreen">was?</span>"</i><br/><br/><br/><br/>
            </p>
            <img src="../../img/vo1img1.png" align="left">
            <p>
                Nun wollen wir anhand eines Beispieles das objektbasierte Programmieren näher erklären. Wie könnte man z.B eine Bibliothek modellieren?
                Erstmal ganz theoretisch. Was ist der Kleinste Baustein unserer imaginären <span class="text-lightgreen">Bibliothek</span>? Ein <span class="text-red">Buch</span>!<br/><br/>

                Ein <span class="text-red">Buch</span> kennzeichnet, <span class="text-darkyellow">wer sein Autor ist, wie der Titel lautet oder auch wie viele Blätter es hat</span>. Ein Buch in einer <span class="text-lightgreen">Bibliothek</span> hat auch sein Zweck und nämlich:
                es kann ausgeliehen oder zurückgegeben werden. Aha! Das <span class="text-red">Buch</span> enthält also irgendwelche Daten und kann z.B ausgeliehen werden, was einer Tätigkeit entspricht.
                <br/><br/><br/><br/><br/><br/><br/>
            </p>
            <img src="../../img/vo1img2.png" align="left">
            <p>
                Alle in der Bibliothek vorhandene Bücher besitzen aber über bestimmte gemeinsamme Eigenschaften. Sie alle haben Autoren, eine gewisse Anzahl an Seiten usw. Deshalb lässt sich ein konkretes Buch zu <span class="text-darkyellow">"irgendeinem" Buch</span> verallgemeinen.
                Dieses abstraktes Buch hat <span class="text-viola">Attribute</span>(z.B Autor, Titel), die aber nicht spezifiziert sind.
                Außerdem hat das verallgemeinte Buch <span class="text-viola">Methode</span>- Tätigkeiten eines Buches<br/><br/>

                <span class="text-darkyellow">Das Konzept "Buch"</span> eintspricht einer <span class="text-viola">Klasse</span> in Java,
                das konkrete Buch entspricht einem <span class="text-viola">Objekt</span>.  <span class="text-viola">Klassen</span>
                bechreiben das allgemeine Verhalten (z.B dass ein Buch einen Titel hat), also alle mögliche Bücher.
                Objekte dagegen spezifizieren die Information (z.B dass der Titel "Harry Potter" lautet) und bescreiben
                deshalb nur ein konkretes Buch.
            </p>
            <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
            <p>
                Unten ist ein Beispiel für eine gültige Java-Klasse:<br/>
                <pre class="prettyprint"><%--

                --%>public class Buch {<br/>  String titel;<br/>  int pages;<br/>}</pre><br/>
                Ein neues Objekt der Klasse erstellt man mit dem Schlüssenlword <span class="text-viola">"new"</span>
                Also <span class="text-viola">"new Buch()"</span> erzeugt eine neue <span class="text-darkyellow">Instanz</span>
                (ein neues Objekt) der  <span class="text-darkyellow">Klasse</span> "Buch".
            </p>
        </div>
    </div>
</div>
</body>
</html>