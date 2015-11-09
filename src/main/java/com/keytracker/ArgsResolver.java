package com.keytracker;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArgsResolver {
    private static final String REGEX = "(mail|file|crypt)=(true|false)";
    private final String[] args;

    private final Map<String, Boolean> argsMap = new HashMap<>();

    public ArgsResolver(String[] args) {
        this.args = args;
        parseArgsToMap();
    }

    public Map<String, Boolean> getArgsMap() {
        return argsMap;
    }

    private void parseArgsToMap() {
        for (String arg : args) {
            if (isArgCorrect(arg)) {
                String[] split = arg.split("=");
                String key = split[0];
                boolean val = Boolean.valueOf(split[1]);
                if (val) {
                    argsMap.put(key, true);
                }
            }
        }
    }

    private boolean isArgCorrect(String arg) {
        Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(arg);
        return m.matches();
    }
}
