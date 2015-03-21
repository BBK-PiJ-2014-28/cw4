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

}