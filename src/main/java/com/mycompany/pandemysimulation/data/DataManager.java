/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.pandemysimulation.data;

import com.mycompany.pandemysimulation.core.data.AbstractDataManager;
import com.mycompany.pandemysimulation.person.Person;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author kacper
 */
public class DataManager extends AbstractDataManager{
    public void update(){
        List<Person> people = getSimulation().getThreadAgents().stream().filter(ta->ta instanceof Person).map(ps->(Person)ps).collect(Collectors.toList());
        List<Person> sickPeople = people.stream().filter(ps->ps.isSick()).collect(Collectors.toList());
        getSimulation().getWorldData().setNumberOfSickPeople(sickPeople.size());
        getSimulation().getWorldData().setNumberOfPeople(people.size());
    }
}
