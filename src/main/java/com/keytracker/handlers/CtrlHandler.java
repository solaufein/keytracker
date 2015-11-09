package com.keytracker.handlers;

import com.keytracker.CharactersProvider;
import de.ksquared.system.keyboard.KeyEvent;

public class CtrlHandler implements ModifierHandler {
    private final CharactersProvider charactersProvider;

    public CtrlHandler(CharactersProvider charactersProvider) {
        this.charactersProvider = charactersProvider;
    }

    @Override
    public String handle(KeyEvent keyEvent) {
        return "";
    }
}
