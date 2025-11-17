/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bp2project;

/**
 *
 * @author Lenovo
 */
public class App {
    
    private User user;
    private String day;
    private int time;
     private String descrb;
     private  int id;
    

    public App(User user,String day, int time, String descrb) {
      this.user=user;
        this.day = day;
        this.time = time;
        this.descrb=descrb;
        this.id=SQLManagement.getIdApp();
        SQLManagement.insertAppointment(this);
        
    }
   public App(){}//Required cause of admin constructor

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescrb() {
        return descrb;
    }

    public void setDescrb(String descrb) {
        this.descrb = descrb;
    }
    
    
    
    
    
    
}
