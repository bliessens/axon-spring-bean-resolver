package be.cheops.axer.complaint;

import be.cheops.axer.api.postprescription.PurchaseReferredToEvent;
import be.cheops.axer.api.postprescription.RegisterComplaintCommand;
import be.cheops.axer.api.postprescription.ComplaintRegisteredEvent;
import be.cheops.axer.api.postprescription.ReferToPurchaseCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.Collection;
import java.util.LinkedList;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Aggregate
class ComplaintAggregate {

    @AggregateIdentifier
    private String aggregateIdentifier;
    private final Collection<Integer> analyses = new LinkedList<>();

    protected ComplaintAggregate() {
    }

    @CommandHandler
    public ComplaintAggregate(RegisterComplaintCommand command /*,ParentOrderResolver resolver*/) {
//        Assert.notNull(resolver, () -> "Requires " + RecipientResolver.class.getSimpleName());
        apply(new ComplaintRegisteredEvent(command.getAggregateIdentifier()));
    }

    @CommandHandler
    public void handle(ReferToPurchaseCommand command /*, RecipientResolver resolver*/) {
//        Assert.notNull(resolver, () -> "Requires " + RecipientResolver.class.getSimpleName());
        apply(new PurchaseReferredToEvent(command.getAggregateIdentifier(), command.getPurchaseId()));
    }

    @EventSourcingHandler
    public void onEvent(ComplaintRegisteredEvent event) {
        this.aggregateIdentifier = event.getAggregateIdentifier();
    }

    @EventSourcingHandler
    public void onEvent(PurchaseReferredToEvent event) {
        this.analyses.add(event.getPurchaseId());
    }

}
