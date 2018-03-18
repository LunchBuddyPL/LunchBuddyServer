package pl.lunchbuddy.server.group.domain

import java.time.LocalTime


class MealTime {

    private val start: LocalTime
    private val end: LocalTime

    constructor(start: LocalTime, end: LocalTime) {
        if (end.isBefore(start)) throw IllegalStateException("End time : $end must be after start time : $start")
        this.start = start
        this.end = end
    }

    override fun toString(): String {
        return "MealTime(start=$start, end=$end)"
    }


}