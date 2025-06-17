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
            "Hypertension",
            "Tuberculosis",
            "B. Asthma",
            "Cancer-breast, colon",
            "Diabetes Mellitus",
            "Hepatitis",
            "Heart Disease",
            "Allergies"
    };

    public static String[] OTHERHISTORYILLNESS = {
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

    public static Object[][] medical_conditions = {
            {"Hypertension", new String[]{"Lisinopril", "Amlodipine", "Losartan"}},
            {"Type 2 Diabetes", new String[]{"Metformin", "Glipizide", "Insulin"}},
            {"Asthma", new String[]{"Salbutamol", "Fluticasone", "Montelukast"}},
            {"High Cholesterol", new String[]{"Atorvastatin", "Rosuvastatin"}},
            {"COPD", new String[]{"Tiotropium", "Salmeterol", "Fluticasone"}},
            {"GERD", new String[]{"Omeprazole", "Pantoprazole"}},
            {"Osteoarthritis", new String[]{"Acetaminophen", "Naproxen"}},
            {"Rheumatoid Arthritis", new String[]{"Methotrexate", "Hydroxychloroquine"}},
            {"Depression", new String[]{"Sertraline", "Fluoxetine"}},
            {"Anxiety Disorder", new String[]{"Alprazolam", "Buspirone"}},
            {"Hypothyroidism", new String[]{"Levothyroxine"}},
            {"Chronic Kidney Disease", new String[]{"Erythropoietin", "Sodium Bicarbonate"}},
            {"Gout", new String[]{"Allopurinol", "Colchicine"}},
            {"Migraine", new String[]{"Propranolol", "Topiramate"}},
            {"Epilepsy", new String[]{"Carbamazepine", "Valproate"}},
            {"Psoriasis", new String[]{"Methotrexate", "Ustekinumab"}},
            {"Multiple Sclerosis", new String[]{"Interferon beta", "Glatiramer"}},
            {"HIV/AIDS", new String[]{"Tenofovir", "Efavirenz", "Lamivudine"}},
            {"Parkinson’s Disease", new String[]{"Levodopa", "Carbidopa"}},
            {"Schizophrenia", new String[]{"Risperidone", "Olanzapine"}},
            {"Bipolar Disorder", new String[]{"Lithium", "Valproic acid"}},
            {"Ankylosing Spondylitis", new String[]{"Etanercept", "Infliximab"}},
            {"Chronic Pain", new String[]{"Gabapentin", "Tramadol"}},
            {"Benign Prostatic Hyperplasia", new String[]{"Tamsulosin", "Finasteride"}},
            {"Coronary Artery Disease", new String[]{"Aspirin", "Atorvastatin", "Metoprolol"}},
            {"Atrial Fibrillation", new String[]{"Warfarin", "Apixaban", "Amiodarone"}},
            {"Peptic Ulcer Disease", new String[]{"Omeprazole", "Ranitidine"}},
            {"Chronic Hepatitis B", new String[]{"Entecavir", "Tenofovir"}},
            {"Chronic Hepatitis C", new String[]{"Sofosbuvir", "Ledipasvir"}},
            {"Systemic Lupus Erythematosus", new String[]{"Hydroxychloroquine", "Prednisone"}},
            {"Ulcerative Colitis", new String[]{"Mesalamine", "Azathioprine"}},
            {"Crohn's Disease", new String[]{"Infliximab", "Adalimumab"}},
            {"Polycystic Ovary Syndrome", new String[]{"Metformin", "Oral contraceptives"}},
            {"Endometriosis", new String[]{"Norethindrone", "GnRH agonists"}},
            {"Heart Failure", new String[]{"Furosemide", "Lisinopril", "Spironolactone"}},
            {"Chronic Sinusitis", new String[]{"Fluticasone nasal spray"}},
            {"Obstructive Sleep Apnea", new String[]{"CPAP therapy (non-drug)"}},
            {"Thalassemia", new String[]{"Folic acid", "Deferoxamine"}},
            {"Hemophilia", new String[]{"Factor VIII", "Desmopressin"}},
            {"Cystic Fibrosis", new String[]{"Ivacaftor", "Dornase alfa"}},
            {"Sickle Cell Disease", new String[]{"Hydroxyurea", "Folic acid"}},
            {"Tuberculosis", new String[]{"Isoniazid", "Rifampin"}},
            {"Glaucoma", new String[]{"Latanoprost", "Timolol"}},
            {"Macular Degeneration", new String[]{"Ranibizumab", "Aflibercept"}},
            {"Chronic Fatigue Syndrome", new String[]{"Amitriptyline", "Duloxetine"}},
            {"Fibromyalgia", new String[]{"Pregabalin", "Milnacipran"}},
            {"Tinnitus", new String[]{"Nortriptyline", "Zinc supplements"}},
            {"Vertigo", new String[]{"Meclizine", "Betahistine"}},
            {"Hyperthyroidism", new String[]{"Methimazole", "Propranolol"}},
            {"Addison’s Disease", new String[]{"Hydrocortisone", "Fludrocortisone"}},
            {"Cushing’s Syndrome", new String[]{"Ketoconazole", "Metyrapone"}},
            {"Acne Vulgaris", new String[]{"Benzoyl peroxide", "Isotretinoin"}},
            {"Rosacea", new String[]{"Metronidazole gel", "Doxycycline"}},
            {"Chronic Urticaria", new String[]{"Cetirizine", "Montelukast"}},
            {"Panic Disorder", new String[]{"Paroxetine", "Clonazepam"}},
            {"Obsessive-Compulsive Disorder", new String[]{"Fluoxetine", "Clomipramine"}},
            {"Tourette Syndrome", new String[]{"Haloperidol", "Clonidine"}},
            {"Narcolepsy", new String[]{"Modafinil", "Sodium oxybate"}},
            {"Restless Legs Syndrome", new String[]{"Ropinirole", "Pramipexole"}},
            {"Pulmonary Hypertension", new String[]{"Sildenafil", "Bosentan"}},
            {"Raynaud’s Disease", new String[]{"Nifedipine", "Losartan"}},
            {"Eczema", new String[]{"Hydrocortisone cream", "Tacrolimus"}},
            {"Chronic Back Pain", new String[]{"Naproxen", "Duloxetine"}},
            {"Tension Headaches", new String[]{"Amitriptyline", "Ibuprofen"}},
            {"Cluster Headaches", new String[]{"Verapamil", "Sumatriptan"}},
            {"Lactose Intolerance", new String[]{"Lactase enzyme supplements"}},
            {"Celiac Disease", new String[]{"Gluten-free diet (no meds)"}},
            {"IBS", new String[]{"Dicyclomine", "Loperamide"}},
            {"Fatty Liver Disease", new String[]{"Vitamin E", "Pioglitazone"}},
            {"Pneumonia (Chronic)", new String[]{"Azithromycin (for prevention)"}},
            {"Chronic Pancreatitis", new String[]{"Pancreatic enzymes", "Omeprazole"}},
            {"Nephrotic Syndrome", new String[]{"Prednisone", "Furosemide"}},
            {"Interstitial Cystitis", new String[]{"Amitriptyline", "Pentosan polysulfate"}},
            {"Urinary Incontinence", new String[]{"Oxybutynin", "Mirabegron"}},
            {"Prostatitis", new String[]{"Ciprofloxacin", "Alpha-blockers"}},
            {"Menopause Symptoms", new String[]{"Estradiol", "Medroxyprogesterone"}},
            {"Andropause", new String[]{"Testosterone replacement"}},
            {"Allergic Rhinitis", new String[]{"Loratadine", "Fluticasone"}},
            {"Psoriatic Arthritis", new String[]{"Methotrexate", "Adalimumab"}},
            {"Eosinophilic Esophagitis", new String[]{"Budesonide (oral suspension)"}},
            {"Barrett’s Esophagus", new String[]{"Esomeprazole", "Lansoprazole"}},
            {"Anemia", new String[]{"Ferrous sulfate", "Erythropoietin"}},
            {"Hypercalcemia", new String[]{"Pamidronate", "Calcitonin"}},
            {"Hypocalcemia", new String[]{"Calcium carbonate", "Calcitriol"}},
            {"Hyperkalemia", new String[]{"Sodium polystyrene sulfonate", "Patiromer"}},
            {"Hypokalemia", new String[]{"Potassium chloride"}},
            {"Pancreatic Cancer", new String[]{"Gemcitabine", "Paclitaxel"}},
            {"Liver Cirrhosis", new String[]{"Lactulose", "Spironolactone"}},
            {"GERD (Refractory)", new String[]{"Esomeprazole", "Famotidine"}},
            {"Reflux Laryngitis", new String[]{"Omeprazole", "Gaviscon"}},
            {"BPH (Severe)", new String[]{"Dutasteride", "Tamsulosin"}},
            {"Meniere’s Disease", new String[]{"Betahistine", "Diuretics"}},
            {"Myasthenia Gravis", new String[]{"Pyridostigmine", "Prednisone"}},
            {"Amyotrophic Lateral Sclerosis", new String[]{"Riluzole", "Edaravone"}},
            {"Huntington’s Disease", new String[]{"Tetrabenazine", "Olanzapine"}},
            {"Alcohol Dependence", new String[]{"Disulfiram", "Naltrexone"}},
            {"Opioid Dependence", new String[]{"Methadone", "Buprenorphine"}}
    };

    public static String[] immunizations = {
            "MMR", "Dtap/Tetanus", "Varicella", "Hepatits B", "Influenza", "Pneumonia"
    };

    public static String[] othersimmunizations = {"Hepatitis A",
            "Hepatitis B",
            "Influenza (Flu)",
            "Tetanus",
            "Diphtheria",
            "Pertussis (Whooping Cough)",
            "MMR (Measles, Mumps, Rubella)",
            "Polio (IPV)",
            "Varicella (Chickenpox)",
            "HPV (Human Papillomavirus)",
            "Meningococcal ACWY",
            "Meningococcal B",
            "Pneumococcal (PCV13)",
            "Pneumococcal (PPSV23)",
            "COVID-19 (Pfizer)",
            "COVID-19 (Moderna)",
            "COVID-19 (Novavax)",
            "Rotavirus (RV1)",
            "Rotavirus (RV5)",
            "Haemophilus influenzae type b (Hib)",
            "Typhoid (Injectable)",
            "Typhoid (Oral)",
            "Cholera",
            "Yellow Fever",
            "Rabies (pre-exposure)",
            "Rabies (post-exposure)",
            "Japanese Encephalitis",
            "Tick-borne Encephalitis",
            "BCG (Tuberculosis)",
            "Zoster (Shingles – Shingrix)",
            "Zoster (Zostavax)",
            "Malaria (RTS,S/AS01 – Mosquirix)",
            "Dengue (Dengvaxia)",
            "Smallpox",
            "Monkeypox (JYNNEOS)",
            "Anthrax",
            "Ebola (Ervebo)",
            "RSV (Respiratory Syncytial Virus)",
            "Adenovirus (military only)",
            "Mumps (standalone)",
            "Measles (standalone)",
            "Rubella (standalone)",
            "TDaP (Tetanus, Diphtheria, Pertussis)",
            "DTaP (Pediatric TDaP)",
            "Td (Tetanus, Diphtheria only)",
            "Hepatitis A and B (Twinrix)",
            "Influenza (High-dose)",
            "Influenza (Live attenuated)",
            "Influenza (Intradermal)",
            "Influenza (Recombinant)",
            "Hib-MenCY (MenHibrix)",
            "MMRV (ProQuad)",
            "Comvax (Hib + Hep B)",
            "Pentacel (DTaP + IPV + Hib)",
            "Kinrix (DTaP + IPV)",
            "Pediarix (DTaP + IPV + Hep B)",
            "Boostrix (TDaP)",
            "Adacel (TDaP)",
            "Heplisav-B (Hepatitis B)",
            "Vaxneuvance (PCV15)",
            "Prevnar 20 (PCV20)",
            "Menactra (Meningococcal ACWY)",
            "MenQuadfi (Meningococcal ACYW)",
            "Bexsero (Meningococcal B)",
            "Trumenba (Meningococcal B)",
            "Gardasil 9 (HPV)",
            "Pneumovax 23",
            "Synflorix (PCV10)",
            "Imovax Rabies",
            "Verorab (Rabies)",
            "Avaxim (Hepatitis A)",
            "Vivotif (Typhoid Oral)",
            "Typhim Vi (Typhoid Injectable)",
            "IXIARO (Japanese Encephalitis)",
            "Qdenga (Dengue)",
            "BioThrax (Anthrax)",
            "ACAM2000 (Smallpox)",
            "TicoVac (Tick-borne Encephalitis)",
            "Jynneos (Monkeypox/Smallpox)",
            "Ervebo (Ebola)",
            "Arexvy (RSV, older adults)",
            "Abrysvo (RSV, maternal)",
            "Vaxchora (Cholera)",
            "Fluzone Quadrivalent",
            "Flucelvax",
            "Afluria",
            "Agriflu",
            "Influvac",
            "Flublok",
            "FluMist",
            "Shingrix",
            "Zostavax",
            "Menveo (Meningococcal ACWY)",
            "Engerix-B (Hep B)",
            "Recombivax HB (Hep B)",
            "Havrix (Hep A)",
            "Vaqta (Hep A)",
            "Twinrix (Hep A + B)",
            "Infanrix (DTaP)",
            "Pentaxim (DTaP + IPV + Hib)",
            "Hexaxim (DTaP + IPV + HepB + Hib)"
        };

    public static String[] vaccine_remarks = {
            "Highly effective in preventing infection.",
            "Administered as a series of doses.",
            "Requires booster shots for continued immunity.",
            "Recommended for all age groups.",
            "Important for travelers to certain regions.",
            "May cause mild side effects such as fever.",
            "Long-term immunity after full vaccination.",
            "Preventative for highly contagious diseases.",
            "Protects against life-threatening infections.",
            "Recommended for infants and young children.",
            "Must be administered in a healthcare setting.",
            "Shown to reduce the spread of disease.",
            "Can prevent complications like hospitalization.",
            "Needs to be updated regularly (e.g., flu shots).",
            "Essential for herd immunity.",
            "Provides protection against multiple strains.",
            "Can be given at various stages of life.",
            "Safety monitored through extensive clinical trials.",
            "Widely available and accessible worldwide.",
            "Required for school entry in many areas.",
            "May cause soreness at the injection site.",
            "Immunity develops within weeks after vaccination.",
            "Recommended during pregnancy for certain vaccines.",
            "Helps prevent outbreaks of infectious diseases.",
            "Effective in reducing disease transmission.",
            "Widely recognized as a public health success.",
            "Helps reduce healthcare costs by preventing disease.",
            "May need special storage conditions (e.g., cold chain).",
            "Has significantly reduced global disease burden.",
            "Ongoing research to improve effectiveness and coverage.",
            "Should be part of routine immunization schedules.",
            "In some cases, vaccination can prevent cancer.",
            "Contains inactive or weakened forms of the virus.",
            "Protection lasts for several years or a lifetime.",
            "May cause mild symptoms similar to the illness.",
            "Essential for protecting vulnerable populations.",
            "Available in combination vaccines for convenience.",
            "Can reduce the spread of antibiotic-resistant bacteria.",
            "Requires informed consent before administration.",
            "Usually covered by health insurance programs.",
            "Can be administered via injection or nasal spray.",
            "New formulations are continuously developed.",
            "Supports global efforts to eradicate diseases.",
            "May need to be updated based on emerging strains.",
            "The cost is typically low and accessible.",
            "Could be mandatory for healthcare workers.",
            "Can be administered alongside other vaccines.",
            "May cause allergic reactions in rare cases.",
            "Ensures immunity even if exposed to the virus.",
            "Helps protect vulnerable groups like the elderly.",
            "Requires careful tracking and record-keeping.",
            "Recommends vaccination before exposure to the virus.",
            "Vaccination programs have proven to save lives.",
            "May reduce the severity of infection if contracted.",
            "Required for certain high-risk professions."
    };
    public static String[] dose = {"1st Dose", "2nd Dose", "3rd Dose", "4th Dose", "5th Dose", "Booster"};
}

