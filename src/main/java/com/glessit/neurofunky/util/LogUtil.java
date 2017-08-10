package com.glessit.neurofunky.util;

import org.slf4j.Logger;

public class LogUtil {
    public final static void debug(Logger logger, String message, Object... messageArgs) {
        if (logger.isDebugEnabled()) {
            logger.debug(message, messageArgs);
        }
    }
}
