package be.cheops.axer.api.postprescription;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ComplaintRegisteredEvent {

    @TargetAggregateIdentifier
    private final String aggregateIdentifier;

    public ComplaintRegisteredEvent(String aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}

