package com.eatclub.takehome;

public final class TimeUtil {
    private TimeUtil() {}
    public static int convertTime(String timeOfDay) {
        int appendHour = 0;
        if (timeOfDay.contains("am")) {
            if (timeOfDay.startsWith("12:")) {
                // fix for 12:00am
                timeOfDay = timeOfDay.replace("12:", "00:");
            }
            timeOfDay = timeOfDay.replace("am", "");
        } else if(timeOfDay.contains("pm")) {
            if (!timeOfDay.startsWith("12:")) {
                appendHour += 12;
            }
            timeOfDay = timeOfDay.replace("pm", "");
        } else {
            if (timeOfDay.startsWith("12:")) {
                // fix for 12:00
                timeOfDay = timeOfDay.replace("12:", "00:");
            }
        }
        String[] times = timeOfDay.split(":");
        int hour = Integer.parseInt(times[0]);
        int minutes = Integer.parseInt(times[1]);

        return (hour + appendHour) * 60 + minutes;
    }

    public static String convertTime(int t) {
        int hour = t / 60;
        int minute = t % 60;
        if (hour >= 13) {
            return String.format("%02d:%02d", hour - 12, minute) + "pm";
        } else if (hour <= 11) {
            return String.format("%02d:%02d", hour, minute) + "am";
        }
        return String.format("%02d:%02d", hour, minute) + "pm";
    }
}
