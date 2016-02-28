/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniandes.ecos.intelligentsearchlambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author dnielben
 */
public class IntelligentSearchLambda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Person> personas = new ArrayList<Person>();
        fillList(personas);
        
        System.out.println("--- Prueba 1, Métodos de búsqueda y acción:");
        printPersonsOlderThan(personas, 20);
        
        System.out.println("--- Prueba 2, Métodos más genéricos:");
        printPersonsWithinAgeRange(personas, 20, 40);
        
        System.out.println("--- Prueba 3, Especificar criterios en una clase:");
        printPersons(personas, new CheckPersonEligibleForSelectiveService());
        
        System.out.println("--- Prueba 4,Especificar criterios en una clase anónima :");
        printPersons(
                personas,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.gender == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 30;
                    }
                }
        );
        
        System.out.println("--- Prueba 5 lambda:");
        printPersons(
                personas,
                (Person p) -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 29
        );

        System.out.println("--- Prueba 6 lambda with Predicate:");
        printPersonsWithPredicate(
                personas,
                p -> p.gender == Person.Sex.FEMALE
                && p.getAge() >= 18
                && p.getAge() <= 25
        );
        System.out.println("--- Prueba 7 lambda with Predicate and Consumer:");
        processPersons(
                personas,
                p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 29,
                p -> p.printPerson()
        );
        System.out.println("--- Prueba 7 lambda with Predicate, function and Consumer:");
        processPersonsWithFunction(
                personas,
                p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 29,
                p -> p.emailAddress,
                email -> System.out.println(email)
        );

        System.out.println("--- Prueba 8 More generic lambda with Predicate, function and Consumer:");
        processElements(
                personas,
                p -> p.gender == Person.Sex.MALE
                && p.getAge() >= 18
                && p.getAge() <= 40,
                p -> p.emailAddress,
                email -> System.out.println(email)
        );

        System.out.println("--- Prueba 9 with aggregate methods:");
        personas.stream().filter(
                        p -> p.gender == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 40).map(p -> p.emailAddress)
                .forEach(email -> System.out.println(email));

    }

    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void processPersonsWithFunction(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper,
            Consumer<String> block) {

        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static void processPersons(
            List<Person> lista,
            Predicate<Person> tester,
            Consumer<Person> block) {
        for (Person p : lista) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    public static void printPersonsWithPredicate(
            List<Person> lista, Predicate<Person> tester) {
        for (Person p : lista) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsOlderThan(List<Person> lista, int age) {
        for (Person p : lista) {
            if (p.getAge() >= age) {
                p.printPerson();
            }
        }
    }

    public static void printPersonsWithinAgeRange(List<Person> lista, int low, int high) {
        for (Person p : lista) {
            if (low <= p.getAge() && p.getAge() < high) {
                p.printPerson();
            }
        }
    }

    private static void fillList(List<Person> personas) {
        personas.add(new Person("Pedro", "1975-01-01", "p@ecos.com", "m"));
        personas.add(new Person("Juan", "1985-01-01", "j@ecos.com", "m"));
        personas.add(new Person("María", "1995-01-01", "m@ecos.com", "female"));
        personas.add(new Person("Sandra", "1999-01-01", "s@ecos.com", "female"));
    }

    public static void printPersons(
            List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                p.printPerson();
            }
        }
    }

}
