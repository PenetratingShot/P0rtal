/**
 * Things changed from pseudocode:
 *
 * Honestly I don't even know why I still have this, but removing it will cause a lot of errors I just don't want to fix them.
 * It's a better take on the public variable problem, since I have this default string that I need to share with all classes but needs to be programmatically immutable at all costs.
 *
 */

package com.shreyaslad.P0rtal.Data;

public class Singleton {
    private static Singleton single_instance = null;
    public String defaultKey;

    private Singleton() {
        defaultKey = "p, p, p, p, p";
    }

    public static Singleton getInstance() {
        if (single_instance == null)
            single_instance = new Singleton();

        return single_instance;
    }

}
