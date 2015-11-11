package com.keytracker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsResolver {
    private static final String REGEX_TRUE = "(file|crypt)=true";  //only true
    private static final String REGEX_MAIL = "mail=.+@.+\\..+";  //only e-mail

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

    public String getEmail() {
        if (isMailArgPresent()) {
            return argsMap.get(MAIL);
        }
        return "radoslawwitek@gmail.com";
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
            if (isTrueArgCorrect(arg)) {
                putToArgumentsMap(arg);
            }
            if (isMailArgCorrect(arg)) {
                putToArgumentsMap(arg);
            }
        }
    }

    private void putToArgumentsMap(String arg) {
        String[] split = arg.split("=");
        String key = split[0];
        String val = split[1];
        argsMap.put(key, val);
    }

    private boolean isTrueArgCorrect(String arg) {
        return matches(arg, REGEX_TRUE);
    }

    private boolean isMailArgCorrect(String arg) {
        return matches(arg, REGEX_MAIL);
    }

    private boolean matches(String arg, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(arg);
        return m.matches();
    }
}
