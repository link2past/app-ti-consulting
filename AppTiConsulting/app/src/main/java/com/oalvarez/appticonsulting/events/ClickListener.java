package com.oalvarez.appticonsulting.events;

import android.view.View;

/**
 * Created by oalvarez on 26/12/2016.
 */

public interface ClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);


}
