package com.glessit.neurofunky.web.facebook;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.neurofunky.service.ISecurityService;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.service.dto.FacebookToken;
import com.glessit.neurofunky.service.util.Util;
import com.glessit.neurofunky.web.facebook.dto.AccessToken;
import com.glessit.neurofunky.web.facebook.dto.FacebookUserDto;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

import static com.glessit.neurofunky.service.util.Util.getIPAddress;
import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping(value = "/facebook/login")
@Slf4j
public class LoginDialogHandler {

    @Value(value = "${facebook.clientId}")
    private String clientId;
    @Value(value = "${facebook.redirectURI}")
    private String redirectURI;
    @Value(value = "${facebook.appSecret}")
    private String appSecret;
    @Value(value = "${facebook.meEndpoint}")
    private String meEndpoint;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ISecurityService securityService;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IUserService userService;

    private final static String DIALOG_URL = "https://www.facebook.com/v2.9/dialog/oauth?client_id=%s&redirect_uri=%s";
    private final static String GET_TOKEN_BY_CODE = "https://graph.facebook.com/v2.9/oauth/access_token?" +
                                                    "client_id=%s&redirect_uri=%s&client_secret=%s&code=%s";

    private class FacebookResponseErrorHandler extends DefaultResponseErrorHandler {
        @Override
        public void handleError(ClientHttpResponse response) throws IOException {
            String rawErrorMessage = Util.toString(response.getBody());
            throw new RestClientException(objectMapper.readTree(rawErrorMessage).get("error").get("message").asText());
        }
    }

    @RequestMapping(value = "/dialog/url", method = RequestMethod.GET)
    public Map<String,String> getDialogHandlerURL() {
        Map<String, String> result = Maps.newHashMap();
        result.put("URL", format(DIALOG_URL, clientId, redirectURI));
        return result;
    }

    @RequestMapping(value = "/handler", method = RequestMethod.GET)
    public FacebookToken handler(@RequestParam(value = "code") String code, HttpServletRequest request) {

        // set error handler for restTemplate
        restTemplate.setErrorHandler(new FacebookResponseErrorHandler());

        log.info("Code received {}", code);
        log.info("Get token by code ..");

        URI URI = create(format(GET_TOKEN_BY_CODE.concat("#_=_"), clientId, redirectURI, appSecret, code));

        ResponseEntity<AccessToken> accessToken = restTemplate.getForEntity(URI, AccessToken.class);

        log.info("Try to get information about user from Facebook API..");
        String[] requiredFields = new String[] {"first_name", "last_name", "picture"}; // "email"
        StringBuilder meEndpointAppender = new StringBuilder();
        meEndpointAppender
                .append(meEndpoint)
                .append("?fields=")
                .append(Joiner.on(",").join(requiredFields))
                .append("&access_token=")
                .append(accessToken.getBody().getAccess_token());
        URI meEndpointURI = create(meEndpointAppender.toString());
        ResponseEntity<FacebookUserDto> responseWithUserInfo = restTemplate.getForEntity(meEndpointURI, FacebookUserDto.class);
        FacebookUserDto user = responseWithUserInfo.getBody();
        log.info("User {} has found", user.getFirstName());

        if (!userService.isFacebookUserExist(user.getId())) {
            /* create user */
            userService.createFacebookUser(user);
        }
        user.setIpAddress(getIPAddress(request));
        log.info("Login as {}", user.getFirstName());
        return securityService.login(user.getId());
    }
}
