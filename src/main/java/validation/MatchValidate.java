package validation;

import model.Match;
import org.h2.util.StringUtils;

public class MatchValidate {
    private final static MatchValidate INSTANCE = new MatchValidate();

    private MatchValidate() {
    }

    public static MatchValidate getInstance() {
        return INSTANCE;
    }

    public boolean isEmptyOrNull(String str) {
        return StringUtils.isNullOrEmpty(str);
    }

    public boolean isNumber(String number) {
        return StringUtils.isNumber(number);
    }
}
