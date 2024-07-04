package io.alet.telebirr_request.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.alet.telebirr_request.constants.TelebirrMessagingValue.*;
import static io.alet.telebirr_request.constants.TelebirrMessagingValue.ROUTING_KEY;

@Configuration
public class MessagingConfig {


    @Bean
    public Queue    queryMandateRequestQueue() {
        return new Queue(QUERY_MANDATE_REQUEST_QUEUE);
    }

    //sms sender exchange
    @Bean
    public TopicExchange queryMandateRequestExchange() {
        return new TopicExchange(QUERY_MANDATE_REQUEST_EXCHANGE);
    }

    @Bean
    public Queue    queryMandateCallbackRequestQueue() {
        return new Queue(QUERY_MANDATE_CALLBACK_QUEUE);
    }

    //sms sender exchange
    @Bean
    public TopicExchange queryMandateCallbackRequestExchange() {
        return new TopicExchange(QUERY_MANDATE_CALLBACK_EXCHANGE);
    }





    @Bean
    public Queue createMandateRequestQueue() {
        return new Queue(CREATE_MANDATE_REQUEST_QUEUE);
    }

    //sms sender exchange
    @Bean
    public TopicExchange createMandateRequestExchange() {
        return new TopicExchange(CREATE_MANDATE_REQUEST_EXCHANGE);
    }
    @Bean
    public Queue createMandateCallbackQueue() {
        return new Queue(CREATE_MANDATE_CALLBACK_QUEUE);
    }

    //sms sender exchange
    @Bean
    public TopicExchange createMandateCallbackExchange() {
        return new TopicExchange(CREATE_MANDATE_CALLBACK_EXCHANGE);
    }

    @Bean
    public Queue cancelMandateRequestQueue() {
        return new Queue(CANCEL_MANDATE_REQUEST_QUEUE);
    }

    //sms sender exchange
    @Bean
    public TopicExchange cancelMandateRequestExchange() {
        return new TopicExchange(CANCEL_MANDATE_REQUEST_EXCHANGE);
    }


    @Bean
    public Queue cancelMandateCallbackQueue() {
        return new Queue(CANCEL_MANDATE_CALLBACK_QUEUE);
    }

    @Bean
    public TopicExchange cancelMandateCallbackExchange() {
        return new TopicExchange(CANCEL_MANDATE_CALLBACK_EXCHANGE);
    }

    @Bean
    public Binding createMandateRequestBinding(@Qualifier("createMandateRequestQueue") Queue queue, @Qualifier("createMandateRequestExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding createMandateCallbackBinding(@Qualifier("createMandateCallbackQueue") Queue queue, @Qualifier("createMandateCallbackExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }
    @Bean
    public Binding cancelMandateCallbackBinding(@Qualifier("cancelMandateCallbackQueue") Queue queue, @Qualifier("cancelMandateCallbackExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding cancelMandateRequestBinding(@Qualifier("cancelMandateRequestQueue") Queue queue, @Qualifier("cancelMandateRequestExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding queryMandateCallbackBinding(@Qualifier("queryMandateCallbackRequestQueue") Queue queue, @Qualifier("queryMandateCallbackRequestExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding queryMandateRequestBinding(@Qualifier("queryMandateRequestQueue") Queue queue, @Qualifier("queryMandateRequestExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }



    @Bean
    AmqpTemplate template(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(converter());
        return template;
    }
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
