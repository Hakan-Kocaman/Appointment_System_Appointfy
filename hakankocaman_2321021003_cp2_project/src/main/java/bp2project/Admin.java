/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bp2project;

import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class Admin extends User{
    
    static String accessCode="&P%$7";
    static ArrayList<App> allAppList= new ArrayList<>();
    static ArrayList<Admin> adminList= new ArrayList<>();

    
    public Admin(String username, String password,String mail,String phoneNum) {
        super();
        this.id=SQLManagement.getIdAdmin();
       this.username= username;
       this.password=password;
       this.mail=mail;
       this.phoneNum=phoneNum;
       //insert to databases
       SQLManagement.insertAdmin(this);
       
      
    }
   public Admin(){}//Required cause of sql method
    
    @Override
    public String getInfo(){
        
    
        return "* Username: "+this.username+
                "\n* Id: "+this.id+
                "\n* Mail: "+this.mail+
                "\n* Phone Number: "+this.phoneNum;
    
    }
    
    @Override
    public void fillPersonelInfos(String mail,String phoneNum,String gender){
    this.mail=mail;
     this.phoneNum=phoneNum;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
