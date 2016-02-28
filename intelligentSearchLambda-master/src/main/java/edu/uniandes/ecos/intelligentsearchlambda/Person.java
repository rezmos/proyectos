/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uniandes.ecos.intelligentsearchlambda;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author dnielben
 */
public class Person {

    Person(String name, String birthday, String email, String gender) {
        this.name = name;
        this.birthday = LocalDate.parse(birthday);
        this.emailAddress = email;
        this.gender = gender=="m"?Sex.MALE:Sex.FEMALE;
    }
    
    
    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return Period.between(birthday,LocalDate.now()).getYears();
    }

    public void printPerson() {
        System.out.println("Name: " + name);
        System.out.println("Edad: " + getAge());
        System.out.println("gender: " + gender);
        System.out.println("email: " + emailAddress);
    }
}