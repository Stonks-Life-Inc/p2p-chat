//package p2pChat;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Contact implements Comparable<Contact> {
    private String name;
    private int ipAdress;
    
    static Scanner scan=new Scanner(System.in);
    public static List<Contact> contacts=new ArrayList<>();
    public Contact(String name, int ipAdress) {
        this.name = name;
        this.ipAdress = ipAdress;       
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getIpAdress() {
        return ipAdress;
    }
    public void setIpAdress(int ipAdress) {
        this.ipAdress = ipAdress;
    }

    public static void readContact(String s) 
    {
        String[] contact=s.split(",");
        System.out.println(contact.length);
         String name=contact[0];
         int ipAdress=Integer.parseInt(contact[1]);
        
      contacts.add(new Contact(name,ipAdress));
    }

    @Override
    public String toString() {
        return "Contact{" + "name=" + name + ", ipAdress=" + ipAdress + '}';
    }

    @Override
    public int compareTo(Contact o) {
        if (getName() == null || o.getName() == null) {
      return 0;
    }
    return getName().compareTo(o.getName());
    }
    
    
}
