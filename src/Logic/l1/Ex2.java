package Logic.l1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;


/**Zurzeit sieht so aus, dass es Sinn macht für jede Aufgabe seine eigene Klasse zu erstellen.
 * Obwohl es nicht so schön ist, ist es viel einfacher
 * */
@SuppressWarnings("Duplicates")
public class Ex2 extends HttpServlet {
    private final String noCompileResponse = "<div style='font-size:20px;font-weight: bold;'>\n" +
            "    <div style='color: #660e10;'>\n" +
            "    - Die eingegbene Klasse kompiliert sich nicht!<br/><br/>" +
            "    </div>\n";
    private StringBuilder stringResponse;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request == null || response == null)
            return;

        stringResponse = new StringBuilder();
        String body = request.getParameter("textFieldParam"); //text from Area ist als Parameter unter diesem Namen gespeichert

        //wenn der User nichts eingegeben hat
        if(body == null || body.equals("")) {
            stringResponse.append("<h3>Nein, leider falsch. Kein Input!</h3>");
        } else {
            try {
                runCode(body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        response.getWriter().write(stringResponse.toString());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); //redireck auf post-Methode, sollte aber nicht vorkommen
    }

    /**Aus patternClass entnehme ich alle Attribute und vergleiche sie mit im Code vorhandenen Attributen*/
    private void runCode(String code) throws Exception {
        String className;
        Object object = null;
        boolean classNameTask = false;
        boolean publicTask = true;

        if(code.contains("class ")) {
            className = extractClassName(code);

            if(className.equals("Person"))
                classNameTask = true;

            if(code.startsWith("class") || code.startsWith(" class") || code.startsWith("   class") || code.startsWith("  class")) {
                publicTask = false;
                code = "public " + code;
            }

        } else {
            stringResponse.append(noCompileResponse);
            return;
        }

        try {
            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager sjfm = javaCompiler.getStandardFileManager(null, null, null);

            File jfile = new File(className+".java"); //create file in current working directory

            PrintWriter pw = new PrintWriter(jfile);

            pw.println(code); //write a code to File
            pw.close();

            Iterable fO = sjfm.getJavaFileObjects(jfile);
            if(!javaCompiler.getTask(null,sjfm,null,null,null,fO).call()) { //compile the code
                throw new Exception("compilation failed");
            }

            URL[] urls = new URL[]{new File("").toURI().toURL()}; //use current working directory
            URLClassLoader urlClassLoader = new URLClassLoader(urls);

            object = urlClassLoader.loadClass(className).newInstance(); //class as Object
        } catch (Exception e) {
            stringResponse.append(noCompileResponse);
            return;
        }


        createResponseString(object, classNameTask, publicTask);
    }

    //diese Klasse schreibt zum stringResponse je nach gemachten Aufgaben, was richtig(grün) ist und was falsch(schwarzrot)
    private void createResponseString(Object object, boolean classNameTask, boolean publicTask) {
        StringBuilder responseModel = new StringBuilder("<div style='font-size:20px;font-weight: bold;'>\n" +
                "    <div style='color: #027d11;'>\n" +
                "    - Die eingegbene Klasse kompiliert sich\n" +
                "    </div>\n" +
                "\n" +
                "    <div style='color: ##publicTask;'>\n" +
                "    - Klasse ist public\n" +
                "    </div>\n" +
                "\n" +
                "    <div style='color: ##fieldsTask;'>\n" +
                "    - Es gibt nur 2 Attribute\n" +
                "    </div>\n" +
                "\n" +
                "    <div style='color: ##ageTask;'>\n" +
                "    - \"age\" ist private und vom Typ int\n" +
                "    </div>\n" +
                "\n" +
                "    <div style='color: ##nameTask;'>\n" +
                "    - \"name\" ist private und vom Typ String\n" +
                "    </div>\n" +
                "    <div style='color: ##classNameTask;'>\n" +
                "    - Die Klasse heisst \"Person\"\n" +
                "    </div>\n" +
                "</div>");
        //publicTask = object.getClass().getModifiers() == Modifier.PUBLIC;
        boolean fieldsTask =  object.getClass().getDeclaredFields().length == 2;

        if(publicTask)
            responseModel.replace(responseModel.indexOf("##publicTask;"), responseModel.lastIndexOf("##publicTask;"), "#027d11;");
        else
            responseModel.replace(responseModel.indexOf("##publicTask;"), responseModel.lastIndexOf("##publicTask;"), "#660e10;");

        if(fieldsTask)
            responseModel.replace(responseModel.indexOf("##fieldsTask;"), responseModel.lastIndexOf("##fieldsTask;"), "#027d11;");
        else
            responseModel.replace(responseModel.indexOf("##fieldsTask;"), responseModel.lastIndexOf("##fieldsTask;"), "#660e10;");



        try {
            if(object.getClass().getDeclaredField("age").getType().isAssignableFrom(Integer.TYPE) &&
                    object.getClass().getDeclaredField("age").getModifiers() == Modifier.PRIVATE)
            {
                responseModel.replace(responseModel.indexOf("##ageTask;"), responseModel.lastIndexOf("##ageTask;"), "#027d11;");
            } else
                responseModel.replace(responseModel.indexOf("##ageTask;"), responseModel.lastIndexOf("##ageTask;"), "#660e10;");

        } catch (NoSuchFieldException e) {
            responseModel.replace(responseModel.indexOf("##ageTask;"), responseModel.lastIndexOf("##ageTask;"), "#660e10;");
        }

        try {
            if(
                    object.getClass().getDeclaredField("name").getType().isAssignableFrom(String.class) &&
                            object.getClass().getDeclaredField("name").getModifiers() == Modifier.PRIVATE
            )
            {
                responseModel.replace(responseModel.indexOf("##nameTask;"), responseModel.lastIndexOf("##nameTask;"), "#027d11;");
            } else
                responseModel.replace(responseModel.indexOf("##nameTask;"), responseModel.lastIndexOf("##nameTask;"), "#660e10;");

        } catch (NoSuchFieldException e) {
            responseModel.replace(responseModel.indexOf("##nameTask;"), responseModel.lastIndexOf("##nameTask;"), "#660e10;");
        }

        if(classNameTask)
            responseModel.replace(responseModel.indexOf("##classNameTask;"), responseModel.lastIndexOf("##classNameTask;"), "#027d11;");
        else
            responseModel.replace(responseModel.indexOf("##classNameTask;"), responseModel.lastIndexOf("##classNameTask;"), "#660e10;");



        stringResponse.append(responseModel);
    }

    private String extractClassName(String code) {
        String clazz = "class ";

        String ret = code.substring(code.indexOf(clazz) + clazz.length(), code.indexOf("{"));


        ret = ret.replaceAll(" ", ""); // alle spaces entfernen

        return ret;
    }

}
