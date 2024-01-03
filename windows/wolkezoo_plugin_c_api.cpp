#include "include/wolkezoo_plugin/wolkezoo_plugin_c_api.h"

#include <flutter/plugin_registrar_windows.h>

#include "wolkezoo_plugin.h"

void WolkezooPluginCApiRegisterWithRegistrar(
    FlutterDesktopPluginRegistrarRef registrar) {
  wolkezoo_plugin::WolkezooPlugin::RegisterWithRegistrar(
      flutter::PluginRegistrarManager::GetInstance()
          ->GetRegistrar<flutter::PluginRegistrarWindows>(registrar));
}
