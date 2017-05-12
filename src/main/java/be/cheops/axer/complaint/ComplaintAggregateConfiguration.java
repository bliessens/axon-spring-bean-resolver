package be.cheops.axer.complaint;

import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.lock.PessimisticLockFactory;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.NoSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.MultiParameterResolverFactory;
import org.axonframework.spring.eventsourcing.SpringPrototypeAggregateFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
class ComplaintAggregateConfiguration {

    @Bean
    public AggregateFactory<ComplaintAggregate> observationGroupFactory() {
        SpringPrototypeAggregateFactory<ComplaintAggregate> prototypeAggregateFactory = new SpringPrototypeAggregateFactory<>();
        prototypeAggregateFactory.setPrototypeBeanName("complaintAggregate");
        return prototypeAggregateFactory;
    }

    @Bean("complaintAggregate")
    @Scope("prototype")
    public ComplaintAggregate complaintAggregate() {
        return new ComplaintAggregate();
    }

    @Bean
    public AggregateAnnotationCommandHandler<ComplaintAggregate> commandHandler(Repository<ComplaintAggregate> complaintAggregateRepository) {
        return new AggregateAnnotationCommandHandler(ComplaintAggregate.class, complaintAggregateRepository);
    }

    @Bean
    public Cache complaintCache() {
        return new WeakReferenceCache();
    }

    @Bean
    public Repository<ComplaintAggregate> complaintAggregateRepository(EventStore eventStore, MultiParameterResolverFactory parameterResolverFactory) {

        CachingEventSourcingRepository<ComplaintAggregate> repository = new CachingEventSourcingRepository<>(
                observationGroupFactory(),
                eventStore, new PessimisticLockFactory(),
                complaintCache(), parameterResolverFactory, NoSnapshotTriggerDefinition.INSTANCE);

        return repository;
    }

}
