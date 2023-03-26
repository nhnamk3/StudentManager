/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student;

import java.sql.*;
import database.MyConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author hoain
 */
public class Student {
    Connection con = MyConnection.getConnection();
    PreparedStatement ps;
    
    //get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs;
            rs = st.executeQuery("select max(id) from student_infor");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return id + 1;
    }
    
    //insert new student
    public boolean insert(int id, String name, String date, String gender, String email, String phone, String father, String mother, String address, String imgAddress) {
        String sql = "insert into student_infor values(?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, date);
            ps.setString(4, gender);
            ps.setString(5, email);
            ps.setString(6, phone);
            ps.setString(7, father);
            ps.setString(8, mother);
            ps.setString(9, address);
            ps.setString(10, imgAddress);
            
            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New Student added successfully");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    //check student email is already exist
    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from student_infor where Email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //check student phone is already exist
    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from student_infor where `Phone Number` = ?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //get all students value from database student table
    public void getStudentValue(JTable table, String searchValue) {
        String sql = "select * from student_infor where concat(`name`, `email`, `phone number`) like ? order by id desc";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[10];
                row[0] = rs.getInt(1);
                for (int i=1; i<10; i++) {
                    row[i] = rs.getString(i+1);
                }
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
