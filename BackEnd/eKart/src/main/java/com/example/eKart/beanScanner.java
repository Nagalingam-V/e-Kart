package com.example.eKart;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class beanScanner {
    @Autowired
    ApplicationContext context;

//    @PostConstruct
//    public void scanBeans() {
//        String[] beans = context.getBeanDefinitionNames();
//        for (String bean : beans) {
//            if (bean.toLowerCase().contains("user") || bean.toLowerCase().contains("security")) {
//                System.out.println("SECURITY BEAN: " + bean + " → " + context.getBean(bean).getClass());
//            }
//        }
//    }
}
