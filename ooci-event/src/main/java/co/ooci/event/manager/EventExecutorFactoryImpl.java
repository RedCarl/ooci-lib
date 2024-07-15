package co.ooci.event.manager;

import lombok.NonNull;
import net.kyori.event.method.EventExecutor;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public final class EventExecutorFactoryImpl implements EventExecutor.Factory<OociEvent, OociListener> {
    public static final EventExecutorFactoryImpl INSTANCE = new EventExecutorFactoryImpl();

    private EventExecutorFactoryImpl() {
    }

    @Override
    public @NonNull EventExecutor<OociEvent, OociListener> create(@NonNull Object object, Method method) throws Exception {
        method.setAccessible(true);
        final Class<? extends OociEvent> actualEventType = method.getParameterTypes()[0].asSubclass(OociEvent.class);
        if (Modifier.isAbstract(actualEventType.getModifiers())) {
            throw new IllegalArgumentException("You cannot create listener for an abstract event type.");
        }
        final MethodHandle handle = MethodHandles.lookup().unreflect(method).bindTo(object);
        return (listener, event) -> {
            if (!actualEventType.isInstance(event))
                return;
            handle.invoke(event);
        };
    }

}