package com.example.reproduceclasscastexceptionbug.eventhandling;

import com.example.reproduceclasscastexceptionbug.aggregate.MyAggregate;
import com.example.reproduceclasscastexceptionbug.commands.SomeCommand;
import com.example.reproduceclasscastexceptionbug.events.CreatedEvent;
import com.example.reproduceclasscastexceptionbug.events.SomeDomainEvent;
import com.example.reproduceclasscastexceptionbug.events.SomeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.unitofwork.UnitOfWork;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Service;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventListener {

    private final CommandGateway commandGateway;

    @EventHandler
    public void handleSomeEvent(CreatedEvent someEvent){
        commandGateway.send(new SomeCommand(someEvent.getId()));
        log.info("Some command was send");
    }
}
