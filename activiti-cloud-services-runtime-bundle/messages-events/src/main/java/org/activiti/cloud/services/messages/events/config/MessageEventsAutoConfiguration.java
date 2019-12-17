/*
 * Copyright 2019 Alfresco, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.activiti.cloud.services.messages.events.config;

import org.activiti.cloud.services.events.configuration.RuntimeBundleProperties;
import org.activiti.cloud.services.messages.events.channels.MessageEventsSource;
import org.activiti.cloud.services.messages.events.producer.BpmnMessageReceivedEventMessageProducer;
import org.activiti.cloud.services.messages.events.producer.BpmnMessageSentEventMessageProducer;
import org.activiti.cloud.services.messages.events.producer.BpmnMessageWaitingEventMessageProducer;
import org.activiti.cloud.services.messages.events.producer.MessageSubscriptionCancelledEventMessageProducer;
import org.activiti.cloud.services.messages.events.producer.StartMessageDeployedEventMessageProducer;
import org.activiti.cloud.services.messages.events.support.BpmnMessageEventMessageBuilderFactory;
import org.activiti.cloud.services.messages.events.support.MessageSubscriptionEventMessageBuilderFactory;
import org.activiti.cloud.services.messages.events.support.StartMessageDeployedEventMessageBuilderFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config/messages-events-channels.properties")
@EnableBinding({
    MessageEventsSource.class
})
public class MessageEventsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public BpmnMessageEventMessageBuilderFactory messageEventPayloadMessageBuilderFactory(RuntimeBundleProperties properties) {
        return new BpmnMessageEventMessageBuilderFactory(properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public StartMessageDeployedEventMessageBuilderFactory messageDeployedEventMessageBuilderFactory(RuntimeBundleProperties properties) {
        return new StartMessageDeployedEventMessageBuilderFactory(properties);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MessageSubscriptionEventMessageBuilderFactory messageSubscriptionEventMessageBuilderFactory(RuntimeBundleProperties properties) {
        return new MessageSubscriptionEventMessageBuilderFactory(properties);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public BpmnMessageReceivedEventMessageProducer throwMessageReceivedEventListener(MessageEventsSource producerChannels,
                                                                                     BpmnMessageEventMessageBuilderFactory messageBuilderFactory) {
        return new BpmnMessageReceivedEventMessageProducer(producerChannels.messageEvents(),
                                                           messageBuilderFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public BpmnMessageWaitingEventMessageProducer throwMessageWaitingEventMessageProducer(MessageEventsSource producerChannels,
                                                                                          BpmnMessageEventMessageBuilderFactory messageBuilderFactory) {
        return new BpmnMessageWaitingEventMessageProducer(producerChannels.messageEvents(),
                                                          messageBuilderFactory);
    }

    @Bean
    @ConditionalOnMissingBean
    public BpmnMessageSentEventMessageProducer bpmnMessageSentEventProducer(MessageEventsSource producerChannels,
                                                                            BpmnMessageEventMessageBuilderFactory messageBuilderFactory) {
        return new BpmnMessageSentEventMessageProducer(producerChannels.messageEvents(),
                                                       messageBuilderFactory);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public StartMessageDeployedEventMessageProducer MessageDeployedEventMessageProducer(MessageEventsSource producerChannels,
                                                                                        StartMessageDeployedEventMessageBuilderFactory messageBuilderFactory) {
        return new StartMessageDeployedEventMessageProducer(producerChannels.messageEvents(),
                                                            messageBuilderFactory);
    }
    
    @Bean
    @ConditionalOnMissingBean
    public MessageSubscriptionCancelledEventMessageProducer messageSubscriptionCancelledEventMessageProducer(MessageEventsSource producerChannels,
                                                                                                             MessageSubscriptionEventMessageBuilderFactory messageBuilderFactory) {
        return new MessageSubscriptionCancelledEventMessageProducer(producerChannels.messageEvents(),
                                                                    messageBuilderFactory);
    }
}
