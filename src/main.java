public class main {
    public static void main(String[] args) {
        BinSortArrayList binSortArrayList = new BinSortArrayList();

        String string1 = "aa";
        String string2 = "bb";
        String string3 = "cc";
        String string4 = "ab";
        String string5 = "ba";

        binSortArrayList.add(string3);
        binSortArrayList.add(string2);
        binSortArrayList.add(string4);
        binSortArrayList.add(string5);
        binSortArrayList.add(string1);


        System.out.println(binSortArrayList);
        System.out.println(binSortArrayList.getLog());

        System.out.println(binSortArrayList.binSearch("ac"));

    }

}
