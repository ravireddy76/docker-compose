package com.play.poc.filters;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * DataFilter class used to filter the payload data classes content.
 *
 * @author ihr extract engineering team.
 * @CopyRight (C) All rights reserved to UHG Inc. It's Illegal to reproduce this code.
 */
public interface DataFilter {

    /**
     * Method to filter the data node based on given filter criteria.
     *
     * @param dataNode
     * @return boolean
     */
    public boolean filter(JsonNode dataNode);

}
