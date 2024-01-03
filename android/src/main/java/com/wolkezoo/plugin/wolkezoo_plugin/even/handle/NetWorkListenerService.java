package com.wolkezoo.plugin.wolkezoo_plugin.even.handle;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import androidx.annotation.NonNull;

import com.wolkezoo.plugin.wolkezoo_plugin.WolkezooPlugin;
import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandle;
import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandleMethodAnno;
import com.wolkezoo.plugin.wolkezoo_plugin.even.utils.HandleRouterUtil;
import com.wolkezoo.plugin.wolkezoo_plugin.even.utils.NetWorkListenerUtils;

import java.lang.reflect.InvocationTargetException;

import cn.hutool.core.convert.Convert;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Application egress traffic detection tools
 * <p>
 * Warnings
 * When calling the openNetworkSpeedTest listener without passing the cycles and cyclesTime parameters,
 * you need to call the closeNetworkSpeedTest method when you don't need to listen, otherwise it may cause OOM
 */
public class NetWorkListenerService implements EvenHandle {
    @Override
    public String evenName() {
        return "NetWorkListenerService";
    }

    @Override
    public void handle(Context context, String methodTargetName, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        try {
            HandleRouterUtil.controlHandleMethod(NetWorkListenerService.class, methodTargetName, context, call, result);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            result.error("500", "invoke method error", e.getMessage());
        }
    }

    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 100) {
                if (WolkezooPlugin.callBackChannel != null) {
                    WolkezooPlugin.callBackChannel.invokeMethod("NetWorkListenerService-network_speed_callback", msg.obj.toString());
                }
            }
            super.handleMessage(msg);
        }
    };

    @EvenHandleMethodAnno(methodTargetName = "open_network_speed_test")
    public void openNetworkSpeedTest(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        NetWorkListenerUtils netWorkListenerUtils = NetWorkListenerUtils.getInstance();
        netWorkListenerUtils.setContext(context);
        netWorkListenerUtils.setHandler(mHandler);

        Object cycles0 = call.argument("cycles");
        Object cyclesTime0 = call.argument("cyclesTime");

        // obtain network listen cycles
        int cycles = Convert.toInt(cycles0, -1);
        if (cycles != -1) {
            if (cycles <= 0) {
                result.error("1000", "cycles cannot be less than or equal to 0", null);
                return;
            }
            netWorkListenerUtils.setCycles(cycles);
        }

        // obtain network listen cycles time, /s
        int cyclesTime = Convert.toInt(cyclesTime0, -1);
        if (cyclesTime != -1) {
            if (cyclesTime <= 0) {
                result.error("1000", "cyclesTime cannot be less than or equal to 0", null);
                return;
            }
            netWorkListenerUtils.setCyclesTime(cyclesTime);
        }
        netWorkListenerUtils.startShowNetSpeed();
        result.success("SUCCESS");
    }

    @EvenHandleMethodAnno(methodTargetName = "close_network_speed_test")
    public void closeNetworkSpeedTest(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        NetWorkListenerUtils.getInstance().unbindShowNetSpeed();

        result.success("SUCCESS");
    }

}
