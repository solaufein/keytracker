package com.keytracker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsResolver {
    private static final String REGEX = "(mail|file|crypt)=(true|.+@.+\\..+)";  //only true/e-mail
    private final String[] args;
    public static final String FILE = "file";
    public static final String MAIL = "mail";
    public static final String CRYPT = "crypt";

    private final Map<String, String> argsMap = new HashMap<>();

    public ArgsResolver(String[] args) {
        this.args = args;
        parseArgsToMap();
    }

    public Map<String, String> getArguments() {
        return argsMap;
    }

    public boolean isFileArgPresent() {
        return argsMap.containsKey(FILE);
    }

    public boolean isMailArgPresent() {
        return argsMap.containsKey(MAIL);
    }

    public boolean isMailOrFileArgPresent() {
        return isFileArgPresent() || isMailArgPresent();
    }

    public boolean isCryptArgPresent() {
        return argsMap.containsKey(CRYPT);
    }

    private void parseArgsToMap() {
        for (String arg : args) {
            if (isArgCorrect(arg)) {
                String[] split = arg.split("=");
                String key = split[0];
                String val = split[1];
                argsMap.put(key, val);          //todo zweryfikowac czy jest ok
//                boolean val = Boolean.valueOf(split[1]);
//                if (val) {
//                    argsMap.put(key, true);
//                }
            }
        }
    }

    private boolean isArgCorrect(String arg) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(arg);
        return m.matches();
    }
}
