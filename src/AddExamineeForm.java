import javax.swing.*;
import javax.xml.transform.Result;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;


class ImmunizationFields{
    public final boolean isOthers;
    private final PCheckBox chkVaccineName;
    private final PTextField txtfldDateGiven, txtfldDose, txtfldRemarks;
    private PTextField txtfldOthers;
    ImmunizationFields(String name){
        chkVaccineName = new PCheckBox(name);
        if(name.equals("Others")){
            chkVaccineName.setText("");
            isOthers = true;
            txtfldOthers = new PTextField("Others", 30, PulsePointConstants.GRAY);
        }else {
            isOthers = false;
        }
        txtfldDateGiven = new PTextField("Date Given", 30, PulsePointConstants.GRAY);
        txtfldDose = new PTextField("DOSE Include Quantity", 30, PulsePointConstants.GRAY);
        txtfldRemarks = new PTextField("Remarks", 30, PulsePointConstants.GRAY);
    }

    public String getDateGiven(){return txtfldDateGiven.getText();}
    public String getDose(){return txtfldDose.getText();}
    public String getRemarks(){return txtfldRemarks.getText();}
    public String getOtherName(){return txtfldOthers.getText();}

    public PCheckBox getChkVaccineName(){return chkVaccineName;}
    public PTextField getTxtfldDateGiven(){return txtfldDateGiven;}
    public PTextField getTxtfldDose(){return txtfldDose;}
    public PTextField getTxtfldRemarks(){return txtfldRemarks;}
    public PTextField getTxtfldOthers(){return txtfldOthers;}
    public void clearAll(){
        this.chkVaccineName.setSelected(false);
        this.txtfldOthers.setText("");
        this.txtfldDateGiven.setText("");
        this.txtfldRemarks.setText("");
        this.txtfldDose.setText("");
    }
}



class MedicalConditionFields{
    private final PTextField txtfldConditionName;
    private final PTextField txtfldDateIdentified;
    private final PTextField txtfldMaintenance;
    MedicalConditionFields(){
        txtfldConditionName = new PTextField("Medical Condition", 30, PulsePointConstants.GRAY);
        txtfldDateIdentified = new PTextField("Date Identified", 30, PulsePointConstants.GRAY);
        txtfldMaintenance = new PTextField("Maintenance", 30, PulsePointConstants.GRAY);
    }

    public boolean isValid(){
        return !this.txtfldConditionName.getText().isEmpty();
    }

    public String getName(){
        return txtfldConditionName.getText();
    }

    public String getDate(){
        return txtfldDateIdentified.getText();
    }

    public String getMaintenance(){
        return txtfldMaintenance.getText();
    }

    public PTextField getTxtfldConditionName(){
        return txtfldConditionName;
    }
    public PTextField getTxtfldDateIdentified(){
        return txtfldDateIdentified;
    }
    public PTextField getTxtfldMaintenance(){
        return txtfldMaintenance;
    }

    public void clearAll(){
        this.txtfldConditionName.setText("");
        this.txtfldDateIdentified.setText("");
        this.txtfldMaintenance.setText("");
    }
}

public class AddExamineeForm extends PScrollPanel{
    private final PGridBagConstraints gbcCons = new PGridBagConstraints();
    private final PGridBagConstraints gbcInner = new PGridBagConstraints();

