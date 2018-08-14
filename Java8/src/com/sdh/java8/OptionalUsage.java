package com.sdh.java8;

import java.util.Optional;

public class OptionalUsage {
    public static void main(String[] args) {
        /*
         * Optional<Insurance> insuranceOptional = Optional.empty();
         * insuranceOptional.get();
         */

        /////////////////////////////////////////////////////////////////////////

        Optional<Insurance> insuranceOptional = Optional.of(new Insurance());
        insuranceOptional.get();
        
        /////////////////////////////////////////////////////////////////////////
        
        Optional<Insurance> objectOptional = Optional.ofNullable(null);
        //objectOptional.orElseGet(Insurance::new);
        //objectOptional.orElse(new Insurance());
        //objectOptional.orElseThrow(RuntimeException::new);
        //objectOptional.orElseThrow(() -> new RuntimeException("Not have reference"));
        
        /////////////////////////////////////////////////////////////////////////
        
        Insurance insurance = insuranceOptional.filter(i -> i.getName() == null).get();
        System.out.println(insurance);
        
        /////////////////////////////////////////////////////////////////////////
        
        Optional<String> nameOptional = insuranceOptional.map(i -> i.getName());
        System.out.println(nameOptional.orElse("empty value"));
        
        /////////////////////////////////////////////////////////////////////////
        
        System.out.println(nameOptional.isPresent());
        
        /////////////////////////////////////////////////////////////////////////
        
        nameOptional.ifPresent(System.out::println);
        
        /////////////////////////////////////////////////////////////////////////
        
        System.out.println(getInsuranceNameByOptional(null));
    }
    
    private static String getInsuranceNameByOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unkonow");
    }
}
