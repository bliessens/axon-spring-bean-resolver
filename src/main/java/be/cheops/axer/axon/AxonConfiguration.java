package be.cheops.axer.axon;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
import org.axonframework.eventsourcing.eventstore.jpa.SQLErrorCodesResolver;
import org.axonframework.messaging.annotation.ParameterResolverFactory;
import org.axonframework.monitoring.NoOpMessageMonitor;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.serialization.upcasting.event.NoOpEventUpcaster;
import org.axonframework.spring.config.EnableAxon;
import org.axonframework.spring.config.annotation.AnnotationCommandHandlerBeanPostProcessor;
import org.axonframework.spring.config.annotation.SpringBeanParameterResolverFactory;
import org.axonframework.spring.config.annotation.SpringParameterResolverFactoryBean;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@EnableAxon
@Configuration
class AxonConfiguration {

    @Bean
    public SimpleCommandBus commandBus(TransactionManager axonTransactionManager) {
        return new SimpleCommandBus(axonTransactionManager, NoOpMessageMonitor.INSTANCE);
    }

    @Bean
    public CommandGateway commandGateway(CommandBus commandBus, RetryScheduler scheduler) {
        return new DefaultCommandGateway(commandBus, scheduler);
    }

    @Bean
    public RetryScheduler retryScheduler() {
        return new IntervalRetryScheduler(new ScheduledThreadPoolExecutor(3), 1000, 5);
    }

    @Bean
    public ThreadPoolTaskExecutor executorService(@Value("${thread.pool.size:5}") int threadPoolSize) {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(threadPoolSize);
        return executor;
    }

    @Bean
    public AnnotationCommandHandlerBeanPostProcessor annotationCommandHandlerBeanPostProcessor() {
        return new AnnotationCommandHandlerBeanPostProcessor();
    }

    @Bean("axonTransactionManager")
    public TransactionManager axonTransactionManager(@Qualifier("transactionManager") PlatformTransactionManager transactionManager) {
        return new SpringTransactionManager(transactionManager);
    }

    @Bean
    public JacksonSerializer jacksonSerializer() {
        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
        return new JacksonSerializer(objectMapper);
    }

    @Bean
    public EventStorageEngine eventStorageEngine(@Qualifier("axonTransactionManager") TransactionManager axonTransactionManager,
                                                 EntityManagerProvider entityManagerProvider, JacksonSerializer jacksonSerializer) throws SQLException {
        return new JpaEventStorageEngine(jacksonSerializer, NoOpEventUpcaster.INSTANCE, new SQLErrorCodesResolver("DB2"), null, entityManagerProvider, axonTransactionManager, null, null, true);
    }

    @Bean
    public EventStore eventStore(EventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus(Integer.MAX_VALUE, NoOpMessageMonitor.INSTANCE);
    }

    @Bean
    public SpringParameterResolverFactoryBean springParameterResolverFactoryBean() {
        return new SpringParameterResolverFactoryBean();
    }
}

