package validation;

import org.h2.util.StringUtils;

import java.util.stream.Stream;

public class PlayerValidate {
    private static final PlayerValidate INSTANCE = new PlayerValidate();

    private PlayerValidate() {
    }

    public boolean isEmptyOrNull(String str) {
       return StringUtils.isNullOrEmpty(str);
    }

    public boolean isNumber(String number) {
       return StringUtils.isNumber(number);
    }

    public static PlayerValidate getInstance() {
        return INSTANCE;
    }

}
