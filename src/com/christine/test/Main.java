package com.christine.test;

import com.christine.ttc.*;

import java.time.LocalTime;

/**
 * Exercise:
 *
 * Given the following subway map, break it down into individual components and model these
 * components using java classes:
 *
 * https://www.ttc.ca/Subway/interactive_map/interactive_map.jsp#
 *
 * When building your data model, try to build it with the following in mind:
 *
 * 1. Implement an API that uses your data model.
 *
 * 2. The API should be able to tell a user when the next train will arrive at a specific station,
 *    given a time and direction (north, south, east, west).
 *
 * 3. Assume that a schedule will be provided indicating when a train will arrive at each station.
 *
 * 4. As per the API, the data should account for direction (north, south, east, west) and handle
 *    stations that have intersecting lines.
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
