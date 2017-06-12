package com.glessit.neurofunky.web.facebook;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.glessit.neurofunky.service.IUserService;
import com.glessit.neurofunky.web.facebook.dto.AccessToken;
import com.glessit.neurofunky.web.facebook.dto.LoginResult;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

import static java.lang.String.format;
import static java.net.URI.create;

@RestController
@RequestMapping(value = "/facebook/login")
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
    private HttpClient httpClient;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IUserService userService;

    private final static String DIALOG_URL = "https://www.facebook.com/v2.9/dialog/oauth?client_id=%s&redirect_uri=%s";
    private final static String GET_TOKEN_BY_CODE = "https://graph.facebook.com/v2.9/oauth/access_token?" +
                                                    "client_id=%s&redirect_uri=%s&client_secret=%s&code=%s";

    private static Logger LOGGER = LoggerFactory.getLogger(LoginDialogHandler.class);

    @RequestMapping(value = "/dialog/url", method = RequestMethod.GET)
    @PreAuthorize(value = "hasRole('glessitsss')")
    public Map<String,String> getDialogHandlerURL() {
        Map<String, String> result = Maps.newHashMap();
        result.put("URL", format(DIALOG_URL, clientId, redirectURI));
        return result;
    }
    /*@RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        LoginResult result = new LoginResult();
        result.setName("asdasd");
        return new ResponseEntity<String>("Hello World", HttpStatus.CREATED);
    }*/

    @RequestMapping(value = "/handler", method = RequestMethod.GET)
    public LoginResult handler(@RequestParam(value = "code") String code) {

        LOGGER.info("Code received {}", code);
        LOGGER.info("Get token by code ..");

        URI URI = create(format(GET_TOKEN_BY_CODE.concat("#_=_"), clientId, redirectURI, appSecret, code));
        HttpGet getTokenRequest = new HttpGet(URI);
        try {
            HttpResponse response = httpClient.execute(getTokenRequest);
            LOGGER.info("Response length {}", response.getEntity().getContentLength());
            String rawJson = CharStreams.toString(
                    new InputStreamReader(
                            response.getEntity().getContent(),
                            Charsets.UTF_8
                    )
            );

            JsonNode jsonTree = objectMapper.readTree(rawJson);
            if (null != jsonTree.get("error")) {
                String errorMessage = jsonTree.get("error").get("message").textValue();
                LOGGER.error("Can't exchange code to token. Reason: {}", errorMessage);
                throw new Exception(errorMessage);
            }

            AccessToken accessTokenObject = objectMapper.readValue(rawJson, AccessToken.class);

            LOGGER.info("Try to get information about user from Facebook API..");

            String[] requiredFields = new String[] {"first_name", "last_name", "email, picture"};

            URI meEndpointURI = create(meEndpoint);

            URIBuilder builder = new URIBuilder();
            builder
                    .setScheme(meEndpointURI.getScheme())
                    .setHost(meEndpointURI.getHost())
                    .setPort(meEndpointURI.getPort())
                    .setPath(meEndpointURI.getPath())
                    .setParameter("fields", Joiner.on(",").join(requiredFields))
                    .setParameter("access_token", accessTokenObject.getAccess_token());

            HttpResponse userInfoResponse = httpClient.execute(
                    new HttpGet(builder.build())
            );

            System.out.println(userInfoResponse.getEntity().getContentLength());

            // https://graph.facebook.com/me?fields=name,picture,last_name,first_name,email&access_token=




                // get username, avatar
                // create user is not exist, and do auto-login
                System.out.println();
            return new LoginResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginResult();
    }

}
