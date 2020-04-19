package com.nec.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamMain {

	public static void createCollectionToStream() {
		// First way
		Stream<Integer> stream = Stream.of(new Integer[] { 1, 2, 4, 5, 6 });
		stream.forEach(p -> System.out.println(p));

		// Second way
		Stream<Integer> stream1 = Stream.of(1, 2, 3, 56, 57, 22);
		stream1.forEach(System.out::println);
		Stream<String> stringStream = Stream.of("sakshi", "Kavita", "Pooja", "Himani");
		stringStream.sorted().forEach(System.out::println);
	}

	/** Print Even numbers */
	public static void performListOperation() {
		List<Integer> list = Arrays.asList(1, 3, 4, 5, 8, 8, 6);
		Consumer<Integer> action = System.out::println;
		// Print distinct element from collection in sorted order
		list.stream().distinct().sorted().forEach(action);

		// Print even numbers from list
		System.out.println("Print even numbers");
		list.stream().filter(p -> p % 2 == 0).forEach(action);

		/*
		 * map method is used to create a separate new object, for every object present
		 * in the collection
		 */
		// add 2 in all element list
		System.out.println("Two is added to every list values");
		list.stream().map(p -> p + 2).forEach(action);

	}

	private static void performMapOperation() {
		HashMap<String, Integer> map = new HashMap<>();
		map.put("A", 1);
		map.put("B", 5);
		map.put("C", 3);
		Consumer<Map.Entry<String, Integer>> action = System.out::println;
		// Display entry set
		System.out.println("Print entry set of map");
		map.entrySet().forEach(action);
		Consumer<String> actionKeys = System.out::println;
		// Display keys of map
		System.out.println("Print key set of map");
		map.keySet().forEach(actionKeys);
		Consumer<Integer> actionValues = System.out::println;
		// Display values of map
		System.out.println("Print value set of map in sorted order");
		map.values().stream().sorted().forEach(actionValues);
	}

	private static void createStreamToCollection() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < 5; i++) {
			list.add(i);
		}
		list.add(2);
		// Convert Stream to list
		List<Integer> evenNumbersList = list.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
		System.out.println("Print Even number of list : ");
		evenNumbersList.forEach(System.out::println);

		// Convert Stream to Array
		Integer[] evenNumbersArr = list.stream().filter(i -> i % 2 == 0).toArray(Integer[]::new);

		// Convert Stream to set
		Set<Integer> set = list.stream().collect(Collectors.toSet());
		System.out.println("Print set element : ");
		set.forEach(System.out::println);

	}

	private static void performIntermediateOperation(List<String> memberNames) {

		// Print list of names which starts with A
		System.out.println("Print list of names which starts with A : ");
		memberNames.stream().filter((s) -> s.startsWith("A")).forEach(System.out::println);

		// Print list of names which starts with A in upper case
		System.out.println("Print list of names which starts with A in upper case");
		memberNames.stream().filter((s) -> s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);

		// Print list of names in sorted order
		System.out.println("Print list of names in sorted order");
		memberNames.stream().sorted().map(String::toUpperCase).forEach(System.out::println);

		// peek method
		List<String> newPeekList = memberNames.stream().peek(System.out::println).collect(Collectors.toList());
		System.out.println("Peek operation : ");
		newPeekList.forEach(System.out::println);

	}

	private static void performTerminalOperation(List<String> memberNames) {

		// print all name in Uppercase (Use of Collect operation)
		List<String> newUpperCaseList = memberNames.stream().map(ele -> ele.toUpperCase()).collect(Collectors.toList());
		System.out.println("print uppercase Letters");
		newUpperCaseList.forEach(System.out::println);

		/*
		 * Use of match terminal operation, list.contains() and stream().anyMatch() is
		 * also does the same thing
		 */
		boolean present = memberNames.stream().anyMatch(ele -> ele.startsWith("A"));
		System.out.println("Any element is found in list which start with A : " + present);

		boolean present1 = memberNames.stream().allMatch(ele -> ele.startsWith("S"));
		System.out.println("All element in list is start with S : " + present1);

		boolean present2 = memberNames.stream().noneMatch(ele -> ele.startsWith("G"));
		System.out.println("none of the element in list is start with G : " + present2);

		// use of count operation
		long totalMatched = memberNames.stream().filter((s) -> s.startsWith("A")).count();
		System.out.println(totalMatched);

		/*
		 * Stream min() method to select the smallest element in the stream according to
		 * the comparator provided in its argument. It will return Optional object
		 */
		List<Integer> list = Arrays.asList(2, 4, 1, 3, 7, 5, 9, 6, 8);
		Optional<Integer> minNumber = list.stream().min((i, j) -> i.compareTo(j));
		System.out.println(minNumber.get());

		/*
		 * Stream max() method to select the biggest element in the stream according to
		 * the comparator provided in its argument. It will return Optional object
		 */
		Comparator<Integer> maxComparator = new Comparator<Integer>() {

			@Override
			public int compare(Integer n1, Integer n2) {
				return n1.compareTo(n2);
			}
		};
		Optional<Integer> maxNumber = list.stream().max(maxComparator);
		System.out.println(maxNumber.get());

		/*
		 * The Stream interface has a default method called count() that returns a long.
		 * Examples of Stream.count() method.
		 */
		long count = LongStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).filter(i -> i % 2 == 0).count();
		System.out.printf("There are %d elements in the stream %n", count);

		/*
		 * Collectors class has one method counting(). It is as a downstream collector.
		 * It accept input elements and counts them. If no elements are present, the
		 * count is 0.
		 */
		count = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).filter(i -> i % 2 == 0).collect(Collectors.counting());
		System.out.printf("There are %d elements in the stream %n", count);
		/* It will return any element is stream */
		Stream.of("one", "two", "three", "four").findAny().ifPresent(System.out::println);
		/* It will return first element is stream */
		Stream.of("one", "two", "three", "four").findFirst().ifPresent(System.out::println);

	}

	private static void sortObjects() {

		List<String> strList = Arrays.asList("sakshi", "kavita", "mohini", "deepika");
		// Sort the list in ascending order
		List<String> ascSortedList = strList.stream().sorted().collect(Collectors.toList());
		System.out.println("List is sorted in Ascending order : " + ascSortedList);

		// Sort the list in descending order
		List<String> descSortedList = strList.stream().sorted((i1, i2) -> i2.compareTo(i1))
				.collect(Collectors.toList());
		System.out.println("List is sorted in descending otder");
		descSortedList.forEach(System.out::println);

		// Sort any object by their id and name
		List<Person> personList = new ArrayList<>();
		Person p1 = new Person(1, "Sakshi", 37000l);
		Person p2 = new Person(5, "Kavita", 18000l);
		Person p3 = new Person(3, "Deepika", 35000l);
		Person p4 = new Person(2, "Thapa", 40000l);
		personList.add(p1);
		personList.add(p2);
		personList.add(p3);
		personList.add(p4);
		List<Person> sortedPersonList = personList.stream()
				.sorted(Comparator.comparing(Person::getId).thenComparing(Person::getName))
				.collect(Collectors.toList());
		System.out.println("Sorted Person List by id and name");
		sortedPersonList.forEach(System.out::println);

	}

	private static void performShortCircutOperation() {

		Stream<Integer> evenNumInfiniteStream = Stream.iterate(0, n -> n + 2);
		// Limit operation is used to get the limited elements from list
		List<Integer> newList = evenNumInfiniteStream.limit(10).collect(Collectors.toList());
		newList.forEach(System.out::println);

		// Skip function skips the first n elements in the encounter order.
		System.out.println("Skip first five : ");
		List<Integer> newList1 = Stream.iterate(0, n -> n + 2).skip(5).limit(10).collect(Collectors.toList());
		newList1.forEach(System.out::println);
	}

	private static void produceInfiniteStream() {
		/*
		 * Produce infinite stream using iterate method. this is infinite sequential
		 * ordered stream
		 */
		List<Integer> intList = IntStream.iterate(0, i -> i + 1).mapToObj(Integer::valueOf).limit(10)
				.collect(Collectors.toList());
		intList.forEach(System.out::println);

		/*
		 * Produce infinite stream using generate() method. this is infinite sequential
		 * unordered stream
		 */
		List<Integer> randomNumbers = Stream.generate(() -> (new Random()).nextInt(100)).limit(10).collect(Collectors.toList());
		randomNumbers.forEach(System.out::println);
	}

	public static void main(String[] args) {
		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");

		StreamMain.createCollectionToStream();
		StreamMain.performListOperation();
		StreamMain.performMapOperation();
		StreamMain.createStreamToCollection();
		StreamMain.sortObjects();
		StreamMain.performIntermediateOperation(memberNames);
		StreamMain.performShortCircutOperation();
		StreamMain.performTerminalOperation(memberNames);
		StreamMain.produceInfiniteStream();
	}

}
