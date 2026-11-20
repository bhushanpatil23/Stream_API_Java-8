
import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Stream_API_Level_2
{
	public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(11,1,2,3,4,5,6,7,8,9, 10, 15, 45, 2,3,1, 45, 45,100);
        
        List<String> names = Arrays.asList("Abhishek","Shahrukh","Ranveer","Salman","Ajay","Sunny","abcd","efghi", "","  ", " ", "done");
        
        
        // 11. Find second smallest number 
        /*
        int secondSmallest = numbers.stream()
                                .distinct()
                                .sorted()
                                .skip(1)
                                .findFirst()
                                .orElse(-1);
                                
        System.out.println(secondSmallest);
        */
        
        // 12. Reverse each string in List
        /*
        List<String> revNames = names.stream()
                                    .map(s-> new StringBuilder(s).reverse().toString())
                                    .toList();
                                    
        System.out.println(revNames);
        
        // advanced version -- to reverse the order of strings and not the actual strings
        List<String> reversedOrder = names.stream()
                                        .collect(Collectors.collectingAndThen(
                                                Collectors.toList(),
                                                list -> {
                                                    Collections.reverse(list);
                                                    return list;
                                                }
                                        ));
        
        System.out.println(reversedOrder);      
        */
        
        // 13. Find duplicate elements in list 
        /*
        Set<Integer> set1 = new HashSet<>();
        List<Integer> duplicateElements =  numbers.stream()
                                                .filter(n -> !set1.add(n))
                                                .distinct()
                                                .toList();
        System.out.println(duplicateElements);
        
        // alternative way 
        List<Integer> duplicates = numbers.stream()
                                    .collect(Collectors.groupingBy(
                                            Function.identity(),
                                            Collectors.counting()))
                                    .entrySet()
                                    .stream()
                                    .filter(e -> e.getValue() > 1)
                                    .map(Map.Entry::getKey)
                                    .toList();
                            
        System.out.println(duplicates);
        
        // print duplicates with count
                                    numbers.stream()
                                   .collect(Collectors.groupingBy(
                                           Function.identity(),
                                           Collectors.counting()))
                                   .forEach((k, v) -> {
                                       if (v > 1)
                                           System.out.println(k + " -> " + v);
                                   });
        */
        
        // 14. find frequency of each number
        /*
        Map<Integer,Long> freqMap = numbers.stream()
                                            .collect(Collectors.groupingBy(
                                                Function.identity(),  // LinkedHashMap::new, add below line if insertion order needs tonbe preserved
                                                Collectors.counting()));
                                            
        System.out.println(freqMap);
        */
        
        // 15. separate even and odd numbers into two lists
        /*
        Map<Boolean,List<Integer>> evenoddsList = numbers.stream()
                                                    .collect(Collectors.partitioningBy(n->n%2==0));
                                                    
        System.out.println(evenoddsList);
        
        // Alternative using groupingBy
        Map<String, List<Integer>> resultEvenOdd = numbers.stream()
                                                .collect(Collectors.groupingBy(
                                                    n -> n % 2 == 0 ? "Even" : "Odd"));
        System.out.println(resultEvenOdd);  
        */
        
        // 16. check if all number are positive
        /*
        Boolean areAllPositive = numbers.stream()
                                    .allMatch(n->n>0);
        
        System.out.println(areAllPositive);     
        */
        
        // 17. Merge two lists and remove duplicates
        /*
        List<Integer> list2 = Arrays.asList(1,1,5,12,45, 15, 20, 24 ,25 , 100);
        List<Integer> resultMerge = Stream.concat(numbers.stream(), list2.stream())
                                .distinct()
                                .collect(Collectors.toList());
        System.out.println(resultMerge);       
        
        // Alternative ways
        List<Integer> mergedList = Stream.of(list1, list2)
                                    .flatMap(List::stream)
                                    .distinct()
                                    .toList();
        */
        
        // 18. Get last elements using Streams
        /*
        int lastElement = numbers.stream()
                            .skip(numbers.size()-1)
                            .findFirst()
                            .orElse(0);
                            
        System.out.println(lastElement);
        
        // alternative way
        Integer last11 = numbers.stream()
                            .reduce((a, b) -> b)    
                            .orElse(-1);
        */
        
        // 19. Find average of 10 numbers greater than 10
        /*
        double avgOfGreaterThan10 = numbers.stream()
                                        .filter(n -> n > 10)
                                        .mapToInt(Integer::intValue)
                                        .average()
                                        .orElse(0.0);
                                        
        System.out.println(avgOfGreaterThan10);
        
        // alternative way
        double averagegreaterthan10 = numbers.stream()
                            .filter(n -> n > 10)
                            .collect(Collectors.averagingInt(Integer::intValue));
        
        */

        // 20. sort list based on last digit of numbers
        List<Integer> sortedBasedOnlastDigit = numbers.stream()
                                                    .sorted(Comparator.comparingInt(n -> n % 10))
                                                    .toList();
        
        System.out.println(sortedBasedOnlastDigit);     
        
        // alternative way 
        List<Integer> sortedlastdigitbased = numbers.stream()
                                .sorted((a, b) -> Integer.compare(a % 10, b % 10))
                                .toList();
        
        // if last digits are same then by atual value 
        List<Integer> sorted = numbers.stream()
                                    .sorted(
                                        Comparator.comparingInt((Integer n) -> n % 10)
                                                  .thenComparingInt(n -> n)
                                    )
                                    .toList();
                                    
                                    
	}
}
