package be.cheops.axer.axon;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EntityScan(basePackages = "org.axonframework.eventsourcing.eventstore.jpa")
class JpaConfiguration {

}
