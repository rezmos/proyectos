/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.uniandes.ecos.intelligentsearchlambda;

/**
 *
 * @author dnielben
 */
public class CheckPersonEligibleForSelectiveService implements CheckPerson {
    public boolean test(Person p) {
        return p.gender == Person.Sex.FEMALE &&
            p.getAge() >= 18 &&
            p.getAge() <= 25;
    }
}