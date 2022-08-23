package com.example.app.wallpaper.changer;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.win32.W32APIOptions;

public class WallpaperChanger {

    public static boolean change(String path) {
        return User32.INSTANCE.SystemParametersInfo(0x0014, 0, path, 1);
    }

    private interface User32 extends Library {

        User32 INSTANCE = Native.load("user32", User32.class, W32APIOptions.DEFAULT_OPTIONS);

        boolean SystemParametersInfo(int uiAction, int uiParam, String pvParam, int fWinIni);
    }
}
