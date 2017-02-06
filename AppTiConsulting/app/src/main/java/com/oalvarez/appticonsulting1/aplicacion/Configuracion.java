package com.oalvarez.appticonsulting1.aplicacion;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by oalvarez on 14/12/2016.
 */

public class Configuracion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Configuración Fabric
        Fabric.with(this, new Crashlytics());

        //Configuración Fresco
        Fresco.initialize(this);

        //Configuración Realm
        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                    .schemaVersion(1)
                    .name("GesTI.realm")
                    .initialData(new RealmInitialData())
                    .deleteRealmIfMigrationNeeded()
                    .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
