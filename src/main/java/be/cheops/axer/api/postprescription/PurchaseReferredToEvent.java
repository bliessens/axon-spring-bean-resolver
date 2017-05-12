package be.cheops.axer.api.postprescription;

public class PurchaseReferredToEvent {

    private final String aggregateIdentifier;
    private final int purchaseId;

    public PurchaseReferredToEvent(String aggregateIdentifier, int purchaseId) {
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
