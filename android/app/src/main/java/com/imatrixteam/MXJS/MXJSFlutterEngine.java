package com.imatrixteam.MXJS;

import android.content.Context;

import io.flutter.view.FlutterView;

public class MXJSFlutterEngine  extends FlutterView{

    private MXJSFlutterViewController flutterViewController;

    public MXJSFlutterEngine(Context context) {
        super(context);
    }

    public MXJSFlutterViewController getFlutterViewController() {
        return flutterViewController;
    }
}
