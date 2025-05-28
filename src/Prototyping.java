import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.*;

class Constants{
    public static String[] FIRSTNAMES = {
            "John", "Alice", "David", "Emma", "James", "Olivia", "Liam", "Sophia",
            "Michael", "Isabella", "William", "Mia", "Benjamin", "Charlotte", "Lucas",
            "Amelia", "Henry", "Harper", "Alexander", "Evelyn", "Aiden", "Olivia", "Ethan", "Emma", "Liam", "Ava", "Noah", "Sophia", "Mason", "Isabella",
            "Logan", "Mia", "Lucas", "Charlotte", "Jackson", "Amelia", "Elijah", "Harper", "Benjamin", "Evelyn",
            "James", "Abigail", "Alexander", "Ella", "Henry", "Avery", "Sebastian", "Scarlett", "Mateo", "Madison",
            "Jack", "Lily", "Owen", "Grace", "Samuel", "Zoey", "Levi", "Chloe", "Isaac", "Stella",
            "Gabriel", "Ellie", "Wyatt", "Nora", "Carter", "Lucy", "Dylan", "Ruby", "Luke", "Victoria",
            "Jayden", "Hazel", "Julian", "Aurora", "Hudson", "Violet", "Grayson", "Lillian", "Leo", "Addison",
            "Lincoln", "Eleanor", "Jaxon", "Hannah", "David", "Leah", "Asher", "Audrey", "Isaiah", "Bella",
            "Eli", "Natalie", "Hunter", "Savannah", "Jonathan", "Skylar", "Connor", "Anna", "Charles", "Brooklyn",
            "Thomas", "Kennedy", "Adrian", "Sophie", "Mateo", "Peyton", "Ryan", "Alice", "Nathan", "Autumn",
            "Cameron", "Sadie", "Caleb", "Serenity", "Eli", "Paisley", "Christian", "Taylor", "Aaron", "Nova",
            "Jeremiah", "Emilia", "Easton", "Willow", "Christopher", "Claire", "Joshua", "Isla", "Andrew", "Rylee",
            "Lincoln", "Penelope", "Joseph", "Riley", "Samuel", "Zoe", "Mateo", "Quinn", "Nathaniel", "Delilah",
            "Dominic", "Ariel", "Adam", "Gabriella", "Nolan", "Lydia", "Austin", "Madelyn", "Eli", "Jasmine",
            "Ian", "Mackenzie", "Jordan", "Hadley", "Cooper", "Piper", "Xavier", "Eva", "Carson", "Everly",
            "Santiago", "Raelynn", "Brayden", "Cora", "Roman", "Peyton", "Angel", "Mila", "Colton", "Mya",
            "Elias", "Ryleigh", "Tristan", "Jade", "Theo", "Kinsley", "Landon", "Amaya", "Bryson", "Summer",
            "Kayden", "Brianna", "Parker", "Londyn", "Weston", "Morgan", "Jason", "Genesis", "Chase", "Harmony",
            "Sawyer", "Camille", "Vincent", "Addison", "Eric", "Peyton", "Micah", "Blakely", "Antonio", "Gia",
            "Maxwell", "Sienna", "Elliot", "Jordyn", "Evan", "Athena", "Brayden", "Aubrey", "August", "Isabel",
            "Miles", "Arianna", "Cole", "Lyla", "Jaxson", "Londyn", "Adam", "Laila", "Robert", "Zara"
    };

