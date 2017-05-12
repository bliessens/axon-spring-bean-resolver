package be.cheops.axer.complaint;

import be.cheops.axer.api.postprescription.ComplaintRegisteredEvent;
import be.cheops.axer.api.postprescription.RegisterComplaintCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class ComplaintAggregateTest {


    private AggregateTestFixture<ComplaintAggregate> fixture;
    private final CustomerResolver customerResolverMock = mock(CustomerResolver.class);

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(ComplaintAggregate.class);
        fixture.registerInjectableResource(customerResolverMock);
    }

    @Test
    public void name() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new RegisterComplaintCommand("fds"))
                .expectEvents(new ComplaintRegisteredEvent("fds"));
    }
}