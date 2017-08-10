package com.glessit.neurofunky.configuration.startup;

import com.glessit.neurofunky.configuration.platform.bot.TelegramBot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static com.glessit.neurofunky.util.LogUtil.debug;

@Component
@Slf4j
public class RootContextStartUp implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private TelegramBot telegramBot;

    {
        ApiContextInitializer.init();
    }

    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            TelegramBotsApi botsApi = new TelegramBotsApi();
            try {
                botsApi.registerBot(telegramBot);
            } catch (TelegramApiException e) {
                debug(log, "Telegram bot initialization was failed!");
                e.printStackTrace();
            }
        }
    }
}