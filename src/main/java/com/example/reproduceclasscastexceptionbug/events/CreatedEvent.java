package com.example.reproduceclasscastexceptionbug.events;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
@Value
public class CreatedEvent {

    @TargetAggregateIdentifier
    String id;
}