    public static String[] LASTNAMES = {
            "Smith", "Johnson", "Brown", "Jones", "Garcia", "Martinez", "Miller", "Davis",
            "Rodriguez", "Martins", "Anderson", "Taylor", "Thomas", "Hernandez", "Moore",
            "Martin", "Lee", "Perez", "Thompson", "White", "Matthews", "Simpson", "Edwards", "Evans", "Howard", "Hawkins", "Powell", "Kramer", "Silva", "Carter",
            "Craig", "Wagner", "Gonzalez", "Rodriguez", "Montgomery", "Nelson", "Burgess", "Walker", "Davidson", "Taylor",
            "Walker", "Vargas", "King", "Douglas", "Martinez", "Fisher", "Gomez", "Mills", "Robinson", "Lewis", "Cole",
            "Reed", "Chang", "Chapman", "Perry", "Greene", "Grant", "Perry", "Mills", "Hunter", "Scott", "Greer", "Hill",
            "Mendez", "Knight", "Thomas", "Russell", "King", "Barker", "Lawrence", "Mitchell", "Williams", "Fox", "Wright",
            "Foster", "Wood", "Adams", "Berry", "Black", "Jackson", "Jenkins", "Ross", "Mitchell", "Harper", "Chavez", "Brown",
            "Hernandez", "Robinson", "Tucker", "Mason", "Payne", "Hernandez", "Lopez", "Ward", "Sanders", "Weaver", "Bishop",
            "Davidson", "Austin", "Gordon", "Gibson", "Bailey", "Ramos", "Perez", "Bates", "Snyder", "Curtis", "Hunter",
            "Bennett", "Price", "Young", "Gomez", "Scott", "Douglas", "Bishop", "Hernandez", "Moore", "Garcia", "Hill", "Jones",
            "White", "Barnes", "Green", "Chang", "Anderson", "Rogers", "Lynch", "Fischer", "Howard", "Freeman", "Parker",
            "Miller", "Curtis", "Shaw", "Spencer", "Woods", "White", "Meyer", "Wilson", "Long", "Palmer", "Stewart", "Ramos",
            "Shaw", "Hansen", "Patterson", "Pittman", "Smith", "Murray", "Thompson", "Walker", "Green", "Walsh", "Cameron",
            "Shaw", "Day", "Sandoval", "Hughes", "Chang", "Parker", "Gonzalez", "Blackwell", "Perez", "Burke", "Sims", "Patel",
            "Gibson", "Ortega", "Roberts", "Valdez", "Ramirez", "Howell", "Sullivan", "Patterson", "Davis", "Pope", "Gibbs",
            "Bennett", "Bryant", "Rios", "George", "Morrison", "Evans", "Gonzalez", "Hicks", "Hansen", "Fowler", "Mack",
            "Harrison", "Wright", "Graham", "Bell", "Freeman", "Stewart", "Craig", "Clark", "Hunt", "Jimenez", "Richards",
            "Austin", "Keller", "Howard", "Lambert", "Riley", "Hayes", "Carr", "Nelson", "Hawkins", "Sullivan", "Lloyd",
            "Lee", "Stevens", "James", "Morris", "Perez", "Harris", "Garrett", "Garcia", "Baker", "Morrison"
    };

    public static String MIDDLEINITIAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String[] ROLE = {"Student", "Employee"};

    public static String[] ADDRESS = {
            "Barangay Kirayan Tacas, Miagao, Iloilo",
            "Barangay Ubos Ilawod, Miagao, Iloilo",
            "Barangay Guibongan, Miagao, Iloilo",
            "Barangay Sapa, Miagao, Iloilo",
            "Barangay San Rafael, Miagao, Iloilo",
            "Barangay Damilisan, Miagao, Iloilo",
            "Barangay Baybay Norte, Miagao, Iloilo",
            "Barangay Baybay Sur, Miagao, Iloilo",
            "Barangay Bagumbayan, Miagao, Iloilo",
            "Barangay Baluarte, Miagao, Iloilo",
            "Barangay Igbugo, Miagao, Iloilo",
            "Barangay Cabalaunan, Miagao, Iloilo",
            "Barangay Kirayan Sur, Miagao, Iloilo",
            "Barangay Kirayan Norte, Miagao, Iloilo",
            "Barangay Igsoligue, Miagao, Iloilo",
            "Barangay Olango, Miagao, Iloilo",
            "Barangay San Fernando, Miagao, Iloilo",
            "Barangay Bacolod, Miagao, Iloilo",
            "Barangay Igcabidio, Miagao, Iloilo",
            "Barangay Lumangan, Miagao, Iloilo",
            "Barangay Igdalaquit, Miagao, Iloilo",
            "Barangay Igpajo, Miagao, Iloilo",
            "Barangay Igbugao, Miagao, Iloilo",
            "Barangay Igbita, Miagao, Iloilo",
            "Barangay Wayang, Miagao, Iloilo",
            "Barangay Igtuba, Miagao, Iloilo",
            "Barangay Tigmalapad, Miagao, Iloilo",
            "Barangay Nalundan, Miagao, Iloilo",
            "Barangay Malagyan, Miagao, Iloilo",
            "Barangay Valencia, Miagao, Iloilo",
            "Barangay Sapa Boloc, Miagao, Iloilo",
            "Barangay Mat-y, Miagao, Iloilo",
            "Barangay Kirikite, Miagao, Iloilo",
            "Barangay Tacas, Miagao, Iloilo",
            "Barangay Igbaras, Miagao, Iloilo",
            "Barangay Miapa, Miagao, Iloilo",
            "Barangay Calampitao, Miagao, Iloilo",
            "Barangay Balantad, Miagao, Iloilo",
            "Barangay Tumagboc, Miagao, Iloilo",
            "Barangay Lumangan Proper, Miagao, Iloilo",
            "Barangay Igtuba Sur, Miagao, Iloilo",
            "Barangay Tigbawan, Miagao, Iloilo",
            "Barangay Dawis, Miagao, Iloilo",
            "Barangay Igtalongon, Miagao, Iloilo",
            "Barangay Igpaho, Miagao, Iloilo",
            "Barangay Tumagbok, Miagao, Iloilo",
            "Barangay Bacauan, Miagao, Iloilo",
            "Barangay Kirayan East, Miagao, Iloilo",
            "Barangay Botong, Miagao, Iloilo",
            "Barangay Punong, Miagao, Iloilo",
            "Barangay Tabunacan, Miagao, Iloilo"
    };

