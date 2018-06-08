package com.fkq.skill.arcgis;

import android.os.Bundle;
import android.view.View;

import com.esri.android.map.MapView;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.skill.R;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment7 extends BaseFragment {
    private MapView mMapView;
    private BaseTiledServiceLayer baseTiledServiceLayer;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_arcgis;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mMapView = view.findViewById(R.id.map);
    }

    @Override
    protected void initData() {
        baseTiledServiceLayer = new BaseTiledServiceLayer();
        mMapView.addLayer(baseTiledServiceLayer);
    }


}
