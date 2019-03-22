package Logic.HelpClasses;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("Duplicates")
public class WebCompiler {
    public Object compileString(String code, String className) throws Exception {
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

        //if we have a class without default-Contr it will threw Exception

        return urlClassLoader.loadClass(className).getConstructor().newInstance();
    }

    public Map<String, Object> compileString(String classCode1, String className1,
                                String classCode2, String className2,
                                String classCode3, String className3,
                                String classCode4, String className4) throws Exception {
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager sjfm = javaCompiler.getStandardFileManager(null, null, null);

        //class1
        File jfile1 = new File(className1+".java");
        PrintWriter pw = new PrintWriter(jfile1);
        pw.println(classCode1);
        pw.close();

        File jfile2 = new File(className2+".java");
        pw = new PrintWriter(jfile2);
        pw.println(classCode2);
        pw.close();

        File jfile3 = new File(className3+".java");
        pw = new PrintWriter(jfile3);
        pw.println(classCode3);
        pw.close();

        File jfile4 = new File(className4+".java");
        pw = new PrintWriter(jfile4);
        pw.println(classCode4);
        pw.close();


        Iterable f1 = sjfm.getJavaFileObjects(jfile1, jfile2, jfile3, jfile4);
        if(!javaCompiler.getTask(null,sjfm,null,null,null,f1).call()) {
            throw new Exception("compilation failed");
        }


        URL[] urls = new URL[]{new File("").toURI().toURL()};
        URLClassLoader urlClassLoader = new URLClassLoader(urls);
        
        Map<String, Object> objectMap = new HashMap<>();

        objectMap.put(className1, urlClassLoader.loadClass(className1).getConstructor().newInstance());
        objectMap.put(className2, urlClassLoader.loadClass(className2).getConstructor().newInstance());
        objectMap.put(className3, urlClassLoader.loadClass(className3).getConstructor().newInstance());
        objectMap.put(className4, urlClassLoader.loadClass(className4).getConstructor().newInstance());

        return objectMap;
    }
}
