package com.channa.mobiledatausageapp.data.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Year {
    private Integer year;
    private List<Quarter> quarters;

    public Year(Integer year, List<Quarter> quarters) {
        this.year = year;
        initQuarters(quarters);
    }

    /**
     * @return true if year has 4 Quarters
     */
    public boolean isYearCompleted() {
        return quarters.size() == 4;
    }

    /**
     * initialize Quarters for the year
     *
     * @param quarterList
     */
    private void initQuarters(List<Quarter> quarterList) {
        /**
         * sort Quarter objects based on quarter name
         */
        Collections.sort(quarterList, new Comparator<Quarter>() {
            public int compare(Quarter q1, Quarter q2) {
                return q1.getQuarterName().compareTo(q2.getQuarterName());
            }
        });


        /**
         * calculate usage growth of quarters
         * we are calculating the growth within an year therefore Q1 is being omitted by setting 0f
         */
        for (int i = 0; i < quarterList.size(); i++) {
            if (i == 0) {
                quarterList.get(i).setUsageGrowth(0f);
            } else {
                float growth = quarterList.get(i).getUsage() - quarterList.get(i - 1).getUsage();
                quarterList.get(i).setUsageGrowth(growth);
            }
        }

        this.quarters = quarterList;
    }

    /***
     * @return total mobile data usage for the year
     */
    public float getTotalUsage() {
        float usage = 0;
        for (Quarter quarter : quarters) {
            usage = usage + quarter.getUsage();
        }
        return usage;
    }

    public Integer getYear() {
        return year;
    }

    public List<Quarter> getQuarters() {
        return quarters;
    }


    /**
     * @return verify if year consists of Quarters with decrease in mobile data usage
     */
    public boolean isDecreasedGrowth() {
        boolean isDecreaseGrowth = false;

        for (Quarter quarter : quarters) {
            if (quarter.getUsageGrowth() < 0) {
                isDecreaseGrowth = true;
            }
        }
        return isDecreaseGrowth;
    }

}
