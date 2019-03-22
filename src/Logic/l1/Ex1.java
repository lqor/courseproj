package Logic.l1;

import Logic.HelpClasses.WebCompiler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;

/**patternString == String, der ausgegeben werden muss. Die Klasse behandelt den Request und überprüft, ob die Ausgabe
 * auf Console gleich dem patternString ist
 * */
@SuppressWarnings({"Duplicates"})
public class Ex1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(doPost(req, resp,"Hi, World!", "textFieldParam"));
    }

    private String doPost(javax.servlet.http.HttpServletRequest request, HttpServletResponse response,String patternString, String paramName) throws javax.servlet.ServletException, IOException {
        String body = request.getParameter(paramName); //text from Area
        if(body == null || body.equals("")) {
            return "<div style='font-size:20px;font-weight: bold;color: #660e10;'><br/>"+"Nein, leider falsch. Kein Input!</div>";

        }

        StringBuilder ret = new StringBuilder(); //return-String

        try {
            boolean result = runCode(body, patternString);


            if(result) {
                ret.append("<div style='font-size:20px;font-weight:bold;color: #027d11;'>");
                ret.append("Ja, das war richtig.");
            }
            else {
                ret.append("<div style='font-size:20px;font-weight: bold;color: #660e10;'><br/>").append("Nein, leider falsch.");
            }
            ret.append("</div>");

            return ret.toString();
        } catch (Exception e) {
            ret.append("<div style='font-size:20px;font-weight: bold;color: #660e10;'><br/>").append("Nein, leider falsch.").append("</div><br/>");

            return ret.toString();
        }
    }

    private boolean runCode(String code, String patternString) throws Exception {
        // patternStriing = String, mit dem die Ausgabe verglichen wird
        Object object = null;
        WebCompiler webCompiler = new WebCompiler();

        try {
            object = webCompiler.compileString(code, "Example1");
        } catch (Exception e) {
            return false;
        }

        String methodResult = getResultFromMethode(object);

        return methodResult.equals(patternString);
    }

    //this Object must be from "urlClassLoader.loadClass("test").newInstance();". It represents the method-Instace and can be
    //used to test execute-Result
    //Return: was auf der Console geprintet werden soll
    private String getResultFromMethode(Object object) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        if(object == null)
            return "";

        File file = new File("Buffer.txt");
        PrintStream printStream = new PrintStream(file);

        PrintStream stdout = System.out; //Hier ist System.out immer noch die nirmale Ausgabe(wir müssen sie sichern)
        System.setOut(printStream); //Wir können aber mittels setOut das Ergebnis zum File speichern

        // die Methode wird ausgeführt und das Ergebnis mittels System.out.println()... nach .out printen, was
        // bei uns dem File entspricht
        String[] params = null;
        object.getClass().getMethod("main", String[].class).invoke(null, (Object) params);

        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(file.toPath()).takeWhile(line -> line != null).forEach(stringBuilder::append); //read all from File

        System.setOut(stdout); //alte System.out speichern

        file.delete();
        return stringBuilder.toString();
    }
}
