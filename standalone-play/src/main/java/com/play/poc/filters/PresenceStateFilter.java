package com.play.poc.filters;

public class PresenceStateFilter implements DataFilter {

    public PresenceStateFilter(String value) {
        System.out.println("Inside the Presence State Filter with value :: "+value);
    }

    @Override
    public boolean filter(com.fasterxml.jackson.databind.JsonNode dataNode) {
        return false;
    }
}
