package com.imatrixteam.MXJS;


import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSValue;

import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.view.FlutterView;

/**
 * BattlePowerPlugin
 */
public class MXJSFlutterApp {

    private static final String MAIN_CHANNEL = "js_flutter.flutter_main_channel";
    private static final String APP_CHANNEL = "js_flutter.js_flutter_app_channel";

    private static MethodChannel jsFlutterAppChannel;
    private static MXJSFlutterEngine jsFlutterEngine;
    private String appName;
    private String appRootPath;
    private JSValue jsAppObj;
    private MXJSEngine jsEngine;

    public static MXJSFlutterApp initWithAppName(String _appName, MXJSFlutterEngine _jsFlutterEngine, String _appRootPath) {
        MXJSFlutterApp self = new MXJSFlutterApp();
        self.appName = _appName;
        self.appRootPath = _appRootPath;
        jsFlutterEngine = _jsFlutterEngine;
        self.setupChannel();
        self.setupJSEngine();
        return self;
    }

    private void setupJSEngine() {
        unsetup();
        jsEngine = MXJSEngine.init();
        String js_basic_lib_Path = "js_basic_lib.js";

    }

    public void unsetup() {

    }


    public void setupChannel() {
        MXJSFlutterViewController view = jsFlutterEngine.getFlutterViewController();
        BinaryMessenger messenger = view.getBinaryMessenger();
        jsFlutterAppChannel = new MethodChannel(messenger, MAIN_CHANNEL); //这里对应dart端的 MethodChannel
        jsFlutterAppChannel.setMethodCallHandler((methodCall, result) -> {
            if (methodCall.method.equals("callJS")) {
                jsEngine.invokeJSValue("invokeJSValue", methodCall.arguments(), (JSValue _result, Exception error) -> {

                });
            } else {
                result.notImplemented();
            }
        });
    }

    public void runApp(){
        runAppWithPageName("");
    }

    public void runAppWithPageName(String pageName){
        MXJSExecutor jsExecutor = getJsExecutor();
        jsExecutor.executeMXJSBlockOnJSThread((executor) -> {
            JSContext jsContext = executor.getJsContext();
            // TODO 设置 jsContext["MXNativeJSFlutterApp"] = this;
String mainJS = this.appRootPath + "/main.js";
            executor.executeScriptPath(mainJS, (error) -> {
                android.util.Log.i("MXJSFlutter", "runApp mainJS error:" + error.getMessage());
                android.util.Log.wtf("MXJSFlutter", error);
            });
        });
    }

    public void exitApp(){
        jsAppObj = null;
        jsEngine = null;
    }

    public void jsAPISetCurrentJSApp(JSValue _jsAppObj){
        jsAppObj = _jsAppObj;
    }

    public void jsAPICallFlutterReloadApp(JSValue _jsAppObj, String data){
        jsAppObj = _jsAppObj;
        jsFlutterEngine.getFlutterViewController().callFlutterReloadAppWithJSWidgetData(data);
    }

    public void callFlutterWidgetChannelWithMethodName(String method, Long arguments){
        jsFlutterAppChannel.invokeMethod(method, arguments);
    }

    /* //////////////////////////////////////////////////////////////////////////////////////////////
     * ///////////////////////////////////  getter 函数  //////////////////////////////////////
     * ////////////////////////////////////////////////////////////////////////////////////////////// */

    private MXJSExecutor getJsExecutor() {
        return jsEngine.jsExecutor();
    }

    /* //////////////////////////////////////////////////////////////////////////////////////////////
     * ///////////////////////////////////  自动生成的 getter  //////////////////////////////////////
     * ////////////////////////////////////////////////////////////////////////////////////////////// */

    public static MethodChannel getJsFlutterAppChannel() {
        return jsFlutterAppChannel;
    }

    public static MXJSFlutterEngine getJsFlutterEngine() {
        return jsFlutterEngine;
    }

    public String getAppName() {
        return appName;
    }

    public String getAppRootPath() {
        return appRootPath;
    }
}