package com.fkq.skill.arcgis;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.esri.android.map.Callout;
import com.esri.android.map.CalloutStyle;
import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.LocationDisplayManager;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Point;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.skill.R;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment6 extends BaseFragment {
    private MapView mMapView;
    private GraphicsLayer graphicsLayer;
    private String mapServerUrl = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";
    private Callout callout;

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_arcgis;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mMapView = view.findViewById(R.id.map);
        //mapview点击事件
        mMapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(float x, float y) {
                //屏幕坐标转地图坐标
                Point point = mMapView.toMapPoint(x, y);
                //设置显示位置
                callout.show(point);
            }
        });

    }

    @Override
    protected void initData() {
        initCallOut();
        mMapView.addLayer(new ArcGISTiledMapServiceLayer(mapServerUrl));
        graphicsLayer = new GraphicsLayer();
        mMapView.addLayer(graphicsLayer);
        LocationDisplayManager locationDisplayManager = mMapView.getLocationDisplayManager();//获取定位类
        locationDisplayManager.setShowLocation(true);
        locationDisplayManager.setAutoPanMode(LocationDisplayManager.AutoPanMode.LOCATION);//设置模式
        locationDisplayManager.setShowPings(true);
        locationDisplayManager.start();//开始定位
    }

    private void initCallOut() {
        //获取一个气泡
        callout = mMapView.getCallout();
        //设置最大的长宽
        callout.setMaxWidth(1500);
        callout.setMaxHeight(200);
        View view = View.inflate(context, R.layout.item_text, null);
        TextView tv_name = view.findViewById(R.id.tv_name);
        tv_name.setText("这是一个气泡");

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        callout.setContent(view);
        CalloutStyle calloutStyle = new CalloutStyle();
        //设置尖尖角的位置，尖尖显示在气泡的左下角，
        calloutStyle.setAnchor(Callout.ANCHOR_POSITION_LOWER_LEFT_CORNER);
        callout.setStyle(calloutStyle);
    }

}
