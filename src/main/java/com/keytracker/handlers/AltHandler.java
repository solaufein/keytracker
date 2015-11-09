package com.keytracker.handlers;

import com.keytracker.CharactersProvider;
import de.ksquared.system.keyboard.KeyEvent;

public class AltHandler implements ModifierHandler {

    private final CharactersProvider charactersProvider;

    public AltHandler(CharactersProvider charactersProvider) {
        this.charactersProvider = charactersProvider;
    }

    @Override
    public String handle(KeyEvent keyEvent) {
        int keyCode = keyEvent.getVirtualKeyCode();

        if (keyEvent.isExtendedKey()) {
            Character character = charactersProvider.getPolishChars().get(keyCode);
            if (character != null) {
                return character.toString();
            }
        }
        return "";
    }
}