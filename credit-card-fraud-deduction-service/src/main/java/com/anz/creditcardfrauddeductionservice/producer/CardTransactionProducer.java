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
import com.anz.creditcardfrauddeductionservice.service.SequenceManager;

/**
 * @author rdavis This producer send message to topic card_transaction. To push
 *         message to this topic
 */
@Service
public class CardTransactionProducer {

	private static final Logger LOG = LoggerFactory.getLogger(CardTransactionProducer.class);

	@Autowired
	private KafkaTemplate<String, CustomerCardTransactions> kafkaTemplate;

	@Autowired
	SequenceManager sequence;

	@Value("${spring.kafka.producer.topic1}")
	private String topic;

	public void send(CustomerCardTransactions data) throws Exception {
		LOG.info("sending JSON data='{}' to topic='{}'", data, topic);
		String key = data.getCustomerId() + SequenceManager.getNext();
		// create message object from JSON data object
		Message<CustomerCardTransactions> message = MessageBuilder.withPayload(data)
				.setHeader(KafkaHeaders.TOPIC, topic).setHeader(KafkaHeaders.MESSAGE_KEY, key).build();

		kafkaTemplate.send(message);

	}
}
