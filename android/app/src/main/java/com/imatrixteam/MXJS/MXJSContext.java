package com.imatrixteam.MXJS;

import org.liquidplayer.javascript.JSContext;

public class MXJSContext {
    private JSContext context;

    public static MXJSContext initWithJSContext(JSContext _context){
        MXJSContext obj = new MXJSContext();
        obj.context = _context;
        return obj;
    }

    public boolean isValid(){
        return context != null;
    }

    public void invalidate(){
        if(isValid()){
            context = null;
        }
    }

    public JSContext getContext(){
        return context;
    }

}
