package com.anz.creditcardfrauddeductionservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;

/**
 * @author rdavis
 * 
 *         This class written to define kafka consumer configuration.
 *
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	@Value("${spring.kafka.consumer.bootstrap-servers}")
	private String bootstrapServers;

	@Value("${spring.kafka.consumer.auto-offset-reset}")
	private String offsetreset;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, offsetreset);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "cardTransactionGrpId");

		return props;
	}

	@Bean
	public ConsumerFactory<String, CustomerCardTransactions> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs(), new StringDeserializer(),
				new JsonDeserializer<>(CustomerCardTransactions.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CustomerCardTransactions> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CustomerCardTransactions> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}

}
