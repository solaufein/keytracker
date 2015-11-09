package com.keytracker;

import com.keytracker.handlers.*;
import de.ksquared.system.keyboard.KeyEvent;

import java.util.HashMap;
import java.util.Map;

import static com.keytracker.KeyResolver.MOD.*;


public class AsciToStringKeyResolver implements KeyResolver {

    private final Map<MOD, ModifierHandler> modifierHandlers = new HashMap<>();
    private final CharactersProvider charactersProvider;

    public AsciToStringKeyResolver(CharactersProvider charactersProvider) {
        this.charactersProvider = charactersProvider;

        modifierHandlers.put(ALT, new AltHandler(charactersProvider));
        modifierHandlers.put(SHIFT, new ShiftHandler(charactersProvider));
        modifierHandlers.put(CTRL, new CtrlHandler(charactersProvider));
        modifierHandlers.put(OTHER, new OtherCharacterHandler(charactersProvider));
    }

    @Override
    public String resolve(KeyEvent keyEvent) {
        if (keyEvent.isShiftPressed()) {
            return modifierHandlers.get(SHIFT).handle(keyEvent);
        } else if (keyEvent.isAltPressed()) {
            return modifierHandlers.get(ALT).handle(keyEvent);
        } else if (keyEvent.isCtrlPressed()) {
            return modifierHandlers.get(CTRL).handle(keyEvent);
        } else {
            return modifierHandlers.get(OTHER).handle(keyEvent);
        }
    }
}
