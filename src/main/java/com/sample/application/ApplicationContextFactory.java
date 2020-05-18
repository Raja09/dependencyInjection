package com.sample.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

/**
 * This class help to create the singleton and prototype objects 
 * 
 * @version Java 1.8
 * @author RAJA
 *
 */
public class ApplicationContextFactory {

    private static List<String> singletonBeans = new ArrayList<String>();
    private static List<String> prototypeBeans = new ArrayList<String>();
    private static  HashMap<String, Object> beansContainer = new HashMap<String, Object>();

    /**
     * It is used to read the application properties
     * 
     */
    public static void init() {
        Properties properties = new Properties();
        try {
            properties.load(ApplicationContextFactory.class.getClassLoader().getResourceAsStream("application.properties"));
            for(String className:properties.getProperty("myinject.classes.to.load.singleton").split(",")){
                singletonBeans.add(className);
            }
            for(String className:properties.getProperty("myinject.classes.to.load.prototype").split(",")){
                prototypeBeans.add(className);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Return bean ApplicationContectFactory bean object
     * 
     * @param Class clazz
     * @return Object
     */
    public static Object getBean(Class clazz) {
        return ApplicationContextFactory.createBean(clazz.getName());
    }

    /**
     * It help to return singleton or prototype objects based on class name
     * 
     * @param String className
     * @return Object
     */
    public static Object createBean(String className) {
        try {
            if(singletonBeans.contains(className) ){
                if(beansContainer.containsKey(className)){
                    return beansContainer.get(className);
                }else{
                    Object obj = Class.forName(className).getConstructor().newInstance();
                    beansContainer.put(className,  obj);
                    return obj;
                }
            }else if(prototypeBeans.contains(className)){
                return Class.forName(className).getConstructor().newInstance();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
