package be.cheops.axer;

import be.cheops.axer.api.postprescription.RegisterComplaintCommand;
import be.cheops.axer.api.postprescription.ReferToPurchaseCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
class AggregateCreator implements ApplicationListener<ContextRefreshedEvent> {

    private final CommandGateway gateway;

    @Autowired
    public AggregateCreator(CommandGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.gateway.sendAndWait(new RegisterComplaintCommand("benoit"));
        this.gateway.sendAndWait(new ReferToPurchaseCommand("benoit", 24758));
    }
}
