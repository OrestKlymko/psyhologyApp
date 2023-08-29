package com.psychology.web.paypalService;

import de.micromata.paypal.PayPalConfig;
import de.micromata.paypal.PayPalConnector;
import de.micromata.paypal.PayPalRestException;
import de.micromata.paypal.data.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

import static de.micromata.paypal.data.Currency.EUR;

@Service
public class PaypalService {
	@Value("${paypal.client.id}")
	private String clientId;
	@Value("${paypal.client.secret}")
	private String secret;
	@Value("${paypal.return_url}")
	private String cancelPage;
	@Value("${paypal.cancel_url}")
	private String returnPage;


	public String createPayment(String paymentName) throws PayPalRestException {

		PayPalConfig payPalConfig = new PayPalConfig()
				.setClientId(clientId).setClientSecret(secret)
				.setReturnUrl(returnPage).setCancelUrl(cancelPage)
				.setMode(PayPalConfig.Mode.SANDBOX);

		Transaction transaction = new Transaction(EUR);
		transaction.addItem(paymentName, 5);
		Random random = new Random();
		transaction.setInoviceNumber(String.valueOf(random.nextInt()));
		Payment payment = new Payment(transaction);
		payment.setNoteToPayer("We will contact with you");
		payment.setShipping(ShippingPreference.NO_SHIPPING);

		Payment paymentCreated = PayPalConnector.createPayment(payPalConfig, payment);
		if (paymentCreated != null) {
			Payment paymentExecuted = PayPalConnector.executePayment(payPalConfig, paymentCreated.getId(), "R3KAP44PTRLT8");
			if (paymentExecuted != null) {
				return paymentCreated.getRedirectUrls().getReturnUrl();
			}
		}
		return cancelPage;
	}
}
