package com.fkq.skill.arcgis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.common.util.ToastUtil;
import com.fkq.skill.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment2 extends BaseFragment {
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
                handleSingleTap(x, y);
            }
        });
    }

    @Override
    protected void initData() {
        mMapView.addLayer(new ArcGISTiledMapServiceLayer(mapServerUrl));

        graphicsLayer = new GraphicsLayer();
        mMapView.addLayer(graphicsLayer);

        Polygon polygon = new Polygon();
        polygon.startPath(new Point(1.2575908509778766E7, 2879410.9266042486));
        polygon.lineTo(new Point(1.284360696117901E7, 3021972.232083669));
        polygon.lineTo(new Point(1.2826182801620414E7, 2713089.403544925));
        Map<String, Object> attr1 = new HashMap<>();
        attr1.put("name", "广州");
        attr1.put("mark", "广州是南方的城市");
        Graphic graphic1 = new Graphic(polygon, new SimpleFillSymbol(Color.RED), attr1);
        graphicsLayer.addGraphic(graphic1);


        Polygon polygon2 = new Polygon();
        polygon2.startPath(new Point(1.3388507951011453E7, 3611225.628065273));
        polygon2.lineTo(new Point(1.3607101952746565E7, 3858331.890896268));
        polygon2.lineTo(new Point(1.3613438010767872E7, 3449656.14852193));
        Map<String, Object> attr2 = new HashMap<>();
        attr2.put("name", "上海");
        attr2.put("mark", "上海是中部的城市");
        Graphic graphic2 = new Graphic(polygon2, new SimpleFillSymbol(Color.GREEN), attr2);
        graphicsLayer.addGraphic(graphic2);
    }

    /**
     * GraphicsLayer的点击查询
     *
     * @param x
     * @param y
     */

    protected void handleSingleTap(float x, float y) {
        int[] graphicIds = graphicsLayer.getGraphicIDs(x, y, 8);
        if (graphicIds != null && graphicIds.length > 0) {
            for (int i = 0; i < graphicIds.length; i++) {
                Graphic graphic = graphicsLayer.getGraphic(graphicIds[i]);
                Map<String, Object> attr = graphic.getAttributes();
                ToastUtil.show(context, attr.get("name") + "====" + attr.get("mark"));
            }
        }
    }
}
