import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BinSortArrayListTest {
    private BinSortArrayList bs;

    @BeforeEach
    void setUp() {
        bs = new BinSortArrayList();
    }

    @Test
    void testAddMaintainsOrder() {
        bs.add("delta");
        bs.add("alpha");
        bs.add("charlie");
        bs.add("bravo");

        List<String> expected = List.of("alpha", "bravo", "charlie", "delta");
        assertEquals(expected, bs.toList(), "List should be sorted lexicographically after adds");
    }

    @Test
    void testDeleteLastAddedRemovesCorrectElement() {
        bs.add("first");
        bs.add("second");
        bs.add("third");

        // 'third' was the last added, so deleting should remove it
        bs.deleteLastAdded();
        List<String> expected = List.of("first", "second");
        assertEquals(expected, bs.toList(), "deleteLastAdded() should remove the most recently added element");
    }

    @Test
    void testBinSearchFound() {
        bs.add("apple");
        bs.add("banana");
        bs.add("cherry");

        String result = bs.binSearch("banana");
        assertEquals("banana Found At Position: 1", result,
                "binSearch should return the found term and its position");
    }

    @Test
    void testBinSearchNotFound() {
        bs.add("apple");
        bs.add("cherry");

        String result = bs.binSearch("banana");
        assertEquals("Search Term Not Found: banana; Would Be At Index Position: 1", result,
                "binSearch should indicate not found and the insertion index");
    }

    @Test
    void testGetLogClearsAfterRetrieval() {
        bs.add("x");
        String firstLog = bs.getLog();
        assertFalse(firstLog.isEmpty(), "Log should contain entries after operations");

        String secondLog = bs.getLog();
        assertTrue(secondLog.isEmpty(), "Log should be cleared after getLog()");
    }
}
