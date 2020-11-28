package com.anz.creditcardfrauddeductionservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anz.creditcardfrauddeductionservice.model.CustomerCardTransactions;
import com.anz.creditcardfrauddeductionservice.producer.CardTransactionProducer;

/**
 * @author rdavis
 * 
 *         This rest api written to push message to card_transaction topic. it
 *         can be called using swagger uri.
 *
 */

@RestController
@RequestMapping(value = "/kafka")
public class CardTransactionProducerController {

	@Autowired
	CardTransactionProducer producer;

	@PostMapping(value = "/pushCardTransactionMessage", consumes = "application/json", produces = "application/json")
	public CustomerCardTransactions pushMessage(@RequestBody CustomerCardTransactions cardTrans) throws Exception {

		producer.send(cardTrans);
		return cardTrans;
	}

}
