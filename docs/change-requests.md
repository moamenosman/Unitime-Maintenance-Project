## Change Requests Description

1. Conflict Detection Issue

Type: Bug Fix

Description:
The current university timetabling system may fail to detect scheduling conflicts in certain edge cases, such as when an instructor or a room is assigned to multiple sessions at overlapping time slots.

Motivation:
This issue can lead to incorrect timetable generation, including double-booking of instructors or rooms, which negatively affects system correctness and real-world usability.

2. Instructor Preferences

Type: New Feature

Description:
Add a feature that allows instructors to specify their preferred days and time slots for teaching. The system will consider these preferences during timetable generation.

Motivation:
This improves user satisfaction and increases scheduling efficiency by aligning generated timetables with instructor availability and preferences.

3. Export Timetable

Type: New Feature

Description:
Implement functionality to export the generated timetable into PDF or CSV format for external use.

Motivation:
This enhances usability by allowing users to share, print, or store timetables outside the system.

4. Room Capacity Validation

Type: New Feature

Description:
Add validation to ensure that the number of students assigned to a course does not exceed the capacity of the assigned room.

Motivation:
This prevents invalid scheduling scenarios and ensures that room assignments are realistic and consistent with physical constraints.

5. Manual Schedule Adjustment

Type: New Feature

Description:
Allow administrators to manually adjust or override automatically generated timetables in case of special requirements or conflicts.

Motivation:
This feature provides flexibility to handle exceptional cases that cannot be resolved automatically by the system, improving usability and control.
