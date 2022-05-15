package com.IsaMrsTim19.projekat.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.neo4j.driver.Value;
import org.neo4j.driver.internal.value.DateValue;
import org.springframework.data.neo4j.core.convert.Neo4jPersistentPropertyConverter;

public class DateStringConverter implements Neo4jPersistentPropertyConverter<Date> {

    @Override
    public Value write(Date source) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(source);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(strDate, formatter);

        DateValue d = new DateValue(localDate);
        return d;
    }

    @Override
    public Date read(Value source) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(source.toString());
//            System.out.println("Uspeo da parsiram");
            return date;
        } catch (ParseException e) {
//            System.out.println("Nisam uspeo da parsiram");
            return null;
        }
    }
}
