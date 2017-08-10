package com.glessit.neurofunky.configuration;

import com.glessit.neurofunky.configuration.platform.bot.TelegramBot;
import com.glessit.neurofunky.configuration.platform.bot.TelegramBotCondition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

    @Value("${platform.bot.telegram.username}")
    private String telegramBotUsername;
    @Value("${platform.bot.telegram.token}")
    private String telegramBotToken;
    @Value("${platform.bot.telegram.adminId}")
    private Integer telegramAdminId;

    @Bean
    @Conditional(TelegramBotCondition.class)
    public TelegramBot telegramBotBean() {
        return new TelegramBot(telegramBotUsername, telegramBotToken, telegramAdminId);
    }
}
