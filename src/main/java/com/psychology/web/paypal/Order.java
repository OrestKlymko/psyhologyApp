package com.psychology.web.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
	private final double price=20;
	private final String currency="EUR";
	private final String method="Paypal";
	private final String intent = "NONE";
	private String email;
	private String description;
}
