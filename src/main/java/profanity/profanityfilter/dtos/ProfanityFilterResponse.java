package profanity.profanityfilter.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfanityFilterResponse {
    private String userdId;
    private String userFilteredMessage;
    private String analyzationResponse;

}
