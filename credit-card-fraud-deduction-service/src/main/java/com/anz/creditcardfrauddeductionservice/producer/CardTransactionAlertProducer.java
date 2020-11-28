package com.anz.creditcardfrauddeductionservice.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;

/**
 * @author rdavis This Kafka Producer send message to topic
 *         card_transaction_alert
 * 
 */

@Service
public class CardTransactionAlertProducer {

	private static final Logger LOG = LoggerFactory.getLogger(CardTransactionAlertProducer.class);

	@Autowired
	private KafkaTemplate<String, CustomerCardTransactions> kafkaTemplate;

	@Value("${spring.kafka.producer.topic2}")
	private String topic;

	public void send(CustomerCardTransactions data) {
		LOG.info("sending JSON data='{}' to topic='{}'", data, topic);

		// create message object from JSON data object
		Message<CustomerCardTransactions> message = MessageBuilder.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, topic).build();

		kafkaTemplate.send(message);
	}
}
