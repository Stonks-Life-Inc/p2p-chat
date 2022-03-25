package src.controller;

import src.entity.Contact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


public class ContactManagement {
    static Scanner scan=new Scanner(System.in);
    private static final String CSV_SEPARATOR = ",";
    public static void StudentManagement() 
    {
        
        System.out.println("choose an option:");
         System.out.println("1.Add contact");
          System.out.println("2.List contacts");
             System.out.println("3.Delete contact");
           System.out.println("Choose an option:");
           int choice=scan.nextInt();
           switch(choice)
           {
               case 1:
                   
                   addContact();
                   break;
               case 2:
        {
            try {
                displayContacts();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ContactManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                   break;
               case 3:
                   deleteContact();
                   break;
               default:
                   System.out.println("Choose a valid entry");
           }
    }

    public static void main(String[] args){
      
           
           
            
    }

    private static void addContact() {
        String name;
        int ipAdress;
       
       System.out.println("Enter user name :");
       name=scan.nextLine();
       System.out.println("Enter user ipAdress :");
       ipAdress=scan.nextInt();
   
       Contact contact=new Contact(name, ipAdress);
       
       try
       {
           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contacts.csv",true), "UTF-8"));
           
               StringBuilder oneLine = new StringBuilder();
               oneLine.append(contact.getName().trim().length() == 0? "" : contact.getName());
               oneLine.append(CSV_SEPARATOR);
               oneLine.append(contact.getIpAdress() <=0 ? "" : contact.getIpAdress());
               bw.append(oneLine.toString());
               bw.newLine();
           
           bw.flush();
           bw.close();
           
           System.out.println("Registration OK");
       }
       catch (UnsupportedEncodingException e) {}
       catch (FileNotFoundException e){}
       catch (IOException e){}
   
   
   }


   private static void displayContacts() throws FileNotFoundException {
    try (Scanner scanner = new Scanner(new File("contacts.csv"));) {
while (scanner.hasNextLine()) {
   
   Contact.readContact(scanner.nextLine());
}
}       catch (FileNotFoundException ex) {
        System.out.println("FICHIER VIDE");
    }
    Collections.sort(Contact.Contacts);
    for (int i = 0; i < Contact.Contacts.size(); i++) {
        System.out.println(Contact.Contacts.get(i).toString());
    }
    Contact.Contacts.clear();
    
}

private static void deleteContact() {
    try (Scanner scanner = new Scanner(new File("contacts.csv"));) {
while (scanner.hasNextLine()) {
  Contact.readContact(scanner.nextLine());
}
}       catch (FileNotFoundException ex) {
       Logger.getLogger(Contact.class.getName()).log(Level.SEVERE, null, ex);
   }
    
   //automize this part and take the user's name and remove it from all the lists
   String contactName;
  System.out.println("Enter user name to delete");
  scan.nextLine();
  contactName=scan.nextLine();
  
  Optional<Contact> matchingObject =Contact.contacts.stream().
filter(p -> String.valueOf(p.getName()).equals(contactName)).
findFirst();
 Contact contact = matchingObject.get();

 Contact.contacts.remove(contact);
 clearfile();
 addlist();
 Contact.contacts.clear();
  
}

    private static void addlist()
    {
        Contact.contacts.forEach((contact) -> {
            try
            {
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("contacts.csv",true), "UTF-8"));
                
                StringBuilder oneLine = new StringBuilder();
                oneLine.append(contact.getName().trim().length() == 0? "" : contact.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(contact.getIpAdress() < 0 ? "" : contact.getIpAdress());
                bw.append(oneLine.toString());
                bw.newLine();
                bw.flush();
                bw.close();
                System.out.println("Contact is saved sucessfully!!!");
            }
            catch (UnsupportedEncodingException e) {}
            catch (FileNotFoundException e){}
            catch (IOException e){}
        });
    }

    private static void clearfile()
    {
         File f;
            f = new File("contacts.csv"); //file to be delete
             f.delete();  
    }    
}
