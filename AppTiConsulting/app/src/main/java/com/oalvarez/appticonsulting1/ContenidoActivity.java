package com.oalvarez.appticonsulting1;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;

import com.oalvarez.appticonsulting1.adapter.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContenidoActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.content_frame)
    FrameLayout contentFrame;

    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido);
        ButterKnife.bind(this);

//        adapter = new ViewPagerAdapter(getSupportFragmentManager());
//        adapter.agregarFragmento(new TicketFragment());
//        viewPager.setAdapter(adapter);
    }
}
