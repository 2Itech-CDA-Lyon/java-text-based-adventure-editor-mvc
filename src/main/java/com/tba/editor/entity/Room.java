package com.tba.editor.entity;

import javax.persistence.*;

/**
 * Represents a place the player can visit
 */
@Entity
@Table(name = "rooms")
public class Room {

    /**
     * Database identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    /**
    * Room name 
    */
    @Column(name = "name", nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
