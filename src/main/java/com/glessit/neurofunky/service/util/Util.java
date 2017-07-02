package com.glessit.neurofunky.service.util;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Util {

    public final static String toString(InputStream inputStream) throws IOException {
        return CharStreams.toString(new InputStreamReader(inputStream,Charsets.UTF_8));
    }

    public final static String getIPAddress(HttpServletRequest request) {
        String remoteAddress = "";
        if (request != null) {
            remoteAddress = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddress == null || "".equals(remoteAddress)) {
                remoteAddress = request.getRemoteAddr();
            }
        }
        return remoteAddress;
    }
}
