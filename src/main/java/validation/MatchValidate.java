package validation;

import model.Match;

public class MatchValidate {
    private final static MatchValidate INSTANCE = new MatchValidate();

    private MatchValidate() {
    }

    public MatchValidate getInstance() {
        return INSTANCE;
    }

    public boolean isExist(Match match) {
        return match == null;
    }
}
