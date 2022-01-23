package profanity.profanityfilter.exceptions;

public class DailyLimitReachedException extends Exception {
    private static final long serialVersionUID = -6945641556834546389L;

    public DailyLimitReachedException() {
        super("Daily limit reached for API calls!");
    }
}
