package com.wolkezoo.plugin.wolkezoo_plugin.even;

import android.content.Context;

import androidx.annotation.NonNull;

import com.wolkezoo.plugin.wolkezoo_plugin.even.handle.NetWorkListenerService;
import com.wolkezoo.plugin.wolkezoo_plugin.even.handle.SystemInfoService;
import com.wolkezoo.plugin.wolkezoo_plugin.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class EvenStrategyFactory {

    private EvenStrategyFactory() {
    }

    private static final Map<String, EvenHandle> EVENT_SERVICE_MAP = new HashMap<>();
    private static final String METHOD_CLASS_SPLIT = "-";

    static {
        LogUtil.print("Wolkezoo Plugin install all method");
        NetWorkListenerService netWorkListenerService = new NetWorkListenerService();
        SystemInfoService systemInfoService = new SystemInfoService();

        EVENT_SERVICE_MAP.put(netWorkListenerService.evenName(), netWorkListenerService);
        EVENT_SERVICE_MAP.put(systemInfoService.evenName(), systemInfoService);
    }

    public static void handle(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        // obtain call method function name
        String method = call.method;
        LogUtil.print("invoke method，method name is ".concat(method));
        // split method class with method
        List<String> ms0 = StrUtil.splitTrim(method, METHOD_CLASS_SPLIT);

        if (CollUtil.size(ms0) != 2) {
            result.notImplemented();
            return;
        }

        EvenHandle evenHandle = EVENT_SERVICE_MAP.get(ms0.get(0));
        if (evenHandle == null) {
            result.notImplemented();
            return;
        }
        evenHandle.handle(context, ms0.get(1), call, result);
    }

}
