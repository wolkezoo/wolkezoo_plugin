import 'dart:async';

import 'package:flutter/services.dart';

typedef CallHandle = Future<dynamic> Function(MethodCall call, String callbackMethodName);

class WolkezooPluginPlatform {
  factory WolkezooPluginPlatform({required CallHandle callHandle, required List<String> callbackFunctionName}) => _getInstance(
        callHandle0: callHandle,
        callbackFunctionName0: callbackFunctionName,
      );
  static WolkezooPluginPlatform? _instance;

  // plugin callback channel
  static var callback = const MethodChannel("wolkezoo_plugin_callback");

  // plugin method channel
  static var methodChannel = const MethodChannel("wolkezoo_plugin");
  static late CallHandle callHandle;
  static late List<String> callbackFunctionName;
  static Map<String, Stream> eventSubscriptionMap = {};

  static WolkezooPluginPlatform _getInstance({required CallHandle callHandle0, required List<String> callbackFunctionName0}) {
    callHandle = callHandle0;
    callbackFunctionName = callbackFunctionName0;

    _instance ??= WolkezooPluginPlatform._init();
    return _instance!;
  }

  WolkezooPluginPlatform._init() {
    callback.setMethodCallHandler((call) async {
      if (callbackFunctionName.contains(call.method)) {
        callHandle.call(call, call.method);
      }
    });
  }

  static Future<T?> invokeMethod<T>({required String methodName, dynamic args}) async {
    return await methodChannel.invokeMethod<T>(methodName, args);
  }
}
