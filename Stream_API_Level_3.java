
import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.IntStream;


public class Main
{
	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(11,1,2,3,4,5,6,7,8,9, 10, 15, 45, 2,3,1, 45, 45,100);

		List<String> names = Arrays.asList("racecar","madam","Level","Abhishek","Shahrukh","Ranveer","Salman","Ajay","Sunny","abcd","efghi", "","  ", " ", "done");

		// 21. Given list of words - group by word length
		Map<Integer, List<String>> groupByLengthStrings = names.stream()
		        .collect(Collectors.groupingBy(
		                     String::length,
		                     Collectors.mapping(
		                         Function.identity(),
		                         Collectors.toList())));

		System.out.println(groupByLengthStrings);

		// alternative ways
		Map<Integer, List<String>> groupByLengthStrings1 = names.stream()
		        .collect(Collectors.groupingBy(String::length));

		// Length → Comma-separated words
		Map<Integer, String> groupByLengthStrings11 = names.stream()
		        .collect(Collectors.groupingBy(
		                     String::length,
		                     Collectors.joining(", ")));

		System.out.println(groupByLengthStrings11);

		//How many words are there of each length?
		Map<Integer, Long> countByLength = names.stream()
		                                   .collect(Collectors.groupingBy(
		                                           String::length,
		                                           Collectors.counting()));

		System.out.println(countByLength);

		// 22. find first repeated character in String
		String myname = "programming";

		Set<Character> set = new HashSet<>();
		Character firstRepeated = myname.chars()
		                          .mapToObj(c -> (char) c)
		                          .filter(ch -> !set.add(ch))
		                          .findFirst()
		                          .orElse(null);

		System.out.println(firstRepeated);

		// alternative ways
		String str = "programming";

		Character firstRepeated1 = str.chars()
		                           .mapToObj(c -> (char) c)
		                           .collect(Collectors.groupingBy(
		                                        Function.identity(),
		                                        LinkedHashMap::new,
		                                        Collectors.counting()))
		                           .entrySet()
		                           .stream()
		                           .filter(entry -> entry.getValue() > 1)
		                           .map(Map.Entry::getKey)
		                           .findFirst()
		                           .orElse(null);

		System.out.println(firstRepeated1);


		// 23. given list employees find highest salary
		List<Employee> employees = Arrays.asList(
                                                new Employee("John", 50000),
                                                new Employee("Alice", 75000),
                                                new Employee("Bob", 65000)
                                        );
		Employee highestPaidEmployee = employees.stream()
		                               .max(Comparator.comparing(Employee::getSalary))
		                               .orElse(null);

		System.out.println(highestPaidEmployee);

		// get only the highest salary
		double highestSalary = employees.stream()
		                       .mapToDouble(Employee::getSalary)
		                       .max()
		                       .orElse(0.0);

		System.out.println(highestSalary);

		// find second highest salary employee
		Employee secondHighest = employees.stream()
		                         .sorted(Comparator.comparing(Employee::getSalary).reversed())
		                         .skip(1)
		                         .findFirst()
		                         .orElse(null);


		// 24. given Transactions calculate total amount per user
        List<Transaction> transactions = Arrays.asList(
                                            new Transaction("John", 100),
                                            new Transaction("Alice", 200),
                                            new Transaction("John", 150),
                                            new Transaction("Bob", 300),
                                            new Transaction("Alice", 100)
                                    );

        Map<String, Double> totalAmountPerUser = transactions.stream()
                                                        .collect(Collectors.groupingBy(
                                                                Transaction::getUser,
                                                                Collectors.summingDouble(Transaction::getAmount)
                                                        ));
                                                
        System.out.println(totalAmountPerUser);
        
        // User with Highest Total Transaction Amount
        Map.Entry<String, Double> highestSpender = transactions.stream()
                                                        .collect(Collectors.groupingBy(
                                                                Transaction::getUser,
                                                                Collectors.summingDouble(Transaction::getAmount)))
                                                        .entrySet()
                                                        .stream()
                                                        .max(Map.Entry.comparingByValue())
                                                        .orElse(null);
        
        System.out.println(highestSpender);
        
        // 25. flatten deeply nested list structure
        List<List<Integer>> nested = Arrays.asList(
                                        Arrays.asList(1, 2),    
                                        Arrays.asList(3, 4),
                                        Arrays.asList(5, 6)
                                );
        
        List<Integer> flattened = nested.stream()
                                    .flatMap(List::stream)
                                    .toList();
                            
        System.out.println(flattened);
        
        // 26. Find top 3 frequent numbers
        List<Integer> top3Frequent = numbers.stream()
                                        .collect(Collectors.groupingBy(
                                                Function.identity(),
                                                Collectors.counting()))
                                        .entrySet()
                                        .stream()
                                        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                                        .limit(3)
                                        .map(Map.Entry::getKey)
                                        .toList();
        
        System.out.println(top3Frequent);
        
        // get number along with frequncy
        Map<Integer, Long> top3Frequent1 = numbers.stream()
                                            .collect(Collectors.groupingBy(
                                                    Function.identity(),
                                                    Collectors.counting()))
                                            .entrySet()
                                            .stream()
                                            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
                                            .limit(3)
                                            .collect(Collectors.toMap(
                                                    Map.Entry::getKey,
                                                    Map.Entry::getValue,
                                                    (a, b) -> a,
                                                    LinkedHashMap::new));
        
        System.out.println(top3Frequent1);
        
        // 27. find K-th largest element 
        int k = 3;
        int kthlargest = numbers.stream()
                            .distinct()
                            .sorted(Comparator.reverseOrder())
                            .skip(k - 1)
                            .findFirst()
                            .orElse(null);

        // kth smallest 
        Integer kthSmallest = numbers.stream()
                                .distinct()
                                .sorted()
                                .skip(k - 1)
                                .findFirst()
                                .orElse(null);
                                
        
        // 28. detect palindrome strings in list 
        List<String> palindromes = names.stream()
                                    .filter(word ->
                                            word.equals(
                                                    new StringBuilder(word).reverse().toString()))
                                    .toList();

        System.out.println(palindromes);
        
        // Case-Insensitive Palindrome Check
        List<String> palindromes1 = names.stream()
                                    .filter(word -> {
                                        String lower = word.toLowerCase();
                                        return lower.equals(
                                                new StringBuilder(lower).reverse().toString());
                                    })
                                    .toList();
        
        System.out.println(palindromes1);             
        
        // 29. from list of integers return product of all numbers except self
        List<Integer> result = IntStream.range(0, numbers.size())
                                .mapToObj(i ->
                                        IntStream.range(0, numbers.size())
                                                .filter(j -> j != i)
                                                .map(numbers::get)
                                                .reduce(1, (a, b) -> a * b))
                                .toList();
        
        System.out.println(result);
        
        //30. 
        
	}
}

class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

class Transaction {
	private String user;
	private double amount;

	// constructor, getters
	public Transaction(String user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public String getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "user='" + user + '\'' +
                ", amount=" + amount +
                '}';
    }
}
