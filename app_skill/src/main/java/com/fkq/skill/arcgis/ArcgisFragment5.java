package com.fkq.skill.arcgis;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.TextSymbol;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.skill.R;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment5 extends BaseFragment {
    private MapView mMapView;
    private GraphicsLayer graphicsLayer;
    private String mapServerUrl = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";

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

            }
        });

    }

    @Override
    protected void initData() {
        mMapView.addLayer(new ArcGISTiledMapServiceLayer(mapServerUrl));
        Drawable drawable = getResources().getDrawable(R.drawable.qq);
        PictureMarkerSymbol pictureMarkerSymbol = new PictureMarkerSymbol(context, drawable);
        Graphic graphic = new Graphic(new Point(113,22), pictureMarkerSymbol);
        graphicsLayer = new GraphicsLayer();
        graphicsLayer.addGraphic(graphic);

        TextSymbol textSymbol = new TextSymbol(20,"我是文字" , Color.RED);
        textSymbol.setFontFamily("DroidSansFallback.ttf");
        Graphic graphic2 = new Graphic(new Point(113,22), textSymbol );
        graphicsLayer.addGraphic(graphic2);
        mMapView.addLayer(graphicsLayer);
    }
}
