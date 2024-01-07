public class Main {
    public static void main(String[] args) {

        // Boş constructor ile MyList oluşturma
        MyList<String> myList1 = new MyList<>();
        System.out.println("myList1 Capacity: " + myList1.getCapacity());

        // Capacity parametresi ile MyList oluşturma
        MyList<Integer> myList2 = new MyList<>(5);
        System.out.println("myList2 Capacity: " + myList2.getCapacity());

        // Eleman eklemek
        myList1.add("Element 1");
        myList1.add("Element 2");
        myList1.add("Element 3");

        myList2.add(10);
        myList2.add(20);
        myList2.add(30);

        // Eleman sayısını ve kapasiteyi yazdırma
        System.out.println("myList1 Size: " + myList1.size());
        System.out.println("myList2 Size: " + myList2.size());

        // Elemanları yazdırma
        System.out.println("myList1 Elements: ");
        for (int i = 0; i < myList1.size(); i++) {
            System.out.println(myList1.get(i));
        }

        System.out.println("myList2 Elements: ");
        for (int i = 0; i < myList2.size(); i++) {
            System.out.println(myList2.get(i));
        }

    }
}