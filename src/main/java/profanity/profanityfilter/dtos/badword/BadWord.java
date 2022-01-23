package profanity.profanityfilter.dtos.badword;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadWord {
    private Double deviations;
    private Integer end;
    private Integer info;
    private String original;
    private Integer replacedLen;
    private Integer start;
    private String word;
}
