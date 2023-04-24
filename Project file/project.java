/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniProject;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Denis Ruparel
 */
public class project extends JFrame implements ActionListener, ItemListener, ChangeListener {

    JTabbedPane jtp = new JTabbedPane();
    JPanel pnlInsert, pnlUpdate, pnlDelete, pnlSelect;
    JLabel lblIBId, lblIBName, lblIBCatagory, lblIBAuthorName, lblIBPrice, lblDBId, lblUBId, lblUBName, lblUBCatagory, lblUBAuthorName, lblUBPrice;
    JTextField txtIBID, txtIBName, txtIBCatagory, txtIBAuthorName, txtIBPrice, txtUBName, txtUBCatagory, txtUBAuthorName, txtUBPrice;
    JButton btnIns, btnDel, btnUpd;
    JComboBox cbdid, cbuid;
    Statement stmt;
    ResultSet rs, rsLoad;
    PreparedStatement pstmt;
    Connection con;
    DefaultTableModel dtm;
    JScrollPane jsp;
    JTable jt;
    String sql, query;

    public project() {

        pnlInsert = new JPanel();
        pnlUpdate = new JPanel();
        pnlDelete = new JPanel();
        pnlSelect = new JPanel();

        pnlInsert.setLayout(null);

        //for insert
        lblIBId = new JLabel("Book ID: ");
        lblIBId.setBounds(70, 60, 100, 20);
        pnlInsert.add(lblIBId);

        txtIBID = new JTextField(15);
        txtIBID.setBounds(175, 60, 150, 20);
        pnlInsert.add(txtIBID);

        lblIBName = new JLabel("Book Name: ");
        lblIBName.setBounds(70, 100, 100, 20);
        pnlInsert.add(lblIBName);

        txtIBName = new JTextField(15);
        txtIBName.setBounds(175, 100, 150, 20);
        pnlInsert.add(txtIBName);

        lblIBCatagory = new JLabel("Book Catagory: ");
        lblIBCatagory.setBounds(70, 140, 100, 20);
        pnlInsert.add(lblIBCatagory);

        txtIBCatagory = new JTextField(15);
        txtIBCatagory.setBounds(175, 140, 150, 20);
        pnlInsert.add(txtIBCatagory);

        lblIBAuthorName = new JLabel("Author Name: ");
        lblIBAuthorName.setBounds(70, 180, 100, 20);
        pnlInsert.add(lblIBAuthorName);

        txtIBAuthorName = new JTextField(15);
        txtIBAuthorName.setBounds(175, 180, 150, 20);
        pnlInsert.add(txtIBAuthorName);

        lblIBPrice = new JLabel("Book Price: ");
        lblIBPrice.setBounds(70, 220, 100, 20);
        pnlInsert.add(lblIBPrice);

        txtIBPrice = new JTextField(15);
        txtIBPrice.setBounds(175, 220, 150, 20);
        pnlInsert.add(txtIBPrice);

        btnIns = new JButton("Insert");
        btnIns.setBounds(120, 260, 120, 20);
        pnlInsert.add(btnIns);
        btnIns.addActionListener(this);

        //for delete
        pnlDelete.setLayout(null);

        lblDBId = new JLabel("Book ID: ");
        lblDBId.setBounds(70, 60, 100, 20);
        pnlDelete.add(lblDBId);

        cbdid = new JComboBox();
        cbdid.setBounds(150, 60, 150, 20);
        pnlDelete.add(cbdid);

        cbdid.addItemListener(this);

        btnDel = new JButton("Delete");
        btnDel.setBounds(120, 100, 120, 20);
        pnlDelete.add(btnDel);
        btnDel.addActionListener(this);

        //for update
        pnlUpdate.setLayout(null);
        lblUBId = new JLabel("Book ID: ");
        lblUBId.setBounds(70, 60, 100, 20);
        pnlUpdate.add(lblUBId);

        cbuid = new JComboBox();
        cbuid.setBounds(175, 60, 150, 20);
        pnlUpdate.add(cbuid);

        cbuid.addItemListener(this);

        lblUBName = new JLabel("Book Name: ");
        lblUBName.setBounds(70, 100, 100, 20);
        pnlUpdate.add(lblUBName);

        txtUBName = new JTextField(15);
        txtUBName.setBounds(175, 100, 150, 20);
        pnlUpdate.add(txtUBName);

        lblUBCatagory = new JLabel("Book Catagory: ");
        lblUBCatagory.setBounds(70, 140, 100, 20);
        pnlUpdate.add(lblUBCatagory);

        txtUBCatagory = new JTextField(15);
        txtUBCatagory.setBounds(175, 140, 150, 20);
        pnlUpdate.add(txtUBCatagory);

        lblUBAuthorName = new JLabel("Author Name: ");
        lblUBAuthorName.setBounds(70, 180, 100, 20);
        pnlUpdate.add(lblUBAuthorName);

        txtUBAuthorName = new JTextField(15);
        txtUBAuthorName.setBounds(175, 180, 150, 20);
        pnlUpdate.add(txtUBAuthorName);

        lblUBPrice = new JLabel("Book Price: ");
        lblUBPrice.setBounds(70, 220, 100, 20);
        pnlUpdate.add(lblUBPrice);

        txtUBPrice = new JTextField(15);
        txtUBPrice.setBounds(175, 220, 150, 20);
        pnlUpdate.add(txtUBPrice);

        btnUpd = new JButton("Update");
        btnUpd.setBounds(120, 260, 120, 20);
        pnlUpdate.add(btnUpd);
        btnUpd.addActionListener(this);

        jtp.addTab("Insert", pnlInsert);
        jtp.addTab("Update", pnlUpdate);
        jtp.addTab("Delete", pnlDelete);
        jtp.addTab("Select", pnlSelect);
        add(jtp);
        jtp.addChangeListener(this);
        
        pnlSelect.setLayout(new BorderLayout());
        showTable();

        setVisible(true);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        project p = new project();
    }

