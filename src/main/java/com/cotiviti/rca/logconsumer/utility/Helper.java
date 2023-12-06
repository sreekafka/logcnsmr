package com.cotiviti.rca.logconsumer.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Helper {

    private final Logger logger = LoggerFactory.getLogger(Helper.class);


    /**
     *  Validate if string has all ascii characters
     */
    public boolean isAscii (String str){
        boolean allAscii = true;
        Scanner sc = new Scanner(System.in);

        boolean result = str.matches("\\A\\p{ASCII}*\\z");
        if(result) {
            allAscii = true;
        } else {
//            System.out.println("Contains non-ASCII values");
            allAscii = false;
        }
        return allAscii;
    }
}