class ExamineeObj{
    public String id,  last_name, first_name;
    public LocalDate birthdate, date_of_exam;
    public Date[] immunization_date = new Date[6];
    public Character middle_initial, sex, civil_status;
    public int age, year_of_exam;
    public String division, role, mobile_number, network, address_in_miagao;
    public String landlord_name, landlord_contact_number, guardian_mobile_number, guardian_name, guardian_address, guardian_relation, guardian_network, family_history_illness;
    public Object[][] medical_conditions = new Object[3][3];
    public String[] dose = new String[6], immunization = new String[6], vaccine_remarks = new String[6];
   private static final Random rand = new Random();

    ExamineeObj(){
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
        guardian_network = generate_guardian_network();
        guardian_mobile_number = generate_mobile_number();
        family_history_illness = generate_family_history_illness();

        // Medical Conditions
        generate_medical_conditions();

        // Immunizations
        generate_immunizations();
    }

    public void generate_medical_conditions(){
        if (rand.nextDouble() < 0.7) {
            int howMany = rand.nextInt(3) + 1; // 1 to 3
            Set<Integer> chosenIndices = new HashSet<>();

            while (chosenIndices.size() < howMany) {
                chosenIndices.add(rand.nextInt(Constants.medical_conditions.length));
            }

            int i = 0;
            for (int index : chosenIndices) {
                medical_conditions[i][0] = Constants.medical_conditions[index][0];
                medical_conditions[i][1] = Constants.medical_conditions[index][1];
                // 50% chance to assign a date
                if (rand.nextDouble() < 0.5) {
                    medical_conditions[i][2] = generate_date();
                } else {
                    medical_conditions[i][2] = null;
                }
                i++;
            }
        } else {
            System.out.println("No medical conditions assigned (30% chance).");
        }
    }

