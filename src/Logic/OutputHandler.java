package Logic;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.util.Arrays;

@SuppressWarnings({"Duplicates"})
public class OutputHandler {
    /**patternString == String, der ausgegeben werden muss. Die Klasse behandelt den Request und überprüft, ob die Ausgabe
     * auf Console gleich dem patternString ist
     * */
    public String doPost(javax.servlet.http.HttpServletRequest request, String patternString) throws javax.servlet.ServletException, IOException {
        String body = request.getParameter("inputTextArea"); //text from Area

        StringBuilder ret = new StringBuilder(); //return-String

        try {
            boolean result = runCode(body, patternString);

            ret.append("<h3>");
            if(result)
                ret.append("Ja, das war richtig. Dein Input: ");
            else
                ret.append("Nein, leider falsch. Dein Input: ");
            ret.append("</h3>").append(body);

            return ret.toString();
        } catch (Exception e) {
            ret.append("<h3><br/>").append("Nein, leider falsch. Dein Input:<br/>").append("</h3><br/>").append(body);

            return ret.toString();
        }
    }

    //Until "getResultFromMethode" is copypast
    private boolean runCode(String code, String patternString) throws Exception {
        // patternStriing = String, mit dem die Ausgabe verglichen wird

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = javaCompiler.getStandardFileManager(null, null, null);

        File jfile = new File("test.java"); //create file in current working directory

        PrintWriter pw = new PrintWriter(jfile);
        pw.println(code); //write a code to File
        pw.close();

        Iterable fO = sjfm.getJavaFileObjects(jfile);
        if(!javaCompiler.getTask(null,sjfm,null,null,null,fO).call()) { //compile the code
            throw new Exception("compilation failed");
        }

        URL[] urls = new URL[]{new File("").toURI().toURL()}; //use current working directory
        URLClassLoader urlClassLoader = new URLClassLoader(urls);

        Object object = urlClassLoader.loadClass("test").newInstance(); //class as Object

        String methodResult = getResultFromMethode(object);

        return methodResult.equals(patternString);
    }

    //this Object must be from "urlClassLoader.loadClass("test").newInstance();". It represents the method-Instace and can be
    //used to test execute-Result
    //Return: iutput in string-form
    private String getResultFromMethode(Object object) throws IOException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        File file = new File("Buffer.txt");
        PrintStream printStream = new PrintStream(file);

        PrintStream stdout = System.out; //Hier ist System.out immer noch die nirmale Ausgabe(wir müssen sie sichern)
        System.setOut(printStream); //Wir können aber mittels setOut das Ergebnis zum File speichern

        // die Methode wird ausgeführt und das Ergebnis mittels System.out.println()... nach .out printen, was
        // bei uns dem File entspricht
        object.getClass().getMethod("main").invoke(object);

        StringBuilder stringBuilder = new StringBuilder();
        Files.lines(file.toPath()).takeWhile(line -> line != null).forEach(stringBuilder::append); //read all from File

        System.setOut(stdout); //alte System.out speichern

        file.delete();
        return stringBuilder.toString();
    }
}
