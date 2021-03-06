package com.oalvarez.appticonsulting1.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapView;

/**
 * Created by oalvarez on 08/01/2017.
 */

public class ScrollableMapView extends MapView {

    public ScrollableMapView(Context context) {
        super(context);
    }

    public ScrollableMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ScrollableMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ScrollableMapView(Context context, GoogleMapOptions googleMapOptions) {
        super(context, googleMapOptions);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
//        switch (action) {
//            case MotionEvent.ACTION_DOWN:
//                // Disallow ScrollView to intercept touch events.
//                this.getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//
//            case MotionEvent.ACTION_UP:
//                // Allow ScrollView to intercept touch events.
//                this.getParent().requestDisallowInterceptTouchEvent(false);
//                break;
//        }
//
//        super.onTouchEvent(event);
//        return true;
//    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action){
            case MotionEvent.ACTION_DOWN:
                this.getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_UP:
                this.getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }


        return super.onTouchEvent(ev);
    }
}
