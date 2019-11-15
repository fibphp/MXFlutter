package com.imatrixteam.MXJS;

import org.liquidplayer.javascript.JSValue;

import java.util.function.BiConsumer;

public class MXJSEngine {
    public static MXJSEngine init() {
        MXJSEngine engine = new MXJSEngine();
        return engine;
    }


    public void invokeJSValue(String invokeJSValue, Object arguments, BiConsumer<JSValue, Exception> callback) {
    }

    public MXJSExecutor jsExecutor() {
        return null;
    }
}
