import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

class Examinee {
    private String strId;
    private LocalDate objExamDate;
    private int intExamYear;
    private String strFirstName;
    private String strLastName;
    private String strMiddleInitial;
    private int intAge;
    private String strSex;
    private String strRole;
    private String strCivilStatus;
    private LocalDate objBirthdate;
    private String strMobileNumber;
    private String strNetwork;
    private String strDivision;

    // Dorm Info
    private String strAddress;
    private String strLandlordName;
    private String strLandlordContact;

    // Guardian Info
    private String strGuardianName;
    private String strGuardianRelation;
    private String strGuardianAddress;
    private String strGuardianContact;
    private String strGuardianNetwork;

    // Family Illness
    private String strFamilyIllness;

    // Lists
    private ArrayList<MedicalCondition> arrMedicalConditions;
    private ArrayList<Immunization> arrImmunizations;

    private Connection conn;
    private PreparedStatement stmnt;

    public Examinee(String strId) {
        this.strId = strId; // Set the ID
        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);

            // Prepare a SQL statement to retrieve examinee information
            String sql = "SELECT * FROM examinee WHERE examinee_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, strId);
            ResultSet objInformation = pstmt.executeQuery();

            // Prepare a SQL statement to retrieve medical conditions
            String conditionSql = "SELECT * FROM current_medical_condition WHERE examinee_id = ?";
            PreparedStatement conditionStmt = conn.prepareStatement(conditionSql);
            conditionStmt.setString(1, strId);
            ResultSet objCondition = conditionStmt.executeQuery();

            // Prepare a SQL statement to retrieve immunizations
            String immunizationSql = "SELECT * FROM immunization_background WHERE examinee_id = ?";
            PreparedStatement immunizationStmt = conn.prepareStatement(immunizationSql);
            immunizationStmt.setString(1, strId);
            ResultSet objImmunizations = immunizationStmt.executeQuery();

            // Call the existing constructor that takes ResultSet objects
            if (objInformation.next()) {
                this.intExamYear = objInformation.getInt("year_of_exam");
                this.objExamDate = objInformation.getDate("date_of_exam").toLocalDate();
                this.strLastName = objInformation.getString("last_name");
                this.strFirstName = objInformation.getString("first_name");
                this.strMiddleInitial = objInformation.getString("middle_initial");
                this.intAge = objInformation.getInt("age");
                this.strSex = objInformation.getString("sex");
                this.objBirthdate = objInformation.getDate("birthdate").toLocalDate();
                this.strCivilStatus = objInformation.getString("civil_status");
                this.strDivision = objInformation.getString("division");
                this.strRole = objInformation.getString("role");
                this.strMobileNumber = objInformation.getString("mobile_number");
                this.strNetwork = objInformation.getString("network");
                this.strAddress = objInformation.getString("address_in_miagao");
                this.strLandlordName = objInformation.getString("landlord_name");
                this.strLandlordContact = objInformation.getString("landlord_contact_number");
                this.strGuardianName = objInformation.getString("guardian_name");
                this.strGuardianAddress = objInformation.getString("guardian_address");
                this.strGuardianRelation = objInformation.getString("guardian_relation");
                this.strGuardianContact = objInformation.getString("guardian_mobile_number");
                this.strGuardianNetwork = objInformation.getString("guardian_network");
                this.strFamilyIllness = objInformation.getString("family_history_illness");
            }

            // Load medical conditions
            arrMedicalConditions = new ArrayList<>();
            while (objCondition.next()) {
                String name = objCondition.getString("medical_condition");
                String date = objCondition.getString("condition_identified_on");
                String maintenance = objCondition.getString("maintenance_medication");
                arrMedicalConditions.add(new MedicalCondition(name, date, maintenance));
            }

