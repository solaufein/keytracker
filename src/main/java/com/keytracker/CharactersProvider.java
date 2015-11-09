package com.keytracker;

import com.keytracker.actions.Action;
import com.keytracker.actions.BackspaceAction;
import com.keytracker.actions.EnterAction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharactersProvider {
    private final List<Integer> abandonedCharacters = Arrays.asList(160, 161, 162, 163, 164, 165);
    private final Map<Integer, Character> polishChars = new HashMap<>();
    private final Map<Integer, Character> specialChars = new HashMap<>();
    private final Map<Integer, Character> alphabeticChars = new HashMap<>();
    private final Map<Integer, Action> actionChars = new HashMap<>();

    public CharactersProvider() {
        alphabeticChars.put(189, (char) 45);    //  -
        alphabeticChars.put(187, (char) 61);    //  =
        alphabeticChars.put(191, (char) 47);    //  /
        alphabeticChars.put(190, (char) 46);    //  .
        alphabeticChars.put(188, (char) 44);    //  ,
        alphabeticChars.put(219, (char) 91);    //  [
        alphabeticChars.put(221, (char) 93);    //  ]
        alphabeticChars.put(186, (char) 59);    //  ;
        alphabeticChars.put(222, (char) 39);    //  '
        alphabeticChars.put(220, (char) 92);    //  \

        actionChars.put(8, new BackspaceAction());    //  Backspace
        actionChars.put(13, new EnterAction());    //  Enter

        polishChars.put(65, (char) 261);    //    ¹
        polishChars.put(69, (char) 281);    //    ê
        polishChars.put(67, (char) 263);    //    æ
        polishChars.put(88, (char) 378);    //    Ÿ
        polishChars.put(90, (char) 380);    //    ¿
        polishChars.put(79, (char) 243);    //    ó
        polishChars.put(76, (char) 322);    //    ³
        polishChars.put(83, (char) 347);    //    œ

        specialChars.put(49, (char) 33);     //  !
        specialChars.put(50, (char) 64);     //  @
        specialChars.put(51, (char) 35);     //  #
        specialChars.put(52, (char) 36);     //  $
        specialChars.put(53, (char) 37);     //  %
        specialChars.put(54, (char) 94);     //  ^
        specialChars.put(55, (char) 38);     //  &
        specialChars.put(56, (char) 42);     //  *
        specialChars.put(57, (char) 40);     //  (
        specialChars.put(48, (char) 41);     //  )
        specialChars.put(189, (char) 95);    //  _
        specialChars.put(187, (char) 43);    //  +
        specialChars.put(191, (char) 63);    //  ?
        specialChars.put(188, (char) 60);    //  <
        specialChars.put(190, (char) 62);    //  >
        specialChars.put(219, (char) 123);    //  {
        specialChars.put(221, (char) 125);    //  }
        specialChars.put(186, (char) 58);    //  :
        specialChars.put(222, (char) 34);    //  "
        specialChars.put(220, (char) 124);    //  |
    }

    public boolean isAllowedCode(int keyCode) {
        return !abandonedCharacters.stream().filter(integer -> keyCode == integer).findAny().isPresent();
    }

    public Map<Integer, Action> getActionChars() {
        return actionChars;
    }

    public Map<Integer, Character> getAlphabeticChars() {
        return alphabeticChars;
    }

    public List<Integer> getAbandonedCharacters() {
        return abandonedCharacters;
    }

    public Map<Integer, Character> getSpecialChars() {
        return specialChars;
    }

    public Map<Integer, Character> getPolishChars() {
        return polishChars;
    }
}
