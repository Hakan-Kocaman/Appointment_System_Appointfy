/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bp2project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class SQLManagement {

    private static final String ConnectionStr = "jdbc:mysql://localhost:3306/appfollow?user=root&password=cemal.can159";
    
 public static void transfer(){
     //Transfer:  Database=>Arraylists,Tables
 transferUserList();
 transferAdminList();
 transferAllApps();
 }
    public static boolean testConnection() {
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);
            connect.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    
    public static boolean ctrlTable(String day,int time){
        //method for Taken Hour Control
      String str = null;
        try {
            Connection connect= DriverManager.getConnection(ConnectionStr);
            String query="SELECT * FROM appfollow.tableboolean WHERE starts_at = "+time;
            Statement statement=connect.createStatement();
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                 str=result.getString(day);
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
       
        if ("         ✘".equals(str)) {
            
            return false;
        } else {
            return true;
        }
    }
       
    public static int getIdApp(){
        testConnection();
    int id=0;
        try {
            Connection connect= DriverManager.getConnection(ConnectionStr);
            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.allapps";
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                id=result.getInt("id");
                
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    id++;
    
    
    return id;
    }
    public static int getIdUser(){
        testConnection();
    int id=100;
        try {
            Connection connect= DriverManager.getConnection(ConnectionStr);
            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.listuser";
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                id=result.getInt("id");
                
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    id++;
    
    
    return id;
    }
    public static int getIdAdmin(){
        testConnection();
    int id=200;
        try {
            Connection connect= DriverManager.getConnection(ConnectionStr);
            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.listadmin";
            ResultSet result=statement.executeQuery(query);
            while (result.next()) {
                id=result.getInt("id");
                
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    id++;
    
    
    return id;
    }
    
    public static ResultSet getTableString(DefaultTableModel tablemodel) {
  ResultSet result=null;
        try { Connection connect= DriverManager.getConnection(ConnectionStr);
        Statement statement=connect.createStatement();
        String query="SELECT * FROM appfollow.tablestring";
        result= statement.executeQuery(query);
        tablemodel.setRowCount(0);
        
        while (result.next()) { 
            int start_at= result.getInt("starts_at");
            String time_distance = result.getString("time_distance");
            String Monday = result.getString("Monday");
            String Tuesday = result.getString("Tuesday");
            String Wednesday = result.getString("Wednesday");
            String Thursday = result.getString("Thursday");
            String Friday = result.getString("Friday");
            String Saturday = result.getString("Saturday");
            String Sunday = result.getString("Sunday");
            tablemodel.addRow(new Object[]{start_at,time_distance,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday});
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }

    public static ResultSet getTableBoolean(DefaultTableModel tablemodel) {

        ResultSet result=null;
        try { Connection connect= DriverManager.getConnection(ConnectionStr);
        Statement statement=connect.createStatement();
        String query="SELECT * FROM appfollow.tableboolean";
        result= statement.executeQuery(query);
        tablemodel.setRowCount(0);
        
        while (result.next()) { 
            int start_at= result.getInt("starts_at");
            String time_distance = result.getString("time_distance");
            String Monday = result.getString("Monday");
            String Tuesday = result.getString("Tuesday");
            String Wednesday = result.getString("Wednesday");
            String Thursday = result.getString("Thursday");
            String Friday = result.getString("Friday");
            String Saturday = result.getString("Saturday");
            String Sunday = result.getString("Sunday");
            tablemodel.addRow(new Object[]{start_at,time_distance,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday});
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return result;

    }
 
    public static ResultSet transferAllApps() {
       
        testConnection();
        ResultSet result = null;
         Admin.allAppList.clear();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

          
            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.allapps";
            result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String username = result.getString("user");
                String day = result.getString("day");
                int time = result.getInt("time");
                String descrb = result.getString("descrb");
                App app = new App();
                app.setId(id);
                app.setDay(day);
                app.setTime(time);
                app.setDescrb(descrb);
                for (int i = 0; i < User.userList.size(); i++) {
                    if (User.userList.get(i)!=null&&User.userList.get(i).username.equals(username)) {
                    app.setUser(User.userList.get(i));
                    User.userList.get(i).currentApp=app;
                    break;
                }
                }
                Admin.allAppList.add(app);
               
            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return result;

    }

    public static ResultSet transferUserList() {
        testConnection();
        ResultSet result = null;
        User.userList.clear();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.listuser";
            result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
               
                String mail = result.getString("mail");
                String phoneNum = result.getString("phoneNum");
                String gender = result.getString("gender");
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                user.fillPersonelInfos(mail, phoneNum, gender);
               

                User.userList.add(user);

            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return result;

    }

    public static ResultSet transferAdminList() {

        testConnection();
        ResultSet result = null;
        Admin.adminList.clear();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            Statement statement = connect.createStatement();

            String query = "SELECT * FROM appfollow.listadmin";
            result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String username = result.getString("username");
                String password = result.getString("password");
                String mail = result.getString("mail");
                String phoneNum = result.getString("phoneNum");

                Admin admin = new Admin();
                admin.setId(id);
                admin.setUsername(username);
                admin.setPassword(password);
                admin.fillPersonelInfos(mail, phoneNum, "");

                Admin.adminList.add(admin);

            }
            statement.close();
            connect.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

        return result;

    }

    public static boolean addAppToTables(App app) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String updateQueryString = "UPDATE appfollow.tablestring "
                    + "SET " + app.getDay() + "='" + app.getUser().getUsername() + "' "
                    + "WHERE starts_at=" + app.getTime() + ";";

            String updateQueryBoolean = "UPDATE appfollow.tableboolean "
                    + "SET " + app.getDay() + "='         ✘' "
                    + "WHERE starts_at=" + app.getTime() + ";";

            Statement statement = connect.createStatement();
            Statement statementt = connect.createStatement();

            statement.execute(updateQueryString);
            statementt.execute(updateQueryBoolean);

            statementt.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }

        return true;

    }

    public static boolean insertAppointment(App app) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String applistQuery = "INSERT appfollow.allapps "
                    + "SET id= " + app.getId() + " "
                    + ", user= '" + app.getUser().username + "' "
                    + ", day= '" + app.getDay() + "' "
                    + ", time= " + app.getTime() + " "
                    + ", descrb= '" + app.getDescrb() + "' ";

            Statement statement = connect.createStatement();
            statement.execute(applistQuery);
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

    public static boolean insertUser(User user) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String userlistQuery = "INSERT appfollow.listuser "
                    + "SET id= " + user.getId() + " , "
                    + "username= '" + user.username + "' , "
                    + " password= '" + user.password + "'  , "
                    + " mail= '" + user.mail + "'  ,"
                    + " phoneNum= '" + user.phoneNum + "' , "
                    + " gender= '" + user.gender + "'  ";
            
            Statement statement = connect.createStatement();
            statement.execute(userlistQuery);
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

    public static boolean insertAdmin(Admin admin) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String adminlistQuery = "INSERT appfollow.listadmin "
                    + "SET id= " + admin.getId() + " "
                    + ", username= '" + admin.username + "' "
                    + ", password= '" + admin.password + "' "
                    + ", mail= '" + admin.mail + "' "
                    + ", phoneNum= '" + admin.phoneNum + "' ";

            Statement statement = connect.createStatement();
            statement.execute(adminlistQuery);
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;

    }

    public static boolean deleteAppFromTables(App app) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String updateQueryString = "UPDATE appfollow.tablestring "
                    + "SET " + app.getDay() + "= null "
                    + "WHERE starts_at=" + app.getTime() + ";";

            String updateQueryBoolean = "UPDATE appfollow.tableboolean "
                    + "SET " + app.getDay() + "= null "
                    + "WHERE starts_at=" + app.getTime() + ";";

            Statement statement = connect.createStatement();
            Statement statementt = connect.createStatement();

            statement.execute(updateQueryString);
            statementt.execute(updateQueryBoolean);

            statementt.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;

        }

        return true;

    }

    public static boolean deleteApp(App app) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String deleteAppQuery = "DELETE FROM appfollow.allapps WHERE id= " + app.getId();

            Statement statement = connect.createStatement();
            statement.execute(deleteAppQuery);
            statement.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean deleteUser(User user) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String deleteUserQuery = "DELETE FROM appfollow.listuser WHERE id= " + user.getId();

            Statement statement = connect.createStatement();
            statement.execute(deleteUserQuery);
            statement.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean deleteAdmin(Admin admin) {
        testConnection();
        try {
            Connection connect = DriverManager.getConnection(ConnectionStr);

            String deleteAdminQuery = "DELETE FROM appfollow.listadmin WHERE id= " + admin.getId();

            Statement statement = connect.createStatement();
            statement.execute(deleteAdminQuery);
            statement.close();

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

}
