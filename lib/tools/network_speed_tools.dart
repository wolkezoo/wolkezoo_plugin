import 'package:wolkezoo_plugin/wolkezoo_plugin_platform_interface.dart';

enum NetworkSpeedMethodEnum {
  openNetworkSpeedListen("NetWorkListenerService-open_network_speed_test"),
  networkSpeedListenCallBack("NetWorkListenerService-network_speed_callback"),
  closeNetworkSpeedListen("NetWorkListenerService-close_network_speed_test"),
  ;

  const NetworkSpeedMethodEnum(this.methodName);

  final String methodName;
}

/// Application egress traffic detection tools
///
/// Warnings
/// When calling the openNetworkSpeedTest listener without passing the cycles and cyclesTime parameters,
/// you need to call the closeNetworkSpeedTest method when you don't need to listen, otherwise it may cause OOM
class NetworkSpeedTools {

  /// Open Application egress traffic detection
  ///
  /// cycles：this parameter indicates that it will listen to network egress traffic several times
  /// for example, You pass in 10, then callback will accept 10 times
  ///
  /// cyclesTime：this parameter indicates how many seconds the egress traffic will be listened to,
  /// and after this second is reached, the egress traffic listening will be turned off automatically.
  /// for example, You pass in 10, then callback will accept 10/s
  ///
  /// When both cycles and cyclesTime are not passed, you must call closeNetworkSpeedListen function at the appropriate time,
  /// otherwise OOM will occur!
  static Future<String?> openNetworkSpeedListen(
    Function(String speed) callback, {
    int cycles = -1,
    int cyclesTime = -1,
  }) async {
    WolkezooPluginPlatform(
      callHandle: (call, callMethodName) async {
        callback.call(call.arguments);
      },
      callbackFunctionName: [NetworkSpeedMethodEnum.networkSpeedListenCallBack.methodName],
    );

    return await WolkezooPluginPlatform.invokeMethod<String>(
      methodName: NetworkSpeedMethodEnum.openNetworkSpeedListen.methodName,
      args: {"cycles": cycles, "cyclesTime": cyclesTime},
    );
  }

  /// Close Application egress traffic detection
  static Future<String?> closeNetworkSpeedListen() async {
    return await WolkezooPluginPlatform.invokeMethod<String>(methodName: NetworkSpeedMethodEnum.closeNetworkSpeedListen.methodName);
  }
}
