package com.psychology.web.GPT;

import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;


@Component
public class ChatGPT {

	@Autowired
	private GPTconfig gpTconfig;

	public String chatGPT(String text) throws Exception {


		HttpURLConnection con = (HttpURLConnection) new URL(gpTconfig.getUrl()).openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + gpTconfig.getApiKey());

		JSONObject data = new JSONObject();
		data.put("prompt", text);
		data.put("max_tokens", 50);
		data.put("temperature", 0.7);

		con.setDoOutput(true);
		try (OutputStream os = con.getOutputStream()) {
			byte[] input = data.toString().getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		StringBuilder response = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
			String responseLine;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
		}
		JSONObject jsonResponse = new JSONObject(response.toString());
		String generatedText = jsonResponse.getJSONArray("choices")
				.getJSONObject(0).getString("text");

		return generatedText;
	}
}
