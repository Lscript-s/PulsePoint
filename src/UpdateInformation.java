import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class UpdateInformation extends PScrollPanel {
    private final PGridBagConstraints gbcCons = new PGridBagConstraints();
    private final PGridBagConstraints gbcInner = new PGridBagConstraints();

    private Examinee objExaminee;

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

    private final PLabel lblCivilStatus = new PLabel("Civil Status");
    private final PRadioButton rdSingle = new PRadioButton("Single");
    private final PRadioButton rdMarried = new PRadioButton("Married");
    private final PRadioButton rdDivorced = new PRadioButton("Divorced");
    private final PRadioButton rdWidowed = new PRadioButton("Widowed");
    private final PRadioButton rdSeparated = new PRadioButton("Separated");
    private final ButtonGroup bgCivilStatus = new ButtonGroup();

    private final PTextField txtfldDivision = new PTextField("Division / College / Unit", 30, PulsePointConstants.GRAY);

    private final PLabel lblRole = new PLabel("Role");
    private final PRadioButton rdStudent = new PRadioButton("Student");
    private final PRadioButton rdEmployee = new PRadioButton("Employee");
    private final ButtonGroup bgRole = new ButtonGroup();

    private final PTextField txtfldMobileNumber = new PTextField("Mobile Number", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldNetwork = new PTextField("Network", 30, PulsePointConstants.GRAY);

    private final PCheckBox chkDormInformation = new PCheckBox("Dorm Information (If Applicable)", PulsePointConstants.HEADING2);
    private final PPanel pnlDormInfo = createFieldGroupPanel();
    private final PTextField txtfldAddress = new PTextField("Address in Miagao", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordName = new PTextField("Landlord's Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldLandlordContact = new PTextField("Landlord's Contact Number", 30, PulsePointConstants.GRAY);

    private final PCheckBox chkGuardianInformation = new PCheckBox("Guardian Information (If Parents are not available)", PulsePointConstants.HEADING2);
    private final PPanel pnlGuardianInfo = createFieldGroupPanel();
    private final PTextField txtfldGuardianName = new PTextField("Guardian's Name", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianContact = new PTextField("Guardian's Contact Number", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianNetwork = new PTextField("Guardian's Network", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianRelation = new PTextField("Relation w/ guardian", 30, PulsePointConstants.GRAY);
    private final PTextField txtfldGuardianAddress = new PTextField("Guardian's Address", 30, PulsePointConstants.GRAY);

    private final PCheckBox chkHypertension = new PCheckBox("Hypertension");
    private final PCheckBox chkTuberculosis = new PCheckBox("Tuberculosis");
    private final PCheckBox chkBAsthma = new PCheckBox("B. Asthma");
    private final PCheckBox chkCancer = new PCheckBox("Cancer-breast, colon");
    private final PCheckBox chkDiabetes = new PCheckBox("Diabetes Mellitus");
    private final PCheckBox chkHepatitis = new PCheckBox("Hepatitis");
    private final PCheckBox chkHeartDisease = new PCheckBox("Heart Disease");
    private final PCheckBox chkAllergies = new PCheckBox("Allergies");
    private final PCheckBox chkOthers = new PCheckBox();
    private final PTextField txtfldFamilyIllnessOthers = new PTextField("Others", 30, PulsePointConstants.GRAY);

    private final MedicalConditionFields[] objMedicalConditions ={
            new MedicalConditionFields(),
            new MedicalConditionFields(),
            new MedicalConditionFields()
    };

    private final ImmunizationFields[] objImmunizations = {
            new ImmunizationFields("MMR"),
            new ImmunizationFields("Dtap/Tetanus"),
            new ImmunizationFields("Varicella"),
            new ImmunizationFields("Hepatitis B"),
            new ImmunizationFields("Influenza"),
            new ImmunizationFields("Pneumonia"),
            new ImmunizationFields("Others")
    };

    private final PButton btnSave = new PButton("Save");
    private final PButton btnCancel = new PButton("Cancel");

    public UpdateInformation(Examinee examinee) {
        this.objExaminee = examinee;
        getScrollPane().getVerticalScrollBar().setValue(0);
        System.out.println(examinee.getStrId());
        setBackground(PulsePointConstants.WHITE);
        setPadding(new Insets(10, 10, 10, 10));

        addBasicInfoFields();
        addDormInfoFields();
        addGuardianInfoFields();
        addFamilyIllnessFields();
        addMedicalConditionFields();
        addImmunizationFields();
        addButton();
        setButton();
        fillFields(examinee);
    }

    private void fillFields(Examinee e){
        txtfldExamineeId.setText(e.getStrId());
        txtfldFirstName.setText(e.getStrFirstName());
        txtfldLastName.setText(e.getStrLastName());
        txtfldMiddleInitial.setText(e.getStrMiddleInitial()!=null ? e.getStrMiddleInitial() : "");
        txtfldAge.setText(String.valueOf(e.getIntAge()));
        txtfldDateExam.setText(e.getObjExamDate()!=null ? e.getObjExamDate().toString() : "");
        txtfldYearExam.setText(String.valueOf(e.getIntExamYear()));
        txtfldBirthdate.setText(e.getObjBirthdate()!=null ? e.getObjBirthdate().toString() : "");
        txtfldDivision.setText(e.getStrDivision());
        txtfldMobileNumber.setText(e.getStrMobileNumber());
        txtfldNetwork.setText(e.getStrNetwork());

        if("M".equalsIgnoreCase(e.getStrSex()) || "Male".equalsIgnoreCase(e.getStrSex())){
            rdMale.setSelected(true);
        } else if("F".equalsIgnoreCase(e.getStrSex()) || "Female".equalsIgnoreCase(e.getStrSex())){
            rdFemale.setSelected(true);
        }

        String civil = e.getStrCivilStatus();
        if("S".equalsIgnoreCase(civil) || "Single".equalsIgnoreCase(civil)) rdSingle.setSelected(true);
        else if("M".equalsIgnoreCase(civil) || "Married".equalsIgnoreCase(civil)) rdMarried.setSelected(true);
        else if("D".equalsIgnoreCase(civil) || "Divorced".equalsIgnoreCase(civil)) rdDivorced.setSelected(true);
        else if("W".equalsIgnoreCase(civil) || "Widowed".equalsIgnoreCase(civil)) rdWidowed.setSelected(true);
        else if("E".equalsIgnoreCase(civil) || "Separated".equalsIgnoreCase(civil)) rdSeparated.setSelected(true);

        if("Student".equalsIgnoreCase(e.getStrRole())) rdStudent.setSelected(true);
        else if("Employee".equalsIgnoreCase(e.getStrRole())) rdEmployee.setSelected(true);

        if(e.getStrAddress()!=null && !e.getStrAddress().isEmpty()){
            chkDormInformation.setSelected(true);
            txtfldAddress.setText(e.getStrAddress());
            txtfldLandlordName.setText(e.getStrLandlordName());
            txtfldLandlordContact.setText(e.getStrLandlordContact());
            expandDormInfo(true);
        } else {
            chkDormInformation.setSelected(false);
            expandDormInfo(false);
        }

        if(e.getStrGuardianName()!=null && !e.getStrGuardianName().isEmpty()){
            chkGuardianInformation.setSelected(true);
            txtfldGuardianName.setText(e.getStrGuardianName());
            txtfldGuardianRelation.setText(e.getStrGuardianRelation());
            txtfldGuardianAddress.setText(e.getStrGuardianAddress());
            txtfldGuardianContact.setText(e.getStrGuardianContact());
            txtfldGuardianNetwork.setText(e.getStrGuardianNetwork());
            expandGuardianInfo(true);
        } else {
            chkGuardianInformation.setSelected(false);
            expandGuardianInfo(false);
        }

        fillFamilyIllness(e.getStrFamilyIllness());

        ArrayList<MedicalCondition> mcs = e.getArrMedicalConditions();
        for(int i=0; i< objMedicalConditions.length; i++){
            if(mcs!=null && i<mcs.size()){
                MedicalCondition mc = mcs.get(i);
                objMedicalConditions[i].getTxtfldConditionName().setText(mc.name);
                objMedicalConditions[i].getTxtfldDateIdentified().setText(mc.date != null ? mc.date : "");
                objMedicalConditions[i].getTxtfldMaintenance().setText(mc.maintenance != null ? mc.maintenance : "");
            } else {
                objMedicalConditions[i].clearAll();
            }
        }

        ArrayList<Immunization> ims = e.getArrImmunizations();

        for (Immunization im : ims) {
            boolean found = false;

            for (ImmunizationFields immField : objImmunizations) {
                PCheckBox chk = immField.getChkVaccineName();
                if (!immField.isOthers && chk.getText().equalsIgnoreCase(im.strName)) {
                    chk.setSelected(true);
                    immField.getTxtfldDateGiven().setText(im.strDate != null ? im.strDate : "");
                    immField.getTxtfldDose().setText(im.strDose != null ? im.strDose : "");
                    immField.getTxtfldRemarks().setText(im.strRemarks != null ? im.strRemarks : "");
                    immField.getTxtfldDateGiven().setColumns(7);
                    immField.getTxtfldRemarks().setColumns(5);
                    found = true;
                    break;
                }
            }

            if (!found) {
                for (ImmunizationFields immField : objImmunizations) {
                    if (immField.isOthers) {
                        PCheckBox chk = immField.getChkVaccineName();
                        chk.setSelected(true);
                        if (immField.getTxtfldOthers() != null) {
                            immField.getTxtfldOthers().setText(im.strName);
                            immField.getTxtfldOthers().setColumns(5);
                        }
                        immField.getTxtfldDateGiven().setText(im.strDate != null ? im.strDate : "");
                        immField.getTxtfldDose().setText(im.strDose != null ? im.strDose : "");
                        immField.getTxtfldRemarks().setText(im.strRemarks != null ? im.strRemarks : "");
                        immField.getTxtfldDateGiven().setColumns(7);
                        immField.getTxtfldRemarks().setColumns(5);
                        break; // use only one "Others" slot
                    }
                }
            }
        }
    }

    private void fillFamilyIllness(String strIllness){
        chkHypertension.setSelected(false);
        chkTuberculosis.setSelected(false);
        chkBAsthma.setSelected(false);
        chkCancer.setSelected(false);
        chkDiabetes.setSelected(false);
        chkHepatitis.setSelected(false);
        chkHeartDisease.setSelected(false);
        chkAllergies.setSelected(false);
        chkOthers.setSelected(false);
        txtfldFamilyIllnessOthers.setText("");

        if(strIllness == null || strIllness.isEmpty()) return;

        String[] illnesses = strIllness.split(",\\s*");
        for(String illness : illnesses){
            switch(illness){
                case "Hypertension" -> chkHypertension.setSelected(true);
                case "Tuberculosis" -> chkTuberculosis.setSelected(true);
                case "B. Asthma" -> chkBAsthma.setSelected(true);
                case "Cancer-breast, colon" -> chkCancer.setSelected(true);
                case "Diabetes Mellitus" -> chkDiabetes.setSelected(true);
                case "Hepatitis" -> chkHepatitis.setSelected(true);
                case "Heart Disease" -> chkHeartDisease.setSelected(true);
                case "Allergies" -> chkAllergies.setSelected(true);
                default -> {
                    chkOthers.setSelected(true);
                    txtfldFamilyIllnessOthers.setText(illness);
                }
            }
        }
    }

    private void expandDormInfo(boolean expand){
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
        revalidate();
        repaint();
    }

    private void expandGuardianInfo(boolean expand){
        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;
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
            }else{
                pnlGuardianInfo.removeAll();
                gbcCons.reset();
                gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
                gbcCons.gridwidth = 2;
                pnlGuardianInfo.add(chkGuardianInformation, gbcCons);
            }
        revalidate();
        repaint();
    }

    private void addBasicInfoFields(){
        PPanel pnlBasicInfo = createFieldGroupPanel();
        txtfldExamineeId.setEnabled(false);
        gbcCons.reset();

        gbcCons.setInsets(new Insets(5,5,5,5));
        gbcCons.setConstraints(-1,0,1,0, GridBagConstraints.HORIZONTAL);

        gbcCons.gridwidth = 4;
        pnlBasicInfo.add(new PLabel("Basic Information", PLabel.HEADING2), gbcCons);

        gbcCons.gridy++;
        gbcCons.gridwidth = 2;
        pnlBasicInfo.add(txtfldExamineeId, gbcCons);
        gbcCons.gridwidth = 1;
        pnlBasicInfo.add(txtfldDateExam, gbcCons);
        pnlBasicInfo.add(txtfldYearExam, gbcCons);

        gbcCons.gridwidth = 1;
        gbcCons.gridy++;
        pnlBasicInfo.add(txtfldLastName, gbcCons);
        pnlBasicInfo.add(txtfldFirstName, gbcCons);
        pnlBasicInfo.add(txtfldMiddleInitial, gbcCons);
        pnlBasicInfo.add(txtfldAge, gbcCons);

        PPanel pnlSex = formatPanel();
        bgSex.add(rdMale);
        bgSex.add(rdFemale);
        gbcInner.reset();
        gbcInner.setConstraints(-1, 0,1,0,GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlSex.add(lblSex, gbcInner);
        gbcInner.setWidth(1);
        gbcInner.setConstraints(-1, 1);
        pnlSex.add(rdMale, gbcInner);
        pnlSex.add(rdFemale, gbcInner);
        gbcCons.setConstraints(-1, gbcCons.gridy+1, 0.5,0);
        pnlBasicInfo.add(pnlSex, gbcCons);

        PPanel pnlRole = formatPanel();
        bgRole.add(rdStudent);
        bgRole.add(rdEmployee);
        gbcInner.reset();
        gbcInner.setConstraints(-1, 0,1,0,GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlRole.add(lblRole, gbcInner);
        gbcInner.setWidth(1);
        gbcInner.gridy++;
        pnlRole.add(rdStudent, gbcInner);
        pnlRole.add(rdEmployee, gbcInner);
        pnlBasicInfo.add(pnlRole, gbcCons);

        PPanel pnlCivilStatus = formatPanel();
        bgCivilStatus.add(rdSingle);
        bgCivilStatus.add(rdMarried);
        bgCivilStatus.add(rdSeparated);
        bgCivilStatus.add(rdWidowed);
        bgCivilStatus.add(rdDivorced);
        gbcInner.reset();
        gbcInner.setConstraints(-1, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        gbcInner.setWidth(2);
        pnlCivilStatus.add(lblCivilStatus, gbcInner);
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

        gbcCons.gridy++;
        gbcCons.gridwidth = 2;
        pnlBasicInfo.add(txtfldBirthdate, gbcCons);
        gbcCons.gridwidth = 1;
        pnlBasicInfo.add(txtfldMobileNumber, gbcCons);
        pnlBasicInfo.add(txtfldNetwork, gbcCons);

        gbcCons.gridy++;
        gbcCons.gridwidth = 4;
        pnlBasicInfo.add(txtfldDivision, gbcCons);

        append(pnlBasicInfo);
    }

    private void addDormInfoFields(){
        gbcCons.reset();
        gbcCons.setConstraints(-1, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;

        chkDormInformation.addActionListener(e -> {
            if(chkDormInformation.isSelected()){
                expandDormInfo(true);
            } else {
                expandDormInfo(false);
            }
        });
        pnlDormInfo.add(chkDormInformation, gbcCons);
        append(pnlDormInfo);
    }

    private void addGuardianInfoFields(){
        gbcCons.reset();
        gbcCons.setConstraints(-1, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 2;

        chkGuardianInformation.addActionListener(e -> {
            if(chkGuardianInformation.isSelected()){
                expandGuardianInfo(true);
            } else{
                expandGuardianInfo(false);
            }
        });
        pnlGuardianInfo.add(chkGuardianInformation, gbcCons);
        append(pnlGuardianInfo);
    }

    private void addFamilyIllnessFields(){
        PPanel pnl = createFieldGroupPanel();
        PLabel lbl = new PLabel("Family's History of Illness", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1,0,1,0,GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 4;
        pnl.add(lbl, gbcCons);
        gbcCons.gridwidth = 1;
        gbcCons.gridy++;
        pnl.add(chkHypertension, gbcCons);
        pnl.add(chkTuberculosis, gbcCons);
        pnl.add(chkBAsthma, gbcCons);
        pnl.add(chkCancer, gbcCons);
        gbcCons.gridy++;
        pnl.add(chkDiabetes, gbcCons);
        pnl.add(chkHepatitis, gbcCons);
        pnl.add(chkHeartDisease, gbcCons);
        pnl.add(chkAllergies, gbcCons);
        gbcCons.gridy++;

        PPanel pnlOthers = new PPanel(new GridBagLayout());
        pnlOthers.setBackground(null);

        PGridBagConstraints gb = new PGridBagConstraints();
        gb.setConstraints(-1, -1, 0, 0, GridBagConstraints.HORIZONTAL);

        pnlOthers.add(chkOthers, gb);

        gb.setConstraints(-1, -1, 1, 0, GridBagConstraints.HORIZONTAL);
        pnlOthers.add(txtfldFamilyIllnessOthers, gb);

        gbcCons.gridwidth = 4;
        pnl.add(pnlOthers, gbcCons);

        append(pnl);
    }

    private void addMedicalConditionFields(){
        PPanel pnl = createFieldGroupPanel();
        PLabel lbl = new PLabel("Current Medical Condition", PLabel.HEADING2);

        gbcCons.reset();
        gbcCons.setConstraints(-1, 0, 1, 0, GridBagConstraints.HORIZONTAL);
        gbcCons.gridwidth = 3;
        pnl.add(lbl, gbcCons);
        gbcCons.gridy++;
        gbcCons.gridwidth = 1;
        for(MedicalConditionFields mcf : objMedicalConditions){
            pnl.add(mcf.getTxtfldConditionName(), gbcCons);
            pnl.add(mcf.getTxtfldDateIdentified(), gbcCons);
            pnl.add(mcf.getTxtfldMaintenance(), gbcCons);
            gbcCons.gridy++;
        }
        append(pnl);
    }

    private void addImmunizationFields(){
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

    private void addButton(){
        btnSave.setBackground(PulsePointConstants.GREEN);
        btnSave.setBackgroundHoverColor(PulsePointConstants.BLUE);
        btnSave.setBackgroundClickedColor(PulsePointConstants.PINK);

        btnCancel.setBackground(PulsePointConstants.DARK_GRAY);
        btnCancel.setBackgroundHoverColor(PulsePointConstants.BLUE);
        btnCancel.setBackgroundClickedColor(PulsePointConstants.PINK);
        PPanel pnlButtons = new PPanel(new GridBagLayout());
        gbcCons.reset();
        gbcCons.setConstraints(-1, -1, 0.1, 1, 1);
        pnlButtons.add(Box.createHorizontalStrut(500), gbcCons);
        gbcCons.setConstraints(-1, -1, 0, 1, 1);
        gbcCons.anchor = GridBagConstraints.WEST;
        pnlButtons.add(btnCancel, gbcCons);
        pnlButtons.add(btnSave, gbcCons);

        append(pnlButtons);
    }

    public void setButton(){
        btnSave.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to update the record of: \n" +
                            objExaminee.getStrId() + " - " + objExaminee.getFullName(),
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if(choice == JOptionPane.NO_OPTION){
                return;
            }

            if(updateDB()){
                JOptionPane.showMessageDialog(null, "Information updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(null, "Information update failed", "Failed to update", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private PPanel createFieldGroupPanel(){
        PPanel pnl = new PPanel(new GridBagLayout());
        pnl.setBorderColor(PulsePointConstants.BLUE);
        pnl.setPadding(new Insets(10,10,10,10));
        pnl.setBackground(null);
        return pnl;
    }

    private PPanel formatPanel() {
        PPanel pnl = new PPanel(new GridBagLayout());
        pnl.setBorderColor(PulsePointConstants.GRAY);
        pnl.setBackground(Color.WHITE);
        pnl.setOpaque(true);
        pnl.setPadding(10, 10, 10, 10);
        pnl.setRadius(30);
        pnl.setBorderThickness(3);
        return pnl;
    }
    private boolean updateDB(){
        PTextField[] objRequiredFields = {txtfldExamineeId, txtfldDateExam, txtfldYearExam, txtfldLastName, txtfldFirstName,
                txtfldAge, txtfldBirthdate, txtfldDivision, txtfldMobileNumber, txtfldNetwork};
        for(PTextField txtfld : objRequiredFields){
            if(txtfld.getText().isEmpty()){
                JOptionPane.showMessageDialog(
                        null,
                        txtfld.getName() + " cannot be empty",
                        "Error", JOptionPane.ERROR_MESSAGE
                );
                return false;
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
            return false;
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
            return false;
        }

        String strFirstName = txtfldFirstName.getText();
        String strLastName = txtfldLastName.getText();

        String strMiddleInitial;
        if(!txtfldMiddleInitial.getText().isBlank()) {
            strMiddleInitial = txtfldMiddleInitial.getText();
        }else{
            strMiddleInitial = null;
        }

        int intAge = 0;
        if(txtfldAge.getText().isEmpty()){
            JOptionPane.showMessageDialog(
                    null,
                    "Age field cannot be empty.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
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
            return false;
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
                return false;
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
                return false;
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

        for (MedicalConditionFields objMedField : objMedicalConditions) {
            if (objMedField.isValid()) {
                String dateIdentified = objMedField.getDate(); // Can be null now
                if (dateIdentified != null) {
                    try {
                        LocalDate.parse(dateIdentified); // Validate format YYYY-MM-DD
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Incorrect Date Format for Date Identified (YYYY-MM-DD)",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return false;
                    }
                }

                arrExamineeConditions.add(new MedicalCondition(
                        objMedField.getName(),
                        dateIdentified,
                        objMedField.getMaintenance()
                ));
            }
        }

        // Immunizations
        // check if date is valid
        ArrayList<Immunization> arrExamineeImmunizations = new ArrayList<>();
        for (ImmunizationFields objImmField : objImmunizations) {
            if (objImmField.getChkVaccineName().isSelected() && objImmField.isOthers) {
                if (objImmField.getOtherName().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill out all vaccine name (others) fields.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                String dateGiven = objImmField.getDateGiven(); // can be null now
                if (dateGiven != null && !dateGiven.trim().isEmpty()) {
                    try {
                        LocalDate.parse(dateGiven.trim()); // Validate format YYYY-MM-DD
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                                null, "Incorrect Date Format for Date Given (YYYY-MM-DD)",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    dateGiven = null; // normalize empty strings to null
                }

                if (objImmField.getDose().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill out the dose field.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                arrExamineeImmunizations.add(new Immunization(
                        objImmField.getTxtfldOthers().getText(),
                        dateGiven,
                        objImmField.getDose(),
                        objImmField.getRemarks()));
                continue;
            }

            if (objImmField.getChkVaccineName().isSelected()) {
                String dateGiven = objImmField.getDateGiven(); // can be null
                if (dateGiven != null && !dateGiven.trim().isEmpty()) {
                    try {
                        LocalDate.parse(dateGiven.trim());
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(
                                null, "Incorrect Date Format for Date Given (YYYY-MM-DD)",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                } else {
                    dateGiven = null;
                }

                if (objImmField.getDose().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please fill out the dose field.",
                            "Input Error",
                            JOptionPane.WARNING_MESSAGE);
                    return false;
                }

                arrExamineeImmunizations.add(new Immunization(
                        objImmField.getChkVaccineName().getText(),
                        dateGiven,
                        objImmField.getDose(),
                        objImmField.getRemarks()));
            }
        }
        if(
        Examinee.updateExamineeInDB(
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
        )){
            return true;
        }
        return false;
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

    public PButton getSaveButton(){
        return btnSave;
    }
    public PButton getCancelButton(){return btnCancel;}
    // MAIN
    public static void main(String[] args) {
        ArrayList<MedicalCondition> medicalConditions = new ArrayList<>();
        medicalConditions.add(new MedicalCondition("Asthma", "2010-05-15", "Inhaler daily"));
        medicalConditions.add(new MedicalCondition("Hypertension", "2018-11-20", "Medication"));

        ArrayList<Immunization> immunizations = new ArrayList<>();
        immunizations.add(new Immunization("MMR", "2010-06-01", "1st Dose", "No reactions"));
        immunizations.add(new Immunization("Tetanus", "2023-03-12", "Booster", "Mild soreness"));

        Examinee examinee = new Examinee(
                "EXM12345",
                LocalDate.of(2025, 6, 12),
                2025,"John","Doe", "A", 30, "Male", "Student", "Single",
                LocalDate.of(1995,4,20), "09171234567","Globe","Miagao",
                "123 Sample St, Miagao","Jane Landlord","09181234567","Mary Guardian","Mother",
                "456 Guardian St, Miagao","09190000000","Smart","No known family illnesses",medicalConditions,immunizations
        );

        JFrame frm = new JFrame();
        frm.setSize(1000, 800);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setLayout(new GridBagLayout());

        UpdateInformation updateInformation = new UpdateInformation(examinee);

        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.setConstraints(-1, -1, 1, 1, 1);

        // ADD SCROLL PANE
        frm.add(updateInformation.getScrollPane(), gbc);

        frm.setVisible(true);
    }
}

