//
//  Generated file. Do not edit.
//

// clang-format off

#include "generated_plugin_registrant.h"

#include <wolkezoo_plugin/wolkezoo_plugin.h>

void fl_register_plugins(FlPluginRegistry* registry) {
  g_autoptr(FlPluginRegistrar) wolkezoo_plugin_registrar =
      fl_plugin_registry_get_registrar_for_plugin(registry, "WolkezooPlugin");
  wolkezoo_plugin_register_with_registrar(wolkezoo_plugin_registrar);
}
