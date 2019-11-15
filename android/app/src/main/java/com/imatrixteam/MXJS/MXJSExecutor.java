package com.imatrixteam.MXJS;

import org.liquidplayer.javascript.JSContext;

import java.util.function.Consumer;

public class MXJSExecutor {

    private MXJSContext _mxContext;

    public static MXJSExecutor init() {
        MXJSExecutor obj = new MXJSExecutor();
        obj.setup();
        return obj;
    }

    public void dealloc() {
        _mxContext = null;
    }

    public void setup() {

    }

    public void runRunLoopThread() {

    }

    public void executeScriptAsync(String script, String sourceURL, Consumer<Exception> onComplete) {
        executeBlockOnJSThread((executor) -> {
            try {
                executor.executeScript(script, sourceURL);
                if (onComplete != null) {
                    onComplete.accept(null);
                }
            } catch (Exception ex) {
                if (onComplete != null) {
                    onComplete.accept(ex);
                }
            }
        });
    }

    private void executeScript(String script, String sourceURL) {
    }

    private void executeBlockOnJSThread(Consumer<MXJSExecutor> block) {
    }

    public void executeMXJSBlockOnJSThread(Consumer<MXJSExecutor> block) {

    }

    public void executeScriptPath(String jsFile, Consumer<Exception> onComplete) {
    }

    public JSContext getJsContext() {
        return _mxContext.getContext();
    }

    public MXJSContext getMXContext() {
        return _mxContext;
    }
}
