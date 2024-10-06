package com.scm.scm.helper;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;
//import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import jakarta.servlet.http.HttpSession;

@Component
public class SessionHelper {

    public static void removeMessage(){

        try{
           
            HttpSession session=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            
            session.removeAttribute("message");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
