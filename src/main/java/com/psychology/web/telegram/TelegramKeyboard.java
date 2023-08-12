package com.psychology.web.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramKeyboard {
	public ReplyKeyboardMarkup replyKeyboardMarkup() {
		ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

		replyKeyboardMarkup.setSelective(true);
		replyKeyboardMarkup.setResizeKeyboard(true);
		replyKeyboardMarkup.setOneTimeKeyboard(false);

		replyKeyboardMarkup.setKeyboard(keyboardRows());

		return replyKeyboardMarkup;
	}

	public List<KeyboardRow> keyboardRows() {
		List<KeyboardRow> rows = new ArrayList<>();
		rows.add(new KeyboardRow(keyboardButtons()));


		return rows;
	}
	public List<KeyboardButton> keyboardButtons() {
		List<KeyboardButton> buttons = new ArrayList<>();
		buttons.add(new KeyboardButton("Consultation"));
		buttons.add(new KeyboardButton("What happened?"));
		buttons.add(new KeyboardButton("Help of a psychotherapist"));

		return buttons;
	}
}
