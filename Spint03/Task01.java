import java.util.Arrays;
import java.util.Comparator;

class MyUtils {
    private static class MyComparator implements Comparator<int[]> {
        /**
         *  To sort a two-dimensional integer array by descending the elements of the first column.
         *  In the case of equality of elements in the first column,
         *  he elements of the second column must be sorted in ascending order.
         */
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1 == null) return o2 == null ? 0 : -1; // null first
            if (o2 == null) return 1;

            if (o1.length == 0) return o2.length == 0 ? 0 : 1;
            if (o2.length == 0) return -1; // abscent first goes last

            if (o1[0] == o2[0]) {
                if (o1.length == 1) return o2.length == 1 ? 0 : -1;
                if (o2.length == 1) return 1; // abscent second goes first

                if (o1[1] == o2[1]) return 0;
                return o1[1] < o2[1] ? -1 : 1; // ascending by second element
            }
            return o1[0] < o2[0] ? 1 : -1; // descending by first element
        }
    }
    public int[][] arrSort(int[][] arr) {
        Arrays.sort(arr, new MyComparator());
        return arr;
    }

    public static void main(String[] args) {
        // smoke test
        int[][] arr = { { 1, 4 }, { 1, 2, 3 }, { 3, 2 }, { 3, 3, 5 }, { 3 }, { 1, 1, 3 }, {3}, {} };
        System.out.println(Arrays.deepToString(arr));
        int[][] sortedArr = new MyUtils().arrSort(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
