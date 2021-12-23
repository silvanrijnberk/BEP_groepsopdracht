package nl.hu.bep.beoordeling.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hu.bep.beoordeling.infrastructure.driven.messaging.RabbitMqEventPublisher;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMqConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${messaging.exchange.bestellingenboard}")
    private String bestellingenboardExchangeName;

    @Value("${messaging.queue.beoordeling-keywords}")
    private String beoordelingKeywordsQueueName;

    @Value("${messaging.queue.all-keywords}")
    private String allKeywordsQueueName;

    @Value("${messaging.routing-key.beoordeling-keywords}")
    private String beoordelingKeywordsRoutingKey;

    @Value("${messaging.routing-key.all-keywords}")
    private String keywordsRoutingKey;

    @Bean
    public TopicExchange bestellingenboardExchange() {
        return new TopicExchange(bestellingenboardExchangeName);
    }

    @Bean
    public Queue beoordelingQueue() {
        return QueueBuilder.durable(beoordelingKeywordsQueueName).build();
    }

    @Bean
    public Binding beoordelingKeywordsBinding() {
        return BindingBuilder
                .bind(beoordelingQueue())
                .to(bestellingenboardExchange())
                .with(beoordelingKeywordsRoutingKey);
    }


    @Bean
    public Queue keywordsQueue() {
        // Creates a new queue in RabbitMQ
        return QueueBuilder.durable(allKeywordsQueueName).build();
    }

    @Bean
    public Binding keywordsBinding() {
        return BindingBuilder
                .bind(keywordsQueue())
                .to(bestellingenboardExchange())
                .with(keywordsRoutingKey);
    }

    @Bean
    public RabbitMqEventPublisher EventPublisher(RabbitTemplate template) {
        return new RabbitMqEventPublisher(template, null);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory((org.springframework.amqp.rabbit.connection.ConnectionFactory) connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public CachingConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(host, port);
    }
}
