# java-exercise-check

java-exercise-check ist ein Projekt zum automatisierten Korrigieren der Java-Hausaufgaben von Studenten.

Es gibt auf der Seite eine Form, wo man den Code schreiben kann. Auf derselben Seite findet man die Beschreibung der Aufgabe. 

Wenn man den Code in die Form schreibt und die Lösung an Server verschickt, fängt ein Servlet HTTTP-Request mit dem Code als String an.

Danach wird der Code mit Hilfe von Java-Reflection auf dem Server ausgeführt und mit vorher geschriebenen Tests analysiert. Das alles erfolgt in Runtime, in dieser Zeit kann der Server natürlich auch andere Abragen bearbeiten.

Im letzten Schritt werden alle Test-Ergebnisse gesammelt, die Antwort formuliert und and Benutzer geschickt

## Warum genau dieses Projekt?
Die Anfrage an E-Learning wird immer höher, die Anzahl an Hausaufgaben, die korrigiert werden müssen, wird auch immer größer. Deshalb wollte ich rausfinden, wie schwer es ist, den Code in Runtime zu analysieren und auf welcher Weise es überhaupt in Java gemacht werden kann.
