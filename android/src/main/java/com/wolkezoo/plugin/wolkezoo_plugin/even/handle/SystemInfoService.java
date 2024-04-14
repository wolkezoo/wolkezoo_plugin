package com.wolkezoo.plugin.wolkezoo_plugin.even.handle;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;

import androidx.annotation.NonNull;

import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandle;
import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandleMethodAnno;
import com.wolkezoo.plugin.wolkezoo_plugin.even.utils.HandleRouterUtil;
import com.wolkezoo.plugin.wolkezoo_plugin.even.utils.SystemInfoUtil;
import com.wolkezoo.plugin.wolkezoo_plugin.utils.LogUtil;

import java.lang.reflect.InvocationTargetException;

import cn.hutool.core.exceptions.ExceptionUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/**
 * Getting system information
 */
public class SystemInfoService implements EvenHandle {

    @Override
    public String evenName() {
        return "SystemInfoService";
    }

    @Override
    public void handle(Context context, String methodTargetName, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        try {
            HandleRouterUtil.controlHandleMethod(SystemInfoService.class, methodTargetName, context, call, result);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LogUtil.print("SystemInfoService Error, Error Message:" + ExceptionUtil.getSimpleMessage(e));
            result.error("500", "invoke method error", e.getMessage());
        }
    }

    @EvenHandleMethodAnno(methodTargetName = "obtain_available_internal_memory_size")
    public void obtainAvailableInternalMemorySize(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success(SystemInfoUtil.obtainAvailableInternalMemorySize());
    }

    @EvenHandleMethodAnno(methodTargetName = "obtain_total_internal_memory_size")
    public void obtainTotalInternalMemorySize(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success(SystemInfoUtil.obtainTotalInternalMemorySize());
    }

    @EvenHandleMethodAnno(methodTargetName = "obtain_available_external_memory_size")
    public void obtainAvailableExternalMemorySize(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success(SystemInfoUtil.obtainAvailableExternalMemorySize());
    }

    @EvenHandleMethodAnno(methodTargetName = "obtain_total_external_memory_size")
    public void obtainTotalExternalMemorySize(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success(SystemInfoUtil.obtainTotalExternalMemorySize());
    }

    @EvenHandleMethodAnno(methodTargetName = "obtain_device_name")
    public void obtainDeviceName(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success(SystemInfoUtil.obtainDeviceName(context));
    }

    @EvenHandleMethodAnno(methodTargetName = "is_tablet")
    public void isTablet(Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) {
        result.success((context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE);
    }
}