    public static String[] NETWORK = {
            "Globe", "TnT", "Smart", "Sun Cellular", "Dito", "GOMO", "Sun", "Holafly"
    };

    public static String[] EMPLOYEEDIVISION = {
            "Human Resources", "Administration", "Facilities Management", "IT Support", "Finance Department", "Legal Department", "Student Services", "Academic Affairs",
            "Admissions Office", "Registrar's Office", "Library Services", "Campus Security", "Counseling Services", "Research and Development", "Alumni Affairs",
            "Marketing Department", "Public Relations", "Operations Department", "Student Affairs", "Health and Wellness Center", "Career Services", "International Students Office",
            "Diversity and Inclusion Office", "Event Planning and Coordination", "Graduate Studies Office", "Undergraduate Admissions", "Campus Dining Services",
            "Technology Support", "Disability Services", "Residential Life", "Faculty Affairs", "Transportation Services", "Payroll Department", "Fundraising and Development",
            "Compliance and Ethics", "Procurement Department", "Student Housing", "Financial Aid Office", "Sports and Recreation", "Custodial Services", "Athletics Department",
            "Testing and Evaluation", "Student Counseling and Support", "Student Government", "Research Support Services", "Student Internship Program", "Study Abroad Program",
            "Office of the Dean", "Work Study Program", "Graduate Assistantships", "Office of Financial Management", "Building Maintenance", "Sustainability and Green Campus Program"
            };

    public static String[] STUDENTDIVISION = {
            "Department of Electrical Engineering", "Department of Mechanical Engineering", "Department of Civil Engineering",
            "Department of Computer Science", "Department of Aerospace Engineering", "Department of Chemical Engineering", "Department of Bioengineering",
            "Department of Industrial Engineering", "Department of Materials Science and Engineering", "Department of Environmental Engineering", "Department of Biomedical Engineering",
            "Department of Civil and Environmental Engineering", "Department of Nuclear Engineering", "Department of Agricultural Engineering", "Department of Energy Engineering",
            "Department of Architecture", "Department of Structural Engineering", "Department of Software Engineering", "Department of Robotics Engineering",
            "Department of Communication Systems", "Department of Information Technology", "Department of Computer Engineering", "Department of Automation Engineering",
            "Department of Geotechnical Engineering", "Department of Ocean Engineering", "Department of Transport Engineering", "Department of Electronics and Telecommunication",
            "Department of Sustainable Energy", "Department of Mechanical Design", "Department of Thermal Engineering", "Department of Earthquake Engineering",
            "Department of Systems Engineering", "Department of Electrical Power Engineering", "Department of Aerospace Systems", "Department of Nanotechnology Engineering",
            "Department of Automotive Engineering", "Department of Construction Management", "Department of Industrial and Systems Engineering", "Department of Data Science Engineering",
            "Department of Mechatronics Engineering", "Department of VLSI Engineering", "Department of Embedded Systems Engineering", "Department of Hydraulics Engineering",
            "Department of Thermal and Fluid Engineering", "Department of Electrical and Computer Engineering", "Department of Information Systems Engineering", "Department of Geospatial Engineering",
            "Department of Robotics and Automation", "Department of Structural and Civil Engineering", "Department of Electronics Engineering", "Department of Renewable Energy Engineering"
    };

