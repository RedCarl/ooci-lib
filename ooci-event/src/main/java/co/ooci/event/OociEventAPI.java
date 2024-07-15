package co.ooci.event;

import co.ooci.event.manager.EventManager;
import co.ooci.event.manager.OociEvent;
import co.ooci.event.manager.OociListener;
import lombok.Getter;

import java.util.logging.Logger;

/**
 * @Author: 孙磊
 * @CreateTime: 2024-04-02
 * @Description: 事件处理
 * @Version: 1.0
 */
public class OociEventAPI {

    @Getter
    private static final OociEventAPI instance = new OociEventAPI();
    @Getter
    private static EventManager eventManager;
    @Getter
    private static Logger log = null;

    public boolean initialize() {
        log = Logger.getLogger(OociEventAPI.class.getName());
        eventManager = new EventManager();
        return true;
    }

    public void callEvent(OociEvent event) {
        eventManager.callEvent(event);
    }

    public void registerListener(OociListener listener){
        eventManager.registerListener(listener);
    }

    public void shutdown() {
        eventManager.unregisterListener();
    }
}
