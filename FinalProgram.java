import java.util.Scanner;

class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (Age: " + age + ")";
    }
}

class PersonQueue {
    private Person[] queue;
    private int size;

    public PersonQueue(int capacity) {
        queue = new Person[capacity];
        size = 0;
    }

    public void enqueue(Person p) {
        if (size < queue.length) {
            queue[size++] = p;
        }
    }

    public Person[] getArray() {
        return queue;
    }

    public void displayQueue() {
        for (int i = 0; i < size; i++) {
            System.out.println(queue[i]);
        }
    }

    // QuickSort by last name (descending)
    public void sortByLastName() {
        quickSortLastName(queue, 0, size - 1);
    }

    private void quickSortLastName(Person[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionLastName(arr, low, high);
            quickSortLastName(arr, low, pi - 1);
            quickSortLastName(arr, pi + 1, high);
        }
    }

    private int partitionLastName(Person[] arr, int low, int high) {
        String pivot = arr[high].getLastName();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getLastName().compareToIgnoreCase(pivot) > 0) { // descending
                i++;
                Person temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Person temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // QuickSort by age (descending)
    public void sortByAge() {
        quickSortAge(queue, 0, size - 1);
    }

    private void quickSortAge(Person[] arr, int low, int high) {
        if (low < high) {
            int pi = partitionAge(arr, low, high);
            quickSortAge(arr, low, pi - 1);
            quickSortAge(arr, pi + 1, high);
        }
    }

    private int partitionAge(Person[] arr, int low, int high) {
        int pivot = arr[high].getAge();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].getAge() > pivot) { // descending
                i++;
                Person temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Person temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}

public class FinalProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        PersonQueue queue = new PersonQueue(5);

        System.out.println("Enter 5 people:");

        for (int i = 0; i < 5; i++) {
            System.out.print("First name: ");
            String first = input.nextLine();

            System.out.print("Last name: ");
            String last = input.nextLine();

            System.out.print("Age: ");
            int age = Integer.parseInt(input.nextLine());

            queue.enqueue(new Person(first, last, age));
            System.out.println();
        }

        System.out.println("\nOriginal Queue:");
        queue.displayQueue();

        System.out.println("\nSorted by Last Name (Descending):");
        queue.sortByLastName();
        queue.displayQueue();

        System.out.println("\nSorted by Age (Descending):");
        queue.sortByAge();
        queue.displayQueue();
    }
}