    public static String[] HISTORYILLNESS = {
            "Huntington's Disease",
            "Cystic Fibrosis",
            "Sickle Cell Anemia",
            "Tay-Sachs Disease",
            "Hemophilia A",
            "Duchenne Muscular Dystrophy",
            "Becker Muscular Dystrophy",
            "Familial Hypercholesterolemia",
            "Neurofibromatosis Type 1",
            "Marfan Syndrome",
            "Down Syndrome (Trisomy 21)",
            "Phenylketonuria (PKU)",
            "Thalassemia",
            "Fragile X Syndrome",
            "Gaucher Disease",
            "Niemann-Pick Disease",
            "Albinism",
            "Achondroplasia",
            "Ehlers-Danlos Syndrome",
            "Osteogenesis Imperfecta",
            "Retinoblastoma",
            "Polycystic Kidney Disease",
            "Alpha-1 Antitrypsin Deficiency",
            "Wilson's Disease",
            "Hereditary Hemochromatosis",
            "Fanconi Anemia",
            "Bloom Syndrome",
            "Ataxia-Telangiectasia",
            "Wiskott-Aldrich Syndrome",
            "Severe Combined Immunodeficiency",
            "Lynch Syndrome",
            "Familial Adenomatous Polyposis",
            "Li-Fraumeni Syndrome",
            "Multiple Endocrine Neoplasia Type 1",
            "Multiple Endocrine Neoplasia Type 2",
            "Von Hippel-Lindau Disease",
            "Tuberous Sclerosis",
            "Prader-Willi Syndrome",
            "Angelman Syndrome",
            "Williams Syndrome",
            "Cri du Chat Syndrome",
            "Turner Syndrome",
            "Klinefelter Syndrome",
            "XYY Syndrome",
            "XXX Syndrome",
            "Noonan Syndrome",
            "Leber's Hereditary Optic Neuropathy",
            "Mitochondrial Encephalomyopathy",
            "Myotonic Dystrophy",
            "Friedreich's Ataxia",
            "Spinal Muscular Atrophy",
            "Amyotrophic Lateral Sclerosis (Familial)",
            "Hereditary Spastic Paraplegia",
            "Charcot-Marie-Tooth Disease",
            "Fabry Disease",
            "Maple Syrup Urine Disease",
            "Homocystinuria",
            "Galactosemia",
            "Glycogen Storage Diseases",
            "Mucopolysaccharidoses",
            "Pompe Disease",
            "Metachromatic Leukodystrophy",
            "Krabbe Disease",
            "Adrenoleukodystrophy",
            "Canavan Disease",
            "Batten Disease",
            "Rett Syndrome",
            "Cornelia de Lange Syndrome",
            "Rubinstein-Taybi Syndrome",
            "Aarskog-Scott Syndrome",
            "Costello Syndrome",
            "CHARGE Syndrome",
            "Waardenburg Syndrome",
            "Treacher Collins Syndrome",
            "Stickler Syndrome",
            "Osteopetrosis",
            "Cleidocranial Dysplasia",
            "Ellis-van Creveld Syndrome",
            "Hereditary Multiple Exostoses",
            "Apert Syndrome",
            "Crouzon Syndrome",
            "Pfeiffer Syndrome",
            "Thanatophoric Dysplasia",
            "Achondrogenesis",
            "Hypophosphatasia",
            "Oculocutaneous Albinism",
            "Xeroderma Pigmentosum",
            "Epidermolysis Bullosa",
            "Ichthyosis",
            "Ectodermal Dysplasia",
            "Peutz-Jeghers Syndrome",
            "Hereditary Diffuse Gastric Cancer",
            "Familial Dysautonomia",
            "Long QT Syndrome",
            "Brugada Syndrome",
            "Arrhythmogenic Right Ventricular Dysplasia",
            "Familial Hypertrophic Cardiomyopathy",
            "Dilated Cardiomyopathy",
            "Familial Hyperaldosteronism",
            "Congenital Nephrotic Syndrome",
            "Alport Syndrome",
            "Nail-Patella Syndrome",
            "Bartter Syndrome"
    };

