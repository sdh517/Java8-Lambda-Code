package com.sdh.java8;

import java.util.Optional;

public class NullPointerExcetion {
    
    public static void main(String[] args) {
        //Optional.ofNullable(getInsuranceNameByOptional(null)).ifPresent(System.out::println);
    }
    
    /*
    private static String getInsuranceNameByDeepDoubts(Persion persion) {
        if (null != persion) {
            Car car = persion.getCar();
            if (null != car) {
                Insurance insurance = car.getInsurance();
                if (null != insurance) {
                    return insurance.getName();
                }
            }
        }
        return "UNKNOWN";
    }
    */
    
    /*
    private static String getInsuranceNameByOptional(Persion persion) {
        return Optional.ofNullable(persion).filter()
                       .flatMap(Persion::getCar)
                       .flatMap(Car::getInsurance)
                       .map(Insurance::getName).orElse("Unknown");
    }
    */
}
