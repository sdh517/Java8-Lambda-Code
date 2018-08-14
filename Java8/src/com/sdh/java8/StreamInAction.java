package com.sdh.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamInAction {
    public static void main(String[] args) {
        
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
                
        );
        
        transactions.stream().filter(transaction -> transaction.getYear() == 2011)
                             .sorted(Comparator.comparing(Transaction::getValue))
                             .collect(Collectors.toList())
                             .stream()
                             .forEach(System.out::println);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        transactions.stream().map(transaction -> transaction.getTrader().getCity())
                             .distinct()
                             .forEach(System.out::println);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        transactions.stream().map(Transaction::getTrader)
                             .filter(trader -> trader.getCity().equals("Cambridge"))
                             .distinct()
                             .sorted(Comparator.comparing(Trader::getName))
                             .forEach(System.out::println);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        String value = transactions.stream().map(transaction -> transaction.getTrader().getName())
                                            .distinct()
                                            .sorted()
                                            .reduce("", (name1, name2) -> name1 + name2);
        System.out.println(value);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        boolean liveInMilan = transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(liveInMilan);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                             .map(Transaction::getValue)
                             .forEach(System.out::println);
        
        ////////////////////////////////////////////////////////////////////////////////////
        
        transactions.stream().map(Transaction::getValue).reduce(Integer::max).ifPresent(System.out::println);;
    }
}