    private final PTextField txtfldExamineeId = new PTextField("Student/Employee no.", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldFirstName = new PTextField("First Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLastName = new PTextField("Last Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldMiddleInitial = new PTextField("Middle Initial", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldDateExam = new PTextField("Exam Date(YYYY-MM-DD)", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldYearExam = new PTextField("Year of Exam(YYYY)", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldAge = new PTextField("Age", 30, PulsePointConstants.GRAY);

    private final PLabel lblSex = new PLabel("Sex");
    private final PRadioButton rdMale = new PRadioButton("Male");
    private final PRadioButton rdFemale = new PRadioButton("Female");
    private final ButtonGroup bgSex = new ButtonGroup();

    private final PTextField txtfldBirthdate = new PTextField("Birthdate (YYYY-MM-DD)", 30, PulsePointConstants.GRAY);
    // Civil Status be radio button
    private final PLabel lblCivilStatus = new PLabel("Civil Status");
    private final PRadioButton rdSingle = new PRadioButton("Single");
    private final PRadioButton rdMarried = new PRadioButton("Married");
    private final PRadioButton rdDivorced = new PRadioButton("Divorced");
    private final PRadioButton rdWidowed = new PRadioButton("Widowed");
    private final PRadioButton rdSeparated = new PRadioButton("Separated");
    private final ButtonGroup bgCivilStatus = new ButtonGroup();

    private final PTextField txtfldDivision = new PTextField("Division / College / Unit", 30, PulsePointConstants.GRAY);

    // Role
    private final PLabel lblRole = new PLabel("Role");
    private final PRadioButton rdStudent = new PRadioButton("Student");
    private final PRadioButton rdEmployee = new PRadioButton("Employee");
    private final ButtonGroup bgRole = new ButtonGroup();


    // Additional Information
    private final PTextField txtfldMobileNumber = new PTextField("Mobile Number", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldNetwork = new PTextField("Network", 30, PulsePointConstants.GRAY);

    private final PCheckBox chkDormInformation = new PCheckBox("Dorm Information (If Applicable)", PulsePointConstants.HEADING2);
    private final PTextField txtfldAddress = new PTextField("Address in Miagao", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordName = new PTextField("Landlord's Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordContact = new PTextField("Landlord's Contact Number", 30, PulsePointConstants.GRAY);

    private final PCheckBox chkGuardianInformation = new PCheckBox("Guardian Information (If Parents are not available)", PulsePointConstants.HEADING2);
    private final PTextField txtfldGuardianName = new PTextField("Guardian's Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianContact = new PTextField("Guardian's Contact Number", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianNetwork = new PTextField("Guardian's Network", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianRelation = new PTextField("Relation w/ guardian", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianAddress = new PTextField("Guardian's Address", 30, PulsePointConstants.GRAY);

    // Checkboxes with Hypertension, Tubercolosis, B.Asthma, Cancer-breast, colon, diabetes mellitus, hepatitis, heart disease, allergies
    private final PCheckBox chkHypertension = new PCheckBox("Hypertension");
    private final PCheckBox chkTuberculosis = new PCheckBox("Tuberculosis");
    private final PCheckBox chkBAsthma = new PCheckBox("B. Asthma");
    private final PCheckBox chkCancer = new PCheckBox("Cancer-breast, colon");
    private final PCheckBox chkDiabetes = new PCheckBox("Diabetes Mellitus");
    private final PCheckBox chkHepatitis = new PCheckBox("Hepatitis");
    private final PCheckBox chkHeartDisease = new PCheckBox("Heart Disease");
    private final PCheckBox chkAllergies = new PCheckBox("Allergies");
    private final PCheckBox chkOthers = new PCheckBox("Others");
    private final PTextField txtfldFamilyIllnessOthers = new PTextField("Others", 30, PulsePointConstants.GRAY);

    private final MedicalConditionFields[] objMedicalConditions = {new MedicalConditionFields(),
                                                new MedicalConditionFields(),
                                                new MedicalConditionFields()};

    private final ImmunizationFields[] objImmunizations = {new ImmunizationFields("MMR"), new ImmunizationFields("Dtap/Tetanus"),
                                        new ImmunizationFields("Varicella"), new ImmunizationFields("Hepatitis B"),
                                        new ImmunizationFields("Influenza"), new ImmunizationFields("Pneumonia"),
                                        new ImmunizationFields("Others")
                                        };

    private final PButton btnAddExaminee = new PButton("Add Examinee to Database");


    AddExamineeForm(){
        setBackground(PulsePointConstants.WHITE);
        setPadding(new Insets(10,10,10,10));

        addBasicInfoFields();
        addDormInfoFields();
        addGuardianInfoFields();
        addFamilyIllnessFields();
        addMedicalConditionFields();
        addImmunizationFields();
        addSaveButton();
    }



    private void addBasicInfoFields(){
        PPanel pnlBasicInfo = createFieldGroupPanel();
        // id, date of exam, year
        // last, first, middle
        // age, sex, role, civil status
        // birthdate, division, mobile number, network
        // Title

        // Row 1
        gbcCons.reset();

        gbcCons.setInsets(new Insets(5,5,5,5));
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);

        gbcCons.gridwidth = 4;
        PLabel lblBasicInfo = new PLabel("Basic Information", PLabel.HEADING2);
        pnlBasicInfo.add(lblBasicInfo, gbcCons);

        gbcCons.gridy++;
        gbcCons.gridwidth = 2;
        pnlBasicInfo.add(txtfldExamineeId, gbcCons);
        gbcCons.gridwidth = 1;
        pnlBasicInfo.add(txtfldDateExam, gbcCons);
        pnlBasicInfo.add(txtfldYearExam, gbcCons);

        // Row 2
        gbcCons.gridwidth = 1;
        gbcCons.gridy++;
        pnlBasicInfo.add(txtfldLastName, gbcCons);
        pnlBasicInfo.add(txtfldFirstName, gbcCons);
        pnlBasicInfo.add(txtfldMiddleInitial, gbcCons);
        pnlBasicInfo.add(txtfldAge, gbcCons);

        // Row 3
        gbcCons.setFill(1);
        PPanel pnlSex = formatPanel();
        bgSex.add(rdMale);
        bgSex.add(rdFemale);
        gbcInner.reset();
        gbcInner.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlSex.add(lblSex, gbcInner);
        gbcInner.setWidth(1);
        gbcInner.setConstraints(-1,1);
        pnlSex.add(rdMale, gbcInner);
        pnlSex.add(rdFemale, gbcInner);
        gbcCons.setConstraints(-1,gbcCons.gridy+1, 0.5,0);
        pnlBasicInfo.add(pnlSex, gbcCons);

        PPanel pnlRole = formatPanel();
        bgRole.add(rdStudent);
        bgRole.add(rdEmployee);
        gbcInner.reset();
        gbcInner.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlRole.add(lblRole,gbcInner);
        gbcInner.setWidth(1);
        gbcInner.gridy++;
        pnlRole.add(rdStudent, gbcInner);
        pnlRole.add(rdEmployee, gbcInner);
        pnlBasicInfo.add(pnlRole,gbcCons);

        PPanel pnlCivilStatus = formatPanel();
        bgCivilStatus.add(rdSingle);
        bgCivilStatus.add(rdMarried);
        bgCivilStatus.add(rdSeparated);
        bgCivilStatus.add(rdWidowed);
        bgCivilStatus.add(rdDivorced);
        gbcInner.reset();
        gbcInner.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlCivilStatus.add(lblCivilStatus,gbcInner);
        gbcInner.setWidth(1);
        gbcInner.gridy++;
        pnlCivilStatus.add(rdSingle, gbcInner);
        pnlCivilStatus.add(rdMarried, gbcInner);
        gbcInner.gridy++;
        pnlCivilStatus.add(rdDivorced, gbcInner);
        pnlCivilStatus.add(rdSeparated, gbcInner);
        pnlCivilStatus.add(rdWidowed, gbcInner);
        gbcCons.gridwidth = 2;
        pnlBasicInfo.add(pnlCivilStatus, gbcCons);
        gbcCons.gridwidth = 1;

        // Row 4
        gbcCons.gridy++;
        gbcCons.gridwidth = 2;
        pnlBasicInfo.add(txtfldBirthdate, gbcCons);
        gbcCons.gridwidth = 1;
        pnlBasicInfo.add(txtfldMobileNumber, gbcCons);
        pnlBasicInfo.add(txtfldNetwork, gbcCons);

        // Row 5
        gbcCons.gridy++;
        gbcCons.gridwidth = 4;
        pnlBasicInfo.add(txtfldDivision, gbcCons);

        append(pnlBasicInfo);
    }

    private final PPanel pnlGuardianInfo = createFieldGroupPanel();
    private void addGuardianInfoFields(){
        //Guardian Name, Relation
        //Guardian Number, Network, Address

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;

        chkGuardianInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkGuardianInformation.isSelected()){
                    gbcCons.gridy++;
                    gbcCons.gridwidth = 1;
                    pnlGuardianInfo.add(txtfldGuardianName, gbcCons);
                    pnlGuardianInfo.add(txtfldGuardianRelation, gbcCons);
                    gbcCons.gridy++;
                    gbcCons.gridwidth = 2;
                    pnlGuardianInfo.add(txtfldGuardianAddress, gbcCons);
                    gbcCons.gridy++;
                    gbcCons.gridwidth = 1;
                    pnlGuardianInfo.add(txtfldGuardianContact, gbcCons);
                    pnlGuardianInfo.add(txtfldGuardianNetwork, gbcCons);

                    revalidate();
                    repaint();
                }else{
                    pnlGuardianInfo.removeAll();
                    gbcCons.reset();
                    gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
                    gbcCons.gridwidth = 2;
                    pnlGuardianInfo.add(chkGuardianInformation, gbcCons);

                    revalidate();
                    repaint();
                }
            }
        });
        pnlGuardianInfo.add(chkGuardianInformation, gbcCons);

        append(pnlGuardianInfo);
    }

    private final PPanel pnlDormInfo = createFieldGroupPanel();
    private void addDormInfoFields(){
        // Address in miagao
        // Landlord name, contact

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;

        chkDormInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkDormInformation.isSelected()){
                    gbcCons.reset();
                    gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
                    gbcCons.gridwidth = 2;
                    gbcCons.gridy++;
                    pnlDormInfo.add(txtfldAddress, gbcCons);
                    gbcCons.gridwidth = 1;
                    gbcCons.gridy++;
                    pnlDormInfo.add(txtfldLandlordName, gbcCons);
                    pnlDormInfo.add(txtfldLandlordContact, gbcCons);

                    repaint();
                    revalidate();
                }else{
                    pnlDormInfo.removeAll();
                    gbcCons.reset();
                    gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
                    gbcCons.gridwidth = 2;
                    pnlDormInfo.add(chkDormInformation, gbcCons);

                    revalidate();
                    repaint();
                }
            }
        });
        pnlDormInfo.add(chkDormInformation, gbcCons);
        append(pnlDormInfo);
    }

