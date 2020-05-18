package com.sample.main;

import com.sample.animals.types.impl.Donkey;
import com.sample.animals.types.impl.Whale;
import com.sample.application.ApplicationContextFactory;

/**
 * Initial call for application startup
 * 
 * @version Java 1.8
 * @author RAJA
 *
 */
public class MyInject {
	
    public static void main(String[] args){
        ApplicationContextFactory.init();
        
        // Print the object hash value
        Whale whale = (Whale) ApplicationContextFactory.getBean(Whale.class);
        System.out.println(whale);
        
        Whale whale1 = (Whale) ApplicationContextFactory.getBean(Whale.class);
        System.out.println(whale1);
        
        Donkey donkey = (Donkey) ApplicationContextFactory.getBean(Donkey.class);
        System.out.println(donkey);
        
        Donkey donkey1 = (Donkey) ApplicationContextFactory.getBean(Donkey.class);
        System.out.println(donkey1);
    }
}
