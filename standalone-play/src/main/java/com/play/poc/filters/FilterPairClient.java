package com.play.poc.filters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilterPairClient {

    public static void main(String[] args) {
        try {

            //List<String> constructedFilters = new ArrayList<>();
            List<DataFilter> filters = new ArrayList<>();
            List<FilterPair> dataFilters = buildFilters();
            computeStartAndEndDate(filters, dataFilters);

            for (FilterPair filterPair : dataFilters) {
                switch (filterPair.getKey()) {
                    case "presenceStateTerm":
                        filters.add(new PresenceStateFilter(filterPair.getValue()));
                        break;
                }
            }


            System.out.println("Collected Filters :: " + filters.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private static List<FilterPair> buildFilters() {
        List<FilterPair> dataFilters = new ArrayList<>();
        dataFilters.add(new FilterPair("clinicallyRelevantStartDate", "2019-12-25"));
        dataFilters.add(new FilterPair("clinicallyRelevantEndDate", "2019-12-31"));
        dataFilters.add(new FilterPair("presenceStateTerm", "Present"));
        return dataFilters;
    }


    private static void computeStartAndEndDate(List<DataFilter> filters, List<FilterPair> dataFilters) throws Exception {
        FilterPair clinicalStartDate = dataFilters.stream().filter(filterPair -> filterPair.getKey().equalsIgnoreCase("clinicallyRelevantStartDate")).findAny().orElse(null);
        FilterPair clinicalEndDate = dataFilters.stream().filter(filterPair -> filterPair.getKey().equalsIgnoreCase("clinicallyRelevantEndDate")).findAny().orElse(null);

        if (Objects.nonNull(clinicalStartDate) && Objects.nonNull(clinicalEndDate)) {
            filters.add(new ClinicallyRelevantDateFilter(clinicalStartDate.getValue(), clinicalEndDate.getValue()));
        } else if (Objects.nonNull(clinicalStartDate)) {
            filters.add(new ClinicallyRelevantDateFilter(clinicalStartDate.getValue(), LocalDate.now().toString()));
        } else if (Objects.nonNull(clinicalEndDate)) {
            throw new Exception("Data filter can't filter only with end date; Please provide start date");
        }
    }
}
