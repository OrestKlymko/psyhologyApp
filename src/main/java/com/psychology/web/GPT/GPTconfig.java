package com.psychology.web.GPT;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@Data
@PropertySource("/application.properties")
public class GPTconfig {
	@Value("${gpt.apikey}")
	private String apiKey;
	@Value("${gpt.url}")
	private String url;
}
