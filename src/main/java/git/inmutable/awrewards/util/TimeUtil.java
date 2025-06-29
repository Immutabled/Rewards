package git.inmutable.awrewards.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @organization Arcania Projects
 *
 * @author Inmutable
 * @since mayo 04, 04:34
 */
public final class TimeUtil {


    /**
     * Milliseconds equivalent to one hour (60 minutes).
     */
    public static final long HOUR = TimeUnit.HOURS.toMillis(60L);

    /**
     * Milliseconds equivalent to one minute (60 seconds).
     */
    public static final long MINUTE = TimeUnit.SECONDS.toMillis(60L);

    /**
     * Milliseconds equivalent to one day (24 hours).
     */
    public static final long DAY = TimeUnit.DAYS.toMillis(1L);

    /**
     * Milliseconds equivalent to one month (30 days approximation).
     */
    public static final long MONTH = TimeUnit.DAYS.toMillis(30L);

    /**
     * Default date format pattern: MM/dd/yyyy HH:mm.
     */
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");

    private TimeUtil() {
        throw new AssertionError("Cannot instantiate utility class");
    }

    /**
     * Formats milliseconds into a user-friendly time string.
     *
     * <p>For times less than a minute, shows seconds with decimal precision.
     * For times over a minute, uses MM:SS format.</p>
     *
     * @param millis the time duration in milliseconds
     * @return formatted time string (e.g., "0.5s" or "01:23")
     */
    public static String formatIntoFancy(long millis) {
        if (millis >= MINUTE) {
            return formatIntoMMSS(millis);
        }

        double seconds = millis / 1000.0;
        return String.format("%.1fs", seconds > 0.1 ? Math.round(10.0 * seconds) / 10.0 : 0.1);
    }

    /**
     * Formats milliseconds into HH:MM:SS format.
     *
     * @param millis the time duration in milliseconds
     * @return time string in HH:MM:SS format (e.g., "01:05:30")
     */
    public static String formatIntoHHMMSS(long millis) {
        long toReturn = millis / 1000;

        long seconds = toReturn % 60;
        toReturn -= seconds;

        long minutesCount = toReturn / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;

        long hours = minutesCount / 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Formats milliseconds into MM:SS or HH:MM:SS format (shows hours only when needed).
     *
     * @param millis the time duration in milliseconds
     * @return formatted time string (e.g., "05:30" or "01:05:30")
     */
    public static String formatIntoMMSS(long millis) {
        long toReturn = millis / 1000;

        long seconds = toReturn % 60;
        toReturn -= seconds;

        long minutesCount = toReturn / 60;
        long minutes = minutesCount % 60;
        minutesCount -= minutes;

        long hours = minutesCount / 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Formats milliseconds into a detailed human-readable string.
     *
     * <p>Example outputs: "2 days 3 hours", "45 minutes 30 seconds", "1 day"</p>
     *
     * @param millis the time duration in milliseconds
     * @return detailed time description
     */
    public static String formatIntoDetailedString(long millis) {
        if (millis <= 0L) {
            return "0 seconds";
        }

        long secs = millis / 1000L;
        long remainder = secs % 86400;

        long days = secs / 86400;
        long hours = remainder / 3600;
        long minutes = remainder / 60 - hours * 60;
        long seconds = remainder % 3600 - minutes * 60;

        StringBuilder builder = new StringBuilder();

        if (days > 0) {
            builder.append(" ").append(days).append(" day").append(days > 1 ? "s" : "");
        }

        if (days < 1) {
            if (hours > 0) {
                builder.append(" ").append(hours).append(" hour").append(hours > 1 ? "s" : "");
            }

            if (hours < 1) {
                if (minutes > 0) {
                    builder.append(" ").append(minutes).append(" minute").append(minutes > 1 ? "s" : "");
                }
                if (seconds > 0) {
                    builder.append(" ").append(seconds).append(" second").append(seconds > 1 ? "s" : "");
                }
            }
        }

        return builder.toString().trim();
    }

    /**
     * Formats milliseconds into a compact string representation.
     *
     * <p>Example outputs: "2d3h", "45m", "1d", "30s"</p>
     *
     * @param millis the time duration in milliseconds
     * @param suppress whether to suppress seconds/minutes when hours/days are present
     * @return compact time string
     */
    public static String formatIntoShortString(long millis, boolean suppress) {
        if (millis <= 1000L) {
            return "0s";
        }

        long secs = millis / 1000L;

        if (secs < 60) {
            return secs + "s";
        }

        long remainder = secs % 86400;
        long days = secs / 86400;
        long hours = remainder / 3600;
        long minutes = remainder / 60 - hours * 60;
        long seconds = remainder % 3600 - minutes * 60;

        StringBuilder builder = new StringBuilder();

        if (days > 0) {
            builder.append(" ").append(days).append("d");
        }

        if (days < 1) {
            if (hours > 0) {
                builder.append(" ").append(hours).append("h");
            }

            if (!suppress) {
                if (minutes > 0) {
                    builder.append(minutes).append("m");
                } else if (seconds > 0) {
                    builder.append(seconds).append("s");
                }
            }

            if (hours < 1) {
                if (minutes > 0) {
                    builder.append(" ").append(minutes).append("m");
                }

                if (!suppress && seconds > 0) {
                    builder.append(" ").append(seconds).append("s");
                }
            }
        }

        return builder.toString().trim();
    }

    /**
     * Overloaded version of {@link #formatIntoShortString(long, boolean)} with suppress=true.
     */
    public static String formatIntoShortString(long millis) {
        return formatIntoShortString(millis, true);
    }

    /**
     * Formats a Date object into a string using the default date format.
     *
     * @param date the date to format
     * @return formatted date string
     */
    public static String formatIntoCalendarString(Date date) {
        return DATE_FORMAT.format(date);
    }

    /**
     * Parses a time string into milliseconds.
     *
     * <p>Supports formats like "1h30m", "2d5h10m", "45s", etc.
     * Returns 0 for empty strings or strings starting with "perm" (case insensitive).</p>
     *
     * @param time the time string to parse
     * @return duration in milliseconds
     * @throws NumberFormatException if the time string contains invalid numbers
     */
    public static long parseTime(String time) {
        if (time == null || time.isEmpty() || time.toLowerCase().startsWith("perm")) {
            return 0L;
        }

        String[] lifeMatch = {"y", "M", "w", "d", "h", "m", "s"};
        int[] lifeInterval = {31536000, 2592000, 604800, 86400, 3600, 60, 1};

        int seconds = 0;

        for (int i = 0; i < lifeMatch.length; i++) {
            Pattern pattern = Pattern.compile("([0-9]*)" + lifeMatch[i]);
            java.util.regex.Matcher matcher = pattern.matcher(time);

            while (matcher.find()) {
                String group = matcher.group(1);
                seconds += Integer.parseInt(group.isEmpty() ? "0" : group) * lifeInterval[i];
            }
        }

        return seconds * 1000L;
    }

    /**
     * Calculates the absolute time difference between two dates.
     *
     * @param a the first date
     * @param b the second date
     * @return absolute difference in milliseconds
     */
    public static long getTimeBetween(Date a, Date b) {
        return Math.abs(a.getTime() - b.getTime());
    }
}