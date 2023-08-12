package com.psychology.web.GPT;

import org.json.JSONObject;
import org.springframework.scheduling.annotation.Async;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public class ChatGPT {
	public static String chatGPT(String text) throws Exception {
		String apiKey = "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";

		String url = "https://api.openai.com/v1/engines/text-davinci-003/completions";
		HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "Bearer " + apiKey);

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
