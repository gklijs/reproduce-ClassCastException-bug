package com.example.reproduceclasscastexceptionbug.runner;

import com.example.reproduceclasscastexceptionbug.aggregate.MyAggregate;
import com.example.reproduceclasscastexceptionbug.commands.CreateCommand;
import com.example.reproduceclasscastexceptionbug.commands.SomeCommand;
import com.example.reproduceclasscastexceptionbug.events.SomeEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.modelling.command.Repository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ReproduceClassCast {

    private static String lastAggregateId;

    private final CommandGateway commandGateway;
    private final EventGateway eventGateway;
    private final Repository<MyAggregate> aggregateRepository;

    @Scheduled(initialDelay = 1000, fixedDelay = 10000)
    public void createAggregate() {
        lastAggregateId = UUID.randomUUID().toString();

        commandGateway.send(new CreateCommand(lastAggregateId));
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 10000)
    public void sendSomeEvent() {
        eventGateway.publish(new SomeEvent(lastAggregateId));
    }
}
