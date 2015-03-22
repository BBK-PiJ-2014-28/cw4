/**
 * File to Implement Contact interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.io.*;

public class ContactImpl implements Contact {

    private int identification;
    private String name;
    private String notes;

    //Constructors

    ContactImpl(String thisName){
        this.name = thisName;
        this.identification = null;
        this.notes = "";
    }

    ContactImpl(int iD){
        this.identification = iD;
        this.name = "Unknown";
        this.notes = "";
    }

    ContactImpl(String contactName, int number){
        this.identification = number;
        this.name = contactName;
        this.notes = "";
    }

    ContactImpl(String aName, int iDent, String comments){
        this.identification = iDent;
        this.name = aName;
        this.notes = comments;
    }

    /**
     * Get ID to return ID number of contact
     * {@Inheritdoc}
     */
    @Override
    int getId(){
        return this.identification;
    }

    /**
     * Get Name to return name of contact
     * {@Inheritdoc}
     */
    @Override
    String getName(){
        return this.name;
    }

    /**
     * Return a (possibly empty) string of notes on contact
     * {@Inheritdoc}
     */
    @Override
    String getNotes(){
        return this.notes;
    }

    /**
     * Add the notes on contact
     * {@Inheritdoc}
     */
    @Override
    void addNotes(String note){
        
    }
}