package be.cheops.axer.api.postprescription;

import org.axonframework.commandhandling.TargetAggregateIdentifier;

public class ReferToPurchaseCommand {

    @TargetAggregateIdentifier
    private final String aggregateIdentifier;
    private final int purchaseId;

    public ReferToPurchaseCommand(String aggregateIdentifier, int purchaseId) {
        this.aggregateIdentifier = aggregateIdentifier;
        this.purchaseId = purchaseId;
    }

    public String getAggregateIdentifier() {
        return aggregateIdentifier;
    }

    public int getPurchaseId() {
        return purchaseId;
    }
}
