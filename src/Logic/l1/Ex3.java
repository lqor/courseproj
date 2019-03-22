package Logic.l1;

import Logic.HelpClasses.CodeRefactor;
import Logic.HelpClasses.WebCompiler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


@SuppressWarnings("Duplicates")
public class Ex3 extends HttpServlet {
    private final String noCompileMotor = "<div style='font-size:20px;font-weight: bold;'>\n" +
            "    <div style='color: #660e10;'>\n" +
            "    - Die eingegbene Klasse Motor kompiliert sich nicht!<br/><br/>" +
            "    - Überprüfen Sie, ob Sie die vorgegebene Klasse nicht verändert haben <br/>"+
            "    </div><br/>\n";
    private final String noCompileRadar = "<div style='font-size:20px;font-weight: bold;'>\n" +
            "    <div style='color: #660e10;'>\n" +
            "    - Die eingegbene Klasse Radar kompiliert sich nicht!<br/><br/>" +
            "    - Überprüfen Sie, ob Sie die vorgegebene Klasse nicht verändert haben <br/>"+
            "    </div><br/>\n";
    private final String noCompileAuto = "<div style='font-size:20px;font-weight: bold;'>\n" +
            "    <div style='color: #660e10;'>\n" +
            "    - Die eingegbene Klasse Automobil kompiliert sich nicht!<br/><br/>" +
            "    - Überprüfen Sie, ob Sie die vorgegebene Klasse nicht verändert haben <br/>"+
            "    </div><br/>\n";
    private final String noCompileZeit = "<div style='font-size:20px;font-weight: bold;'>\n" +
            "    <div style='color: #660e10;'>\n" +
            "    - Die eingegbene Klasse Zeitspanne kompiliert sich nicht!<br/><br/>" +
            "    - Überprüfen Sie, ob Sie die vorgegebene Klasse nicht verändert haben<br/>"+
            "    </div><br/>";
    private StringBuilder stringResponse;

    //for test
    private ArrayList<String> reqAttr;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initialize();

        String motor = req.getParameter("motor");
        String radar = req.getParameter("radar");
        String auto = req.getParameter("auto");
        String zeit = req.getParameter("zeit");

        runCode(motor, radar, auto, zeit);

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().println(stringResponse);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    public void initialize() {
        reqAttr = new ArrayList<String>();
        stringResponse = new StringBuilder();

        reqAttr.add("Automobil");
        reqAttr.add("Motor");
        reqAttr.add("Radar");
    }

    private void runCode(String motor, String radar, String auto, String zeit) {
        motor = CodeRefactor.refactorCode(motor, "Motor");
        radar = CodeRefactor.refactorCode(radar, "Radar");
        auto = CodeRefactor.refactorCode(auto, "Automobil");
        zeit = CodeRefactor.refactorCode(zeit, "Zeitspanne");

        Object motorObj = null;
        Object radarObj = null;
        Object autoObj = null;
        Object zeitObj = null;

        WebCompiler webCompiler = new WebCompiler();

        Map<String, Object> map = null;
        try {
             map = webCompiler.compileString(
                     motor,"Motor",
                    auto,"Automobil",
                    radar,"Radar",
                    zeit, "Zeitspanne"
            );
        } catch (Exception e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                    "    - Das gesamte Projekt kompiliert sich nicht! Beachten Sie, dass einige Klasse voneinander abhängen<br/><br/>" +
                    "</div><br/>"
            );
            return;
        }

        motorObj = map.get("Motor");
        radarObj = map.get("Radar");
        autoObj = map.get("Automobil");
        zeitObj = map.get("Zeitspanne");

