package uni.yueyangtong.page;

import java.util.EventListener;

/**
 * author : JZ_CHEN on 2020/9/21 0021 15:37
 * e-mail : 3152981452@qq.com
 * desc   : 监听器接口
 */
public  interface MyListener  extends EventListener {
    void onChange(MyEvent myEvent);
}