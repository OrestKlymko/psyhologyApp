package com.psychology.web.paypalService;

import de.micromata.paypal.PayPalConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PayPalConfiguration {
	private static PayPalConfig instance;

	@Value("${paypal.client.id}")
	private String clientId;
	@Value("${paypal.client.secret}")
	private String secret;
	@Value("${paypal.return_url}")
	private String cancelPage;
	@Value("${paypal.cancel_url}")
	private String returnPage;

	private PayPalConfiguration() {
		instance = new PayPalConfig()
				.setClientId(clientId)
				.setClientSecret(secret)
				.setReturnUrl(returnPage)
				.setCancelUrl(cancelPage)
				.setMode(PayPalConfig.Mode.SANDBOX);
	}

	public static PayPalConfig getConfig() {
		return instance;
	}
}
