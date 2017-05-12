package be.cheops.axer.api.postprescription;

public class ComplaintRegisteredEvent {

    private final String aggregateIdentifier;

    public ComplaintRegisteredEvent(String aggregateIdentifier) {
        this.aggregateIdentifier = aggregateIdentifier;
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }
}

