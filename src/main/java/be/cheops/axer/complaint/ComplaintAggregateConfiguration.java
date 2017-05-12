package be.cheops.axer.complaint;

import org.axonframework.commandhandling.AggregateAnnotationCommandHandler;
import org.axonframework.commandhandling.AnnotationCommandTargetResolver;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.common.lock.PessimisticLockFactory;
import org.axonframework.eventsourcing.CachingEventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.NoSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.annotation.MultiParameterResolverFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ComplaintAggregateConfiguration {

//    @Bean
//    public AggregateFactory<ComplaintAggregate> complaintAggregateFactory() {
//        SpringPrototypeAggregateFactory<ComplaintAggregate> prototypeAggregateFactory = new SpringPrototypeAggregateFactory<>();
//        prototypeAggregateFactory.setPrototypeBeanName("complaintAggregate");
//        return prototypeAggregateFactory;
//    }
//
//    @Bean("complaintAggregate")
//    @Scope("prototype")
//    public ComplaintAggregate complaintAggregate() {
//        return new ComplaintAggregate();
//    }

    /**
     * for @CommandHandlers in aggregates (@Aggregate annotated classes)
     *
     * @return
     */
    @Bean
    public AggregateAnnotationCommandHandler<ComplaintAggregate> commandHandler(Repository<ComplaintAggregate> complaintAggregateRepository, MultiParameterResolverFactory parameterResolverFactory) {
        return new AggregateAnnotationCommandHandler(ComplaintAggregate.class, complaintAggregateRepository, new AnnotationCommandTargetResolver(), parameterResolverFactory);
    }

    @Bean
    public Cache complaintCache() {
        return new WeakReferenceCache();
    }

    @Bean
    public Repository<ComplaintAggregate> complaintAggregateRepository(EventStore eventStore, MultiParameterResolverFactory parameterResolverFactory) {

        CachingEventSourcingRepository<ComplaintAggregate> repository = new CachingEventSourcingRepository<>(
                new GenericAggregateFactory<>(ComplaintAggregate.class),
                eventStore, new PessimisticLockFactory(),
                complaintCache(), parameterResolverFactory, NoSnapshotTriggerDefinition.INSTANCE);

        return repository;
    }

}