    public static String[] RELATION = {
        "Friend",
                "Best Friend",
                "Close Friend",
                "Acquaintance",
                "Colleague",
                "Work Friend",
                "School Friend",
                "College Friend",
                "Childhood Friend",
                "Neighbor",
                "Family",
                "Parent",
                "Mother",
                "Father",
                "Sibling",
                "Brother",
                "Sister",
                "Child",
                "Son",
                "Daughter",
                "Grandparent",
                "Grandmother",
                "Grandfather",
                "Aunt",
                "Uncle",
                "Cousin",
                "Niece",
                "Nephew",
                "Spouse",
                "Wife",
                "Husband",
                "Partner",
                "Fiancé",
                "Fiancée",
                "Girlfriend",
                "Boyfriend",
                "Significant Other",
                "Ex",
                "Ex-Wife",
                "Ex-Husband",
                "Ex-Girlfriend",
                "Ex-Boyfriend",
                "Mentor",
                "Mentee",
                "Teacher",
                "Student",
                "Boss",
                "Manager",
                "Supervisor",
                "Employee",
                "Coworker",
                "Teammate",
                "Classmate",
                "Roommate",
                "Flatmate",
                "Housemate",
                "Landlord",
                "Tenant",
                "Doctor",
                "Patient",
                "Therapist",
                "Client",
                "Business Partner",
                "Business Associate",
                "Professional Contact",
                "Network Contact",
                "Friend of a Friend",
                "Mutual Friend",
                "Teammate",
                "Club Member",
                "Gym Buddy",
                "Workout Partner",
                "Travel Buddy",
                "Pen Pal",
                "Online Friend",
                "Gaming Friend",
                "Study Partner",
                "Tutor",
                "Coach",
                "Trainer",
                "Babysitter",
                "Nanny",
                "Caregiver",
                "Guardian",
                "Godparent",
                "Godchild",
                "Family Friend",
                "In-Law",
                "Mother-in-Law",
                "Father-in-Law",
                "Sister-in-Law",
                "Brother-in-Law",
                "Step-Parent",
                "Step-Sibling",
                "Half-Sibling",
                "Adopted Family",
                "Foster Family"
    };

}

class Examinee{
    public String id,  last_name, first_name;
    public LocalDate birthdate, date_of_exam;
    public Character middle_initial, sex, civil_status;
    public int age, year_of_exam;
    public String division, role, mobile_number, network, address_in_miagao;
    public String landlord_name, landlord_contact_number, guardian_name, guardian_address, guardian_relation, family_history_illness;
    private static final Random rand = new Random();

    Examinee(){
        id = generate_id();
        date_of_exam = generate_date();
        year_of_exam = date_of_exam.getYear();
        last_name = generate_last_name();
        first_name = generate_first_name();
        middle_initial = generate_middle_initial();
        birthdate = generate_birthdate();
        civil_status = generate_civil_status();
        age = generate_age();
        sex = generate_sex();
        role = generate_role();
        division = generate_division();
        mobile_number = generate_mobile_number();
        network = generateString(Constants.NETWORK);
        address_in_miagao = generate_address_in_miagao();
        landlord_name = generate_landlord_name(address_in_miagao);
        landlord_contact_number = generate_landlord_contact(address_in_miagao);
        guardian_name = generate_guardian_name();
        guardian_address = generate_guardian_address(guardian_name);
        guardian_relation = generate_guardian_relation(guardian_name);
        family_history_illness = generate_family_history_illness();
    }

    public void printExaminee() {
        System.out.println("Examinee Information:");
        System.out.println("---------------------");
        System.out.println("ID: " + id);
        System.out.println("Date of Exam: " + date_of_exam);
        System.out.println("Year of Exam: " + year_of_exam);
        System.out.println("Last Name: " + last_name);
        System.out.println("First Name: " + first_name);
        System.out.println("Middle Initial: " + (middle_initial == null ? "N/A" : middle_initial));
        System.out.println("Civil Status: " + civil_status);
        System.out.println("Birthdate: " + birthdate);
        System.out.println("Age: " + age);
        System.out.println("Sex: " + sex);
        System.out.println("Role: " + role);
        System.out.println("Division: " + division);
        System.out.println("Mobile Number: " + mobile_number);
        System.out.println("Network: " + network);
        System.out.println("Address in Miagao: " + (address_in_miagao != null ? address_in_miagao : "N/A"));
        System.out.println("Landlord Name: " + (landlord_name != null ? landlord_name : "N/A"));
        System.out.println("Landlord Contact Number: " + (landlord_contact_number != null ? landlord_contact_number : "N/A"));
        System.out.println("Guardian Name: " + (guardian_name != null ? guardian_name : "N/A"));
        System.out.println("Guardian Address: " + (guardian_address != null ? guardian_address : "N/A"));
        System.out.println("Guardian Relation: " + (guardian_relation != null ? guardian_relation : "N/A"));
        System.out.println("Family History of Illness: " + family_history_illness);
    }


