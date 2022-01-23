package profanity.profanityfilter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import profanity.profanityfilter.dtos.ProfanityFilterRequest;
import profanity.profanityfilter.dtos.ProfanityFilterResponse;
import profanity.profanityfilter.dtos.badword.BadWordsResponse;
import profanity.profanityfilter.services.ProfanityFilterService;

@RestController
@CrossOrigin(origins = "https://api.promptapi.com/bad_words")
@RequestMapping(value = "/profanity")
public class ProfanityFilterController {
    private static final Logger logger = LoggerFactory.getLogger(ProfanityFilterController.class);
    private static final String USER_WARNING_MESSAGE = "Sending this kind of content can get you banned!";

    @Autowired
    private ProfanityFilterService profanityFilterService;

    @GetMapping
    public boolean isAlive() {
        return true;
    }

    @PostMapping(value = "/filter/message")
    public ResponseEntity<Object> filterUserMessage(@RequestBody ProfanityFilterRequest request) throws Exception {
        if (request == null || request.getUserId() == null || request.getUserMessage() == null) {
            logger.error("Information missing when requesting service!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        logger.info("Request received: " + request.toString());

        BadWordsResponse badWordsResponse = profanityFilterService.filterMessage(request.getUserMessage());

        ProfanityFilterResponse response = new ProfanityFilterResponse();
        response.setUserdId(request.getUserId());
        response.setAnalyzationResponse(USER_WARNING_MESSAGE);
        response.setUserFilteredMessage(badWordsResponse.getCensoredContent());

        return ResponseEntity.ok(response);
    }
}
