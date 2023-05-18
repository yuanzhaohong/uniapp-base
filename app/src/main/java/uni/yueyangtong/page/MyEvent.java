package uni.yueyangtong.page;

/**
 * author : JZ_CHEN on 2020/9/21 0021 15:36
 * e-mail : 3152981452@qq.com
 * desc   : 事件对象 用来传递数据的
 */
public class MyEvent  {

    //数据
    private  Object data;

    //事件来源  字符串
    private  String source;

    //触发对象
    private  Object  trigger;

    public MyEvent(Object data) {
        this.data = data;
    }

    public Object getTrigger() {
        return trigger;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "data=" + data +
                ", source='" + source + '\'' +
                ", trigger=" + trigger +
                '}';
    }

    public void setTrigger(Object trigger) {
        this.trigger = trigger;
    }

    public MyEvent(Object data, String source) {
        this.data = data;
        this.source = source;
    }

    public MyEvent() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
