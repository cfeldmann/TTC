package com.christine.test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Sample schedule used for testing.
 */
class Schedule {

    static final List<LocalTime> SCHEDULE = new ArrayList<>();
    
    static {
        SCHEDULE.add(LocalTime.of(1, 0));
        SCHEDULE.add(LocalTime.of(9, 0));
        SCHEDULE.add(LocalTime.of(10, 0));
        SCHEDULE.add(LocalTime.of(11, 0));
        SCHEDULE.add(LocalTime.of(12, 0));
        SCHEDULE.add(LocalTime.of(13, 0));
        SCHEDULE.add(LocalTime.of(14, 0));
        SCHEDULE.add(LocalTime.of(15, 0));
        SCHEDULE.add(LocalTime.of(16, 0));
        SCHEDULE.add(LocalTime.of(17, 0));
        SCHEDULE.add(LocalTime.of(18, 0));
        SCHEDULE.add(LocalTime.of(19, 0));
        SCHEDULE.add(LocalTime.of(20, 0));
        SCHEDULE.add(LocalTime.of(21, 0));
        SCHEDULE.add(LocalTime.of(22, 0));
        SCHEDULE.add(LocalTime.of(23, 0));
    }

}
