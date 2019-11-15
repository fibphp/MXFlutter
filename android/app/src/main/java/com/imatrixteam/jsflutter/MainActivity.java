package com.imatrixteam.jsflutter;

import android.os.Bundle;

import com.imatrixteam.MXJS.MXJSFlutterApp;

import org.liquidplayer.javascript.JSContext;
import org.liquidplayer.javascript.JSFunction;
import org.liquidplayer.javascript.JSValue;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    JSContext context = new JSContext();
    JSFunction javaFactorial = new JSFunction(context,"factorial") {
      public Integer factorial(Integer x) {
        int factorial = 1;
        for (; x > 1; x--) {
          factorial *= x;
        }
        return factorial;
      }
    };
    context.property("factorial", javaFactorial);
    JSValue result = context.evaluateScript("(() => { let x = 10; return factorial(x); })()");
    android.util.Log.i("LiquidCoreExample", "The factorial of 10 is " + result.toString());

    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);

    MXJSFlutterApp.registerMXJS(getFlutterView());
  }
}
