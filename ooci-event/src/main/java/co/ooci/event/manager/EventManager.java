package co.ooci.event.manager;

import co.ooci.event.OociEventAPI;
import net.kyori.event.EventBus;
import net.kyori.event.PostResult;
import net.kyori.event.SimpleEventBus;
import net.kyori.event.method.MethodSubscriptionAdapter;
import net.kyori.event.method.SimpleMethodSubscriptionAdapter;

import java.util.*;
import java.util.logging.Level;

public class EventManager {
    private final EventBus<OociEvent> bus;
    private final MethodSubscriptionAdapter<OociListener> msa;
    private final List<OociListener> listeners = new ArrayList<>();

    public EventManager() {
        this.bus = new SimpleEventBus<>(OociEvent.class);
        this.msa = new SimpleMethodSubscriptionAdapter<>(bus, EventExecutorFactoryImpl.INSTANCE, MethodScannerImpl.INSTANCE);
    }
    
    // 触发事件
    public void callEvent(OociEvent event) {
        final PostResult result = bus.post(event);
        if (!result.wasSuccessful()) {
            OociEventAPI.getLog().log(Level.FINER,"Unexpected exception while posting event.");
            for (final Throwable t : result.exceptions().values()) {
                t.printStackTrace();
            }
        }
    }
    
    // 注册监听器
    public void registerListener(OociListener listener) {
        try {
            msa.register(listener);
        } catch (SimpleMethodSubscriptionAdapter.SubscriberGenerationException e) {
            msa.unregister(listener); // rollback
            throw e; // rethrow
        }
        listeners.add(listener);
    }

    // 注销监听器
    public void unregisterListener() {
        listeners.forEach(this::unregisterHandlers);
        listeners.clear();
    }

    public void unregisterHandlers(OociListener listener) {
        msa.unregister(listener);
    }
}