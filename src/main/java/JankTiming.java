/**
 * None of the available timer classes/methods work for me. This class implements a simple pause method.
 * @author adhi-thirumala
 */
public class JankTiming {
    /**
     * Pauses the code for some amount of time in milliseconds.
     * @param milliseconds the amount of time in (ms) to be paused.
     */
    public static void pause(int milliseconds){
        long timestamp = System.currentTimeMillis();
        //noinspection StatementWithEmptyBody
        while (System.currentTimeMillis() < timestamp + milliseconds);
/*
 *    Title: How to pause my Java program for 2 seconds
 *    Author: <a href = https://stackoverflow.com/users/13274713/acsjr> ACSJR </a>
 *    Date: April 10, 2020
 *    Availability: https://stackoverflow.com/questions/43507587/how-to-pause-my-java-program-for-2-seconds
 */
    }
}
