package com.optum.reporting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SubscriberCategoryTracker {

    private int empCounter = 0;
    private int espCounter = 0;
    private int echCounter = 0;
    private int famCounter = 0;
}
