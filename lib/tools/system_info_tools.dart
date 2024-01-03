import 'package:wolkezoo_plugin/wolkezoo_plugin_platform_interface.dart';

enum SystemInfoEnum {
  obtainAvailableInternalMemorySize("obtain_available_internal_memory_size"),
  obtainTotalInternalMemorySize("obtain_total_internal_memory_size"),
  obtainAvailableExternalMemorySize("obtain_available_external_memory_size"),
  obtainTotalExternalMemorySize("obtain_total_external_memory_size"),
  ;

  const SystemInfoEnum(this.methodName);

  final String methodName;
}

///
/// Getting system information
class SystemInfoTools {
  static const String _processPrefix = "SystemInfoService";

  ///
  /// obtain available internal memory size
  static Future<int?> obtainAvailableInternalMemorySize() async {
    return await WolkezooPluginPlatform.invokeMethod<int>(
      methodName: "$_processPrefix-${SystemInfoEnum.obtainAvailableInternalMemorySize.methodName}",
    );
  }

  ///
  /// obtain total internal memory size
  static Future<int?> obtainTotalInternalMemorySize() async {
    return await WolkezooPluginPlatform.invokeMethod<int>(
      methodName: "$_processPrefix-${SystemInfoEnum.obtainTotalInternalMemorySize.methodName}",
    );
  }

  ///
  /// obtain available external memory size
  static Future<int?> obtainAvailableExternalMemorySize() async {
    return await WolkezooPluginPlatform.invokeMethod<int>(
      methodName: "$_processPrefix-${SystemInfoEnum.obtainAvailableExternalMemorySize.methodName}",
    );
  }

  ///
  /// obtain total external memory size
  static Future<int?> obtainTotalExternalMemorySize() async {
    return await WolkezooPluginPlatform.invokeMethod<int>(
      methodName: "$_processPrefix-${SystemInfoEnum.obtainTotalExternalMemorySize.methodName}",
    );
  }
}
