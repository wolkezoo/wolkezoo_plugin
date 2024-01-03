package com.wolkezoo.plugin.wolkezoo_plugin.even;

import android.content.Context;

import androidx.annotation.NonNull;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

public interface EvenHandle {

    String evenName();

    void handle(Context context, String methodTargetName, @NonNull MethodCall call, @NonNull MethodChannel.Result result);

}