    public void generate_immunizations() {
        if (rand.nextDouble() <= 0.7) {
            int count = rand.nextInt(6) + 1;  // 1 to 6 immunizations
            boolean addOther = rand.nextDouble() <= 0.5;  // 50% chance to add from otherimmunization
            int total = count + (addOther ? 1 : 0);

            immunization = new String[total];
            immunization_date = new Date[total];
            vaccine_remarks = new String[total];
            dose = new String[total];

            // Pick unique random indexes for regular immunizations
            Set<Integer> chosenIndexes = new HashSet<>();
            while (chosenIndexes.size() < count) {
                chosenIndexes.add(rand.nextInt(Constants.immunizations.length));
            }

            int i = 0;
            for (int idx : chosenIndexes) {
                immunization[i] = Constants.immunizations[idx];

                // 80% chance to assign a generated date
                if (rand.nextDouble() <= 0.8) {
                    immunization_date[i] = Date.valueOf(generate_date());
                } else {
                    immunization_date[i] = null;
                }

                // 30% chance to assign remarks string
                if (rand.nextDouble() <= 0.3) {
                    vaccine_remarks[i] = generateString(Constants.vaccine_remarks);
                } else {
                    vaccine_remarks[i] = null;
                }

                dose[i] = Constants.dose[rand.nextInt(Constants.dose.length)];
                i++;
            }

            // Add one from otherimmunization if chosen
            if (addOther && Constants.othersimmunizations.length > 0) {
                int otherIdx = rand.nextInt(Constants.othersimmunizations.length);
                immunization[i] = Constants.othersimmunizations[otherIdx];

                // Generate same format for date, remarks, and dose
                if (rand.nextDouble() <= 0.8) {
                    immunization_date[i] = Date.valueOf(generate_date());
                } else {
                    immunization_date[i] = null;
                }

                if (rand.nextDouble() <= 0.3) {
                    vaccine_remarks[i] = generateString(Constants.vaccine_remarks);
                } else {
                    vaccine_remarks[i] = null;
                }

                dose[i] = Constants.dose[rand.nextInt(Constants.dose.length)];
            }

        } else {
            immunization = new String[0];
            immunization_date = new Date[0];
            vaccine_remarks = new String[0];
            dose = new String[0];
        }
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
        System.out.println("Guardian Network: " + (guardian_network != null ? guardian_network : "N/A"));
        System.out.println("Family History of Illness: " + family_history_illness);
    }

