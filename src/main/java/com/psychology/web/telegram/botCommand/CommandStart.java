package com.psychology.web.telegram.botCommand;

import com.psychology.web.telegram.TelegramKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CommandStart {
	public static SendMessage init(long chatId) {
		SendMessage message = new SendMessage();
		TelegramKeyboard keyboard = new TelegramKeyboard();
		message.setChatId(chatId);
		message.setText("What you prefer?");
		message.setReplyMarkup(keyboard.replyKeyboardMarkup());
		return message;
	}

	public static SendMessage getWrongCommand(long chatId) {
		SendMessage message = new SendMessage();
		TelegramKeyboard keyboard = new TelegramKeyboard();
		message.setChatId(chatId);
		message.setText("Sorry, I don't know this command, please choose button: ");
		message.setReplyMarkup(keyboard.replyKeyboardMarkup());
		return message;
	}
}
