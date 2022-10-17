import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.AssertJUnit.assertEquals;

public class Tests {
    List<String> memberNames = new ArrayList<>();


    // 1. Show in console
    @Test
    public void testPrintToConsole() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        stream.collect(Collectors.toList())
                .forEach(System.out::println);
    }

    // 2. Create a list of random numbers using Stream.generate() , should have 20 items and print in console
    @Test
    public void testCreateListOfRandomNumbers() {
        Stream.generate(() -> new Random().nextInt(100))
                .limit(20)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    // 3. Collect Stream elements to a List:  Convert list elements to stream, select only even ones, use the collect method to collect them into a List:

    @Test
    public void testCreateListWithEvenNumbers() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> listOfEvenNumbers = stream.collect(Collectors.toList())
                .stream()
                .filter((e) -> e % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(listOfEvenNumbers);
        assertEquals(listOfEvenNumbers, Arrays.asList(2, 4, 6, 8));
    }

    // 4. Repeat the same as in task 3 but collect all the elements in Array []

    @Test
    public void testCreateArrayWithEvenNumbers() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int[] arrayOfEvenNumbers = stream.filter((e) -> e % 2 == 0)
                .mapToInt(x -> x)
                .toArray();
        System.out.println(Arrays.toString(arrayOfEvenNumbers));
        assertEquals(Arrays.toString(arrayOfEvenNumbers), "[2, 4, 6, 8]");
    }

    // 5. filter values, keep those that start with "c", convert all values, convert to uppercase, sort in Descending order
    @Test
    public void testFilterConvertAndSortValues() {
        List<String> myList = Arrays.asList("a1", "a2", "b1", "c2", "c1");
        myList.stream()
                .filter((c) -> c.startsWith("c"))
                .map((e) -> e.toUpperCase())
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    // 6. a) Display names that start with ‘A’ and have length > 5;
    @Test
    public void testDisplayNamesThatStartWithA() {
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        memberNames.stream()
                .filter((e) -> e.startsWith("A"))
                .filter((e) -> e.length() > 5)
                .forEach(System.out::println);
    }

    // 6. b) Sort all names and display them in lower case on the screen
    @Test
    public void testDisplaySortedNames() {
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        memberNames.stream().
                sorted()
                .map((name) -> name.toLowerCase())
                .forEach(System.out::println);
    }

    // 7. From task 6, take an array and check with allMatch or anyMatch whether the list contains names with the letter ‘S’ (allMatch should display false, anyMatch should display true)
    //    From task 6, take an array and check with noneMatch whether the list contains names with the letter ‘H’ (should display true)

    @Test
    public void testMatchResults() {
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        boolean allContainsS = memberNames.stream().allMatch((name) -> name.contains("S"));
        boolean anyContainsS = memberNames.stream().anyMatch((name) -> name.contains("S"));
        boolean noneContainsH = memberNames.stream().noneMatch((name) -> name.contains("H"));

        assertEquals(allContainsS, false);
        assertEquals(anyContainsS, true);
        assertEquals(noneContainsH, true);
    }

    // 9. From task 6 count the number of names starting with “A” - display their number.

    @Test
    public void countNamesStartingWithA() {
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        long numberOfNames = memberNames.stream()
                .filter((name) -> name.startsWith("A"))
                .count();
        System.out.println(numberOfNames);
    }


    // 10. Stream.findFirst() - take the array from task 6 and print the first name starting with ‘L’

    @Test
    public void testFindFirstNameStartingWithF() {
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Abibaba");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");
        String firstElement = memberNames.stream()
                .filter((name) -> name.startsWith("L"))
                .findFirst()
                .orElse(null);
        System.out.println(firstElement);
    }

    // 11a. Java Stream flatMap() - concatenate 3 arrays into one using flatMap ()

    @Test
    public void testCombineThreeListsIntoOne() {
    List<Integer> list1 = Arrays.asList(1, 2, 3);
    List<Integer> list2 = Arrays.asList(4, 5, 6);
    List<Integer> list3 = Arrays.asList(7, 8, 9);
    List<Integer> totalList = Stream.of(list1, list2, list3)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());
        System.out.println(totalList);
    assertEquals(totalList.size(), 9);
    }

    // 11b. And these are also combined into 1

    @Test
    public void testCombineThreeArraysIntoOne(){
        String[][] dataArray = new String[][]{{"a", "b"}, {"c", "d"}, {"e", "f"}, {"g", "h"}};
        String[] totalArray = Arrays.stream(dataArray).flatMap(Arrays::stream)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(totalArray));
        assertEquals(totalArray.length, dataArray.length * 2);
    }

    // 12. Stream.distinct() to remove duplicates - remove duplicates from this array and display.
    @Test
    public void testRemoveDuplicates(){
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7));
        numbersList.stream()
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new))
                .forEach(System.out::println);
    }

    // 13. (OPTIONAL, NOT MANDATORY) Collectors.toMap() - move the list into Map where the key is an element of the array and the value is the number of times the element occurs in the array

    @Test
    public void testMoveListToMap(){
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7));
        Function<Integer, Long> countElements = (element) -> numbersList.stream().filter((number) -> number==element).count();
        Map<Object, Object> mapFromList = numbersList.stream()
                .collect(Collectors.toMap((element) -> element, (element) -> countElements.apply(element), (elementOne, elementTwo) -> elementOne));
        System.out.println(mapFromList);
        assertEquals(mapFromList.toString(), "{1=2, 2=1, 3=3, 4=1, 5=1, 6=3, 7=1}");
    }

    // 14. Write to a new list only letters that occur in the values of this map

    @Test
    public void testCreateListOfLetters() {
        Map<String, List<String>> people = new HashMap<>();
        people.put("John", Arrays.asList("555-1123","s", "555-3389", "a"));
        people.put("Mary", Arrays.asList("555-2243","z", "555-5264"));
        people.put("Steve", Arrays.asList("555-6654", "555-3242", "d"));
        List<String> letters = people.values().stream()
                .flatMap(Collection::stream)
                .filter((a) -> a.matches("[a-zA-Z]"))
                .collect(Collectors.toList());
        System.out.println(letters);
        assertEquals(letters.toString(), "[d, s, a, z]");
    }

}
