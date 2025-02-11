package com.example.reproduceclasscastexceptionbug.aggregate;

import com.example.reproduceclasscastexceptionbug.commands.CreateCommand;
import com.example.reproduceclasscastexceptionbug.commands.SomeCommand;
import com.example.reproduceclasscastexceptionbug.events.CreatedEvent;
import com.example.reproduceclasscastexceptionbug.events.SomeDomainEvent;
import com.example.reproduceclasscastexceptionbug.events.SomeEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@Slf4j
public class MyAggregate {

    @AggregateIdentifier
    private String id;

    private boolean someEventHappened;

    public MyAggregate() {
    }

    @CommandHandler
    public MyAggregate(CreateCommand command) {
        apply(new CreatedEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(CreatedEvent event) {
        this.id = event.getId();
        this.someEventHappened = false;
    }

    @ExceptionHandler
    public void handleException(Exception ex) {
        System.err.println(ex);
    }

    @CommandHandler
    public void handle(SomeCommand command) {
        apply(new SomeDomainEvent(command.getId()));
    }

    @EventSourcingHandler
    public void on(SomeDomainEvent event) {
        this.someEventHappened = true;
        log.info("Some domain event happened");
    }
}
