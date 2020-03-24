package com.play.poc.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PresenceState {
    Present("Present"),
    Not_Present("Not Present"),
    Past_Occurrence("Past Occurrence"),
    Planned_Occurrence("Planned Occurrence");

    private String value;


    private PresenceState(String value) {
        this.value = value;
    }

    /**
     * Returns a <tt>RuleTypeEvaluator<tt> enum based on string matching
     *
     * @param value string stored in database
     * @return a matching <tt>Genre</tt>
     */
    @JsonCreator
    public static PresenceState fromValue(String value) {
        return valueOf(value);
    }

    /**
     * Converts a <tt>RuleTypeEvaluator</tt> to matching type string
     *
     * @param presenceState
     * @return matching type string
     */
    @JsonValue
    public static String toValue(PresenceState presenceState) {
        return presenceState.name().toLowerCase();
    }
}
