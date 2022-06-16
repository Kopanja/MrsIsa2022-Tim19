package com.IsaMrsTim19.projekat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.LocalDateTimeValue;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;

public class DateTimeStringConverter  implements Neo4jPersistentPropertyConverter<Date> {

    @Override
    public Value write(Date source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strDate = dateFormat.format(source);
        System.out.println(strDate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
        //LocalDateTime date = LocalDateTime.parse(strDate, formatter);
        LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
    
        LocalDateTimeValue b = new LocalDateTimeValue(dateTime);
        return b;
    }

    @Override
    public Date read(Value source) {
        try {
        	System.out.println(source.toString());
            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(source.toString());
            //System.out.println("Uspeo da parsiram");
            return date;
        } catch (ParseException e) {
            //System.out.println("Nisam uspeo da parsiram");
            return null;
        }
    }

}
