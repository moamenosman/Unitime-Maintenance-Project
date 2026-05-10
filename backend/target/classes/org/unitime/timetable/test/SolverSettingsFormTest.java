package org.unitime.timetable.test;


import org.junit.Test;
import org.unitime.timetable.form.SolverSettingsForm;

import static org.junit.Assert.assertThrows;

public class SolverSettingsFormTest {

    private SolverSettingsForm solverSettingsForm;
    @Test
    public void testSetAppearanceType_NullInpt_ThrowsNullPointerException(){
        assertThrows(NullPointerException.class , ()->
               solverSettingsForm.setAppearanceType(null) );
    }
}
