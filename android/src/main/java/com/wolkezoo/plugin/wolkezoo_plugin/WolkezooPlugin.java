package com.wolkezoo.plugin.wolkezoo_plugin;

import android.content.Context;

import androidx.annotation.NonNull;


import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenStrategyFactory;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * WolkezooPlugin
 */
public class WolkezooPlugin implements FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;

    public static MethodChannel callBackChannel;

    private Context context;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "wolkezoo_plugin");
        callBackChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "wolkezoo_plugin_callback");
        channel.setMethodCallHandler(this);
        callBackChannel.setMethodCallHandler(this);
        context = flutterPluginBinding.getApplicationContext();
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        EvenStrategyFactory.handle(context, call, result);

//        if (call.method.equals("getPlatformVersion")) {
//            result.success("Android " + android.os.Build.VERSION.RELEASE);
//        } else {
//            result.notImplemented();
//        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
        callBackChannel.setMethodCallHandler(null);
    }
}
