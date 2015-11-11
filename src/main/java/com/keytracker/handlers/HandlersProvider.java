package com.keytracker.handlers;

import com.keytracker.CharactersProvider;
import com.keytracker.KeyResolver;

import java.util.HashMap;
import java.util.Map;

import static com.keytracker.KeyResolver.MOD.*;

public class HandlersProvider {
    private final Map<KeyResolver.MOD, ModifierHandler> modifierHandlers = new HashMap<>();

    public HandlersProvider(CharactersProvider charactersProvider) {
        modifierHandlers.put(ALT, new AltHandler(charactersProvider));
        modifierHandlers.put(SHIFT, new ShiftHandler(charactersProvider));
        modifierHandlers.put(CTRL, new CtrlHandler(charactersProvider));
        modifierHandlers.put(OTHER, new OtherCharacterHandler(charactersProvider));
    }

    public ModifierHandler getHandler(KeyResolver.MOD modifier) {
        return modifierHandlers.get(modifier);
    }
}
