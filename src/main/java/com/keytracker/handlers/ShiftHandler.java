package com.keytracker.handlers;

import com.keytracker.CharactersProvider;
import de.ksquared.system.keyboard.KeyEvent;

import static com.keytracker.utils.LoggingUtils.character;

public class ShiftHandler implements ModifierHandler {

    private final CharactersProvider charactersProvider;

    public ShiftHandler(CharactersProvider charactersProvider) {
        this.charactersProvider = charactersProvider;
    }

    @Override
    public String handle(KeyEvent keyEvent) {
        int keyCode = keyEvent.getVirtualKeyCode();

        if (charactersProvider.isAllowedCode(keyCode)) {
            Character character = charactersProvider.getSpecialChars().get(keyCode);
            if (character != null) {
                return character.toString();
            }

            return character(keyCode);
        }

        return "";
    }
}
