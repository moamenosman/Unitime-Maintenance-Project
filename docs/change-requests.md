## Change Requests Description
##Bugs

Bug 1: Fix NullPointerException in Multiple Classes
Type: Bug Fix

Description: SonarQube detected that multiple classes 
across the codebase use nullable objects without null 
checks, which can cause NullPointerException crashes 
at runtime.

Affected Locations:
- Debug.java (L47)
- CreateBaseModelFromXml.java (L167, L348, L354)
- JGroupsUtils.java (L117)
- ApplicationProperties.java (L106, L249)
- AssignedExamsAction.java (L134)
- ExamAssignmentReportAction.java (L172)
- ExamDetailAction.java (L195, L240)
- ClassInstructorAssignmentForm.java (L172)
- SolverSettingsForm.java (L130)
- TimeHint.java (L57)
- AriaSuggestArea.java (L140, L145)
- AriaSuggestBox.java (L145, L150)
- FindOnlineSectioningLogAction.java (L574, L580, L586)
- SectioningServlet.java (L1012, L2049, L2084)
- PreferenceLevel.java (L334, L336, L355, L376)

Motivation: These bugs cause unexpected system crashes 
at runtime. SonarQube reported 1,600 reliability issues.

Test Cases:

Test Case 1:
Location: SolverSettingsForm.java (L130)
Input: appearance = null
Expected: Handled gracefully with default value
Actual: NullPointerException — system crash

Test Case 2:
Location: ApplicationProperties.java (L106)
Input: appPropertiesUrl = null
Expected: Return empty properties
Actual: NullPointerException

Test Case 3:
Location: AriaSuggestArea.java (L140)
Input: first = null
Expected: Skip processing gracefully
Actual: NullPointerException

Test Case 4:
Location: ExamDetailAction.java (L195)
Input: exam object = null
Expected: Show user-friendly error message
Actual: NullPointerException — page crash

Test Case 5:
Location: PreferenceLevel.java (L334)
Input: color = null
Expected: Return default color value
Actual: NullPointerException


Bug 2: Fix XXE Vulnerability in XML Parsing
Type: Bug Fix

Description: SonarQube detected that XML parsing in 
multiple classes does not disable access to external 
entities, making the system vulnerable to XXE attacks.

Affected Locations:
- DatabaseUpdate.java (L75, L78)
- PageNameGenerator.java (L226)
- DataImportAction.java (L343, L355, L360)
- XmlApiHelper.java (L52)
- SessionRestore.java (L712)
- BaseImport.java (L66)
- DataExchangeIntegrationHelper.java (L42)
- StaffImport.java (L62)
- CurriculumClassification.java (L82)
- SolverInfo.java (L268)
- StudentSectioningQueue.java (L112)

Motivation: High severity security vulnerability that 
can allow attackers to read internal files. SonarQube 
reported 120 security issues.

Test Cases:

Test Case 1:
Location: DatabaseUpdate.java (L75)
Input: XML file with external entity reference
Expected: External entity access blocked
Actual: External entity processed — security breach

Test Case 2:
Location: BaseImport.java (L66)
Input: Malicious XML with XXE payload
Expected: Parser rejects external entities
Actual: Internal file contents exposed

Test Case 3:
Location: XmlApiHelper.java (L52)
Input: XML request with DOCTYPE declaration
Expected: DOCTYPE processing disabled
Actual: XXE attack succeeds

Test Case 4:
Location: SessionRestore.java (L712)
Input: XML backup file with external entity
Expected: Entity reference ignored
Actual: Server-side request forgery possible

Bug 3: Fix Division by Zero in Multiple Classes
Type: Bug Fix

Description: SonarQube detected multiple locations where 
division operations are performed without checking if 
the divisor can be zero, causing ArithmeticException.

Affected Locations:
- AccessStatsAction.java (L423, L470)
- XLSPrinter.java (L478, L544)
- CurriculumProjectionRulesPage.java (L905)
- SuggestionsPageContext.java (L236)
- CurriculumInterface.java (L814, L840)
- Class_.java (L1960)
- SchedulingSubpart.java (L991)
- XReservation.java (L509)
- StudentSchedulingSolutionStatisticsReport.java (L313, L703, L1244)
- CurModel.java (L126)
- JenrlInfo.java (L210)
- ProgressTracker.java (L221, L248, L279)
- StudentEmail.java (L2144, L2306)

Motivation: Division by zero causes crashes in critical 
scheduling components. SonarQube reported these as 
High severity reliability issues.

Test Cases:

Test Case 1:
Location: AccessStatsAction.java (L423)
Input: cnt = 0
Expected: Return 0 or show N/A
Actual: ArithmeticException — crash

Test Case 2:
Location: ProgressTracker.java (L221)
Input: cnt = 0
Expected: Return 0% progress
Actual: ArithmeticException

Test Case 3:
Location: CurriculumInterface.java (L814)
Input: total = 0
Expected: Return 0 or default value
Actual: ArithmeticException — page crash

Test Case 4:
Location: XLSPrinter.java (L478)
Input: colWidthMM = 0
Expected: Use default column width
Actual: ArithmeticException — export fails

Test Case 5:
Location: JenrlInfo.java (L210)
Input: total = 0
Expected: Show 0% utilization
Actual: ArithmeticException


Bug 4: Fix Unclosed Resources in Multiple Classes
Type: Bug Fix

Description: SonarQube detected that multiple stream 
and resource objects are opened but not properly closed 
using try-with-resources causing memory leaks.

