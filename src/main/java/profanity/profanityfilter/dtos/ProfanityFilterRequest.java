package profanity.profanityfilter.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfanityFilterRequest {
    private String userId;
    private String userMessage;
}