    private String generate_address_in_miagao() {
        if (rand.nextDouble() < 0.4) {
            return Constants.ADDRESS[rand.nextInt(Constants.ADDRESS.length)];
        }
        return null;
    }

    private String generate_landlord_name(String addressInMiagao) {
        if (addressInMiagao == null) return null;

        return generate_first_name() + " " + generate_middle_initial() + ". " + generate_last_name();
    }

    private String generate_landlord_contact(String addressInMiagao) {
        if (addressInMiagao == null) return null;

        return generate_mobile_number();
    }

    private String generate_guardian_name() {
        if (rand.nextDouble() < 0.5) {
            return generate_first_name() + " " + generate_middle_initial() + ". " + generate_last_name();
        }
        return null;
    }

    private String generate_guardian_address(String guardianName) {
        if (guardianName == null) return null;

        return Constants.ADDRESS[rand.nextInt(Constants.ADDRESS.length)];
    }

    private String generate_guardian_relation(String guardianName) {
        if (guardianName == null) return null;

        return Constants.RELATION[rand.nextInt(Constants.RELATION.length)];
    }

    private String generate_family_history_illness() {
        int numberOfIllnesses = rand.nextInt(6); // 0 to 5
        if (numberOfIllnesses == 0) {
            return null;
        }

        List<String> illnessList = new ArrayList<>(Arrays.asList(Constants.HISTORYILLNESS));
        Collections.shuffle(illnessList, rand); // Randomize the list
        List<String> selected = illnessList.subList(0, Math.min(numberOfIllnesses, illnessList.size()));
        return String.join(", ", selected);
    }


    private String generate_mobile_number(){
        StringBuilder mobileNumber = new StringBuilder("09");

        while(mobileNumber.length() != 11){
            mobileNumber.append(rand.nextInt(0,10));
        }

        return mobileNumber.toString();
    }

    private String generate_id(){
        String year = generate_year();
        String number = String.valueOf(rand.nextInt(10000,99999));
        return year+"-"+number;
    }

    private String generate_year(){
        int startYear = 1990;
        int endYear = 2025;
        int randomYear = rand.nextInt(endYear - startYear + 1) + startYear;

        return String.valueOf(randomYear);
    }

    private String generate_first_name(){
        int randomIndex = rand.nextInt(Constants.FIRSTNAMES.length);  // random index from 0 to length-1

       return Constants.FIRSTNAMES[randomIndex];
    }

    private String generate_last_name(){
        int randomIndex = rand.nextInt(Constants.LASTNAMES.length);

        return Constants.LASTNAMES[randomIndex];
    }

    private String generate_division(){
        if(this.role.equals("Student")){
            return generateString(Constants.STUDENTDIVISION);
        }else{
            return generateString(Constants.EMPLOYEEDIVISION);
        }
    }

    private Character generate_middle_initial(){
        int hasMiddle = rand.nextInt(10);

        if(hasMiddle > 7){
            return null;
        }

        int randomIndex = rand.nextInt(Constants.MIDDLEINITIAL.length());
        return Constants.MIDDLEINITIAL.charAt(randomIndex);
    }

    private String generateString(String[] strArr){
        int randomIndex = rand.nextInt(strArr.length);  // random index from 0 to length-1

        return strArr[randomIndex];
    }

    private String generate_role(){
        int randomIndex = rand.nextInt(Constants.ROLE.length);
        return Constants.ROLE[randomIndex];
    }

    private LocalDate generate_date(){
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 12, 31);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = rand.nextLong(daysBetween + 1);

