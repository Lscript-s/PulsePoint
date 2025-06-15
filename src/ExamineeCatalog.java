import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class ExamineeCatalog extends PScrollPanel{
    private PButton btnBack = new PButton("Back");
    private PButton btnDelete = new PButton("Delete");
    private PButton btnUpdate = new PButton("Update");
    ExamineeCatalog(){
        setBackground(PulsePointConstants.WHITE);
        initButton();
        loadDb();
    }

    ExamineeCatalog(String strId){
        setBackground(PulsePointConstants.WHITE);
        initButton();
        loadDb(strId);
    }

    ExamineeCatalog(String strMethod, String strKey){

    }

    private PPanel pnlButton = new PPanel(new GridBagLayout());
    private void initButton(){
        PGridBagConstraints objGbcInner = new PGridBagConstraints();

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hi");
                removeAll();
                loadDb();
            }
        });

        objGbcInner.setConstraints(-1,0,1,1,1);
        objGbcInner.setConstraints(-1,0,0,1,1);
        objGbcInner.setInsets(new Insets(1,5,1,5));
        pnlButton.add(btnBack, objGbcInner);
        pnlButton.add(btnDelete, objGbcInner);
        pnlButton.add(btnUpdate, objGbcInner);
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
                final ExamineeRecord objCurrentRecord = objRecord;
                objRecord.setActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openExamineeRecord(objCurrentRecord);
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

    PreparedStatement pstmnt;
    private void loadDb(String strId){
        try{
            conn = DriverManager.getConnection(
                    PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD
            );

            String query = "SELECT examinee_id, last_name, first_name, middle_initial, role " +
                    "FROM examinee WHERE examinee_id " +
                    "LIKE (?)";
            pstmnt = conn.prepareStatement(query);
            pstmnt.setString(1, "%" + strId + "%");
            result = pstmnt.executeQuery();

            if (result.next()){
                do {
                    String strFullName;
                    if (result.getString("middle_initial") != null) {
                        strFullName = result.getString("last_name") + ", " + result.getString("first_name") + " " + result.getString("middle_initial") + ".";
                    } else {
                        strFullName = result.getString("last_name") + ", " + result.getString("first_name");
                    }
                    ExamineeRecord objRecord = new ExamineeRecord(
                            result.getString("examinee_id"),
                            strFullName,
                            result.getString("role"));
                    final ExamineeRecord objCurrentRecord = objRecord;
                    objRecord.setActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            openExamineeRecord(objCurrentRecord);
                        }
                    });
                    append(objRecord);
                }while(result.next());
            }else{
                if(!result.next()){
                    PLabel lbl = new PLabel("No records found", PLabel.HEADING1);
                    lbl.setAlignmentCenter();
                    append(lbl);
                }
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error occured\n" + e, "Unknown Error Occured", JOptionPane.WARNING_MESSAGE);
        }finally {
            try{result.close();}catch (Exception ignored){}
            try{pstmnt.close();}catch (Exception ignored){}
            try{conn.close();}catch (Exception ignored){}
        }
    }

    public void openExamineeRecord(ExamineeRecord objRecord){
        removeAll();
        append(createButtonPanel());
        ExamineeInformationBuilder objInfoBuilder = new ExamineeInformationBuilder(objRecord.getID());
        append(objInfoBuilder.buildExamineeInfoPanel());
        append(objInfoBuilder.buildMedicalConditionPanel());
        append(objInfoBuilder.buildImmunizationPanel());

        for (ActionListener al : btnDelete.getActionListeners()) {
            btnDelete.removeActionListener(al);
        }
        for (ActionListener al : btnUpdate.getActionListeners()) {
            btnUpdate.removeActionListener(al);
        }

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to delete the record of: \n" +
                        objRecord.getID() + " - " + objRecord.getFullName(),
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                );
                if(choice == JOptionPane.YES_OPTION) {
                    if(Examinee.deleteExamineeFromDB(objRecord.getID())){
                        JOptionPane.showMessageDialog(
                                null,
                                "Record Deleted successfully",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else{
                        JOptionPane.showMessageDialog(
                                null,
                                "Record Deletion Failed",
                                "Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    refresh();
                }
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAll();
                UpdateInformation objUpdateInfo = new UpdateInformation(new Examinee(objRecord.getID()));

                for (ActionListener al : objUpdateInfo.getSaveButton().getActionListeners()) {
                    objUpdateInfo.getSaveButton().removeActionListener(al);
                }

                for (ActionListener al : objUpdateInfo.getCancelButton().getActionListeners()) {
                    objUpdateInfo.getCancelButton().removeActionListener(al);
                }

                objUpdateInfo.getSaveButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openExamineeRecord(objRecord);
                    }
                });

                objUpdateInfo.getCancelButton().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openExamineeRecord(objRecord);
                    }
                });

                objUpdateInfo.setButton();

                append(objUpdateInfo);
                revalidate();
                repaint();
            }
        });

        revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> {
            getScrollPane().getVerticalScrollBar().setValue(0);
        });
    }

    private PPanel createButtonPanel(){
        PPanel pnl = new PPanel(new GridBagLayout());
        PGridBagConstraints objGbc = new PGridBagConstraints();

        btnBack.setBackground(PulsePointConstants.DARK_GRAY);
        btnUpdate.setBackground(PulsePointConstants.GREEN);
        btnDelete.setBackground(PulsePointConstants.RED);

        btnBack.setHoverClick();
        btnUpdate.setHoverClick();
        btnDelete.setHoverClick();

        objGbc.setConstraints(-1,0,1,1,1);
        pnl.add(Box.createHorizontalStrut(10), objGbc);
        objGbc.setConstraints(-1,0,0,1,1);
        pnl.add(pnlButton, objGbc);
        return pnl;
    }

    public void refresh(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        removeAll();
        loadDb();
        getScrollPane().getVerticalScrollBar().setValue(0);
        revalidate();
        repaint();
    }

    public static void main(String[] args){
        JFrame frm = new JFrame();
        frm.setSize(1000,800);
        frm.setDefaultCloseOperation(3);

        ExamineeCatalog examineeCatalog = new ExamineeCatalog("1990-43731");
        frm.add(examineeCatalog.getScrollPane());

        frm.setVisible(true);
    }
}