    private String generate_guardian_network(){
        if(this.guardian_name == null){return null;}

        return generateString(Constants.NETWORK);
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
        int numberOfIllnesses = rand.nextInt(8); // 0 to 7 inclusive
        List<String> selected = new ArrayList<>();
        if (numberOfIllnesses > 0) {
            List<String> illnessList = new ArrayList<>(Arrays.asList(Constants.HISTORYILLNESS));
            Collections.shuffle(illnessList, rand); // Randomize the list
            selected.addAll(illnessList.subList(0, Math.min(numberOfIllnesses, illnessList.size())));
        }
        // 50% chance to include one random illness from OTHERHISTORYILLNESS
        if (rand.nextBoolean()) {
            String[] otherIllnesses = Constants.OTHERHISTORYILLNESS;
            String randomOtherIllness = otherIllnesses[rand.nextInt(otherIllnesses.length)];
            selected.add(randomOtherIllness);
        }
        if (selected.isEmpty()) {
            return null;
        }
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

            ExamineeObj examinee = new ExamineeObj();
            examinee.printExaminee();


            String cols = "examinee_id, year_of_exam, date_of_exam, last_name, first_name, middle_initial, age, sex, birthdate, civil_status, role, mobile_number, network, address_in_miagao, landlord_name, landlord_contact_number, guardian_name, guardian_address, guardian_relation, family_history_illness, division, guardian_network, guardian_mobile_number";
            String query = "INSERT INTO examinee (" + cols + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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

            if (examinee.guardian_network == null) {
                stmnt.setNull(22, java.sql.Types.VARCHAR);
            } else {
                stmnt.setString(22, examinee.guardian_network);
            }

            if (examinee.guardian_mobile_number == null) {
                stmnt.setNull(23, java.sql.Types.VARCHAR);
            } else {
                stmnt.setString(23, examinee.guardian_mobile_number);
            }


            // Execute the insert
            int rowsAffected = stmnt.executeUpdate();
            System.out.println("Inserted rows: " + rowsAffected);


            String insertSql = "INSERT INTO current_medical_condition (examinee_id, medical_condition, condition_identified_on, maintenance_medication) VALUES (?,?,?,?)";
            stmnt = conn.prepareStatement(insertSql);
            for (int i = 0; i < examinee.medical_conditions.length; i++) {
                Object[] conditionRow = examinee.medical_conditions[i];
                if(conditionRow[0] == null){
                    continue;
                }
                stmnt.setString(1, examinee.id);

                // medical_condition at index 0, cast to String
                String medicalCondition = (conditionRow[0] != null) ? conditionRow[0].toString() : null;
                stmnt.setString(2, medicalCondition);

                // condition_identified_on at index 2, cast to LocalDate or String and convert to java.sql.Date
                if (conditionRow[2] != null) {
                    // assuming conditionRow[2] is a LocalDate or a String formatted yyyy-MM-dd
                    java.sql.Date sqlDate;
                    if (conditionRow[2] instanceof java.time.LocalDate) {
                        sqlDate = java.sql.Date.valueOf((java.time.LocalDate) conditionRow[2]);
                    } else {
                        sqlDate = java.sql.Date.valueOf(conditionRow[2].toString());
                    }
                    stmnt.setDate(3, sqlDate);
                } else {
                    stmnt.setNull(3, java.sql.Types.DATE);
                }

                // maintenance_medication at index 1
                if (conditionRow[1] != null && conditionRow[1] instanceof String[]) {
                    String[] meds = (String[]) conditionRow[1];
                    String maintenanceMed = String.join(", ", meds); // join into a single string
                    stmnt.setString(4, maintenanceMed);
                } else if (conditionRow[1] != null) {
                    stmnt.setString(4, conditionRow[1].toString());
                } else {
                    stmnt.setNull(4, java.sql.Types.VARCHAR);
                }

                stmnt.executeUpdate();
            }

            insertSql = "INSERT INTO immunization_background (examinee_id, vaccine_name, vaccine_given_date, vaccine_dose, vaccine_remarks) VALUES (?,?,?,?,?)";
            stmnt = conn.prepareStatement(insertSql);

            for (int i = 0; i < examinee.immunization.length; i++) {
                if (examinee.immunization[i] != null && !examinee.immunization[i].isEmpty()) {
                    stmnt.setString(1, examinee.id);  // examinee_id
                    stmnt.setString(2, examinee.immunization[i]);  // vaccine_name

                    if (examinee.immunization_date[i] != null) {
                        stmnt.setDate(3, examinee.immunization_date[i]);  // vaccine_given_date
                    } else {
                        stmnt.setNull(3, java.sql.Types.DATE);
                    }

                    if (examinee.dose[i] != null && !examinee.dose[i].isEmpty()) {
                        stmnt.setString(4, examinee.dose[i]);  // vaccine_dose
                    } else {
                        stmnt.setNull(4, java.sql.Types.VARCHAR);
                    }

                    if (examinee.vaccine_remarks[i] != null && !examinee.vaccine_remarks[i].isEmpty()) {
                        stmnt.setString(5, examinee.vaccine_remarks[i]);  // vaccine_remarks
                    } else {
                        stmnt.setNull(5, java.sql.Types.VARCHAR);
                    }

                    stmnt.executeUpdate();
                }
            }

        }catch(Exception e){
            System.out.println("Error Occured: "+ e);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("type auto for automatic mode, type anything for manual mode: ");
        if(sc.nextLine().equalsIgnoreCase("auto")){
            while(true) {
                System.out.print("type abort to exit, enter to populate the database: ");
                if (sc.nextLine().equalsIgnoreCase("abort")) {
                    break;
                }

                System.out.print("Enter number of data to populate the database: ");
                int num = sc.nextInt();
                for (int i = 0; i < num; i++) {
                    new Prototyping();
                }
                System.out.println("Done");
            }
        }

        while(true) {
            System.out.print("type abort to exit, enter to populate the database: ");
            if (sc.nextLine().equalsIgnoreCase("abort")) {
                break;
            }
            new Prototyping();
        }
    }

}