        System.out.println(stringResponse);
        analyseClasses(autoObj, motorObj, radarObj, zeitObj);
    }

    private void analyseClasses(Object auto, Object motor, Object radar, Object zeit) {
        try {
            testAutomobilGetDistance(auto, motor);
            testRadarDetect(auto, motor, radar);
            testAccessModifier(auto, motor, radar);
            testZeitspanne(zeit);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    private Object initMotor(Object motor,
                             int pLeistung,
                             int pGewicht,
                             int pDrehmoment,
                             int pZylinder,
                             double pHubraum) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return motor.getClass()
                .getConstructor(Integer.TYPE,Integer.TYPE, Integer.TYPE,Integer.TYPE, Double.TYPE)
                .newInstance(pLeistung, pGewicht, pDrehmoment, pZylinder, pHubraum);
    }

    private Object initAutomibil(Object auto, int sitze, String farbe, int maxV, Object motor) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return auto.getClass().getConstructor(Integer.TYPE, String.class, Integer.TYPE, motor.getClass())
                .newInstance(sitze, farbe, maxV, motor);
    }

    /**Tests*/
    private void testAutomobilGetDistance(Object auto, Object motor) throws Exception {
        try {
            motor = motor.getClass()
                    .getConstructor(Integer.TYPE,Integer.TYPE, Integer.TYPE,Integer.TYPE, Double.TYPE)
                    .newInstance(77, 1200, 150, 4, 2.0);

            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Konstruktor der Klasse Motor ist richtig!<br/><br/>" +
                            "</div><br/>"
            );
        } catch (NoSuchMethodException e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                    "    - Konstruktor der Klasse Motor ist falsch!<br/><br/>" +
                    "</div><br/>"
            );
            return;
        }

        try {
            auto = initAutomibil(auto,5, "Rot", 190, motor);

            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Konstruktor der Klasse Automobil ist richtig!<br/><br/>" +
                            "</div><br/>"
            );
        } catch (NoSuchMethodException e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Konstruktor der Klasse Automobil ist falsch!<br/><br/>" +
                            "</div><br/>"
            );
            return;
        }


        Field[] fields = auto.getClass().getDeclaredFields();
        String kennzeichen = "";
        for (Field f : fields) {
            if (f.getName().toLowerCase().equals("momv")) {
                f.setAccessible(true);
                f.setInt(auto, 100);
            } else if (f.getName().toLowerCase().equals("kennzeichen")) {
                f.setAccessible(true);
                f.set(auto, "M-EX-X2013");
                kennzeichen = (String) f.get(auto);
            }
        }

        if(
                (int) auto.getClass()
                        .getDeclaredMethod("getDistance", Integer.TYPE)
                        .invoke(auto,10) == 1000
        ) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                    "    - Die Automobil-Klasse berechnet die Distanz richtig!<br/><br/>" +
                    "</div><br/>"
            );
        } else {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Die Automobil-Klasse berechnet die Distanz falsch!<br/><br/>" +
                            "</div><br/>"
            );
        }
    }

    private void testRadarDetect(Object auto, Object motor, Object radar) throws Exception {
        try {
            motor = initMotor(motor,77, 1200, 150, 4, 2.0);
            auto = initAutomibil(auto,5, "Rot", 190, motor);
            radar = radar.getClass().getConstructor().newInstance();
        } catch (Exception e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Radar test falsch!<br/><br/>" +
                            "</div><br/>"
            );
            return;
        }

        Field[] fields = auto.getClass().getDeclaredFields();
        for (Field f : fields) {
            if (f.getName().toLowerCase().equals("momv")) {
                f.setAccessible(true);
                f.setInt(auto, 100);
            } else if (f.getName().toLowerCase().equals("kennzeichen")) {
                f.setAccessible(true);
                f.set(auto, "M-EX-X2013");
            }
        }
        //radar.detect(automobilBlau);
        radar.getClass().getMethod("detect", auto.getClass()).invoke(radar, auto);

        fields = radar.getClass().getDeclaredFields();
        Field check4Field = null;
        for (Field field : fields) {
            if (field.getName().toLowerCase().equals("automobil")) {
                field.setAccessible(true);
                check4Field = field;
                break;
            }
        }

        if (check4Field != null) {
            //assertEquals("Automobils werden vom Radar nicht gespeichert!", automobilBlau, check4Field.get(radar));
            if(check4Field.get(radar).equals(auto))
                stringResponse.append(
                        "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                                "    - Richtig! Automobils werden vom Radar gespeichert!<br/><br/>" +
                                "</div><br/>"
                );
            else
                stringResponse.append(
                        "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                                "    - Automobils werden vom Radar nicht gespeichert!<br/><br/>" +
                                "</div><br/>"
                );
            check4Field.setAccessible(false);
        } else {
            // fail("Attribut \"automobil\" in Klasse Radar nicht gefunden.");
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Attribut \"automobil\" in Klasse Radar nicht gefunden.!<br/><br/>" +
                            "</div><br/>"
            );
        }
    }

    private void testAccessModifier(Object auto, Object motor, Object radar) throws Exception {
        boolean allPrivateAuto = true;
        boolean allPrivateMotor = true;
        boolean allPrivateRadar = true;

        /**MOTOR*/
        for (Field f : motor.getClass().getDeclaredFields())
            if (!Modifier.isPrivate(f.getModifiers()))
                allPrivateMotor = false;
        if(allPrivateMotor)
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Alle Attribute der Klasse \"Motor\" sind private.<br/><br/>" +
                            "</div><br/>"
            );
        else
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Alle Attribute der Klasse \"Motor\" sind private.<br/><br/>" +
                            "</div><br/>"
            );

        /**AUTO*/
        for (Field f : auto.getClass().getDeclaredFields())
            if (!Modifier.isPrivate(f.getModifiers()))
                allPrivateAuto = false;
        if(allPrivateAuto)
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Alle Attribute der Klasse \"Automobil\" sind private.<br/><br/>" +
                            "</div><br/>"
            );
        else
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Alle Attribute der Klasse \"Automobil\" sind private.<br/><br/>" +
                            "</div><br/>"
            );


        /**RADAR*/
        for (Field f : radar.getClass().getDeclaredFields())
            if (!Modifier.isPrivate(f.getModifiers()))
                allPrivateRadar = false;

        if(allPrivateRadar)
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Alle Attribute der Klasse \"Radar\" sind private.<br/><br/>" +
                            "</div><br/>"
            );
        else
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Alle Attribute der Klasse \"Radar\" sind private.<br/><br/>" +
                            "</div><br/>"
            );
    }

    private void testZeitspanne(Object zeit) {
        Method meth = null;
        try {
            meth = zeit.getClass().getMethod("getTimespan");
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Klasse Zeitspanne verfügt über getTimespan-Methode <br/><br/>" +
                            "</div><br/>"
            );
        } catch (NoSuchMethodException e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Klasse Zeitspanne verfügt über getTimespan-Methode <br/><br/>" +
                            "</div><br/>"
            );
        }

        if(meth.getModifiers() == Modifier.PUBLIC
                && meth.getReturnType() == Long.TYPE
                && meth.getParameters().length == 0
        )
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Die Methode \"getTimespan\" ist richtig geschrieben <br/><br/>" +
                            "</div><br/>"
            );
        else
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Die Methode \"getTimespan\" ist richtig geschrieben <br/><br/>" +
                            "</div><br/>"
            );


        try {
            zeit = zeit.getClass()
                    .getConstructor(java.util.Date.class, java.util.Date.class)
                    .newInstance(new Date(99000300), new Date(300));
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Der Konstruktor der Klasse \"Zeitspanne\" ist richtig.<br/><br/>" +
                            "</div><br/>"
            );
        } catch (Exception e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Der Konstruktor der Klasse \"Zeitspanne\" ist richtig.<br/><br/>" +
                            "</div><br/>"
            );
        }

        try {
            if((long)zeit.getClass().getMethod("getTimespan").invoke(zeit) == (long)27)
                stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #027d11;'>\n" +
                            "    - Klasse \"Zeitspanne\" ist richtig implementiert!<br/><br/>" +
                            "</div><br/>"
                    );
        } catch (NoSuchMethodException e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Keine  \"getTimespan\"-Methode gefunden ist richtig.<br/><br/>" +
                            "</div><br/>"
            );
        } catch (Exception e) {
            stringResponse.append(
                    "<div style='font-size:20px;font-weight: bold;color: #660e10;'>\n" +
                            "    - Klasse \"Zeitspanne\" ist richtig implementiert!<br/><br/>" +
                            "</div><br/>"
            );
        }
    }
}
