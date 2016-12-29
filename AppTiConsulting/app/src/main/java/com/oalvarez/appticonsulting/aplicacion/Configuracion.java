package com.oalvarez.appticonsulting.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Configuracion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        Fresco.initialize(this);
    }
}
