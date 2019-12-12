package org.activiti.cloud.services.message.connector.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource(properties = {
        "activiti.cloud.messages.aggregator.message-store-type=simple"})
public class SimpleMessageStoreTests extends AbstractIntegrationFlowTests {

    @Test
    public void testMessageStore() throws Exception {
        assertThat(this.aggregatingMessageHandler.getMessageStore()).isInstanceOf(SimpleMessageStore.class);
    }
}
