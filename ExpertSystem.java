import java.util.Scanner;

public class ExpertSystem {
    // Function to diagnose based on symptoms
    static void diagnose(String[] symptoms) {
        // Define possible diseases and their associated symptoms
        if (contains(symptoms, "fever") && contains(symptoms, "headache") && contains(symptoms, "body ache")) {
            System.out.println("Possible Diagnosis: You might have the Flu or a viral infection.");
        } else if (contains(symptoms, "cough") && contains(symptoms, "shortness of breath") && contains(symptoms, "fever")) {
            System.out.println("Possible Diagnosis: You might have a respiratory infection such as COVID-19 or Pneumonia.");
        } else if (contains(symptoms, "  chest pain") && contains(symptoms, "shortness of breath")) {
            System.out.println("Possible Diagnosis: You might be experiencing a Heart Attack. Seek medical attention immediately.");
        } else if (contains(symptoms, "headache") && contains(symptoms, "nausea")) {
            System.out.println("Possible Diagnosis: You might have a Migraine.");
        } else if (contains(symptoms, "abdominal pain") && contains(symptoms, "nausea") && contains(symptoms, "vomiting")) {
            System.out.println("Possible Diagnosis: You might have Gastroenteritis or Food Poisoning.");
        } else {
            System.out.println("I'm unable to diagnose. Please consult a medical professional for a proper diagnosis.");
        }
    }

    // Helper method to check if a symptom is in the user's input
    static boolean contains(String[] symptoms, String symptom) {
        for (String s : symptoms) {
            if (s.equals(symptom)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // List of symptoms provided by the user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Medical Expert System!");

        // Asking for symptoms
        System.out.println("Please enter your symptoms (separate symptoms by commas, e.g., fever, cough, headache):");
        String input = scanner.nextLine().toLowerCase();

        // Split the user input by commas and store symptoms in an array
        String[] symptoms = input.split(",");
        // Trim leading and trailing spaces from each symptom
        for (int i = 0; i < symptoms.length; i++) {
            symptoms[i] = symptoms[i].trim();
        }

        // Diagnose based on the symptoms
        diagnose(symptoms);
        scanner.close();
    }
}
