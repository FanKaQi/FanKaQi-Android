package com.fkq.skill.arcgis;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.esri.android.map.FeatureLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.event.OnSingleTapListener;
import com.esri.core.geodatabase.ShapefileFeatureTable;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.Feature;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.table.FeatureTable;
import com.esri.core.tasks.query.QueryParameters;
import com.fkq.common.fragment.BaseFragment;
import com.fkq.common.util.ToastUtil;
import com.fkq.skill.R;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2018/5/30.
 */

public class ArcgisFragment3 extends BaseFragment {
    private MapView mMapView;
    private String shpPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/test/test.shp";
    private FeatureLayer featureLayer;

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
        Button button = view.findViewById(R.id.clear_graphic);
        button.setText("查询");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query();
            }
        });
    }

    @Override
    protected void initData() {
        try {
            featureLayer = new FeatureLayer(new ShapefileFeatureTable(shpPath));
            featureLayer.setRenderer(new SimpleRenderer(new SimpleFillSymbol(
                    Color.GREEN)));
            mMapView.addLayer(featureLayer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * GraphicsLayer的点击查询
     *
     * @param x
     * @param y
     */

    protected void handleSingleTap(float x, float y) {
        long[] featureIds = featureLayer.getFeatureIDs(x, y, 8);
        if (featureIds != null && featureIds.length > 0) {
            for (long id : featureIds) {
                com.esri.core.map.Feature feature = featureLayer.getFeature(id);
                Map<String, Object> attrs = feature.getAttributes();
                Set<Map.Entry<String, Object>> setEntry = attrs.entrySet();
                for (Map.Entry<String, Object> entryItem : setEntry) {
                    ToastUtil.show(context, entryItem.getKey() + "====" + entryItem.getValue());
                }
            }
        }
    }

    private void query() {
        FeatureTable featureTable = featureLayer.getFeatureTable();
        QueryParameters parameters = new QueryParameters();
        // 查询条件，有点像数据库中的查询
        parameters.setWhere("name = 'shanghai'");
        Future<long[]> queryIds = featureTable.queryIds(parameters, new CallbackListener<long[]>() {

            public void onError(Throwable e) {

            }

            public void onCallback(long[] objs) {

            }
        });
        //
        try {
            long[] Ids = queryIds.get();
            if (Ids != null && Ids.length > 0) {
                for (int i = 0; i < Ids.length; i++) {
                    Feature feature = featureLayer.getFeature(Ids[i]);
                    ToastUtil.show(context, feature.getAttributes().get("name") + "");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
