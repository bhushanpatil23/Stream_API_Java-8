
import java.util.*;

import java.util.function.Function;
import java.util.stream.Collectors;


public class Stream_API_Level_1_Qs
{
	public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(11,1,2,3,4,5,6,7,8,9, 10, 15, 45, 2,3,1, 45);
        
        List<String> names = Arrays.asList("Abhishek","Shahrukh","Ranveer","Salman","Ajay","Sunny","abcd","efghi", "","  ", " ", "done");
        
        //  1. find odd numbers from list which are divisible by 3 and 5 both
        List<Integer> oddNums = numbers.stream()
                                    .filter(n->n%2 != 0 && n%3==0 && n%5==0)
                                    .toList();
        System.out.print(oddNums);      
        
        
        // 2. List of strings into their lengths
        List<Integer> strLengths = names.stream()
                                     .map(s->s.length())
                                     .toList();
        System.out.print(strLengths);
            
        // 3. remove all null and empty strings
        List<String> reqStrings = names.stream()
                                    .filter(s-> s!= null && !s.trim().isEmpty())
                                    .toList();
        System.out.print(reqStrings);
        
        
        // 4. find all numbers divisible by both 3 and 5
        // added in Q.1 
        
        // 5. Find total sum of squares of all numbers
        int sumOfSquares = numbers.stream()
                                    .map(n->n*n)
                                    .reduce(0, Integer::sum); // if not pass default 0 then add .orElse(0);
        System.out.println(sumOfSquares);
        
        int sumOfSquares1 = numbers.stream()
                                .mapToInt(n->n*n)
                                .sum();
        System.out.println(sumOfSquares1);
        
        
        // 6. Get first non-repeated element from List
        Integer firstNonRepeated = numbers.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        java.util.LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(java.util.Map.Entry::getKey)
                .findFirst()
                .orElse(null);
                
        System.out.println(firstNonRepeated);
        
        // Below solution is O(n-square)
        Integer firstNonRepeated1 = numbers.stream()
                                    .filter(n -> Collections.frequency(numbers, n) == 1)
                                    .findFirst()
                                    .orElse(-1);

        
        System.out.println(firstNonRepeated1);
        
        // Using hashmap and 2 passes // may prefer for interview
        Map<Integer, Long> freqMap = numbers.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()));

        Integer firstNonRepeated3 = numbers.stream()
                .filter(n -> freqMap.get(n) == 1)
                .findFirst()
                .orElse(-1);

        System.out.println(firstNonRepeated3);
        
        
        // Using toMap solution
        Integer firstNonRepeated4 = numbers.stream()
                                    .collect(Collectors.toMap(
                                            Function.identity(),
                                            n -> 1,
                                            Integer::sum,
                                            LinkedHashMap::new))
                                    .entrySet()
                                    .stream()
                                    .filter(e -> e.getValue() == 1)
                                    .map(Map.Entry::getKey)
                                    .findFirst()
                                    .orElse(-1);
        
        
        System.out.println(firstNonRepeated4);
        
        
        // 7. Count how many strings have length greater than 4
        long countNames = names.stream()
                            .filter(s->s.length()>4)
                            .count();
                            
        System.out.println(countNames);
        
        
        // 8. Find minimum number from List
        // below is worst time complexity solution please use alternative solution below using Integer::compareTo
        int minimumNumber = numbers.stream()
                                .distinct()
                                .sorted()
                                .findFirst()
                                .orElse(-1);
        System.out.println(minimumNumber);          
        
        // other alternative ways
        
        Integer min = numbers.stream()
                                .min(Integer::compareTo)
                                .orElseThrow();
                                
        Integer min1 = numbers.stream()
                                .min(Comparator.naturalOrder())
                                .orElse(-1);            
                                
        Integer min2 = numbers.stream()
                                .reduce(Integer::min)
                                .orElse(-1);    
                                
        Integer min3 = numbers.stream()
                                .reduce((a, b) -> a < b ? a : b)
                                .orElse(-1);                        
        
        int min4 = numbers.stream()
                            .mapToInt(Integer::intValue)
                            .min()
                            .orElse(-1);
                            
        System.out.println( min+ " " + min1+ " " + min2 + " " + min3 + " " + min4);   
                
        //9. Get top 5 largest numbers
        List<Integer> top5Largest = numbers.stream()
                                        .distinct()
                                        .sorted((a,b)-> b-a) // Comparator.reverseOrder() => this can also be pass in sorted()
                                        .limit(5)
                                        .toList();
        
        System.out.println(top5Largest);
        
        // 10. convert list of string into comma separated string
        String commaSepString = names.stream()
                                    .collect(Collectors.joining(",")); // if want comma space separated then pass => ", "
                                    
        System.out.println(commaSepString);
        
        // other ways without using stream 
        String result = String.join(",", names);
        System.out.println(result);
        // for comma-separated String → List:
        List<String> list = Arrays.asList(str.split(","));
        
        
	}
}
