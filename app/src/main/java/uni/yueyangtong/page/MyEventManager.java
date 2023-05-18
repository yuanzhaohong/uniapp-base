package uni.yueyangtong.page;

import android.util.Log;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**
 * author : JZ_CHEN on 2020/9/21 0021 15:37
 * e-mail : 3152981452@qq.com
 * desc   : 自定义事件管理类
 */
public class MyEventManager {
    private static MyEventManager myEventManager;

    private Map<String, Collection<MyListener>> listeners;

    /**
     * 不能外部 new  实例化
     */
    private MyEventManager() {
        this.listeners = new HashMap<String, Collection<MyListener>>();
    }

    /**
     * 返回监听 总数
     *
     * @return
     */

    public int getSize() {
        int size = 0;
        for (String str : listeners.keySet()) {
            size = size + listeners.get(str).size();

        }
        return size;
    }

    public Map<String, Collection<MyListener>> getListeners() {

        return listeners;
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static MyEventManager getMyEventManager() {
        if (myEventManager == null) {
            synchronized (MyEventManager.class) {
                if (myEventManager == null) {
                    myEventManager = new MyEventManager();
                }
            }
        }
        return myEventManager;
    }

    /***
     * 添加事件
     * @param listener    事件对象
     * @param source      来源
     */
    public MyListener addListener(MyListener listener, String source) {

        if (listener != null && source != null) {
            Collection<MyListener> myListeners = listeners.get(source);

            if (myListeners == null) {
                myListeners = new HashSet<MyListener>();
                listeners.put(source, myListeners);
            }
            myListeners.add(listener);

        }
        return listener;

    }

    /***
     * 添加事件
     *  @param source      来源
     * @param listener    事件对象
     */
    public MyListener addListener(String source, MyListener listener) {

        return addListener(listener, source);

    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void removeListener(MyListener listener) {
        if (listeners == null || listener == null) {
            return;
        }

        //变量所有  找出相同的  删除
        for (String str : listeners.keySet()) {

            Collection collection = listeners.get(str);
            Iterator<MyListener> iter = collection.iterator();
            while (iter.hasNext()) {
                MyListener next = (MyListener) iter.next();
                if (next == listener) {
                    collection.remove(next);
                    return;
                }
            }
        }

    }

    /***
     *   发送数据
     * @param data   数据
     * @param source 来源
     * @return
     */
    public static MyEvent postMsg(Object data, String source) {
        MyEventManager myEventManager = MyEventManager.getMyEventManager();
        MyEvent myEvent = new MyEvent(data);
        myEvent.setSource(source);
        if (myEventManager.listeners == null)

            return myEvent;
        myEventManager.notifyListeners(myEvent, myEvent.getSource());
        return myEvent;
    }

    /**
     * 通知所有的myListener    相同的 (source) 来源才通知
     */
    private void notifyListeners(MyEvent event, String source) {

        //取出  key为source 的  监听器集合
        Collection<MyListener> collection = listeners.get(source);

        Log.i(MyEventManager.class.getName(), source + "--->" + event.getData());

        if (collection == null) {
            return;
        }
        //遍历监听器集合
        Iterator<MyListener> iter = collection.iterator();
        while (iter.hasNext()) {
            MyListener next = iter.next();
            //通知回调
            next.onChange(event);
        }

        //销毁事件对象
        event = null;
    }

}