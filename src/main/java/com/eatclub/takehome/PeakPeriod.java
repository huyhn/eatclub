package com.eatclub.takehome;

public class PeakPeriod {
    private final String peakTimeStart;
    private final String peakTimeEnd;

    public PeakPeriod(String start, String end) {
        this.peakTimeStart = start;
        this.peakTimeEnd = end;
    }

    public String getPeakTimeStart() {
        return peakTimeStart;
    }

    public String getPeakTimeEnd() {
        return peakTimeEnd;
    }
}
