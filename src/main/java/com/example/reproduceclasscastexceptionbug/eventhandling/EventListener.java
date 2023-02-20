package com.example.reproduceclasscastexceptionbug.eventhandling;

import com.example.reproduceclasscastexceptionbug.aggregate.MyAggregate;
import com.example.reproduceclasscastexceptionbug.commands.SomeCommand;
import com.example.reproduceclasscastexceptionbug.events.SomeEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EventListener {

    private final CommandGateway commandGateway;
    private final Repository<MyAggregate> aggregateRepository;

    @EventHandler
    public void handleSomeEvent(SomeEvent someEvent) throws Exception {
        var command = new SomeCommand(someEvent.getId());
        aggregateRepository
                .load(someEvent.getId())
                .handle(new GenericCommandMessage<>(command));
    }
}
