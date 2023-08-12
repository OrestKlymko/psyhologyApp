package com.psychology.web.telegram;

import com.psychology.web.GPT.ChatGPT;
import com.psychology.web.telegram.botCommand.CommandConsultation;
import com.psychology.web.telegram.botCommand.CommandWhatHappened;
import com.psychology.web.telegram.botCommand.CommandStart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
	private List<String> userHistory;
	private final BotConfig botConfig;

	private ChatGPT chatGPT;

	@Override
	public String getBotUsername() {
		return botConfig.getBotName();
	}

	@Override
	public String getBotToken() {
		return botConfig.getToken();
	}


	@Override
	public void onUpdateReceived(Update update) {
		SendMessage message;
		String messageText = update.getMessage().getText();
		Long chatId = update.getMessage().getChatId();

		switch (messageText) {
			case "/start" -> message = CommandStart.init(chatId,"What you prefer?");
			case "Consultation" -> message = CommandConsultation.init(chatId);
			case "What happened?" -> message = CommandWhatHappened.init(chatId);
			case "Help of a psychotherapist" -> message = null; // there will be a PayPal service
			default -> message = CommandStart.init(chatId,"Sorry, I don't know this command, please choose button: ");
		}

		userHistory.add(message.getText());
		if (userHistory!=null) {
			secondCommand(userHistory, message, messageText, chatId);
		}


		try {
			execute(message);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void onRegister() {
		super.onRegister();
	}

	private void secondCommand(List<String> userHistory, SendMessage message, String messageText, Long chatId){
		if (userHistory.size() >= 2) {
			if (userHistory.get(userHistory.size() - 2).equals("Tell me your history")) {
				try {
					message.setText(chatGPT.chatGPT(messageText));
				} catch (Exception e) {
					message.setText("Something wrong, try to connect throw Consultation");
				}
			}

			if (userHistory.get(userHistory.size() - 2).equals("Enter your number with code of  and we call you as soon as we can")) {
				if(ValidatePhoneNumber.isValid(messageText)){
					message.setText("We registration your number, thank you!");
					//add number to DataBase or call tracking.
				}else {
					message.setText("Please, go to the Consultation and provide your number on format +380630000000");
				}
			}
		}

	}
}




