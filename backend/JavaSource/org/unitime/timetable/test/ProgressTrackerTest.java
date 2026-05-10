package org.unitime.timetable.test;

import org.junit.Test;
import org.unitime.timetable.util.ProgressTracker;

import static org.junit.Assert.*;

public class ProgressTrackerTest {

    @Test
    public void testProgressTrackerInitialization() {
        ProgressTracker tracker = new ProgressTracker("Test Task", 10, ProgressTrackerTest.class);
        assertNotNull(tracker);
    }

    @Test
    public void testProgressWithZeroTotal_NoCrash() {
        ProgressTracker tracker = new ProgressTracker("Zero Total", 0, ProgressTrackerTest.class);

        try {
            tracker.getProgressStringIfNeeded();
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testLogProgress_NoCrash() {
        ProgressTracker tracker = new ProgressTracker("Log Test", 5, ProgressTrackerTest.class);

        try {
            tracker.logProgressIfNeeded();
        } catch (Exception e) {
            fail("Should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testTotalTimeMethods() {
        ProgressTracker tracker = new ProgressTracker("Time Test", 5, ProgressTrackerTest.class);

        assertNotNull(tracker.totalTimeToProcess());
        assertNotNull(tracker.totalTimeToProcessInMilliseconds());
    }
}