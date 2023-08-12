package com.psychology.web.telegram.botCommand;

import com.psychology.web.telegram.TelegramKeyboard;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Component
public class CommandStart {
	public static SendMessage init(long chatId, String messageResponse) {
		SendMessage message = new SendMessage();
		TelegramKeyboard keyboard = new TelegramKeyboard();
		message.setChatId(chatId);
		message.setText(messageResponse);
		message.setReplyMarkup(keyboard.replyKeyboardMarkup());
		return message;
	}
}
