package com.christine.test;

import com.christine.ttc.*;

import java.time.LocalTime;

/**
 * This main class is used for testing the API and showing examples of how it's used.
 */
public class Main {

    private static TTC ttc = new TTC();

    private static void runTest(LocalTime time, Station station, Direction direction) {
        try {
            LocalTime nextTrain = ttc.nextTrain(time, station, direction);
            System.out.println("The next train after " + time + " is " + nextTrain);
        } catch (DirectionNotSupportedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        // In reality, Union's trains are defined as "University North" and
        // "Yonge North".  However, both of those trains have to travel east or west
        // before they turn and go north.  For that reason, and because it was
        // the only station with two trains heading in the same direction, I've
        // chosen to define Union's trains as going east (to Yonge) and west
        // (to University).
        ttc.addSchedule(Station.UNION, Direction.EAST, Schedule.SCHEDULE);
        ttc.addSchedule(Station.UNION, Direction.WEST, Schedule.SCHEDULE);

        // While the subway map shows St. George as having two west trains
        // (one on Bloor line, one on University line), the TTC's own schedule
        // for St. George lists the University trains as going north and south.
        // This works well for my model.
        ttc.addSchedule(Station.STGEORGE, Direction.NORTH, Schedule.SCHEDULE);
        ttc.addSchedule(Station.STGEORGE, Direction.SOUTH, Schedule.SCHEDULE);
        ttc.addSchedule(Station.STGEORGE, Direction.EAST, Schedule.SCHEDULE);
        ttc.addSchedule(Station.STGEORGE, Direction.WEST, Schedule.SCHEDULE);

        // Bathurst is just a regular station with two directions.
        ttc.addSchedule(Station.BATHURST, Direction.EAST, Schedule.SCHEDULE);
        ttc.addSchedule(Station.BATHURST, Direction.WEST, Schedule.SCHEDULE);

        runTest(LocalTime.of(6, 0), Station.UNION, Direction.EAST);

        runTest(LocalTime.of(9, 47), Station.STGEORGE, Direction.NORTH);
        runTest(LocalTime.of(17, 49), Station.STGEORGE, Direction.WEST);

        runTest(LocalTime.of(0, 21), Station.BATHURST, Direction.EAST);
        runTest(LocalTime.of(23, 55), Station.BATHURST, Direction.EAST);

        // Add a slight delay so the stack trace from the next test doesn't intermingle
        // with the output from the above tests.
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Bathurst doesn't have a north train, this test should result in an exception.
        runTest(LocalTime.of(6, 0), Station.BATHURST, Direction.NORTH);

    }
}
