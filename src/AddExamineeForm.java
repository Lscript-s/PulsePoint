import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

class ImmunizationFields{
    private final PCheckBox chkVaccineName;
    private final PTextField txtfldDateGiven, txtfldDose, txtfldRemarks;
    ImmunizationFields(String name){
        chkVaccineName = new PCheckBox(name);
        txtfldDateGiven = new PTextField("Date Given", 30, PulsePointConstants.GRAY);
        txtfldDose = new PTextField("DOSE Include Quantity", 30, PulsePointConstants.GRAY);
        txtfldRemarks = new PTextField("Remarks", 30, PulsePointConstants.GRAY);
    }

    public String getDateGiven(){return txtfldDateGiven.getText();}
    public String getDose(){return txtfldDose.getText();}
    public String getRemarks(){return txtfldRemarks.getText();}

    public PCheckBox getChkVaccineName(){return chkVaccineName;}
    public PTextField getTxtfldDateGiven(){return txtfldDateGiven;}
    public PTextField getTxtfldDose(){return txtfldDose;}
    public PTextField getTxtfldRemarks(){return txtfldRemarks;}

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
        return !this.txtfldConditionName.getText().isEmpty() && !this.txtfldDateIdentified.getText().isEmpty();
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

}

class MedicalCondition{
    public final String name;
    public final String date;
    public final String maintenance;

    MedicalCondition(String name, String date, String maintenance){
        this.name = name;
        this.date = date;
        this.maintenance = maintenance;
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
    private final PTextField txtfldAddress = new PTextField("Address in Miagao", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordName = new PTextField("Landlord's Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordContact = new PTextField("Landlord's Contact Number", 30, PulsePointConstants.GRAY);
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
        // id, date of exam
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
        pnlBasicInfo.add(txtfldDateExam, gbcCons);

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

    private void addGuardianInfoFields(){
        //Guardian Name, Relation
        //Guardian Number, Network, Address

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        PPanel pnlGuardianInfo = createFieldGroupPanel();

        PLabel lblGuardianInfo = new PLabel("Guardian Information (If Parents are not available)", PLabel.HEADING2);
        gbcCons.gridwidth = 2;
        pnlGuardianInfo.add(lblGuardianInfo, gbcCons);
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

        append(pnlGuardianInfo);
    }

    private void addDormInfoFields(){
        // Address in miagao
        // Landlord name, contact
        PPanel pnlDormInfo = createFieldGroupPanel();
        PLabel lblDormInfo = new PLabel("Dorm Information", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;
        pnlDormInfo.add(lblDormInfo, gbcCons);
        gbcCons.gridy++;
        pnlDormInfo.add(txtfldAddress, gbcCons);
        gbcCons.gridwidth = 1;
        gbcCons.gridy++;
        pnlDormInfo.add(txtfldLandlordName, gbcCons);
        pnlDormInfo.add(txtfldLandlordContact, gbcCons);

        append(pnlDormInfo);
    }

    private void addFamilyIllnessFields(){
        PPanel pnlFamilyIllness = createFieldGroupPanel();
        PLabel lblFamilyIllness = new PLabel("Family's History of Illness", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 4;
        pnlFamilyIllness.add(lblFamilyIllness);
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
        btnAddExaminee.setBackground(PulsePointConstants.PINK);
        btnAddExaminee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToDatabase();
            }
        });
        append(btnAddExaminee);
    }

    private void addToDatabase(){
        // Basic info
        String strId = txtfldExamineeId.getText();
        String strExamDate = txtfldDateExam.getText();
        String strFirstName = txtfldFirstName.getText();
        String strLastName = txtfldLastName.getText();
        String strMiddleInitial = txtfldMiddleInitial.getText();
        Integer intAge = txtfldAge.getText().isEmpty() ? null : Integer.parseInt(txtfldAge.getText());
        String strSex = rdMale.isSelected() ? "M" :
                        rdFemale.isSelected() ? "F" : null;
        String strRole = rdStudent.isSelected() ? "Student" :
                        rdEmployee.isSelected() ? "Employee" : null;
        String strCivilStatus = rdSingle.isSelected() ? "S" :
                        rdMarried.isSelected() ? "M" :
                        rdDivorced.isSelected() ? "D" :
                        rdSeparated.isSelected() ? "E" :
                        rdWidowed.isSelected() ? "W" : null;
        String strBirthdate = txtfldBirthdate.getText();
        String strMobileNumber = txtfldMobileNumber.getText();
        String strNetwork = txtfldNetwork.getText();
        String strDivistion = txtfldDivision.getText();

        // Dorm
        String strAddress = txtfldAddress.getText();
        String strLandlordName = txtfldLandlordName.getText();
        String strLandlordContact = txtfldLandlordContact.getText();

        // Guardian
        String strGuardianName = txtfldGuardianName.getText();
        String strGuardianRelation = txtfldGuardianRelation.getText();
        String strGuardianAddress = txtfldGuardianAddress.getText();
        String strGuardianContact = txtfldGuardianContact.getText();
        String strGuardianNetwork = txtfldGuardianNetwork.getText();

        // Family Illness
        String strFamilyIllness = getFamilyIlnesses();

        // Medical Condition
        ArrayList<MedicalCondition> arrExamineeConditions = new ArrayList<>();
        for(int intIndex = 0; intIndex < objMedicalConditions.length; intIndex++){
            if(objMedicalConditions[intIndex].isValid()){
                arrExamineeConditions.add(new MedicalCondition(objMedicalConditions[intIndex].getName(), objMedicalConditions[intIndex].getDate(), objMedicalConditions[intIndex].getMaintenance()));
            }
        }
        


        System.out.println(strFamilyIllness);
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
