/**
 * File to Implement Contact interface
 * @author Annabel Jump
 * for PiJ Coursework 4
 */

import java.io.*;

public class ContactImpl implements Contact, Serializable {

    public int identification;
    public String name;
    public String notes;

    //Constructors

    ContactImpl(String thisName){
        this.name = thisName;
        this.identification = 0;
        this.notes = "";
    }

    ContactImpl(int iD){
        this.identification = iD;
        this.name = "Unknown";
        this.notes = "";
    }

    ContactImpl(int number, String contactName){
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
    public int getId(){
        return this.identification;
    }

    /**
     * Get Name to return name of contact
     * {@Inheritdoc}
     */
    @Override
    public String getName(){
        return this.name;
    }

    /**
     * Return a (possibly empty) string of notes on contact
     * {@Inheritdoc}
     */
    @Override
    public String getNotes(){
        return this.notes;
    }

    /**
     * Add the notes on contact - if
     * notes already added, punctuation separator inserted
     * {@Inheritdoc}
     */
    @Override
    public void addNotes(String note){
        if(this.notes == ""){
            this.notes = note;
        } else{
            this.notes = this.notes + "; " + note;
        }
        return;
    }
}