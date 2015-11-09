package com.keytracker.handlers;

import com.keytracker.actions.Action;
import com.keytracker.CharactersProvider;
import de.ksquared.system.keyboard.KeyEvent;

import static com.keytracker.utils.LoggingUtils.character;

public class OtherCharacterHandler implements ModifierHandler {
    private final CharactersProvider charactersProvider;

    public OtherCharacterHandler(CharactersProvider charactersProvider) {
        this.charactersProvider = charactersProvider;
    }

    @Override
    public String handle(KeyEvent keyEvent) {
        int keyCode = keyEvent.getVirtualKeyCode();

        Character character = charactersProvider.getAlphabeticChars().get(keyCode);
        if (character != null) {
            return character.toString();
        }

        Action action = charactersProvider.getActionChars().get(keyCode);
        if (action != null) {
            return action.execute();
        }

        return character(keyCode).toLowerCase();
    }
}