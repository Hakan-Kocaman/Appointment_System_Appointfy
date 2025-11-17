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
public class User {
     String mail;
     String phoneNum;
     String gender;
  
   int id;
   String username;
 String password;
     App currentApp;
    
    UserPanel userinfopanel;
  
    
    static int idCounter=101;
    static ArrayList<User> userList= new ArrayList<>();
    
    
    

    public User(String username, String password, String mail,String phoneNum,String gender) {
        this.username=username;
        this.password=password;
        this.mail=mail;
        this.phoneNum=phoneNum;
        this.gender=gender;
        this.id=SQLManagement.getIdUser();
        userinfopanel=new UserPanel(this);
        SQLManagement.insertUser(this);
        
        
    }
    public User() {
    userinfopanel=new UserPanel(this);
    }
    
    public void reSetFrame(){
    
        userinfopanel= new UserPanel(this);
    }
    
     public void fillPersonelInfos(String mail,String phoneNum,String gender){
   this.mail=mail;
     this.phoneNum=phoneNum;
     this.gender=gender;
     }
    
    public String [] getAppInfo(){
    String [] appinfo= new String[4];
  
    appinfo[0]=currentApp.getDay();
    appinfo[1]=currentApp.getTime()+".00-"+(currentApp.getTime()+1)+".00";
    appinfo[2]=currentApp.getDescrb();

     return appinfo;    
    }
    
    public void DetermineCurrentApp(String day,int time,String descrb){
    currentApp= new App(this,day, time,descrb);
   
    }
    
    public String  getInfo(){
      String str=  "* Username: "+this.username+
                "\n* Id: "+this.id+
                "\n* Mail: "+this.mail+
                "\n* Phone Number: "+this.phoneNum+
              "\n* Gender: "+this.gender;
        if (this.currentApp!=null) {
            str+="\n\nCurrent app:"
                    + "\n* Time: "+this.currentApp.getTime()+".00-"+(this.currentApp.getTime()+1)+".00"+
                    "\n* Day: "+this.currentApp.getDay()+
                    "\n* Description: "+this.currentApp.getDescrb();
            
        }
      
    
         return str;
    
    }

  
    public App getCurrentApp() {
        return currentApp;
    }

    public void setCurrentApp(App currentApp) {
        this.currentApp = currentApp;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