        LocalDate randomDate = startDate.plusDays(randomDays);
        return randomDate;
    }

    private LocalDate generate_birthdate(){
        LocalDate startDate = LocalDate.of(1990, 1, 1);
        LocalDate endDate = LocalDate.of(2007, 12, 31);

        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long randomDays = rand.nextLong(daysBetween + 1);

        LocalDate randomDate = startDate.plusDays(randomDays);
        return randomDate;
    }


    private int generate_age(){
        LocalDate today = LocalDate.now();
        if ((this.birthdate != null) && (this.birthdate.isBefore(today) || this.birthdate.isEqual(today))) {
            return Period.between(this.birthdate, today).getYears();
        } else {
            throw new IllegalArgumentException("Birthdate is invalid");
        }
    }

    private Character generate_sex(){
        String sex = "MF";
        int randomIndex = rand.nextInt(sex.length());
        return sex.charAt(randomIndex);
    }

    private Character generate_civil_status(){
        String civilStatus = "MSWDE";
        int randomIndex = rand.nextInt(civilStatus.length());
        return civilStatus.charAt(randomIndex);
    }


}

public class Prototyping {
    private Scanner sc = new Scanner(System.in);
    private final String url = "jdbc:mysql://localhost:3306/PulsePoint";
    private final String username = "root";
    private final String password = "admin";

    Prototyping(){
        try{
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Success");

            while(true) {
                Examinee examinee = new Examinee();
                examinee.printExaminee();
                if(sc.nextLine().equalsIgnoreCase("abort")){
                    break;
                }

                String cols = "examinee_id, year_of_exam, date_of_exam, last_name, first_name, middle_initial, age, sex, birthdate, civil_status, role, mobile_number, network, address_in_miagao, landlord_name, landlord_contact_number, guardian_name, guardian_address, guardian_relation, family_history_illness, division";
                String query = "INSERT INTO examinee (" + cols + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmnt = conn.prepareStatement(query);
                stmnt.setString(1, examinee.id);
                stmnt.setInt(2, examinee.year_of_exam);
                stmnt.setDate(3, Date.valueOf(examinee.date_of_exam));
                stmnt.setString(4, examinee.last_name);
                stmnt.setString(5, examinee.first_name);
                // middle_initial can be null, so handle it
                if (examinee.middle_initial == null) {
                    stmnt.setNull(6, java.sql.Types.CHAR);
                } else {
                    stmnt.setString(6, examinee.middle_initial.toString());
                }

                stmnt.setInt(7, examinee.age);

// sex can be null
                if (examinee.sex == null) {
                    stmnt.setNull(8, java.sql.Types.CHAR);
                } else {
                    stmnt.setString(8, examinee.sex.toString());
                }

                stmnt.setDate(9, Date.valueOf(examinee.birthdate));
                stmnt.setString(10, examinee.civil_status.toString());


                stmnt.setString(11, examinee.role);
                stmnt.setString(12, examinee.mobile_number);
                stmnt.setString(13, examinee.network);

// For address_in_miagao and others, if null, setNull to VARCHAR
                if (examinee.address_in_miagao == null) {
                    stmnt.setNull(14, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(14, examinee.address_in_miagao);
                }

                if (examinee.landlord_name == null) {
                    stmnt.setNull(15, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(15, examinee.landlord_name);
                }

                if (examinee.landlord_contact_number == null) {
                    stmnt.setNull(16, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(16, examinee.landlord_contact_number);
                }

                if (examinee.guardian_name == null) {
                    stmnt.setNull(17, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(17, examinee.guardian_name);
                }

                if (examinee.guardian_address == null) {
                    stmnt.setNull(18, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(18, examinee.guardian_address);
                }

                if (examinee.guardian_relation == null) {
                    stmnt.setNull(19, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(19, examinee.guardian_relation);
                }

                if (examinee.family_history_illness == null) {
                    stmnt.setNull(20, java.sql.Types.VARCHAR);
                } else {
                    stmnt.setString(20, examinee.family_history_illness);
                }
                stmnt.setString(21, examinee.division);


                // Execute the insert
                int rowsAffected = stmnt.executeUpdate();
                System.out.println("Inserted rows: " + rowsAffected);

                // Close PreparedStatement before next loop iteration
                stmnt.close();
                stmnt = null;
            }

        }catch(Exception e){
            System.out.println("Error Occured: "+ e);
        }
    }

    public static void main(String[] args) {
        new Prototyping();
    }

}
