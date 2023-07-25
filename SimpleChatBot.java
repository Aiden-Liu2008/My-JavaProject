import java.util.Scanner;

public class SimpleChatBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hi, I'm your simple chatbot! What's your name?");
        String name = scanner.nextLine();
        System.out.println("Nice to meet you, " + name + "! How can I assist you today?");

        String input;
        while (true) {
            input = scanner.nextLine().toLowerCase();
            
            if (input.equals("hi") || input.equals("hello")) {
                System.out.println("Hello, " + name + "!");
            } else if (input.equals("how are you") || input.equals("how r u")) {
                System.out.println("I'm just a chatbot, but I'm here to help you!");
            } else if (input.equals("bye")) {
                System.out.println("Goodbye, " + name + "! Have a great day!");
                break;
            } else {
                System.out.println("I'm sorry, I don't understand. Could you please clarify?");
            }
        }

        scanner.close();
    }
}