    private void addFamilyIllnessFields(){
        PPanel pnlFamilyIllness = createFieldGroupPanel();
        PLabel lblFamilyIllness = new PLabel("Family's History of Illness", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 4;
        pnlFamilyIllness.add(lblFamilyIllness, gbcCons);
        gbcCons.gridwidth = 1;
        gbcCons.gridy++;
        pnlFamilyIllness.add(chkHypertension,gbcCons);
        pnlFamilyIllness.add(chkTuberculosis,gbcCons);
        pnlFamilyIllness.add(chkBAsthma,gbcCons);
        pnlFamilyIllness.add(chkCancer,gbcCons);
        gbcCons.gridy++;
        pnlFamilyIllness.add(chkDiabetes,gbcCons);
        pnlFamilyIllness.add(chkHepatitis,gbcCons);
        pnlFamilyIllness.add(chkHeartDisease,gbcCons);
        pnlFamilyIllness.add(chkAllergies,gbcCons);
        gbcCons.gridy++;

        PPanel pnlOthers = new PPanel(new GridBagLayout());
        pnlOthers.setBackground(null);
        gbcInner.reset();
        gbcInner.setConstraints(-1,-1,0,0,GridBagConstraints.HORIZONTAL);
        pnlOthers.add(chkOthers, gbcInner);
        gbcInner.setConstraints(-1,-1,1,0,GridBagConstraints.HORIZONTAL);
        pnlOthers.add(txtfldFamilyIllnessOthers, gbcInner);

        gbcCons.gridwidth = 4;
        pnlFamilyIllness.add(pnlOthers,gbcCons);

        append(pnlFamilyIllness);
    }

    private void addMedicalConditionFields(){
        // 3 fields each with date identified, maintenance
        PPanel pnlMedicalCondition = createFieldGroupPanel();
        PLabel lblMedicalCondition = new PLabel("Current Medical Condition", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 3;
        pnlMedicalCondition.add(lblMedicalCondition, gbcCons);
        gbcCons.gridy++;
        gbcCons.gridwidth = 1;
        for(int intIndex = 0; intIndex<objMedicalConditions.length; intIndex++){
            pnlMedicalCondition.add(objMedicalConditions[intIndex].getTxtfldConditionName(), gbcCons);
            pnlMedicalCondition.add(objMedicalConditions[intIndex].getTxtfldDateIdentified(), gbcCons);
            pnlMedicalCondition.add(objMedicalConditions[intIndex].getTxtfldMaintenance(), gbcCons);
            gbcCons.gridy++;
        }

        append(pnlMedicalCondition);
    }

    private void addImmunizationFields(){
        // Given Vaccine Fields
        // 1 "Others" field
        // 3 fields each with date identified, maintenance
        PPanel pnlImmunization = createFieldGroupPanel();
        PLabel lblImmunization = new PLabel("Immunizations - Indicate latest booster dose", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 3;
        pnlImmunization.add(lblImmunization, gbcCons);
        gbcCons.gridy++;
        gbcCons.gridwidth = 1;

        for(int intIndex = 0; intIndex<objImmunizations.length; intIndex++){
            if(intIndex == objImmunizations.length-1){
                //Others
                JPanel pnlOthers = new JPanel();
                pnlOthers.setBackground(null);
                pnlOthers.setLayout(new GridBagLayout());

                PGridBagConstraints gbc = new PGridBagConstraints();
                gbc.setConstraints(-1,-1,0,1,0);
                pnlOthers.add(objImmunizations[intIndex].getChkVaccineName(), gbc);
                gbc.setConstraints(-1,-1,1,1,2);
                pnlOthers.add(objImmunizations[intIndex].getTxtfldOthers(), gbc);

                gbcCons.setConstraints(-1,gbcCons.gridy, 0,0, GridBagConstraints.HORIZONTAL);
                pnlImmunization.add(pnlOthers, gbcCons);
                gbcCons.setConstraints(-1,gbcCons.gridy, 0.1,0, GridBagConstraints.HORIZONTAL);

                pnlImmunization.add(objImmunizations[intIndex].getTxtfldDateGiven(), gbcCons);
                pnlImmunization.add(objImmunizations[intIndex].getTxtfldDose(), gbcCons);
                gbcCons.setConstraints(-1,gbcCons.gridy, 1,0, GridBagConstraints.HORIZONTAL);
                pnlImmunization.add(objImmunizations[intIndex].getTxtfldRemarks(), gbcCons);
                gbcCons.gridy++;
                continue;
            }

            gbcCons.setConstraints(-1,gbcCons.gridy, 0,0, GridBagConstraints.HORIZONTAL);
            pnlImmunization.add(objImmunizations[intIndex].getChkVaccineName(), gbcCons);
            gbcCons.setConstraints(-1,gbcCons.gridy, 0.1,0, GridBagConstraints.HORIZONTAL);
            pnlImmunization.add(objImmunizations[intIndex].getTxtfldDateGiven(), gbcCons);
            pnlImmunization.add(objImmunizations[intIndex].getTxtfldDose(), gbcCons);
            gbcCons.setConstraints(-1,gbcCons.gridy, 1,0, GridBagConstraints.HORIZONTAL);
            pnlImmunization.add(objImmunizations[intIndex].getTxtfldRemarks(), gbcCons);
            gbcCons.gridy++;
        }

        append(pnlImmunization);
    }

    private void addSaveButton(){
        PPanel pnlAdd = new PPanel();
        pnlAdd.setLayout(new GridBagLayout());
        btnAddExaminee.setBackground(PulsePointConstants.RED);
        btnAddExaminee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Examinee examinee = extractInfo();
                if(examinee != null) {
                    if(!examinee.addBasicInfoToDB()){
                        JOptionPane.showMessageDialog(
                                null,
                                "Error adding basic Information",
                                "Insert Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    if(!examinee.addMedicalConditionsToDB()){
                        JOptionPane.showMessageDialog(
                                null,
                                "Error adding medical conditions to database",
                                "Insert Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    if(!examinee.addImmunizationsToDB()){
                        JOptionPane.showMessageDialog(
                                null,
                                "Error adding immunizations to database",
                                "Insert Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }

                    resetFormFields();
                    JOptionPane.showMessageDialog(
                            null,
                            "Successfully added to database",
                            "Insert Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });

        pnlAdd.setBackground(null);
        gbcCons.reset();
        gbcCons.setConstraints(-1,-1,0,1,1);
        gbcCons.anchor = GridBagConstraints.WEST;
        pnlAdd.add(btnAddExaminee,gbcCons);
        gbcCons.reset();
        append(pnlAdd);
    }

    private void resetFormFields() {
        // Clear all text fields
        txtfldExamineeId.setText("");
        txtfldFirstName.setText("");
        txtfldLastName.setText("");
        txtfldMiddleInitial.setText("");
        txtfldDateExam.setText("");
        txtfldYearExam.setText("");
        txtfldAge.setText("");
        txtfldBirthdate.setText("");
        txtfldDivision.setText("");
        txtfldMobileNumber.setText("");
        txtfldNetwork.setText("");
        txtfldAddress.setText("");
        txtfldLandlordName.setText("");
        txtfldLandlordContact.setText("");
        txtfldGuardianName.setText("");
        txtfldGuardianContact.setText("");
        txtfldGuardianNetwork.setText("");
        txtfldGuardianRelation.setText("");
        txtfldGuardianAddress.setText("");
        txtfldFamilyIllnessOthers.setText("");

        // Deselect radio buttons via ButtonGroups
        bgSex.clearSelection();
        bgCivilStatus.clearSelection();
        bgRole.clearSelection();

        // Uncheck all checkboxes
        chkDormInformation.setSelected(false);
        chkGuardianInformation.setSelected(false);
        chkHypertension.setSelected(false);
        chkTuberculosis.setSelected(false);
        chkBAsthma.setSelected(false);
        chkCancer.setSelected(false);
        chkDiabetes.setSelected(false);
        chkHepatitis.setSelected(false);
        chkHeartDisease.setSelected(false);
        chkAllergies.setSelected(false);
        chkOthers.setSelected(false);

        for(ImmunizationFields objImmunization : objImmunizations){
            objImmunization.clearAll();
        }

        for(MedicalConditionFields objMedicalCondition : objMedicalConditions){
            objMedicalCondition.clearAll();
        }

    }

    private Examinee extractInfo(){
        PTextField[] objRequiredFields = {txtfldExamineeId, txtfldDateExam, txtfldYearExam, txtfldLastName, txtfldFirstName,
                                        txtfldAge, txtfldBirthdate, txtfldDivision, txtfldMobileNumber, txtfldNetwork};
        for(PTextField txtfld : objRequiredFields){
            if(txtfld.getText().isEmpty()){
                JOptionPane.showMessageDialog(
                        null,
                        txtfld.getName() + " cannot be empty",
                        "Error", JOptionPane.ERROR_MESSAGE
                );
                return null;
            }
        }

        String strId = txtfldExamineeId.getText();

        LocalDate objExamDate;
        try{
            objExamDate = LocalDate.parse(txtfldDateExam.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,"Incorrect Date Format for Exam Date (YYYY-MM-DD)",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }

        int strExamYear;
        try{
            strExamYear = Integer.parseInt(txtfldYearExam.getText());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Incorrect Date Format for Exam Year (YYYY)",
                    "Error", JOptionPane.ERROR_MESSAGE
            );
            return null;
        }

        String strFirstName = txtfldFirstName.getText();
        String strLastName = txtfldLastName.getText();
        String strMiddleInitial = txtfldMiddleInitial.getText();

        int intAge = 0;
        if(txtfldAge.getText().isEmpty()){
            JOptionPane.showMessageDialog(
                    null,
                    "Age field cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }else{
            intAge = Integer.parseInt(txtfldAge.getText());
        }

        String strSex = rdMale.isSelected() ? "M" :
                        rdFemale.isSelected() ? "F" : null;
        if(strSex == null){
            JOptionPane.showMessageDialog(
                    null,
                    "Sex cannot be empty",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return null;
        }

        String strRole = rdStudent.isSelected() ? "Student" :
                        rdEmployee.isSelected() ? "Employee" : null;
        if(strRole == null){
            JOptionPane.showMessageDialog(
                    null,
                    "Role cannot be empty",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
                    );
        }

        String strCivilStatus = rdSingle.isSelected() ? "S" :
                        rdMarried.isSelected() ? "M" :
                        rdDivorced.isSelected() ? "D" :
                        rdSeparated.isSelected() ? "E" :
                        rdWidowed.isSelected() ? "W" : null;
        if(strCivilStatus == null){
            JOptionPane.showMessageDialog(
                    null,
                    "Civil status cannot be empty",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        LocalDate objBirthdate = LocalDate.parse(txtfldBirthdate.getText());
        String strMobileNumber = txtfldMobileNumber.getText();
        String strNetwork = txtfldNetwork.getText();
        String strDivision = txtfldDivision.getText();

        // Dorm
        String strAddress;
        String strLandlordName;
        String strLandlordContact;
        if(chkDormInformation.isSelected()) {
            strAddress = txtfldAddress.getText();
            strLandlordName = txtfldLandlordName.getText();
            strLandlordContact = txtfldLandlordContact.getText();

            if (strAddress.trim().isEmpty() ||
                    strLandlordName.trim().isEmpty() ||
                    strLandlordContact.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Please fill out all dorm information fields.",
                        "Input Error",
                        JOptionPane.WARNING_MESSAGE
                );
                return null; // or break, depending on your flow
            }
        }else{
            strAddress = null;
            strLandlordName = null;
            strLandlordContact = null;
        }
        // Guardian
        String strGuardianName;
        String strGuardianRelation;
        String strGuardianAddress;
        String strGuardianContact;
        String strGuardianNetwork;
        if(chkGuardianInformation.isSelected()) {
            strGuardianName = txtfldGuardianName.getText();
            strGuardianRelation = txtfldGuardianRelation.getText();
            strGuardianAddress = txtfldGuardianAddress.getText();
            strGuardianContact = txtfldGuardianContact.getText();
            strGuardianNetwork = txtfldGuardianNetwork.getText();
            if (strGuardianName.trim().isEmpty() ||
                    strGuardianRelation.trim().isEmpty() ||
                    strGuardianAddress.trim().isEmpty() ||
                    strGuardianContact.trim().isEmpty() ||
                    strGuardianNetwork.trim().isEmpty()) {

                JOptionPane.showMessageDialog(null,
                        "Please fill out all guardian information fields.",
                        "Input Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
                return null;
            }
        }else{
            strGuardianName = null;
            strGuardianRelation = null;
            strGuardianAddress = null;
            strGuardianContact = null;
            strGuardianNetwork = null;
        }
        // Family Illness
        String strFamilyIllness = getFamilyIlnesses();

        // Medical Condition
        ArrayList<MedicalCondition> arrExamineeConditions = new ArrayList<>();
        for(int intIndex = 0; intIndex < objMedicalConditions.length; intIndex++){
            if(objMedicalConditions[intIndex].isValid()){
                arrExamineeConditions.add(new MedicalCondition(objMedicalConditions[intIndex].getName(), objMedicalConditions[intIndex].getDate(), objMedicalConditions[intIndex].getMaintenance()));
            }
        }

        // Immunizations
        // check if date is valid
        ArrayList<Immunization> arrExamineeImmunizations = new ArrayList<>();
        for(ImmunizationFields objImmField : objImmunizations){
            if(objImmField.getChkVaccineName().isSelected() && objImmField.isOthers){
                if(objImmField.getOtherName().isEmpty()){
                    //Throw an error
                    return null;
                }
                if(objImmField.getDateGiven().isEmpty()){
                    //Throw an error
                    return null;
                }
                if(objImmField.getDose().isEmpty()){
                    //Throw an error
                    return null;
                }
                arrExamineeImmunizations.add(new Immunization(objImmField.getTxtfldOthers().getText(), objImmField.getDateGiven(), objImmField.getDose(), objImmField.getRemarks()));
                continue;
            }

            if(objImmField.getChkVaccineName().isSelected()){
                if(objImmField.getDateGiven().isEmpty()){
                    //Throw an error
                    return null;
                }
                if(objImmField.getDose().isEmpty()){
                    //Throw an error
                    return null;
                }
                arrExamineeImmunizations.add(new Immunization(objImmField.getChkVaccineName().getText(), objImmField.getDateGiven(), objImmField.getDose(), objImmField.getRemarks()));
            }
        }

        return new Examinee(
                strId, objExamDate, strExamYear,
                strFirstName, strLastName, strMiddleInitial,
                intAge, strSex, strRole, strCivilStatus,
                objBirthdate, strMobileNumber, strNetwork, strDivision,
                strAddress, strLandlordName, strLandlordContact,
                strGuardianName, strGuardianRelation, strGuardianAddress,
                strGuardianContact, strGuardianNetwork,
                strFamilyIllness,
                arrExamineeConditions,
                arrExamineeImmunizations
        );

    }

    private String getFamilyIlnesses(){
        String strHypertension = chkHypertension.isSelected() ? "Hypertension" : null;
        String strTuberculosis = chkTuberculosis.isSelected() ? "Tuberculosis" : null;
        String strDiabetes = chkDiabetes.isSelected() ? "Diabetes Mellitus" : null;
        String strHepatitis = chkHepatitis.isSelected() ? "Hepatitis" : null;
        String strAsthma = chkBAsthma.isSelected() ? "B. Asthma" : null;
        String strHeartDisease = chkHeartDisease.isSelected() ? "Heart Disease" : null;
        String strCancer = chkCancer.isSelected() ? "Cancer-breast, colon" : null;
        String strAllergies = chkAllergies.isSelected() ? "Allergies" : null;
        String strOthers = chkOthers.isSelected() ? txtfldFamilyIllnessOthers.getText() : null;
        String[] strarrFamilyIllness = Stream.of(
                strHypertension,
                strTuberculosis,
                strDiabetes,
                strHepatitis,
                strAsthma,
                strHeartDisease,
                strCancer,
                strAllergies,
                strOthers
        ).filter(Objects::nonNull).toArray(String[]::new);

        String strFamilyIllness = "";
        if (strarrFamilyIllness.length > 0) {
            strFamilyIllness = String.join(", ", strarrFamilyIllness);
        }
        return strFamilyIllness;
    }

    private PPanel createFieldGroupPanel(){
        PPanel pnl = new PPanel(new GridBagLayout());
        pnl.setBorderColor(PulsePointConstants.BLUE);
        pnl.setPadding(new Insets(10,10,10,10));
        pnl.setBackground(null);
        return pnl;
    }

    private PPanel formatPanel(){
        PPanel pnl = new PPanel(new GridBagLayout());
        pnl.setBorderColor(PulsePointConstants.GRAY);
        pnl.setBackground(Color.WHITE);
        pnl.setOpaque(true);
        pnl.setPadding(10,10,10,10);
        pnl.setRadius(30);
        pnl.setBorderThickness(3);
        return pnl;
    }

    public static void main(String[] args){
        JFrame frm = new JFrame();
        frm.setSize(1000,800);
        frm.setDefaultCloseOperation(3);

        AddExamineeForm examineeForm = new AddExamineeForm();
        frm.add(examineeForm.getScrollPane());

        frm.setVisible(true);
    }
}
