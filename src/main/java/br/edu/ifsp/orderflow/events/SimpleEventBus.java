package br.edu.ifsp.orderflow.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SimpleEventBus implements IEventBus{

    private Map<
            Class<? extends IDomainEvent>,
            List<IEventHandler<? extends IDomainEvent>>
            > handlers = new HashMap<>();

    @Override
    public <E extends IDomainEvent> void publish(E event) {
        List<IEventHandler<? extends IDomainEvent>> listHandlers = this.handlers.get(
                event.getClass()
        );

        if (listHandlers == null){
            return; // ninguém interessado neste evento (por enquanto)
        }

        for (IEventHandler<? extends IDomainEvent> registered : listHandlers){
            IEventHandler<E> handler = (IEventHandler<E>) registered;
            handler.handle(event);
        }
    }

    // o registrador garante que toda classe está restrita ao tipo que passamos

    @Override
    public <E extends IDomainEvent> void register(IEventHandler<E> handler) {

        List<IEventHandler<? extends IDomainEvent>> listHandlers = this.handlers.get(handler.eventType());

        if (listHandlers == null) {
            listHandlers = new ArrayList<>();
            this.handlers.put(handler.eventType(), listHandlers);
        }

        listHandlers.add(handler);
    }
}
