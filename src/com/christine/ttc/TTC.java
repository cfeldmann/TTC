package com.christine.ttc;

import com.sun.istack.internal.NotNull;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents the TTC subway system.
 *
 * A station's direction of travel capabilities are defined by adding a schedule for that station.
 *
 * The stations purposely do not care about what line(s) they belong to because it's extraneous
 * information for the purposes of this API.  All we care about is what direction we're going
 * to travel and when the next train will arrive.
 */
public class TTC {

    private ScheduleManager scheduleManager = new ScheduleManager();

    /**
     * Adds a schedule for the given subway station and direction of travel.
     * It is assumed that the provided schedule is sorted in chronological order.
     * @param station the station to add a schedule for
     * @param direction the direction of travel for this schedule
     * @param schedule a chronologically sorted, non-empty list of train times
     */
    public void addSchedule(@NotNull Station station, @NotNull Direction direction, @NotNull List<LocalTime> schedule) {
        scheduleManager.addSchedule(station, direction, schedule);
    }

    /**
     * Returns the time of arrival of the next scheduled train after the given time.
     *
     * Note: if the last train for today has already passed, then the first train for tomorrow
     *       will be returned.
     *
     * @param time time to check the schedule for
     * @param station station to check the schedule for
     * @param direction desired direction of travel
     * @return the time of the next train scheduled after the given time
     * @throws DirectionNotSupportedException if the given station does not have a train going in the provided direction
     */
    public LocalTime nextTrain(@NotNull LocalTime time, @NotNull Station station, @NotNull Direction direction)
            throws DirectionNotSupportedException {

        if (time == null) {
            throw new IllegalArgumentException("time cannot be null");
        }

        List<LocalTime> schedule = scheduleManager.getSchedule(station, direction);

        for (LocalTime nextTrain : schedule) {
            if (time.isBefore(nextTrain)) {
                return nextTrain;
            }
        }

        // We've exhausted all the trains in the schedule, so the next train after the given time
        // will be the first train scheduled the next day.
        return schedule.get(0);
    }
}
