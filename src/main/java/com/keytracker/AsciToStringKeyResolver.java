package com.keytracker;

import com.keytracker.handlers.HandlersProvider;
import de.ksquared.system.keyboard.KeyEvent;

import static com.keytracker.KeyResolver.MOD.*;


public class AsciToStringKeyResolver implements KeyResolver {
    private final HandlersProvider handlersProvider;

    public AsciToStringKeyResolver(HandlersProvider handlersProvider) {
        this.handlersProvider = handlersProvider;
    }

    @Override
    public String resolve(KeyEvent keyEvent) {
        if (keyEvent.isShiftPressed()) {
            return handlersProvider.getHandler(SHIFT).handle(keyEvent);
        } else if (keyEvent.isAltPressed()) {
            return handlersProvider.getHandler(ALT).handle(keyEvent);
        } else if (keyEvent.isCtrlPressed()) {
            return handlersProvider.getHandler(CTRL).handle(keyEvent);
        } else {
            return handlersProvider.getHandler(OTHER).handle(keyEvent);
        }
    }
}
