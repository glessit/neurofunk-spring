package com.glessit.neurofunky.configuration.platform.bot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class TelegramBotCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return Boolean.valueOf(env.getProperty("platform.bot.telegram.enabled"));
    }
}