    public void showTable() {
        dtm = new DefaultTableModel();
        dtm.addColumn("Book ID");
        dtm.addColumn("Book Title");
        dtm.addColumn("Book Catagory");
        dtm.addColumn("Book Author Name");
        dtm.addColumn("Book Price");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject_db", "root", "");
            stmt = con.createStatement();
            query = "select * from book";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String catagory = rs.getString(3);
                String author = rs.getString(4);
                String price = rs.getString(5);
                dtm.addRow(new Object[]{id, title, catagory, author, price});
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception (DT): " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ce) {
                System.out.println("Close Exception (DT)");
            }
        }
        jt = new JTable(dtm);
        jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jsp = new JScrollPane(jt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pnlSelect.add(jsp);
    }

    public void loadData() {
        cbuid.removeItemListener(this);
        cbuid.removeAllItems();

        txtUBName.setText("");
        txtUBCatagory.setText("");
        txtUBAuthorName.setText("");
        txtUBPrice.setText("");

        cbdid.removeAllItems();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject_db", "root", "");
            stmt = con.createStatement();
            query = "select id from book";
            rsLoad = stmt.executeQuery(query);
            while (rsLoad.next()) {
                cbuid.addItem(rsLoad.getInt(1));
                cbdid.addItem(rsLoad.getInt(1));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception (LN): " + ex);
        } finally {
            try {
                if (rsLoad != null) {
                    rsLoad.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ce) {
                System.out.println("Close Exception (LN)");
            }
            cbuid.setSelectedIndex(-1);
            cbdid.setSelectedIndex(-1);
            cbuid.addItemListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pressBtn = e.getActionCommand();
        System.out.println(pressBtn);
        if (pressBtn.equals("Insert") && (txtIBID.getText().equals("") || txtIBName.getText().equals("") || txtIBCatagory.getText().equals("") || txtIBAuthorName.getText().equals("") || txtIBPrice.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Enter Insert details properly", "Insert Status", JOptionPane.ERROR_MESSAGE);
        } else if (pressBtn.equals("Update") && (cbuid.getSelectedIndex() == -1 || txtUBName.getText().equals("") || txtUBCatagory.getText().equals("") || txtUBAuthorName.getText().equals("") || txtUBPrice.getText().equals(""))) {
            JOptionPane.showMessageDialog(this, "Enter Update details properly", "Update Status", JOptionPane.ERROR_MESSAGE);
        } else if (pressBtn.equals("Delete") && cbdid.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Enter details properly", "Delete Status", JOptionPane.ERROR_MESSAGE);
        }
        else{
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject_db", "root", "");
                stmt = con.createStatement();
               
                if (pressBtn.equals("Insert")){
                    sql = "insert into book(id, book_title, catagory, author_name, price) values(?, ?, ?, ?, ?)";
                     pstmt = con.prepareStatement(sql);
                     pstmt.setString(1, txtIBID.getText());
                    pstmt.setString(2, txtIBName.getText());
                    pstmt.setString(3, txtIBCatagory.getText());
                    pstmt.setString(4, txtIBAuthorName.getText());
                    pstmt.setString(5, txtIBPrice.getText());
                    int executeUpdate = pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Book Inserted Successfully..!", "Success", JOptionPane.INFORMATION_MESSAGE); 
                }
                else if (pressBtn.equals("Update")){
                    query = "update book set book_title='" + txtUBName.getText() + "', catagory='"+ txtUBCatagory.getText()+"', author_name='"+txtUBAuthorName.getText()+"', price="+txtUBPrice.getText()+" where id=" + cbuid.getSelectedItem() + ";";
                    System.out.println(query);
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(this, "Book updated successfully...", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                }
                else if (pressBtn.equals("Delete")) {
                    query = "delete from book where id='" + cbdid.getSelectedItem() + "'";
                    stmt.executeUpdate(query);
                    JOptionPane.showMessageDialog(this, "Book deleted successfully...", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                }
                txtIBID.setText("");
                txtIBName.setText("");
                txtIBCatagory.setText("");
                txtIBAuthorName.setText("");
                txtIBPrice.setText("");
                cbuid.removeItemListener(this);
                cbuid.setSelectedIndex(-1);
                cbuid.addItemListener(this);
                cbdid.setSelectedIndex(-1);
                
            } catch (ClassNotFoundException | SQLException ex) {
                System.out.println("Exception (final): " + e);
                if (pressBtn.equals("Insert")) {
                    JOptionPane.showMessageDialog(this, "Book Not Added...!", "Insert Status", JOptionPane.INFORMATION_MESSAGE);
                } else if (pressBtn.equals("Update")) {
                    JOptionPane.showMessageDialog(this, "Book Not Updated...!", "Update Status", JOptionPane.INFORMATION_MESSAGE);
                } else if (pressBtn.equals("Delete")) {
                    JOptionPane.showMessageDialog(this, "Book Not Deleted...!", "Delete Status", JOptionPane.INFORMATION_MESSAGE);
                }
            }
             finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (stmt != null) {
                        stmt.close();
                    }
                    if (con != null) {
                        con.close();
                    }
                } catch (SQLException ce) {
                    System.out.println("Close Exception (final)");
                }
                loadData();
            }
        }
 }
    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject_db", "root", "");
            stmt = con.createStatement();
            if(cbuid.getSelectedIndex()!=-1){
                query = "select * from book where id=" + cbuid.getSelectedItem() + "";
                rs = stmt.executeQuery(query);
                rs.next();
                 txtUBName.setText(rs.getString(2) + "");
                 txtUBCatagory.setText(rs.getString(3) + "");
                 txtUBAuthorName.setText(rs.getString(4) + "");
                 txtUBPrice.setText(rs.getInt(5) + "");
            }
            if(cbuid.getSelectedIndex()==-1){
                resetItemsOfTextField();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Exception (ISC): " + ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ce) {
                System.out.println("Close Exception (ISC)");
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int activetab = jtp.getSelectedIndex();
        if (activetab == 1 || activetab == 2) {
            cbuid.setSelectedIndex(-1);
            cbdid.setSelectedIndex(-1);
            resetItemsOfTextField();
            loadData();
        }
        if (activetab == 3) {
            pnlSelect.removeAll();
            showTable();
        }
        
    }

    private void resetItemsOfTextField() {
        txtUBName.setText("");
        txtUBAuthorName.setText("");
        txtUBCatagory.setText("");
        txtUBPrice.setText("");
    }

    }