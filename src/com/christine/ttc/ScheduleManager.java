package com.christine.ttc;

import com.sun.istack.internal.NotNull;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the schedules for all of trains at all of the subway stations.
 * It is assumed that the schedules are the same for every day of the week.
 */
class ScheduleManager {

    private Map<ScheduleKey, List<LocalTime>> schedules = new HashMap<>();

    /**
     * Adds a schedule for the given station and direction.
     * @param station station for the provided schedule
     * @param direction direction of travel for the provided schedule
     * @param schedule the schedule for the given station and direction
     */
    void addSchedule(@NotNull Station station, @NotNull Direction direction, @NotNull List<LocalTime> schedule) {

        if (schedule == null) {
            throw new IllegalArgumentException("schedule cannot be null");
        }
        if (schedule.isEmpty()) {
            throw new IllegalArgumentException("schedule cannot be an empty list");
        }

        schedules.put(new ScheduleKey(station, direction), schedule);
    }

    /**
     * Returns the schedule for the given station and direction.
     * @param station the station to retrieve the schedule for
     * @param direction the direction to retrieve the schedule for
     * @return the schedule for the given station and direction
     * @throws DirectionNotSupportedException if there is no schedule for the given station and direction
     */
    List<LocalTime> getSchedule(@NotNull Station station, @NotNull Direction direction)
            throws DirectionNotSupportedException {

        List<LocalTime> schedule = schedules.get(new ScheduleKey(station, direction));
        if (schedule == null) {
            throw new DirectionNotSupportedException(station
                    + " Station does not have a " + direction + " train.");
        }
        return schedule;
    }

    /**
     * The key used to access a schedule.  It is composed of the station name and direction of travel.
     */
    private static class ScheduleKey {

        private final Station station;
        private final Direction direction;

        private ScheduleKey(@NotNull Station station, @NotNull Direction direction) {

            if (station == null) {
                throw new IllegalArgumentException("station cannot be null");
            }
            if (direction == null) {
                throw new IllegalArgumentException("direction cannot be null");
            }

            this.station = station;
            this.direction = direction;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ScheduleKey that = (ScheduleKey) o;
            return station == that.station && direction == that.direction;
        }

        @Override
        public int hashCode() {
            int result = station.hashCode();
            result = 31 * result + direction.hashCode();
            return result;
        }

        @Override
        public String toString() {
            return station + " " + direction;
        }
    }
}
