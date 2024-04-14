package com.wolkezoo.plugin.wolkezoo_plugin.even.utils;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandle;
import com.wolkezoo.plugin.wolkezoo_plugin.even.EvenHandleMethodAnno;
import com.wolkezoo.plugin.wolkezoo_plugin.even.exception.NotFoundException;
import com.wolkezoo.plugin.wolkezoo_plugin.even.handle.NetWorkListenerService;
import com.wolkezoo.plugin.wolkezoo_plugin.utils.LogUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.hutool.core.util.StrUtil;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public class HandleRouterUtil {

    public static void controlHandleMethod(Class<?> clazz, String methodTargetAnnoName, Context context, @NonNull MethodCall call, @NonNull MethodChannel.Result result) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Method[] declaredMethods = clazz.getDeclaredMethods();

        for (Method declaredMethod : declaredMethods) {
            EvenHandleMethodAnno annotation = declaredMethod.getAnnotation(EvenHandleMethodAnno.class);
            if (annotation == null) {
                continue;
            }

            String mTN0 = annotation.methodTargetName();
            if (StrUtil.isBlankOrUndefined(mTN0)) {
                continue;
            }

            if (StrUtil.equals(mTN0, methodTargetAnnoName)) {
                // control target method
                declaredMethod.invoke(clazz.newInstance(), context, call, result);
                return;
            }
        }

        throw new NotFoundException("Control method not fount，Please check your methodTargetAnnoName is all right");
    }
}
