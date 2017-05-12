package be.cheops.axer.api.postprescription;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class RegisterComplaintCommand {

    private final String aggregateIdentifier;

    public RegisterComplaintCommand(String aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}

