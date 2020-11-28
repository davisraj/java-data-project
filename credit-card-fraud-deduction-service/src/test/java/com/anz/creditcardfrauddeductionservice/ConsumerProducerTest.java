package com.anz.creditcardfrauddeductionservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import com.anz.creditcardfrauddeductionservice.consumer.CardTransactionsConsumer;
import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;
import com.anz.creditcardfrauddeductionservice.producer.CardTransactionProducer;


@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = { ConsumerProducerTest.TOPIC })
public class ConsumerProducerTest {

	static final String TOPIC = "card_transaction";

	@Autowired
	private CustomerCardTransactions customerTrans;

	@Autowired
	private CardTransactionsConsumer receiver;

	@Autowired
	private CardTransactionProducer sender;

	@Test
	public void testConsume() throws Exception {

		customerTrans.setAmount(100.00);
		customerTrans.setCity("chennai");
		customerTrans.setCustomerId("123456");
		customerTrans.setDate("02102020");
		customerTrans.setTransactionDesciption("Jaya store");
		sender.send(customerTrans);

		receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
		assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}
}
