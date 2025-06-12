import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class ExamineeCatalog extends PScrollPanel{
    PButton btnBack = new PButton("Back");
    ExamineeCatalog(){
        setBackground(PulsePointConstants.WHITE);
        initButton();
        loadDb();
    }

    private void initButton(){
        btnBack.setBackground(PulsePointConstants.RED);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                loadDb();
            }
        });

    }

    Connection conn;
    Statement stmnt;
    ResultSet result;
    private void loadDb(){
        try{
            conn = DriverManager.getConnection(
                    PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD
            );

            String query = "SELECT examinee_id, last_name, first_name, middle_initial, role FROM examinee";
            stmnt = conn.createStatement();
            result = stmnt.executeQuery(query);

            while(result.next()){
                String strFullName;
                if(result.getString("middle_initial") != null) {
                    strFullName = result.getString("last_name") + ", " + result.getString("first_name") + " " + result.getString("middle_initial") + ".";
                }else{
                    strFullName = result.getString("last_name") + ", " + result.getString("first_name");
                }
                ExamineeRecord objRecord = new ExamineeRecord(
                        result.getString("examinee_id"),
                        strFullName,
                        result.getString("role"));
                objRecord.setActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removeAll();
                        append(btnBack);
                        ExamineeInformationBuilder objInfoBuilder = new ExamineeInformationBuilder(objRecord.getID());
                        append(objInfoBuilder.buildExamineeInfoPanel());
                        append(objInfoBuilder.buildMedicalConditionPanel());
                        append(objInfoBuilder.buildImmunizationPanel());
                    }
                });
                append(objRecord);
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occured\n" + e, "Unknown Error Occured", JOptionPane.WARNING_MESSAGE);
        }finally {
            try{result.close();}catch (Exception ignored){}
            try{stmnt.close();}catch (Exception ignored){}
            try{conn.close();}catch (Exception ignored){}
        }
    }

    public void refresh(){
        removeAll();
        loadDb();
        revalidate();
        repaint();
    }

    public static void main(String[] args){
        JFrame frm = new JFrame();
        frm.setSize(1000,800);
        frm.setDefaultCloseOperation(3);

        ExamineeCatalog examineeCatalog = new ExamineeCatalog();
        frm.add(examineeCatalog.getScrollPane());

        frm.setVisible(true);
    }
}
