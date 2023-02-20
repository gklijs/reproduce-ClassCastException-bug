package com.example.reproduceclasscastexceptionbug.commands;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@RequiredArgsConstructor
@Value
public class CreateCommand {

    @TargetAggregateIdentifier
    String id;
}
