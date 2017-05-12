package be.cheops.axer.axon;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "org.axonframework.eventsourcing.eventstore.jpa")
class JpaConfiguration {

}
