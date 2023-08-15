package com.psychology.web.paypal;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PaypalController {
	@Autowired
	PaypalService service;
	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@GetMapping("/payment")
	public ModelAndView home() {
		return new ModelAndView("order-form");
	}

	@PostMapping("/payment")
	public String payment(@ModelAttribute("order") Order order) {
		String approvalLink = null;
		try {
			Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
					order.getIntent(), order.getDescription(),
					"http://localhost:8081/pay/cancel", "http://localhost:8081/pay/success");
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					approvalLink = link.getHref();
					break;
				}
			}
		} catch (PayPalRESTException e) {
			throw new RuntimeException(e);
		}
		return "redirect:" + approvalLink;
	}

	@GetMapping(value = CANCEL_URL)
	public ModelAndView cancelUrl() {
		return new ModelAndView("payment-failed");
	}


	@GetMapping(value = SUCCESS_URL)
	public ModelAndView successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				return new ModelAndView("payment-success");
			}

		} catch (PayPalRESTException e) {
			return new ModelAndView("payment-failed");
		}
		return new ModelAndView("order-form");
	}
}