            // Load immunizations
            arrImmunizations = new ArrayList<>();
            while (objImmunizations.next()) {
                String name = objImmunizations.getString("vaccine_name");
                String date = objImmunizations.getString("vaccine_given_date");
                String dose = objImmunizations.getString("vaccine_dose");
                String remarks = objImmunizations.getString("vaccine_remarks");
                arrImmunizations.add(new Immunization(name, date, dose, remarks));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error initializing Examinee from database:\n" + e.getMessage(),
                    "Constructor Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Examinee(
            String strId,
            LocalDate objExamDate,
            int intExamYear,
            String strFirstName,
            String strLastName,
            String strMiddleInitial,
            int intAge,
            String strSex,
            String strRole,
            String strCivilStatus,
            LocalDate objBirthdate,
            String strMobileNumber,
            String strNetwork,
            String strDivision,
            String strAddress,
            String strLandlordName,
            String strLandlordContact,
            String strGuardianName,
            String strGuardianRelation,
            String strGuardianAddress,
            String strGuardianContact,
            String strGuardianNetwork,
            String strFamilyIllness,
            ArrayList<MedicalCondition> arrMedicalConditions,
            ArrayList<Immunization> arrImmunizations
    ) {
        this.strId = strId;
        this.objExamDate = objExamDate;
        this.intExamYear = intExamYear;
        this.strFirstName = strFirstName;
        this.strLastName = strLastName;
        this.strMiddleInitial = strMiddleInitial;
        this.intAge = intAge;
        this.strSex = strSex;
        this.strRole = strRole;
        this.strCivilStatus = strCivilStatus;
        this.objBirthdate = objBirthdate;
        this.strMobileNumber = strMobileNumber;
        this.strNetwork = strNetwork;
        this.strDivision = strDivision;
        this.strAddress = strAddress;
        this.strLandlordName = strLandlordName;
        this.strLandlordContact = strLandlordContact;
        this.strGuardianName = strGuardianName;
        this.strGuardianRelation = strGuardianRelation;
        this.strGuardianAddress = strGuardianAddress;
        this.strGuardianContact = strGuardianContact;
        this.strGuardianNetwork = strGuardianNetwork;
        this.strFamilyIllness = strFamilyIllness;
        this.arrMedicalConditions = arrMedicalConditions;
        this.arrImmunizations = arrImmunizations;
    }

