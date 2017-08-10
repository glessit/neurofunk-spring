package com.glessit.neurofunky.configuration.platform.bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

import java.util.HashMap;
import java.util.Map;

import static com.glessit.neurofunky.util.LogUtil.debug;

@Slf4j
public class TelegramBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private final Integer adminId;

    /* key - userId, val - chatId */
    private final Map<Integer,Long> chats = new HashMap<>();

    public Map<Integer, Long> getChats() {
        return chats;
    }

    public TelegramBot(String botUsername, String botToken, Integer adminId) {
        this.adminId = adminId;
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        Integer messageUserId = update.getMessage().getFrom().getId();
        Long chatId = update.getMessage().getChatId();
        if (!adminId.equals(messageUserId) ) {
            return;
        }

        debug(
                log,
                "New message on bot's channel: {}, from user: {}[{}]",
                update.getMessage().getText(),
                update.getMessage().getFrom().getUserName(),
                messageUserId
        );

        if (chats.isEmpty() || !chats.get(messageUserId).equals(chatId)) {
            chats.clear();
            chats.put(messageUserId, update.getMessage().getChatId());
        }

        /* put something useful here */

        /*
            SendMessage message = new SendMessage()
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                sendMessage(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        */
    }

    @Override
    public String getBotUsername() {
        return this.botUsername;
    }

    @Override
    public String getBotToken() {
        return this.botToken;
    }
}
