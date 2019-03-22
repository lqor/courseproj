<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> Aufgabe 3 </title>
    <style>
        <%@include file='../../css/style.css' %>
        <%@include file='../../css/lectures.css' %>
        <%@include file='../../css/ex3Styles.css' %>
    </style>
    <script type="text/javascript">
      function send(servletUrl) {
        var motor = $('#motor').val();
        var radar = $('#radar').val();
        var auto = $('#auto').val();
        var zeit = $('#zeit').val();

        $.ajax({
          url: servletUrl,
          method: 'POST',
          data: {"motor": motor, "radar":radar, "zeit":zeit, "auto":auto},

          success: function resp(data) {
            document.getElementById('foo').innerHTML = data;
          }
        });
      }
    </script>

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>

    <link rel="stylesheet" href="/codemirror/lib/codemirror.css">
    <link rel="stylesheet" href="/codemirror/theme/eclipse.css">

    <script src="/codemirror/lib/codemirror.js"></script>
    <script src="/codemirror/mode/clike/clike.js"></script>

</head>
<body>
<div class="container">
    <div class="sidebar">
        <h4 class="sitename"><a href="../index.jsp">SITE NAME</a></h4>
        <ul>
            <li>
                <a href="/Level1">Level 1</a>
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

    <div id="table-task-container-ex3">
        <table>
            <tr>
                <td colspan="2">
                    <div id="task-list">
                        <p>
                            <span style="font-weight: bold;font-style: italic;">a)</span> Entwerfen Sie eine Klasse Motor.<br/> Ein Motor wird dabei beschrieben durch eine
                            Leistung in kW <strong>(leistung)</strong>, ein Gewicht in kg <strong>(gewicht)</strong>, ein Drehmoment in
                            Nm <strong>(drehmoment)</strong>, eine Zylinderanzahl <strong>(zylinder)</strong> und einen Hubraum in Litern
                            <strong>(hubraum)</strong>. <br/>
                            Der Konstruktor sieht dabei wie folgt aus: Motor(leistung, gewicht,zylinder, hubraum)
                        </p>
                        <br/>
                        <p>
                            <span style="font-weight: bold;font-style: italic;">b)</span> Entwerfen Sie eine Klasse Automobil. <br/>Ein Automobil wird beschrieben durch die
                            Anzahl Sitzplätze (sitze), seine Farbe (farbe), seine maximale und seine momenta-
                            ne Geschwindigkeit in km/h (maxV und momV). Des Weiteren besitzt ein Automobil
                            einen Motor (motor) und wird durch sein Kennzeichen (kennzeichen) eindeutig
                            identifiziert. Der Konstruktor sieht dabei wie folgt aus: Automobil(sitze, farbe, maxV, motor).
                        </p>
                        <br/>
                        <p>
                            <span style="font-weight: bold;font-style: italic;">c)</span> Erweitern Sie die Klasse Automobil aus Teilaufgabe b) um die Methode getDistance,
                            die die zurückgelegte Wegstrecke s in Kilometer eines Automobils innerhalb einer
                            Zeitspanne dauer in Stunden ermittelt und diese zurückgibt; dauer muss beim Aufruf von getDistance parametrisierbar sein. Die Berechnung erfolgt nach der Formel
                            <span style="font-weight: bold;font-style: italic;">V = (S/dauer)</span>
                            wobei v die Geschwindigkeit des Fahrzeugs ist.
                        </p>
                        <br/>
                        <p>
                            <span style="font-weight: bold;font-style: italic;">d)</span> Entwerfen Sie eine Klasse Radar. Fügen Sie dieser Klasse die Methode detect hinzu,
                            welche die Geschwindigkeit vorbeifahrender Automobile erfasst und diese optisch
                            an den Autofahrer signalisiert; hier ist es ausreichend die erfasste Geschwindigkeit
                            eines Autos zusammen mit dem Autokennzeichen auf der Konsole auszugeben. Des
                            Weiteren werden vorbeifahrende Autos von einem Radar solange gespeichert bis ein
                            nächstes Automobil detektiert wird.
                        </p>
                        <br>
                        <p>
                            <span style="font-weight: bold;font-style: italic;">e)</span> Entwerfen Sie eine Klasse Zeitspanne, die über die Methode
                            getTimespan() verfügt. Diese Methode liefert die Differenz zwischen einer End- und Startzeit in Stunden zurück.
                            Die Objekte für End- und Startzeit werden bei der Instantiierung der
                            Klasse Zeitspanne übergeben. Der Konstruktor für die Klasse Zeitspanne sieht dabei wie folgt aus:
                            Zeitspanne(startZeit, endZeit). Erweitern Sie anschließend
                            die Klasse Automobil um eine weitere Methode getDistanceByTimespan, welche
                            basierend auf einer übergebenen Zeitspanne und der aktuellen Geschwindigkeit des
                            Automobils die zurückgelegte Distanz berechnet. Hinweis: Nutzen Sie die Klasse
                            java.util.Date der Java-Standardbibliothek zur Typisierung beider Zeitpunkte.
                            Eine Beschreibung dieser Klasse finden Sie in der Java API.
                        </p>
                        <br/>
                    </div>
                </td>
            </tr>

            <tr>
                <td class="inputTD">
                    <textarea id="motor" name="motor">
public class Motor {
    //schreibe den Code hier
}
                            </textarea> <br/><br/>

                    <textarea id="auto" name="auto">
public class Automobil {
    //schreibe den Code hier
}
                            </textarea> <br/><br/>

                        <textarea id="radar" name="radar">
public class Radar {
    //schreibe den Code hier
}
    </textarea><br/><br/>

                        <textarea id="zeit" name="zeit">
public class Zeitspanne {
    //schreibe den Code hier
}</textarea> <br/><br/>

                    <input type="button" id="btn" value="Code ausführen" onclick="send('/ex3Handler');"/><br/>
                    </form>
                </td>

                <td class="outputTD">
                    <div id="foo"></div>
                </td>
            </tr>
        </table>
    </div>

</div>
</body>
</html>