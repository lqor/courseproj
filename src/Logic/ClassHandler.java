package Logic;

import Logic.PatternClasses.PatternClassForEx1;

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
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

@SuppressWarnings("Duplicates")
public class ClassHandler extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write(doPost(request, "Person", new PatternClassForEx1()));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private String doPost(javax.servlet.http.HttpServletRequest request, String className, Object patternClass) throws javax.servlet.ServletException, IOException {
        String body = request.getParameter("textFieldParam"); //text from Area

        StringBuilder ret = new StringBuilder(); //return-String

        if(body == null || body.equals(""))
            return null;

        try {
            boolean result = runCode(body, className, patternClass);

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

    /**Aus patternClass entnehme ich alle Attribute und vergleiche sie mit im Code vorhandenen Attributen*/
    private boolean runCode(String code, String className, Object patternClass) throws Exception {
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

        Object object = urlClassLoader.loadClass(className).getConstructor().newInstance(); //class as Object

        //Attribute
        ArrayList<Field> patternFields = new ArrayList<Field>(Arrays.asList(patternClass.getClass().getDeclaredFields()));
        ArrayList<Field> ourFields = new ArrayList<Field>(Arrays.asList(object.getClass().getDeclaredFields()));


        //Methode
        ArrayList<Method> patternMethods = new ArrayList<Method>(Arrays.asList(patternClass.getClass().getDeclaredMethods()));
        ArrayList<Method> usersMethods = new ArrayList<Method>(Arrays.asList(object.getClass().getDeclaredMethods()));

        //Kostruktore
        ArrayList<Constructor> patternConstructors = new ArrayList<Constructor>(Arrays.asList(patternClass.getClass().getDeclaredConstructors()));
        ArrayList<Constructor> usersConstructors = new ArrayList<Constructor>(Arrays.asList(object.getClass().getDeclaredConstructors()));

        boolean ret = compareArraysOfFields(patternFields, ourFields)
                        && object.getClass().getName().equals(className)
                        && compareArraysOfMethods(patternMethods, usersMethods);
        return ret;
    }


    /**Diese Methode vergleicht 2 Arrays von Attribute der Klasse. !!Es sind keine zusaetzliche Attribute gestatet */
    private boolean compareArraysOfFields(ArrayList<Field> patternFields, ArrayList<Field> ourFields) {
        if(patternFields.size() != ourFields.size())
            return false; //d.h im Weiteren nur Arrays der gleichen Länge

        Iterator<Field> itr = patternFields.iterator();
        while (itr.hasNext()) {
            Field field = itr.next();
            for (int i = 0; i < ourFields.size(); i++) {
                if(field.getModifiers() == ourFields.get(i).getModifiers()
                        && (field.getName().equals(ourFields.get(i).getName()))
                        && (field.getType().equals(ourFields.get(i).getType()))
                ) {
                    //wenn name, modifikator und Typ übereinstimmen
                    itr.remove();
                    ourFields.remove(ourFields.get(i));
                    break;
                }
            }
        }

        return patternFields.size() == 0 && ourFields.size() == 0;
    }

    private boolean compareArraysOfMethods(ArrayList<Method> patternMethods, ArrayList<Method> usersMethods) {
        if(patternMethods.size() != usersMethods.size())
            return false; //d.h im Weiteren nur Arrays der gleichen Länge

        Iterator<Method> itr = patternMethods.iterator();
        while (itr.hasNext()) {
            Method patternMethod = itr.next();
            for (int i = 0; i < usersMethods.size(); i++) {
                Method usersMethod = usersMethods.get(i);
                //todo: hier einbisschen schlecht geschrieben, vll etwas zu viel oder zu wenig
                if(patternMethod.getReturnType().equals(usersMethod.getReturnType())
                    && patternMethod.getModifiers() == usersMethod.getModifiers()
                        && patternMethod.getName().equals(usersMethod.getName())
                        && patternMethod.getParameterCount() == usersMethod.getParameterCount()
                        && Arrays.equals(patternMethod.getParameterTypes(), usersMethod.getParameterTypes())
                )
                {
                    itr.remove();
                    usersMethods.remove(usersMethods.get(i));
                    break;
                }
            }
        }

        return patternMethods.size() == usersMethods.size();
    }
}
