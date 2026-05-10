package org.unitime.timetable.test;

import org.junit.Test;
import org.unitime.timetable.model.PreferenceLevel;

import static org.junit.Assert.assertThrows;


public class PreferenceLevelTest {

    @Test
    public void testProlog2color_NullInpt_ThrowsNullPointerException(){
        assertThrows(NullPointerException.class ,()->
                PreferenceLevel.prolog2color(null));
    }
    @Test
    public void testProlog2awtColor_NullInpt_ThrowsNullPointerException(){
        assertThrows(NullPointerException.class ,()->
                PreferenceLevel.prolog2awtColor(null));
    }
    @Test
    public void testProlog2bgColor_NullInpt_throwsNullPointerException(){
        assertThrows(NullPointerException.class ,()->
                PreferenceLevel.prolog2bgColor(null));
    }

}
