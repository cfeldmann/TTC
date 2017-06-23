# ttc

This repository contains a simple model of the TTC subway system and an API to get the next scheduled train, given the time, station and desired direction of travel.  It was implemented as a solution to the following exercise:


> Exercise:

> Given the following subway map, break it down into individual components and model these components using java classes:
>
> https://www.ttc.ca/Subway/interactive_map/interactive_map.jsp#
>
> When building your data model, try to build it with the following in mind:
>
> 1. Implement an API that uses your data model.
>
> 2. The API should be able to tell a user when the next train will arrive at a specific station, given a time and direction (north, south, east, west).
>
> 3. Assume that a schedule will be provided indicating when a train will arrive at each station.
>
> 4. As per the API, the data should account for direction (north, south, east, west) and handle stations that have intersecting lines.



My first thought on this exercise was the obvious approach:

A class for each station with methods to describe the station (isAccessible(), hasWashroom(), etc.), where each of those classes extended an abstract Station class that contained default implementations for those methods.  The child classes would override them as needed.  The subway lines would be represented in a similar way.

However, after looking at what the API needed to do, I remembered an article I read a while back that talking about YAGNI (You Ain't Gonna Need It) and re-thought my approach.  Since the only needed function of the API was to tell the user when the next train is, my original thoughts on the data model were all wrong.  I'd be churning out over 60 near-identical classes for no good reason.

I redesigned the data model to be driven more by configuration, so that a station's direction capabilities were defined by whether or not it had a schedule for that direction.  Adding or removing a station from my model is as easy as adding another element to the Station enum and then adding a schedule for it.

The Main class in the test package contains examples of how to use the API.
