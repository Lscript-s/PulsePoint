import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UpdateInformation extends PPanel{
    private Examinee objExaminee;
    UpdateInformation(Examinee objExaminee){
        this.objExaminee = objExaminee;
        objExaminee.printInfo();
    }

    public static void main(String[] args) {
        ArrayList<MedicalCondition> medicalConditions = new ArrayList<>();
        medicalConditions.add(new MedicalCondition("Asthma", "2010-05-15", "Inhaler daily"));
        medicalConditions.add(new MedicalCondition("Hypertension", "2018-11-20", "Medication"));

        ArrayList<Immunization> immunizations = new ArrayList<>();
        immunizations.add(new Immunization("MMR", "2010-06-01", "1st Dose", "No reactions"));
        immunizations.add(new Immunization("Tetanus", "2023-03-12", "Booster", "Mild soreness"));

        Examinee examinee = new Examinee(
                "EXM12345",                      // strId
                LocalDate.of(2025, 6, 12),       // objExamDate
                2025,                           // intExamYear
                "John",                        // strFirstName
                "Doe",                         // strLastName
                "A",                           // strMiddleInitial
                30,                            // intAge
                "Male",                        // strSex
                "Student",                     // strRole
                "Single",                      // strCivilStatus
                LocalDate.of(1995, 4, 20),     // objBirthdate
                "09171234567",                 // strMobileNumber
                "Globe",                       // strNetwork
                "Miagao",                      // strDivision
                "123 Sample St, Miagao",       // strAddress
                "Jane Landlord",               // strLandlordName
                "09181234567",                 // strLandlordContact
                "Mary Guardian",               // strGuardianName
                "Mother",                     // strGuardianRelation
                "456 Guardian St, Miagao",     // strGuardianAddress
                "09190000000",                 // strGuardianContact
                "Smart",                       // strGuardianNetwork
                "No known family illnesses",  // strFamilyIllness
                medicalConditions,             // arrMedicalConditions
                immunizations                  // arrImmunizations
        );
        JFrame frm = new JFrame();
        frm.setSize(800,800);
        frm.setDefaultCloseOperation(3);
        frm.setLayout(new GridBagLayout());
        PGridBagConstraints gbc = new PGridBagConstraints();
        gbc.setConstraints(-1,-1,1,1,1);

        frm.add(new UpdateInformation(examinee), gbc);

        frm.setVisible(true);
    }
}
