package com.fkq.skill.arcgis;

import android.util.Log;
import com.esri.android.map.TiledServiceLayer;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
public class BaseTiledServiceLayer extends TiledServiceLayer {

    private int minLevel = 0;
    private int maxLevel = 19;
    private TileInfo mTileInfo;

    public BaseTiledServiceLayer() {
        super(true);
        this.init();
    }

    protected void init() {
        try {
            getServiceExecutor().submit(new Runnable() {
                public void run() {
                    BaseTiledServiceLayer.this.initLayer();
                }
            });
        } catch (RejectedExecutionException rejectedexecutionexception) {
            Log.e("BaseTiledServiceLayer", "initialization failed.", rejectedexecutionexception);
        }
    }

    private void buildTileInfo() {
        Point iPoint = new Point(-180, 90);
        double[] iScale = {400000000, 295497598.5708346, 147748799.285417,
                73874399.6427087, 36937199.8213544, 18468599.9106772,
                9234299.95533859, 4617149.97766929, 2308574.98883465,
                1154287.49441732, 577143.747208662, 288571.873604331,
                144285.936802165, 72142.9684010827, 36071.4842005414,
                18035.7421002707, 9017.87105013534, 4508.93552506767,
                2254.467762533835, 1127.2338812669175, 563.616940

        };
        double[] iRes = {1.40625, 0.703125, 0.3515625, 0.17578125, 0.087890625,
                0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625,
                0.00274658203125, 0.001373291015625, 0.0006866455078125,
                0.00034332275390625, 0.000171661376953125, 8.58306884765629E-05,
                4.29153442382814E-05, 2.14576721191407E-05,
                1.07288360595703E-05, 5.36441802978515E-06,
                2.68220901489258E-06, 1.34110450744629E-06};

        this.mTileInfo = new TileInfo(
                iPoint, iScale, iRes, 20, 96, 256, 256);
        this.setTileInfo(this.mTileInfo);
    }

    public void refresh() {
        try {
            getServiceExecutor().submit(new Runnable() {
                public final void run() {
                    if (layer.isInitialized())
                        try {
                            layer.clearTiles();
                            return;
                        } catch (Exception exception) {
                            Log.e("ArcGIS", "Re-initialization of the layer failed.", exception);
                        }
                }

                final BaseTiledServiceLayer layer;

                {
                    layer = BaseTiledServiceLayer.this;
                }
            });
            return;
        } catch (RejectedExecutionException ree) {
            return;
        }
    }

    @Override
    protected byte[] getTile(int level, int col, int row) throws Exception {
        if (level > maxLevel || level < minLevel)
            return new byte[0];
        int yy = (int) Math.abs(Math.pow(2, level - 1) - row - 1);

        String url = "http://112.91.128.50:1915/geoserver/gwc/service/tms/1.0.0/stfgj%3Aliancheng@My_EPSG2%3A4326@jpeg/"
                + (level) + "/" + col + "/" + yy + ".jpg";

        Map<String, String> map = null;
        return com.esri.core.internal.io.handler.a.a(url, map);

    }

    @Override
    public TileInfo getTileInfo() {
        return this.mTileInfo;
    }

    protected void initLayer() {
        this.buildTileInfo();
        this.setFullExtent(new Envelope(116.537998381, 25.2294536475, 117.14138446, 25.9060114037));
        this.setInitialExtent(new Envelope(116.537998381, 25.2294536475, 117.14138446, 25.9060114037));
        this.setDefaultSpatialReference(SpatialReference.create(4326));
        super.initLayer();
    }

}