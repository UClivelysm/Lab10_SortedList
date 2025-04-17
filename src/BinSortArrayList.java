import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper around ArrayList<String> that maintains elements in
 * ascending lexicographic order by inserting via manual binary search.
 * Logs each comparison and pointer adjustment in a readable format.
 * Supports deleting the most recently added element.
 */
public class BinSortArrayList {
    private final ArrayList<String> list;
    private final StringBuilder log;
    private int lastAddedIndex;

    public BinSortArrayList() {
        this.list = new ArrayList<>();
        this.log = new StringBuilder();
        this.lastAddedIndex = -1;
        log.append("[Init] Created empty sorted list.\n");
    }

    /**
     * Inserts the element into the list in sorted order.
     * Logs each step of the binary search.
     */
    public void add(String element) {
        log.append(String.format("\n== Insert '%s' ==%n", element));

        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            String midVal = list.get(mid);

            // Log comparison
            log.append(String.format("  [Compare] '%s' vs '%s' at index %d -> ",
                    element, midVal, mid));

            int cmp = element.compareTo(midVal);
            if (cmp > 0) {
                log.append(String.format("greater; move low to %d%n", mid + 1));
                low = mid + 1;
            } else {
                log.append(String.format("not greater; move high to %d%n", mid - 1));
                high = mid - 1;
            }
        }

        // Final insertion point
        log.append(String.format("-> Found position: %d; inserting '%s'.%n", low, element));
        list.add(low, element);
        lastAddedIndex = low;
    }

    /**
     * Deletes the element that was most recently added via add().
     * Logs the deletion step. If no element to delete, logs a notice.
     */
    public void deleteLastAdded() {
        if (lastAddedIndex >= 0 && lastAddedIndex < list.size()) {
            String removed = list.remove(lastAddedIndex);
            log.append(String.format("\n[Delete] Removed '%s' from index %d.%n", removed, lastAddedIndex));
            lastAddedIndex = -1;
        } else {
            log.append("\n[Delete] No element to remove (no recent addition or already deleted).\n");
        }
    }

    /**
     * Retrieves and clears the log buffer.
     */
    public String getLog() {
        String output = log.toString();
        log.setLength(0);
        return output;
    }

    /* Delegated methods */
    public String get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public List<String> toList() {
        return new ArrayList<>(list);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
