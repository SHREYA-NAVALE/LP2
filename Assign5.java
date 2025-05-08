import java.util.Scanner;

public class Assign5 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! I am your virtual assistant. How can I help you today?");
        boolean isActive = true;

        while (isActive) {
            System.out.print("You: ");
            String userInput = scanner.nextLine().toLowerCase();

            if (userInput.contains("hello") || userInput.contains("hi")) {
                System.out.println("Chatbot: Hello! How can I assist you today?");
            } 
            else if (userInput.contains("help")) {
                System.out.println("Chatbot: I can help you with general inquiries. You can ask me about our services, hours, or products.");
            } 
            else if (userInput.contains("product") || userInput.contains("service")) {
                System.out.println("Chatbot: Our product is a top-tier software solution for businesses. We offer 24/7 support. Do you want more details?");
            } 
            else if (userInput.contains("hours") || userInput.contains("timing")) {
                System.out.println("Chatbot: We are open Monday to Friday from 9 AM to 6 PM.");
            } 
            else if (userInput.contains("thank you") || userInput.contains("thanks")) {
                System.out.println("Chatbot: You're welcome! Let me know if you need anything else.");
            } 
            else if (userInput.contains("bye") || userInput.contains("exit")) {
                System.out.println("Chatbot: Goodbye! Have a great day.");
                isActive = false;
            } 
            else {
                System.out.println("Chatbot: I'm sorry, I didn't quite understand that. Can you ask me something else?");
            }
        }

        scanner.close();
    }
}
