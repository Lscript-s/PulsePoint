import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExamineeInformationBuilder {
    private Connection conn;
    private PreparedStatement stmnt;
    private ResultSet result;
    private ResultSet objExamineeInfo;
    private ResultSet objCurrentMedicalCondition;
    private ResultSet objImmunizationBackground;
    private String strId;
    private Examinee objExaminee;
    ExamineeInformationBuilder(String strId){
        this.strId = strId;
        retrieveInfo();
    }

    private void retrieveInfo(){
        try{
            conn = DriverManager.getConnection(
                    PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD
            );

            stmnt = conn.prepareStatement(
                    "SELECT * FROM examinee" +
                    " WHERE examinee_id = (?)");
            stmnt.setString(1,strId);
            objExamineeInfo = stmnt.executeQuery();


            stmnt = conn.prepareStatement("SELECT * FROM current_medical_condition WHERE examinee_id = (?)");
            stmnt.setString(1, strId);
            objCurrentMedicalCondition = stmnt.executeQuery();

            stmnt = conn.prepareStatement("SELECT * FROM immunization_background WHERE examinee_id = (?)");
            stmnt.setString(1, strId);
            objImmunizationBackground = stmnt.executeQuery();

            objExaminee = new Examinee(objExamineeInfo, objCurrentMedicalCondition, objImmunizationBackground);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "An error occured: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }finally {
            try {
                objExamineeInfo.close();
                objImmunizationBackground.close();
                objCurrentMedicalCondition.close();
            } catch (Exception ignored){}
            try {stmnt.close();} catch (Exception ignored){}
            try {conn.close();} catch (Exception ignored){}
        }
    }

    private PPanel buildIllnessGroupPanel(String heading, String illnessText) {
        PPanel pnlGroup = new PPanel(new GridBagLayout());
        pnlGroup.setBackground(PulsePointConstants.WHITE);
        pnlGroup.setBorderColor(PulsePointConstants.BLUE);
        pnlGroup.setPadding(5, 10, 5, 10);

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.setConstraints(0, 0, 1, 0, 1);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Heading label
        PLabel lblHeading = new PLabel(heading, PLabel.HEADING2);
        pnlGroup.add(lblHeading, gbc);
        gbc.gridy++;

        // Label and TextArea in one row
        PPanel pnlRow = new PPanel(new GridBagLayout());
        PGridBagConstraints innerGbc = new PGridBagConstraints();
        pnlRow.setBackground(PulsePointConstants.WHITE);

        innerGbc.setConstraints(-1,-1,1,1,1);
        PLabel lblField = new PLabel("Illness: ", true);
        lblField.setVerticalTextPosition(PLabel.NORTH);
        JTextArea txtIllness = new JTextArea(illnessText);
        txtIllness.setLineWrap(true);
        txtIllness.setWrapStyleWord(true);
        txtIllness.setEditable(false);
        txtIllness.setOpaque(false);
        txtIllness.setBorder(null);
        txtIllness.setFont(lblField.getFont().deriveFont(Font.PLAIN)); // Match font with labels
        txtIllness.setColumns(40); // adjust width
        txtIllness.setRows(1);     // adjust height
        txtIllness.setFocusable(false);

        pnlRow.add(lblField,innerGbc);
        pnlRow.add(txtIllness,innerGbc);

        pnlGroup.add(pnlRow, gbc);

        return pnlGroup;
    }

    private PPanel buildGroupPanel(String heading, Map<String, String> fields) {
        PPanel pnlGroup = new PPanel(new GridBagLayout());
        pnlGroup.setBackground(PulsePointConstants.WHITE);
        pnlGroup.setBorderColor(PulsePointConstants.BLUE);
        pnlGroup.setPadding(5, 10, 5, 10);

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.setConstraints(0, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Heading label
        pnlGroup.add(new PLabel(heading, PLabel.HEADING2), gbc);
        gbc.gridy++;

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            // Create horizontal subpanel for label and value side-by-side
            PPanel pnlRow = new PPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
            pnlRow.setBackground(PulsePointConstants.WHITE);
            PLabel lblField = new PLabel(entry.getKey() + ":", true);
            PLabel lblValue = new PLabel(entry.getValue());

            pnlRow.add(lblField);
            pnlRow.add(lblValue);

            pnlGroup.add(pnlRow, gbc);
            gbc.gridy++;
        }

        return pnlGroup;
    }

    public PPanel buildExamineeInfoPanel() {
        PPanel pnlExamineeInfo = new PPanel();
        pnlExamineeInfo.setBackground(PulsePointConstants.WHITE);
        pnlExamineeInfo.setLayout(new BoxLayout(pnlExamineeInfo, BoxLayout.Y_AXIS));
        pnlExamineeInfo.setPadding(5, 5, 5, 5);

        java.util.function.Function<String, String> safeStr = s -> (s == null || s.trim().isEmpty()) ? "N/A" : s;

        String birthdateStr = "N/A";
        if (objExaminee.getObjBirthdate() != null) {
            birthdateStr = objExaminee.getObjBirthdate().toString();
        }

        Map<String, String> basicFields = new LinkedHashMap<>();
        basicFields.put("ID Number", safeStr.apply(objExaminee.getStrId()));
        basicFields.put("Name", safeStr.apply(objExaminee.getFullName()));
        basicFields.put("Role", safeStr.apply(objExaminee.getStrRole()));
        basicFields.put("Age", String.valueOf(objExaminee.getIntAge()));
        basicFields.put("Sex", safeStr.apply(objExaminee.getStrSex()));
        basicFields.put("Civil Status", safeStr.apply(objExaminee.getStrCivilStatus()));
        basicFields.put("Birthdate", birthdateStr);
        basicFields.put("Mobile Number", safeStr.apply(objExaminee.getStrMobileNumber()));
        basicFields.put("Network", safeStr.apply(objExaminee.getStrNetwork()));
        basicFields.put("Division", safeStr.apply(objExaminee.getStrDivision()));

        Map<String, String> dormFields = new LinkedHashMap<>();
        dormFields.put("Address", safeStr.apply(objExaminee.getStrAddress()));
        dormFields.put("Landlord Name", safeStr.apply(objExaminee.getStrLandlordName()));
        dormFields.put("Landlord Contact", safeStr.apply(objExaminee.getStrLandlordContact()));

        Map<String, String> guardianFields = new LinkedHashMap<>();
        guardianFields.put("Guardian Name", safeStr.apply(objExaminee.getStrGuardianName()));
        guardianFields.put("Relation", safeStr.apply(objExaminee.getStrGuardianRelation()));
        guardianFields.put("Address", safeStr.apply(objExaminee.getStrGuardianAddress()));
        guardianFields.put("Contact", safeStr.apply(objExaminee.getStrGuardianContact()));
        guardianFields.put("Network", safeStr.apply(objExaminee.getStrGuardianNetwork()));

        Map<String, String> familyIllnessFields = new LinkedHashMap<>();
        familyIllnessFields.put("Illness", safeStr.apply(objExaminee.getFormattedStrFamilyIllness(20)));

        pnlExamineeInfo.add(buildGroupPanel("Basic Information", basicFields));
        pnlExamineeInfo.add(Box.createVerticalStrut(10));
        pnlExamineeInfo.add(buildGroupPanel("Dorm Information", dormFields));
        pnlExamineeInfo.add(Box.createVerticalStrut(10));
        pnlExamineeInfo.add(buildGroupPanel("Guardian Information", guardianFields));
        pnlExamineeInfo.add(Box.createVerticalStrut(10));
        pnlExamineeInfo.add(buildIllnessGroupPanel("Family Illness", safeStr.apply(objExaminee.getStrFamilyIllness())));
        pnlExamineeInfo.setBackground(PulsePointConstants.WHITE);
        return pnlExamineeInfo;
    }

    private PPanel buildSingleImmunizationPanel(Immunization immun) {
        PGridBagConstraints objGbcInner = new PGridBagConstraints();
        PPanel pnlRecord = new PPanel(new GridBagLayout());
        pnlRecord.setBackground(PulsePointConstants.WHITE);
        pnlRecord.setBorderColor(PulsePointConstants.BLUE);
        pnlRecord.setBorderThickness(2);
        pnlRecord.setPadding(new Insets(5, 5, 5, 5));

        int row = 0;
        java.util.function.Function<String, String> safeVal = v -> (v == null || v.isBlank()) ? "N/A" : v;

        // Name
        objGbcInner.setConstraints(0, row, 0, 0, GridBagConstraints.NONE);
        pnlRecord.add(new PLabel("Name:", true), objGbcInner);
        objGbcInner.gridx = 1;
        pnlRecord.add(new PLabel(safeVal.apply(immun.strName)), objGbcInner);

        // Date
        objGbcInner.gridy = ++row;
        objGbcInner.gridx = 0;
        pnlRecord.add(new PLabel("Date:", true), objGbcInner);
        objGbcInner.gridx = 1;
        pnlRecord.add(new PLabel(safeVal.apply(immun.strDate)), objGbcInner);

        // Dose
        objGbcInner.gridy = ++row;
        objGbcInner.gridx = 0;
        pnlRecord.add(new PLabel("Dose:", true), objGbcInner);
        objGbcInner.gridx = 1;
        pnlRecord.add(new PLabel(safeVal.apply(immun.strDose)), objGbcInner);

        // Remarks
        objGbcInner.gridy = ++row;
        objGbcInner.gridx = 0;
        pnlRecord.add(new PLabel("Remarks:", true), objGbcInner);
        objGbcInner.gridx = 1;
        pnlRecord.add(new PLabel(safeVal.apply(immun.strRemarks)), objGbcInner);

        return pnlRecord;
    }

    public PPanel buildMedicalConditionPanel() {
        PGridBagConstraints objGbc = new PGridBagConstraints();
        PPanel pnlMedicalCondition = new PPanel(new GridBagLayout());
        pnlMedicalCondition.setBackground(PulsePointConstants.WHITE);
        pnlMedicalCondition.setPadding(5, 5, 5, 5);
        pnlMedicalCondition.setBorderColor(PulsePointConstants.GRAY);

        objGbc.setConstraints(-1, 0, 1, 0, 2);
        objGbc.setInsets(new Insets(5, 5, 5, 5));
        pnlMedicalCondition.add(new PLabel("Current Medical Condition", PLabel.HEADING1), objGbc);

        objGbc.setConstraints(-1, 1, 1, 1, 1);
        PGridBagConstraints objGbcInner = new PGridBagConstraints();
        objGbcInner.setConstraints(-1, 0, 1, 0, 1);
        objGbcInner.setInsets(new Insets(0, 0, 0, 5));
        objGbcInner.anchor = GridBagConstraints.NORTH;

        // Check if there are no medical conditions
        if (objExaminee.getArrMedicalConditions() == null || objExaminee.getArrMedicalConditions().isEmpty()) {
            pnlMedicalCondition.add(new PLabel("No medical condition recorded."), objGbc);
            objGbc.gridy++; // increment so the spacer is positioned properly
        } else {
            for (MedicalCondition objConditon : objExaminee.getArrMedicalConditions()) {
                PPanel pnlCondition = new PPanel(new GridBagLayout());
                pnlCondition.setBackground(PulsePointConstants.WHITE);
                pnlCondition.setBorderColor(PulsePointConstants.BLUE);
                pnlCondition.setBorderThickness(2);
                pnlCondition.setPadding(new Insets(5, 5, 5, 5));
                PLabel lblConditionName = new PLabel("Condition Name: ", true);
                PLabel lblIdentifiedOn = new PLabel("Condition Identified On: ", true);
                PLabel lblMaintenance = new PLabel("Condition Maintenance: ", true);

                objGbcInner.weightx = 0;
                pnlCondition.add(lblConditionName, objGbcInner);
                objGbcInner.weightx = 1;
                pnlCondition.add(new PLabel(objConditon.name), objGbcInner);
                objGbcInner.gridy++;

                objGbcInner.weightx = 0;
                pnlCondition.add(lblIdentifiedOn, objGbcInner);
                objGbcInner.weightx = 1;
                if (objConditon.date != null) {
                    pnlCondition.add(new PLabel(objConditon.date), objGbcInner);
                } else {
                    pnlCondition.add(new PLabel("No date recorded"), objGbcInner);
                }

                objGbcInner.gridy++;
                objGbcInner.weightx = 0;
                pnlCondition.add(lblMaintenance, objGbcInner);
                objGbcInner.weightx = 1;
                if (objConditon.maintenance != null) {
                    pnlCondition.add(new PLabel(objConditon.maintenance), objGbcInner);
                } else {
                    pnlCondition.add(new PLabel("No record of maintenance"), objGbcInner);
                }

                pnlMedicalCondition.add(pnlCondition, objGbc);
                objGbc.gridy++;
            }
        }

        objGbc.gridy++;
        objGbc.weighty = 1;
        objGbc.fill = GridBagConstraints.VERTICAL;
        pnlMedicalCondition.add(Box.createHorizontalStrut(50), objGbc);

        return pnlMedicalCondition;
    }

    public PPanel buildImmunizationPanel() {
        PGridBagConstraints gbc = new PGridBagConstraints();
        PPanel pnlImmunization = new PPanel(new GridBagLayout());
        pnlImmunization.setBackground(PulsePointConstants.WHITE);
        pnlImmunization.setPadding(5, 5, 5, 5);
        pnlImmunization.setBorderColor(PulsePointConstants.GRAY);

        // Heading label
        gbc.setConstraints(-1, 0, 1, 0, 2);
        gbc.setInsets(new Insets(5, 5, 5, 5));
        pnlImmunization.add(new PLabel("Immunization Background", PLabel.HEADING1), gbc);

        // Inner panel for immunization entries
        gbc.setConstraints(-1, 1, 1, 1, 1);
        PGridBagConstraints gbcInner = new PGridBagConstraints();
        gbcInner.setConstraints(-1, 0, 1, 0, 1);
        gbcInner.setInsets(new Insets(0, 0, 0, 5));
        gbcInner.anchor = GridBagConstraints.NORTH;

        if (objExaminee.getArrImmunizations() == null || objExaminee.getArrImmunizations().isEmpty()) {
            pnlImmunization.add(new PLabel("No immunization records available"), gbc);
        } else {
            for (Immunization immun : objExaminee.getArrImmunizations()) {
                PPanel pnlImmun = new PPanel(new GridBagLayout());
                pnlImmun.setBorderColor(PulsePointConstants.BLUE);
                pnlImmun.setBorderThickness(2);
                pnlImmun.setPadding(new Insets(5, 5, 5, 5));

                PLabel lblName = new PLabel("Name: ", true);
                PLabel lblDate = new PLabel("Date: ", true);
                PLabel lblDose = new PLabel("Dose: ", true);
                PLabel lblRemarks = new PLabel("Remarks: ", true);

                // Row 0 - Name
                gbcInner.weightx = 0;
                pnlImmun.add(lblName, gbcInner);
                gbcInner.weightx = 1;
                pnlImmun.add(new PLabel(immun.strName == null || immun.strName.trim().isEmpty() ? "N/A" : immun.strName), gbcInner);
                gbcInner.gridy++;

                // Row 1 - Date
                gbcInner.weightx = 0;
                pnlImmun.add(lblDate, gbcInner);
                gbcInner.weightx = 1;
                String date = (immun.strDate == null || immun.strDate.trim().isEmpty()) ? "N/A" : immun.strDate;
                pnlImmun.add(new PLabel(date), gbcInner);
                gbcInner.gridy++;

                // Row 2 - Dose
                gbcInner.weightx = 0;
                pnlImmun.add(lblDose, gbcInner);
                gbcInner.weightx = 1;
                pnlImmun.add(new PLabel(immun.strDose == null || immun.strDose.trim().isEmpty() ? "N/A" : immun.strDose), gbcInner);
                gbcInner.gridy++;

                // Row 3 - Remarks
                gbcInner.weightx = 0;
                pnlImmun.add(lblRemarks, gbcInner);
                gbcInner.weightx = 1;
                pnlImmun.add(new PLabel(immun.strRemarks == null || immun.strRemarks.trim().isEmpty() ? "N/A" : immun.strRemarks), gbcInner);
                gbcInner.gridy = 0; // reset for next panel

                pnlImmunization.add(pnlImmun, gbc);
                gbc.gridy++;
            }
        }

        // Spacer at the bottom
        gbc.gridy++;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        pnlImmunization.add(Box.createHorizontalStrut(50), gbc);

        return pnlImmunization;
    }



    public static void main(String[] args) {
        JFrame frm = new JFrame();
        frm.setDefaultCloseOperation(3);
        frm.setLayout(new GridLayout(3,1));
        frm.setSize(500,500);
        ExamineeInformationBuilder e = new ExamineeInformationBuilder("1991-29885");
        frm.add(e.buildExamineeInfoPanel());
        frm.add(e.buildMedicalConditionPanel());

        frm.setVisible(true);
    }
}