Affected Locations:
- WikiGet.java (L76, L78, L100, L103)
- POHelper.java (L122, L127, L190, L244)
- ApplicationProperties.java (L102, L119)
- DataImportAction.java (L346, L381)
- SessionBackup.java (L783)
- SessionRestore.java (L985, L987)
- OnlineSectioningLogger.java (L268)
- AGHCourseDetailsProvider.java (L104)
- ExportReport.java (L129)
- OnlineSectioningReport.java (L68)
- SolverServerImplementation.java (L642, L646)
- TimetableInfoUtil.java (L69)
- BlobRoomAvailabilityService.java (L56, L76)
- PdfWorksheet.java (L697)

Motivation: Unclosed resources cause memory leaks 
degrading system performance. SonarQube identified 
20+ occurrences across the codebase.

Test Cases:

Test Case 1:
Location: WikiGet.java (L76)
Input: Open InputStream for file download
Expected: Stream closed after use
Actual: Stream remains open — memory leak

Test Case 2:
Location: SessionBackup.java (L783)
Input: Create FileOutputStream for backup
Expected: Stream closed after backup
Actual: Stream not closed — file handle leak

Test Case 3:
Location: OnlineSectioningLogger.java (L268)
Input: Open database Session for logging
Expected: Session closed in finally block
Actual: Session left open — connection pool exhaustion

Test Case 4:
Location: BlobRoomAvailabilityService.java (L56)
Input: Open CallableStatement for DB query
Expected: Statement closed after execution
Actual: Statement not closed — memory leak

Test Case 5:
Location: TimetableInfoUtil.java (L69)
Input: Open GZIPOutputStream for compression
Expected: Stream closed after compression
Actual: Stream not closed — data corruption risk


Bug 5: Fix Extremely High Cognitive Complexity
Type: Bug Fix

Description: SonarQube detected methods with extremely 
high cognitive complexity. The worst case is 
CreateBaseModelFromXml.java (L280) with complexity 
of 526 — far exceeding the allowed limit of 15.

Affected Locations:
- CreateBaseModelFromXml.java (L280) — complexity 526
- CreateBaseModelFromXml.java (L197) — complexity 16
- NaturalOrderComparator.java (L41) — complexity 16
- NaturalOrderComparator.java (L75) — complexity 27
- MultiComparable.java (L71) — complexity 16
- ToolBox.java (L234) — complexity 21
- CalendarVTimeZoneGenerator.java (L71)
- Debug.java (L97, L100, L103)
- LocalContext.java (L424, L461)

Motivation: Methods with extremely high cognitive 
complexity are impossible to test and maintain safely. 
SonarQube reported 51,000 maintainability issues.

Test Cases:

Test Case 1:
Location: CreateBaseModelFromXml.java (L280)
Input: Complex XML model with nested elements
Expected: Method processes model correctly
Actual: Method untestable — complexity 526

Test Case 2:
Location: NaturalOrderComparator.java (L75)
Input: Compare strings with mixed numbers
Expected: Correct natural order comparison
Actual: Complex nested logic — complexity 27

Test Case 3:
Location: ToolBox.java (L234)
Input: Utility method with edge case input
Expected: Clear predictable behavior
Actual: High complexity makes edge cases untestable

Test Case 4:
Location: MultiComparable.java (L71)
Input: Compare multiple objects
Expected: Consistent comparison results
Actual: Overly complex logic — complexity 16

Test Case 5:
Location: CreateBaseModelFromXml.java (L197)
Input: XML with special attributes
Expected: Attributes processed correctly
Actual: Nested complexity causes maintenance issues

Bug 6: Hard-coded Secret Token (Blocker)
Type: Bug Fix

Description: SonarQube detected hard-coded secret tokens 
directly embedded in the source code. This is classified 
as a Blocker severity issue meaning it must be fixed immediately before any release.

Affected Locations:
- ImportTranslations.java (L290)
- QueryEncoderBackend.java (L171)

Motivation: Hard-coded secrets in source code 
expose sensitive authentication tokens to anyone with repository access.
This is a critical security vulnerability classified as Blocker by SonarQube.

Test Cases:

Test Case 1:
Location: ImportTranslations.java (L290)
Input: Attacker reads source code
Expected: Token should be stored in environment variable
Actual: Token visible in plain text in source code

Test Case 2:
Location: QueryEncoderBackend.java (L171)
Input: Token used for encoding queries
Expected: Token loaded from secure configuration
Actual: Token hard-coded — exposed in version control

Test Case 3:
Input: Token compromised by attacker
Expected: System should allow token rotation without code change
Actual: Requires code change and redeployment to rotate token

##Features
1. Instructor Preferences

Type: New Feature

Description:
Add a feature that allows instructors to specify their preferred days and time slots for teaching. The system will consider these preferences during timetable generation.

Motivation:
This improves user satisfaction and increases scheduling efficiency by aligning generated timetables with instructor availability and preferences.


2. Export Timetable

Type: New Feature

Description:
Implement functionality to export the generated timetable into PDF or CSV format for external use.

Motivation:
This enhances usability by allowing users to share, print, or store timetables outside the system.


3. Room Capacity Validation

Type: New Feature

Description:
Add validation to ensure that the number of students assigned to a course does not exceed the capacity of the assigned room.

Motivation:
This prevents invalid scheduling scenarios and ensures that room assignments are realistic and consistent with physical constraints.

SonarQube reported missing validation checks across 
the codebase as part of its 51,000 maintainability issues.


4. Manual Schedule Adjustment
Type: New Feature

Description:
Allow administrators to manually adjust or override automatically generated timetables in case of special requirements or conflicts.

Motivation:
This feature provides flexibility to handle exceptional cases that cannot be resolved automatically by the system, improving usability and control.
