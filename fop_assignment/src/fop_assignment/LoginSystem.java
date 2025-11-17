/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fop_assignment;
import java.util.*;
import java.io.*;


public class LoginSystem {
    

    public static User login(String username, String password) throws IOException {
        List<User> users = UserManager.loadUsers();

        for (User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}



