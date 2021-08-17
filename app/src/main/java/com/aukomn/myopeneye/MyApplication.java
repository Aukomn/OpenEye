package com.aukomn.myopeneye;

import android.app.Application;
import android.content.Context;

import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.embedding.engine.FlutterEngineCache;
import io.flutter.embedding.engine.dart.DartExecutor;

public class MyApplication extends Application {
    private Context context;
    private static MyApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        // Instantiate a FlutterEngine.
       FlutterEngine flutterEngine = new FlutterEngine(this);
        // Configure an initial route.
     //   flutterEngine.getNavigationChannel().setInitialRoute("route1");
        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.getDartExecutor().executeDartEntrypoint(
                DartExecutor.DartEntrypoint.createDefault()
        );

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
                .getInstance()
                .put("my_engine_id", flutterEngine);
    }
}
