package profanity.profanityfilter.dtos.badword;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BadWordsResponse {
    @JsonProperty(value = "bad_words_list")
    private List<BadWord> badWords;
    @JsonProperty(value = "bad_words_total")
    private Integer badWordsTotal;
    @JsonProperty(value = "censored_content")
    private String censoredContent;
    private String content;
}
