package Logic.HelpClasses;

public class CodeRefactor {
    private String code;

    public CodeRefactor() {
        this.code = "";
    }

    public CodeRefactor(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String refactorCode(String code, String className) {
        StringBuilder stringBuilder = new StringBuilder(code);

        String constr = "public "+className+"(){}";
        stringBuilder.insert(stringBuilder.indexOf("{")+1, constr);

        return stringBuilder.toString();
    }
}
