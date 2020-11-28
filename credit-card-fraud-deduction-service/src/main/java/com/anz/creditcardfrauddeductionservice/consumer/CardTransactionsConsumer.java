package com.anz.creditcardfrauddeductionservice.consumer;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;
import com.anz.creditcardfrauddeductionservice.producer.CardTransactionAlertProducer;
import com.anz.creditcardfrauddeductionservice.service.CustomerLocationService;
import org.springframework.kafka.support.KafkaHeaders;

/**
 * @author rdavis This Kafka consumer Listener will recive message from Kafka
 *         topic card_transaction and validate location. If false location it
 *         will push alert message to topic card_transaction_alert
 */
@Component
public class CardTransactionsConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(CardTransactionsConsumer.class);

	@Autowired
	CustomerLocationService locationServ;
	@Autowired
	CardTransactionAlertProducer alertProducer;
	//Added for testing purpose. It should not be kept for production code
	private CountDownLatch latch = new CountDownLatch(1);
	List<String> locations = null;

	@KafkaListener(topics = "${spring.kafka.consumer.topic}",containerFactory = "kafkaListenerContainerFactory")
	public void consume(@Payload CustomerCardTransactions cardTrans, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
		LOGGER.info("Consuming Credit Card Transaction detailsr='{}' MessageKey '{}'", cardTrans.toString(), key);
		//added for testing purpose
		latch.countDown();
		try {

			if (!locationServ.ValidateLocation(cardTrans)) {

				alertProducer.send(cardTrans);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception while getting customer location", e);
		}
	}
	
	

	  public CountDownLatch getLatch() {
	    return latch;
	  }

}
