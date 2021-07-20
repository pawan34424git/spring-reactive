package com.pawan.reactive.config;

 
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    private static ApplicationContext context;

    

    public static ApplicationContext getContext() {
        return context;
    }
    
    public static void setContext(ApplicationContext c) {
        context=c;
    }

	@Override
	public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;   
		
	}
    
    
    
}