#ifndef FLUTTER_PLUGIN_WOLKEZOO_PLUGIN_H_
#define FLUTTER_PLUGIN_WOLKEZOO_PLUGIN_H_

#include <flutter/method_channel.h>
#include <flutter/plugin_registrar_windows.h>

#include <memory>

namespace wolkezoo_plugin {

class WolkezooPlugin : public flutter::Plugin {
 public:
  static void RegisterWithRegistrar(flutter::PluginRegistrarWindows *registrar);

  WolkezooPlugin();

  virtual ~WolkezooPlugin();

  // Disallow copy and assign.
  WolkezooPlugin(const WolkezooPlugin&) = delete;
  WolkezooPlugin& operator=(const WolkezooPlugin&) = delete;

  // Called when a method is called on this plugin's channel from Dart.
  void HandleMethodCall(
      const flutter::MethodCall<flutter::EncodableValue> &method_call,
      std::unique_ptr<flutter::MethodResult<flutter::EncodableValue>> result);
};

}  // namespace wolkezoo_plugin

#endif  // FLUTTER_PLUGIN_WOLKEZOO_PLUGIN_H_
