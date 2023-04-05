import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests of combination.
     */
    @Test
    public void testCombination_substring() {
        String str1 = "combination";
        String str1Expected = "combination";
        String str2 = "nation";
        String str2Expected = "nation";
        int overlap = str2.length();
        int overlapExpected = str2.length();
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(overlapExpected, overlap);
        assertEquals("combination", result);
    }

    @Test
    public void testCombination_oneLetterSubstring() {
        String str1 = "combination";
        String str1Expected = "combination";
        String str2 = "n";
        String str2Expected = "n";
        int overlap = str2.length();
        int overlapExpected = str2.length();
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(overlapExpected, overlap);
        assertEquals("combination", result);
    }

    @Test
    public void testCombination_identicalStrings() {
        String str1 = "combination";
        String str1Expected = "combination";
        String str2 = "combination";
        String str2Expected = "combination";
        int overlap = str2.length();
        int overlapExpected = str2.length();
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(overlapExpected, overlap);
        assertEquals("combination", result);
    }

    @Test
    public void testCombination_partialSubstring() {
        String str1 = "combination";
        String str1Expected = "combination";
        String str2 = "national";
        String str2Expected = "national";
        int overlap = 6;
        int overlapExpected = 6;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(overlapExpected, overlap);
        assertEquals("combinational", result);
    }

    @Test
    public void testCombination_emptyString() {
        String str1 = "combination";
        String str1Expected = "combination";
        String str2 = "";
        String str2Expected = "";
        int overlap = 0;
        int overlapExpected = 0;
        String result = StringReassembly.combination(str1, str2, overlap);
        assertEquals(str1Expected, str1);
        assertEquals(str2Expected, str2);
        assertEquals(overlapExpected, overlap);
        assertEquals("combination", result);
    }

    /*
     * Tests of addToSetAvoidingSubstrings.
     */
    @Test
    public void testAddToSetAvoidingSubstrings_substring() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = strSet.newInstance();
        String str = "nation";
        String strExpected = "nation";
        strSet.add("combination");
        strSetExpected.add("combination");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
        assertEquals(strExpected, str);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_partialSubstring() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = strSet.newInstance();
        String str = "nation";
        String strExpected = "nation";
        strSet.add("combinatio");
        strSetExpected.add("combinatio");
        strSetExpected.add("nation");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
        assertEquals(strExpected, str);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_hasSubstrings() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = strSet.newInstance();
        String str = "nation";
        String strExpected = "nation";
        strSet.add("combinatio");
        strSet.add("ion");
        strSet.add(" ");
        strSet.add("");
        strSetExpected.add("combinatio");
        strSetExpected.add("nation");
        strSetExpected.add(" ");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
        assertEquals(strExpected, str);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_substringHasSubstrings() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = strSet.newInstance();
        String str = "nation";
        String strExpected = "nation";
        strSet.add("combinatio");
        strSet.add("nation");
        strSet.add("ion");
        strSet.add(" ");
        strSet.add("");
        strSetExpected.add("combinatio");
        strSetExpected.add("nation");
        strSetExpected.add("ion");
        strSetExpected.add(" ");
        strSetExpected.add("");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
        assertEquals(strExpected, str);
    }

    @Test
    public void testAddToSetAvoidingSubstrings_emptyString() {
        Set<String> strSet = new Set1L<String>();
        Set<String> strSetExpected = strSet.newInstance();
        String str = "";
        String strExpected = "";
        strSet.add("combinatio");
        strSet.add("nation");
        strSet.add("ion");
        strSet.add(" ");
        strSetExpected.add("combinatio");
        strSetExpected.add("nation");
        strSetExpected.add("ion");
        strSetExpected.add(" ");
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSetExpected, strSet);
        assertEquals(strExpected, str);
    }

    /*
     * Tests of linesFromInput.
     */
    @Test
    public void testLinesFromInput_simpleStrings() {
        SimpleReader input = new SimpleReader1L("cheer-8-2.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("Bucks -- Beat");
        result.add("Go Bucks");
        result.add("o Bucks -- B");
        result.add("Beat Mich");
        result.add("Michigan~");

        assertEquals(resultExpected, result);
    }

    @Test
    public void testLinesFromInput_emptyString() {
        SimpleWriter output = new SimpleWriter1L("testfile.txt");
        output.println("combination nation");
        output.println("abomination in the face of");
        output.println();
        output.println("traces");
        output.close();

        SimpleReader input = new SimpleReader1L("testfile.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("combination nation");
        result.add("abomination in the face of");
        result.add("traces");

        assertEquals(resultExpected, result);
    }

    @Test
    public void testLinesFromInput_noSubstrings() {
        SimpleWriter output = new SimpleWriter1L("noSubstringsTest.txt");
        output.println("combination nation");
        output.println("abomination in the face of");
        output.println("traces");
        output.close();

        SimpleReader input = new SimpleReader1L("noSubstringsTest.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("combination nation");
        result.add("abomination in the face of");
        result.add("traces");

        assertEquals(resultExpected, result);
    }

    @Test
    public void testLinesFromInput_substrings() {
        SimpleWriter output = new SimpleWriter1L("substringsTest.txt");
        output.println("combination nation");
        output.println("abomination in the face of");
        output.println("traces");
        output.println("combination");
        output.println("nat");
        output.println("face");
        output.close();

        SimpleReader input = new SimpleReader1L("substringsTest.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("combination nation");
        result.add("abomination in the face of");
        result.add("traces");

        assertEquals(resultExpected, result);
    }

    @Test
    public void testLinesFromInput_identicalStrings() {
        SimpleWriter output = new SimpleWriter1L("identicalStringsTest.txt");
        output.println("combination nation");
        output.println("combination nation");
        output.println("combination nation");
        output.close();

        SimpleReader input = new SimpleReader1L("identicalStringsTest.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("combination nation");

        assertEquals(resultExpected, result);
    }

    @Test
    public void testLinesFromInput_identicalEmptyStrings() {
        SimpleWriter output = new SimpleWriter1L(
                "identicalEmptyStringsTest.txt");
        output.println("");
        output.println("");
        output.println("");
        output.close();

        SimpleReader input = new SimpleReader1L(
                "identicalEmptyStringsTest.txt");
        Set<String> resultExpected = StringReassembly.linesFromInput(input);
        input.close();

        Set<String> result = new Set1L<String>();
        result.add("");

        assertEquals(resultExpected, result);
    }

    /*
     * Tests of printWithLineSeparators.
     */
    @Test
    public void testPrintWithLineSeperators_noLineSeperator() {
        String text = "combonation";
        SimpleWriter output = new SimpleWriter1L("noLineSeperatorTest.txt");
        StringReassembly.printWithLineSeparators(text, output);
        output.close();

        SimpleReader input = new SimpleReader1L("noLineSeperatorTest.txt");

        assertEquals("combonation", input.nextLine());
        assertTrue(input.atEOS());
        input.close();
    }

    @Test
    public void testPrintWithLineSeperators_oneLineSeperator() {
        String text = "combo~nation";
        SimpleWriter output = new SimpleWriter1L("oneLineSeperatorTest.txt");
        StringReassembly.printWithLineSeparators(text, output);
        output.close();

        SimpleReader input = new SimpleReader1L("oneLineSeperatorTest.txt");

        assertEquals("combo", input.nextLine());
        assertEquals("nation", input.nextLine());
        assertTrue(input.atEOS());
        input.close();
    }

    @Test
    public void testPrintWithLineSeperators_twoLineSeperator() {
        String text = "combo~nation~amalgamation";
        SimpleWriter output = new SimpleWriter1L("twoLineSeperatorTest.txt");
        StringReassembly.printWithLineSeparators(text, output);
        output.close();

        SimpleReader input = new SimpleReader1L("twoLineSeperatorTest.txt");

        assertEquals("combo", input.nextLine());
        assertEquals("nation", input.nextLine());
        assertEquals("amalgamation", input.nextLine());
        assertTrue(input.atEOS());
        input.close();
    }

    @Test
    public void testPrintWithLineSeperators_lineSeperatorNoText() {
        String text = "~";
        SimpleWriter output = new SimpleWriter1L("lineSeperatorNoTextTest.txt");
        StringReassembly.printWithLineSeparators(text, output);
        output.close();

        SimpleReader input = new SimpleReader1L("lineSeperatorNoTextTest.txt");

        assertEquals("", input.nextLine());
        assertTrue(input.atEOS());
        input.close();
    }

    @Test
    public void testPrintWithLineSeperators_leadingTrailingLineSepeartor() {
        String text = "~combo~nation~";
        SimpleWriter output = new SimpleWriter1L(
                "leadingTrailingLineSeperatorTest.txt");
        StringReassembly.printWithLineSeparators(text, output);
        output.close();

        SimpleReader input = new SimpleReader1L(
                "leadingTrailingLineSeperatorTest.txt");

        assertEquals("", input.nextLine());
        assertEquals("combo", input.nextLine());
        assertEquals("nation", input.nextLine());
        assertTrue(input.atEOS());
        input.close();
    }
}
