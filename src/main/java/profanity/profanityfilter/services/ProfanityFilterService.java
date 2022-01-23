package profanity.profanityfilter.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import profanity.profanityfilter.dtos.badword.BadWordsResponse;
import profanity.profanityfilter.exceptions.DailyLimitReachedException;

import java.util.Collections;

@Service
public class ProfanityFilterService {
    private static final Logger logger = LoggerFactory.getLogger(ProfanityFilterService.class);

    private static final String API_KEY = "mt5AX8lOiOQs1LDpZaNv1kZqfoYRK0rf";
    private static final String URL = "https://api.promptapi.com/bad_words";

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private RestTemplate restTemplate;

    public BadWordsResponse filterMessage(String message) throws Exception {
        logger.info("Sending request to BadWordsAPI " + "{ \"message\": \"" + message + " \"}");
        HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", API_KEY);
        headers.add("user-agent", "Application");
        headers.setAccept(Collections.singletonList(MediaType.ALL));

        HttpEntity<String> entity = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

        logger.info("Response" + response.getBody());
        if (response.getStatusCode().is4xxClientError()) {
            logger.error("Exceeded daily limit for requests!");
            throw new DailyLimitReachedException();
        }

        return mapper.readValue(response.getBody(), BadWordsResponse.class);
    }

}