    public Examinee(ResultSet objInformation, ResultSet objCondition, ResultSet objImmunizations) {
        try {
            if (objInformation.next()) {
                this.strId = objInformation.getString("examinee_id");
                this.intExamYear = objInformation.getInt("year_of_exam");
                this.objExamDate = objInformation.getDate("date_of_exam").toLocalDate();
                this.strLastName = objInformation.getString("last_name");
                this.strFirstName = objInformation.getString("first_name");
                this.strMiddleInitial = objInformation.getString("middle_initial");
                this.intAge = objInformation.getInt("age");
                this.strSex = objInformation.getString("sex");
                this.objBirthdate = objInformation.getDate("birthdate").toLocalDate();
                this.strCivilStatus = objInformation.getString("civil_status");
                this.strDivision = objInformation.getString("division");
                this.strRole = objInformation.getString("role");
                this.strMobileNumber = objInformation.getString("mobile_number");
                this.strNetwork = objInformation.getString("network");
                this.strAddress = objInformation.getString("address_in_miagao");
                this.strLandlordName = objInformation.getString("landlord_name");
                this.strLandlordContact = objInformation.getString("landlord_contact_number");
                this.strGuardianName = objInformation.getString("guardian_name");
                this.strGuardianAddress = objInformation.getString("guardian_address");
                this.strGuardianRelation = objInformation.getString("guardian_relation");
                this.strGuardianContact = objInformation.getString("guardian_mobile_number");
                this.strGuardianNetwork = objInformation.getString("guardian_network");
                this.strFamilyIllness = objInformation.getString("family_history_illness");
            }

            // Load medical conditions
            arrMedicalConditions = new ArrayList<>();
            while (objCondition.next()) {
                String name = objCondition.getString("medical_condition");
                String date = objCondition.getString("condition_identified_on");
                String maintenance = objCondition.getString("maintenance_medication");

                arrMedicalConditions.add(new MedicalCondition(name, date, maintenance));
            }

            // Load immunizations
            arrImmunizations = new ArrayList<>();
            while (objImmunizations.next()) {
                String name = objImmunizations.getString("vaccine_name");
                String date = objImmunizations.getString("vaccine_given_date");
                String dose = objImmunizations.getString("vaccine_dose");
                String remarks = objImmunizations.getString("vaccine_remarks");

                arrImmunizations.add(new Immunization(name, date, dose, remarks));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                    "Error initializing Examinee from database:\n" + e.getMessage(),
                    "Constructor Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean deleteExamineeFromDB(String examineeId) {
        Connection conn = null;
        PreparedStatement deleteMedStmt = null;
        PreparedStatement deleteImmStmt = null;
        PreparedStatement deleteExamineeStmt = null;

        try {
            conn = DriverManager.getConnection(
                    PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD
            );

            conn.setAutoCommit(false);

            String deleteMedSql = "DELETE FROM current_medical_condition WHERE examinee_id = ?";
            deleteMedStmt = conn.prepareStatement(deleteMedSql);
            deleteMedStmt.setString(1, examineeId);
            deleteMedStmt.executeUpdate();


            String deleteImmSql = "DELETE FROM immunization_background WHERE examinee_id = ?";
            deleteImmStmt = conn.prepareStatement(deleteImmSql);
            deleteImmStmt.setString(1, examineeId);
            deleteImmStmt.executeUpdate();


            String deleteExamineeSql = "DELETE FROM examinee WHERE examinee_id = ?";
            deleteExamineeStmt = conn.prepareStatement(deleteExamineeSql);
            deleteExamineeStmt.setString(1, examineeId);
            int rowsAffected = deleteExamineeStmt.executeUpdate();

            conn.commit(); // Commit if all deletions are successful

            System.out.println("Deleted " + rowsAffected + " examinee record(s).");
            return rowsAffected > 0;

        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                System.err.println("Rollback failed: " + rollbackEx.getMessage());
            }

            JOptionPane.showMessageDialog(null,
                    "Failed to delete examinee records:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;

        } finally {
            try { if (deleteMedStmt != null) deleteMedStmt.close(); } catch (Exception ignored) {}
            try { if (deleteImmStmt != null) deleteImmStmt.close(); } catch (Exception ignored) {}
            try { if (deleteExamineeStmt != null) deleteExamineeStmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }

    public static boolean updateExamineeInDB(
            String strId,
            LocalDate objExamDate,
            int intExamYear,
            String strFirstName,
            String strLastName,
            String strMiddleInitial,
            int intAge,
            String strSex,
            String strRole,
            String strCivilStatus,
            LocalDate objBirthdate,
            String strMobileNumber,
            String strNetwork,
            String strDivision,
            String strAddress,
            String strLandlordName,
            String strLandlordContact,
            String strGuardianName,
            String strGuardianRelation,
            String strGuardianAddress,
            String strGuardianContact,
            String strGuardianNetwork,
            String strFamilyIllness,
            ArrayList<MedicalCondition> arrMedicalConditions,
            ArrayList<Immunization> arrImmunizations
) {
        Connection conn = null;
        PreparedStatement stmntExaminee = null;
        PreparedStatement stmntDeleteMed = null;
        PreparedStatement stmntInsertMed = null;
        PreparedStatement stmntDeleteImm = null;
        PreparedStatement stmntInsertImm = null;

        try {
            conn = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);

            conn.setAutoCommit(false); // Start transaction

            // Update examinee main record
            String sqlUpdateExaminee = "UPDATE examinee SET " +
                    "year_of_exam = ?, " +
                    "date_of_exam = ?, " +
                    "last_name = ?, " +
                    "first_name = ?, " +
                    "middle_initial = ?, " +
                    "age = ?, " +
                    "sex = ?, " +
                    "birthdate = ?, " +
                    "civil_status = ?, " +
                    "division = ?, " +
                    "role = ?, " +
                    "mobile_number = ?, " +
                    "network = ?, " +
                    "address_in_miagao = ?, " +
                    "landlord_name = ?, " +
                    "landlord_contact_number = ?, " +
                    "guardian_name = ?, " +
                    "guardian_address = ?, " +
                    "guardian_relation = ?, " +
                    "guardian_mobile_number = ?, " +
                    "guardian_network = ?, " +
                    "family_history_illness = ? " +
                    "WHERE examinee_id = ?";

            stmntExaminee = conn.prepareStatement(sqlUpdateExaminee);

            stmntExaminee.setInt(1, intExamYear);
            stmntExaminee.setDate(2, objExamDate != null ? Date.valueOf(objExamDate) : null);
            stmntExaminee.setString(3, strLastName);
            stmntExaminee.setString(4, strFirstName);
            stmntExaminee.setString(5, strMiddleInitial);

            if (intAge != 0) {
                stmntExaminee.setInt(6, intAge);
            } else {
                stmntExaminee.setNull(6, java.sql.Types.INTEGER);
            }

            stmntExaminee.setString(7, strSex);
            stmntExaminee.setDate(8, objBirthdate != null ? Date.valueOf(objBirthdate) : null);
            stmntExaminee.setString(9, strCivilStatus);
            stmntExaminee.setString(10, strDivision);
            stmntExaminee.setString(11, strRole);
            stmntExaminee.setString(12, strMobileNumber);
            stmntExaminee.setString(13, strNetwork);

            if (strAddress != null) {
                stmntExaminee.setString(14, strAddress);
                stmntExaminee.setString(15, strLandlordName);
                stmntExaminee.setString(16, strLandlordContact);
            } else {
                stmntExaminee.setNull(14, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(15, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(16, java.sql.Types.VARCHAR);
            }

            if (strGuardianName != null) {
                stmntExaminee.setString(17, strGuardianName);
                stmntExaminee.setString(18, strGuardianAddress);
                stmntExaminee.setString(19, strGuardianRelation);
                stmntExaminee.setString(20, strGuardianContact);
                stmntExaminee.setString(21, strGuardianNetwork);
            } else {
                stmntExaminee.setNull(17, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(18, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(19, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(20, java.sql.Types.VARCHAR);
                stmntExaminee.setNull(21, java.sql.Types.VARCHAR);
            }

            if (strFamilyIllness != null) {
                stmntExaminee.setString(22, strFamilyIllness);
            } else {
                stmntExaminee.setNull(22, java.sql.Types.VARCHAR);
            }

            stmntExaminee.setString(23, strId); // WHERE clause

            int rowsUpdated = stmntExaminee.executeUpdate();

            if (rowsUpdated == 0) {
                conn.rollback();
                JOptionPane.showMessageDialog(null,
                        "No examinee record found with ID: " + strId,
                        "Update Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }

            // Delete existing medical conditions
            String sqlDeleteMed = "DELETE FROM current_medical_condition WHERE examinee_id = ?";
            stmntDeleteMed = conn.prepareStatement(sqlDeleteMed);
            stmntDeleteMed.setString(1, strId);
            stmntDeleteMed.executeUpdate();

            // Insert new medical conditions
            String sqlInsertMed = "INSERT INTO current_medical_condition " +
                    "(examinee_id, medical_condition, condition_identified_on, maintenance_medication) " +
                    "VALUES (?, ?, ?, ?)";
            stmntInsertMed = conn.prepareStatement(sqlInsertMed);

            if (arrMedicalConditions != null) {
                for (MedicalCondition mc : arrMedicalConditions) {
                    stmntInsertMed.setString(1, strId);
                    stmntInsertMed.setString(2, mc.name);
                    if (mc.date != null && !mc.date.isEmpty()) {
                        stmntInsertMed.setDate(3, Date.valueOf(mc.date));
                    } else {
                        stmntInsertMed.setNull(3, java.sql.Types.DATE);
                    }
                    stmntInsertMed.setString(4, mc.maintenance);
                    stmntInsertMed.executeUpdate();
                }
            }

            // Delete existing immunizations
            String sqlDeleteImm = "DELETE FROM immunization_background WHERE examinee_id = ?";
            stmntDeleteImm = conn.prepareStatement(sqlDeleteImm);
            stmntDeleteImm.setString(1, strId);
            stmntDeleteImm.executeUpdate();

            // Insert new immunizations
            String sqlInsertImm = "INSERT INTO immunization_background " +
                    "(examinee_id, vaccine_name, vaccine_given_date, vaccine_dose, vaccine_remarks) " +
                    "VALUES (?, ?, ?, ?, ?)";
            stmntInsertImm = conn.prepareStatement(sqlInsertImm);

            if (arrImmunizations != null) {
                for (Immunization im : arrImmunizations) {
                    stmntInsertImm.setString(1, strId);
                    stmntInsertImm.setString(2, im.strName);

                    if (im.strDate != null && !im.strDate.isEmpty()) {
                        stmntInsertImm.setDate(3, Date.valueOf(im.strDate));
                    } else {
                        stmntInsertImm.setNull(3, java.sql.Types.DATE);
                    }

                    stmntInsertImm.setString(4, im.strDose);
                    stmntInsertImm.setString(5, im.strRemarks);

                    stmntInsertImm.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction

            System.out.println("Update successful for examinee_id: " + strId);
            return true;
        } catch (SQLException e) {
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                System.err.println("Rollback failed: " + ex.getMessage());
            }

            JOptionPane.showMessageDialog(null,
                    "Failed to update examinee records:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try { if (stmntExaminee != null) stmntExaminee.close(); } catch (Exception ignored) {}
            try { if (stmntDeleteMed != null) stmntDeleteMed.close(); } catch (Exception ignored) {}
            try { if (stmntInsertMed != null) stmntInsertMed.close(); } catch (Exception ignored) {}
            try { if (stmntDeleteImm != null) stmntDeleteImm.close(); } catch (Exception ignored) {}
            try { if (stmntInsertImm != null) stmntInsertImm.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
            System.out.println("Update Process Done");
        }
    }

    public void printInfo() {
        System.out.println("=== Examinee Information ===");
        System.out.println("ID: " + strId);
        System.out.println("Name: " + strLastName + ", " + strFirstName + " " + strMiddleInitial + ".");
        System.out.println("Age: " + intAge);
        System.out.println("Sex: " + strSex);
        System.out.println("Birthdate: " + objBirthdate);
        System.out.println("Civil Status: " + strCivilStatus);
        System.out.println("Division: " + strDivision);
        System.out.println("Role: " + strRole);
        System.out.println("Exam Year: " + intExamYear);
        System.out.println("Date of Exam: " + objExamDate);
        System.out.println("Address in Miagao: " + strAddress);
        System.out.println("Mobile: " + strMobileNumber + " (" + strNetwork + ")");

        System.out.println("\n-- Landlord Info --");
        System.out.println("Name: " + strLandlordName);
        System.out.println("Contact: " + strLandlordContact);

        System.out.println("\n-- Guardian Info --");
        System.out.println("Name: " + strGuardianName);
        System.out.println("Relation: " + strGuardianRelation);
        System.out.println("Address: " + strGuardianAddress);
        System.out.println("Contact: " + strGuardianContact + " (" + strGuardianNetwork + ")");

        System.out.println("\nFamily History of Illness: " + strFamilyIllness);

        System.out.println("\n=== Medical Conditions ===");
        if (arrMedicalConditions != null && !arrMedicalConditions.isEmpty()) {
            for (MedicalCondition mc : arrMedicalConditions) {
                System.out.println("- " + mc.name + " (Identified on: " + mc.date + ", Maintenance: " + mc.maintenance + ")");
            }
        } else {
            System.out.println("None reported.");
        }

        System.out.println("\n=== Immunizations ===");
        if (arrImmunizations != null && !arrImmunizations.isEmpty()) {
            for (Immunization im : arrImmunizations) {
                System.out.println("- " + im.strName + " | Date: " + im.strDate + " | Dose: " + im.strDose + " | Remarks: " + im.strRemarks);
            }
        } else {
            System.out.println("None reported.");
        }
    }

    public boolean addBasicInfoToDB() {
        try {
            conn = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);

            String strColumns = "(examinee_id, year_of_exam, date_of_exam, last_name, first_name, " +
                    "middle_initial, age, sex, birthdate, civil_status, division, role, mobile_number, " +
                    "network, address_in_miagao, landlord_name, landlord_contact_number, guardian_name, " +
                    "guardian_address, guardian_relation, guardian_mobile_number, guardian_network, " +
                    "family_history_illness)";
            String strStatement = "INSERT INTO examinee " + strColumns + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmnt = conn.prepareStatement(strStatement);

            stmnt.setString(1, this.strId);
            stmnt.setInt(2, this.intExamYear);
            stmnt.setDate(3, Date.valueOf(this.objExamDate));
            stmnt.setString(4, this.strLastName);
            stmnt.setString(5, this.strFirstName);
            stmnt.setString(6, this.strMiddleInitial);

            if (this.intAge != 0) {
                stmnt.setInt(7, this.intAge);
            } else {
                stmnt.setNull(7, Types.INTEGER);
            }

            stmnt.setString(8, this.strSex);
            stmnt.setDate(9, Date.valueOf(this.objBirthdate));
            stmnt.setString(10, this.strCivilStatus);
            stmnt.setString(11, this.strDivision);
            stmnt.setString(12, this.strRole);
            stmnt.setString(13, this.strMobileNumber);
            stmnt.setString(14, this.strNetwork);

            if (this.strAddress != null) {
                stmnt.setString(15, this.strAddress);
                stmnt.setString(16, this.strLandlordName);
                stmnt.setString(17, this.strLandlordContact);
            } else {
                stmnt.setNull(15, Types.VARCHAR);
                stmnt.setNull(16, Types.VARCHAR);
                stmnt.setNull(17, Types.VARCHAR);
            }

            if (this.strGuardianName != null) {
                stmnt.setString(18, this.strGuardianName);
                stmnt.setString(19, this.strGuardianAddress);
                stmnt.setString(20, this.strGuardianRelation);
                stmnt.setString(21, this.strGuardianContact);
                stmnt.setString(22, this.strGuardianNetwork);
            } else {
                stmnt.setNull(18, Types.VARCHAR);
                stmnt.setNull(19, Types.VARCHAR);
                stmnt.setNull(20, Types.VARCHAR);
                stmnt.setNull(21, Types.VARCHAR);
                stmnt.setNull(22, Types.VARCHAR);
            }

            if (this.strFamilyIllness != null) {
                stmnt.setString(23, this.strFamilyIllness);
            } else {
                stmnt.setNull(23, Types.VARCHAR);
            }

            int intRowsAffected = stmnt.executeUpdate();

            System.out.println(intRowsAffected + " Rows Affected");
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to insert examinee records:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try { stmnt.close(); } catch (Exception ignored) {}
            try { conn.close(); } catch (Exception ignored) {}
            System.out.println("Basic Info Process Done");
        }
    }

    public boolean addMedicalConditionsToDB() {
        try {
            conn = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);

            String sql = "INSERT INTO current_medical_condition " +
                    "(examinee_id, medical_condition, condition_identified_on, maintenance_medication) " +
                    "VALUES (?, ?, ?, ?)";

            stmnt = conn.prepareStatement(sql);

            for (MedicalCondition condition : arrMedicalConditions) {
                stmnt.setString(1, this.strId);
                stmnt.setString(2, condition.name);

                if (condition.date != null) {
                    stmnt.setDate(3, Date.valueOf(condition.date));
                } else {
                    stmnt.setNull(3, Types.DATE);
                }

                stmnt.setString(4, condition.maintenance);

                stmnt.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to insert medical conditions:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try { stmnt.close(); } catch (Exception ignored) {}
            try { conn.close(); } catch (Exception ignored) {}
            System.out.println("Medical Condition Process Done");
        }
    }

    public boolean addImmunizationsToDB() {
        try {
            conn = DriverManager.getConnection(PulsePointConstants.URL,
                    PulsePointConstants.USERNAME,
                    PulsePointConstants.PASSWORD);

            String sql = "INSERT INTO immunization_background " +
                    "(examinee_id, vaccine_name, vaccine_given_date, vaccine_dose, vaccine_remarks) " +
                    "VALUES (?, ?, ?, ?, ?)";

            stmnt = conn.prepareStatement(sql);

            for (Immunization vaccine : arrImmunizations) {
                stmnt.setString(1, this.strId);
                stmnt.setString(2, vaccine.strName);

                if (vaccine.strDate != null) {
                    stmnt.setDate(3, Date.valueOf(vaccine.strDate));
                } else {
                    stmnt.setNull(3, Types.DATE);
                }

                stmnt.setString(4, vaccine.strDose);
                stmnt.setString(5, vaccine.strRemarks);

                stmnt.executeUpdate();
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Failed to insert immunization records:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            try { stmnt.close(); } catch (Exception ignored) {}
            try { conn.close(); } catch (Exception ignored) {}
            System.out.println("Immunization Process Done");
        }
    }

    public String getStrId() {
        return strId;
    }

    public LocalDate getObjExamDate() {
        return objExamDate;
    }

    public int getIntExamYear() {
        return intExamYear;
    }

    public String getStrFirstName() {
        return strFirstName;
    }

    public String getStrLastName() {
        return strLastName;
    }

    public String getStrMiddleInitial() {
        return strMiddleInitial;
    }

    public int getIntAge() {
        return intAge;
    }

    public String getStrSex() {
        return strSex;
    }

    public String getStrRole() {
        return strRole;
    }

    public String getStrCivilStatus() {
        return strCivilStatus;
    }

    public LocalDate getObjBirthdate() {
        return objBirthdate;
    }

    public String getStrMobileNumber() {
        return strMobileNumber;
    }

    public String getStrNetwork() {
        return strNetwork;
    }

    public String getStrDivision() {
        return strDivision;
    }

    // Dorm Info
    public String getStrAddress() {
        return strAddress;
    }

    public String getStrLandlordName() {
        return strLandlordName;
    }

    public String getStrLandlordContact() {
        return strLandlordContact;
    }

    // Guardian Info
    public String getStrGuardianName() {
        return strGuardianName;
    }

    public String getStrGuardianRelation() {
        return strGuardianRelation;
    }

    public String getStrGuardianAddress() {
        return strGuardianAddress;
    }

    public String getStrGuardianContact() {
        return strGuardianContact;
    }

    public String getStrGuardianNetwork() {
        return strGuardianNetwork;
    }

    // Family Illness
    public String getStrFamilyIllness() {
        return strFamilyIllness;
    }

    public String getFormattedStrFamilyIllness(int maxLineLength) {
        if (strFamilyIllness == null || strFamilyIllness.trim().isEmpty()) {
            return "N/A";
        }

        StringBuilder result = new StringBuilder();
        int lastBreak = 0;

        while (lastBreak < strFamilyIllness.length()) {
            int nextBreak = Math.min(lastBreak + maxLineLength, strFamilyIllness.length());
            int lastComma = strFamilyIllness.lastIndexOf(",", nextBreak);

            if (lastComma <= lastBreak || lastComma == -1) {
                lastComma = Math.min(nextBreak, strFamilyIllness.length());
            } else {
                lastComma += 1; // include comma
            }

            result.append(strFamilyIllness, lastBreak, Math.min(lastComma, strFamilyIllness.length())).append("\n");
            lastBreak = lastComma;
        }

        return result.toString().trim();
    }

    // Lists
    public ArrayList<MedicalCondition> getArrMedicalConditions() {
        return arrMedicalConditions;
    }

    public ArrayList<Immunization> getArrImmunizations() {
        return arrImmunizations;
    }

    public String getFullName(){
        if (strMiddleInitial != null && !strMiddleInitial.trim().isEmpty()) {
            return strLastName + ", " + strFirstName + " " + strMiddleInitial + ".";
        } else {
            return strLastName + ", " + strFirstName;
        }
    }

}

class Immunization{
    public final String strName, strDate, strDose, strRemarks;
    Immunization(String strName, String strDate, String strDose, String strRemarks){
        this.strName = strName;
        this.strDate = strDate;
        this.strDose = strDose;
        this.strRemarks = strRemarks;
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