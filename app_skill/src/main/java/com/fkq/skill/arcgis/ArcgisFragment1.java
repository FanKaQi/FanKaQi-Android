package com.fkq.skill.arcgis;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.ags.ArcGISTiledMapServiceLayer;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.common.util.ToastUtil;
import com.fkq.skill.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment1 extends BaseFragment {
    private MapView mMapView;
    private Spinner graphicTypeSpinner;
    private Button clernBtn;
    private ArcGISTiledMapServiceLayer arcGISTiledMapServiceLayer;
    private GraphicsLayer graphicsLayer;
    private String mapServerUrl = "http://services.arcgisonline.com/ArcGIS/rest/services/World_Street_Map/MapServer";
    //点集合
    private List<Point> pointList = new ArrayList<>();

    private Graphic graphic;
    private String[] mItems_st = {"点", "线", "面"};

    @Override
    protected int setLayoutView() {
        return R.layout.fragment_arcgis;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mMapView = view.findViewById(R.id.map);
        graphicTypeSpinner = view.findViewById(R.id.spinner_type);
        clernBtn = view.findViewById(R.id.clear_graphic);
        //mapview点击事件
        mMapView.setOnSingleTapListener(new OnSingleTapListener() {
            @Override
            public void onSingleTap(float x, float y) {
                handleSingleTap(x, y);
            }
        });
        //
        ArrayAdapter<String> adapter_st = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, mItems_st);
        adapter_st.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        graphicTypeSpinner.setAdapter(adapter_st);
        graphicTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pointList.removeAll(pointList);
                graphicsLayer.removeAll();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //
        clernBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointList.removeAll(pointList);
                graphicsLayer.removeAll();
            }
        });
    }

    @Override
    protected void initData() {
        addLayer();
    }

    private void addLayer() {
        arcGISTiledMapServiceLayer = new ArcGISTiledMapServiceLayer(mapServerUrl);
        mMapView.addLayer(arcGISTiledMapServiceLayer);

        graphicsLayer = new GraphicsLayer();
        mMapView.addLayer(graphicsLayer);
    }


    private void handleSingleTap(float x, float y) {
        Point point = mMapView.toMapPoint(x, y);
        graphicsLayer.removeAll();
        pointList.add(point);
        String type = graphicTypeSpinner.getSelectedItem().toString().trim();
        //
        int[] graphicIds = graphicsLayer.getGraphicIDs(x, y, 8);
        if (graphicIds != null && graphicIds.length > 0) {
            for (int i = 0; i < graphicIds.length; i++) {
                Graphic graphic = graphicsLayer.getGraphic(graphicIds[i]);
                Map<String, Object> attr = graphic.getAttributes();
                ToastUtil.show(context, attr.get("name") + "====" + attr.get("mark"));
            }
        }
        //
        switch (type) {
            case "点":
                graphic = new Graphic(point, new SimpleMarkerSymbol(Color.RED, 5, SimpleMarkerSymbol.STYLE.CIRCLE));
                graphicsLayer.addGraphic(graphic);
                break;
            case "线":
                Polyline polyline = new Polyline();
                if (pointList.size() > 1) {
                    for (int i = 0; i < pointList.size(); i++) {
                        if (i == 0) {
                            polyline.startPath(pointList.get(i));
                        } else {
                            polyline.lineTo(pointList.get(i));
                        }
                    }
                }
                graphic = new Graphic(polyline, new SimpleLineSymbol(Color.RED, 3, SimpleLineSymbol.STYLE.SOLID));
                graphicsLayer.addGraphic(graphic);
                break;
            case "面":
                Polygon polygon = new Polygon();
                for (int i = 0; i < pointList.size(); i++) {
                    if (i == 0) {
                        polygon.startPath(pointList.get(i));
                    } else {
                        polygon.lineTo(pointList.get(i));
                    }
                }
                graphic = new Graphic(polygon, new SimpleFillSymbol(Color.GREEN, SimpleFillSymbol.STYLE.SOLID));
                graphicsLayer.addGraphic(graphic);
                break;
        }
    }

}
