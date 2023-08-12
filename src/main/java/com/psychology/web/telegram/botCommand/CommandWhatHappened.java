package com.psychology.web.telegram.botCommand;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;

@Component
public class CommandWhatHappened {
	public static SendMessage init(long chatId) {

		SendMessage message = new SendMessage();

		message.setChatId(chatId);
		message.setText("Tell me your history");

		ReplyKeyboardRemove keyboardRemove = new ReplyKeyboardRemove();
		keyboardRemove.setRemoveKeyboard(true);
		keyboardRemove.setSelective(false);

		message.setReplyMarkup(keyboardRemove);
		return message;
	}

}
