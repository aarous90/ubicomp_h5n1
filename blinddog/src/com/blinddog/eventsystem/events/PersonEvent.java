/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * MazeTD Project (c) 2012 by Hady Khalifa, Ahmed Arous and Hans Ferchland
 * 
 * MazeTD rights are by its owners/creators.
 * The project was created for educational purposes and may be used under 
 * the GNU Public license only.
 * 
 * If you modify it please let other people have part of it!
 * 
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * GNU Public License
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License 3 as published by
 * the Free Software Foundation.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 * 
 * Email us: 
 * hans[dot]ferchland[at]gmx[dot]de
 * 
 * 
 * Project: MazeTD Project
 * File: CreepEvent.java
 * Type: eventsystem.events.CreepEvent
 * 
 * Documentation created: 01.06.2012 - 16:16:55 by Hans Ferchland
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.blinddog.eventsystem.events;

import com.blinddog.entities.Person;
import com.blinddog.entities.ModelObject;

/**
 * The class CreepEvent that capsules all data of a creep action.
 * @author Hans Ferchland
 * @version 1.0
 */
public class PersonEvent extends AbstractEvent {
    /**
     * The type of creep event.
     */
    public enum PersonEventType {
        
        /** The Death. */
        Death,
        
        /** The Reached end. */
        ReachedEnd,
        
        /** The Attacks. */
        Attacks,
        
        /** The Created. */
        Created
    }
    //==========================================================================
    //===   Private Fields
    //==========================================================================
    /** The type. */
    private PersonEvent.PersonEventType type;
    
    /** The target. */
    private ModelObject target;
    
    /** The creep. */
    private Person person;
    //==========================================================================
    //===   Methods & Constructor
    //==========================================================================
    /**
     * Creates a new creep evnent by a given type and creep. Tower is optional.
     * @param type the events type
     * @param creep the creep that triggers the event
     * @param target the tower if creep was killed or attacks the tower, null otherwise
     */
    public PersonEvent(PersonEvent.PersonEventType type, Person person, ModelObject target) {
        super(person);
        this.person = person;
        this.target = target;
        this.type = type;
    }
    
    /**
     * Gets the creep that triggered the event.
     * @return the creep
     */
    public Person getPerson() {
        return person;
    }
    
    /**
     * The tower currently attacked by the creep, or the tower that killed the
     * creep.
     * @return the tower
     */
    public ModelObject getTarget() {
        return target;
    }

    /**
     * Gets the type of event the creep had triggered.
     * @return the events type
     */
    public PersonEvent.PersonEventType getType() {
        return type;
    }
}
