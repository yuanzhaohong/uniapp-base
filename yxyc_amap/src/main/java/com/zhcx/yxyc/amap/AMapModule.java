package com.zhcx.yxyc.amap;

import android.view.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapNaviLocation;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;

import java.util.ArrayList;
import java.util.List;

public class AMapModule extends WXModule implements INaviInfoCallback {
    @JSMethod
    public void setApiKey(String key, JSCallback callback) {
        JSONObject result = new JSONObject();
        try {
            AMapNavi.setApiKey(this.mWXSDKInstance.getContext(), key);
            result.put("message", "success");
            result.put("status", "success");
            callback.invoke(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", e.toString());
            result.put("status", "failure");
            callback.invoke(result);
        }
    }

    @JSMethod
    public void startNavigation(JSONObject jsonParam, JSCallback callback) {
        JSONObject result = new JSONObject();
        try {
            Poi startPoi = new Poi("", new LatLng(jsonParam.getJSONObject("start").getDouble("lat"), jsonParam.getJSONObject("start").getDouble("lng")), "");
            Poi endPoi = new Poi("", new LatLng(jsonParam.getJSONObject("end").getDouble("lat"), jsonParam.getJSONObject("end").getDouble("lng")), "");
            JSONArray poiListJSON = jsonParam.getJSONArray("poiList");
            int poiListLength = poiListJSON.size();
            List<Poi> poiList = new ArrayList();
            for (int index = 0; index < poiListLength; ++index) {
                JSONObject a = poiListJSON.getJSONObject(index);
                poiList.add(new Poi("", new LatLng(a.getDouble("lat"), a.getDouble("lng")), ""));
            }
            AmapNaviType amapNaviType = null;
            String type = jsonParam.getString("type");
            switch (type) {
                case "RIDE":
                    amapNaviType = AmapNaviType.RIDE;
                    break;
                case "WALK":
                    amapNaviType = AmapNaviType.WALK;
                    break;
                default:
                    amapNaviType = AmapNaviType.DRIVER;
            }
            AmapNaviParams params = new AmapNaviParams(startPoi, poiList, endPoi, amapNaviType, AmapPageType.NAVI);
            params.setUseInnerVoice(true);
            params.setNeedDestroyDriveManagerInstanceWhenNaviExit(true);
            params.isShowRouteStrategyPreferenceView();
            params.isTrafficEnabled();
            params.setNaviMode(NaviType.GPS);
            params.setTrafficEnabled(true);
            AmapNaviPage.getInstance().showRouteActivity(this.mWXSDKInstance.getContext(), params, this);
            result.put("message", "success");
            result.put("status", "success");
            callback.invoke(result);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", e.toString());
            result.put("status", "failure");
            callback.invoke(result);
        }
    }

    @Override
    public void onInitNaviFailure() {
    }

    @Override
    public void onGetNavigationText(String s) {
    }

    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
    }

    @Override
    public void onArriveDestination(boolean b) {
    }

    @Override
    public void onStartNavi(int i) {
    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {
    }

    @Override
    public void onCalculateRouteFailure(int i) {
    }

    @Override
    public void onStopSpeaking() {
    }

    @Override
    public void onReCalculateRoute(int i) {
    }

    @Override
    public void onExitPage(int i) {
    }

    @Override
    public void onStrategyChanged(int i) {
    }

    @Override
    public View getCustomNaviBottomView() {
        return null;
    }

    @Override
    public View getCustomNaviView() {
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {
    }

    @Override
    public void onMapTypeChanged(int i) {
    }

    @Override
    public View getCustomMiddleView() {
        return null;
    }

    @Override
    public void onNaviDirectionChanged(int i) {
    }

    @Override
    public void onDayAndNightModeChanged(int i) {
    }

    @Override
    public void onBroadcastModeChanged(int i) {
    }

    @Override
    public void onScaleAutoChanged(boolean b) {
    }
}
