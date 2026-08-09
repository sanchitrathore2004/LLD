package TextFormatter;

import java.util.Scanner;

interface FormatterStrategy {
    public void format(String text);
}

class UpperCaseFormatterStrategy implements FormatterStrategy {
    @Override
    public void format (String text) {
        String formattedText = text.toUpperCase();
        System.out.println("Formatted text: "+formattedText);
    }
}

class LowerCaseFormatterStrategy implements FormatterStrategy {
    @Override
    public void format (String text) {
        String formattedText = text.toLowerCase();
        System.out.println("Formatted text: "+formattedText);
    }
}

class TitleCaseFormatterStrategy implements FormatterStrategy {
    @Override
    public void format (String text) {
        String formattedText = new String();
        for (String word : text.split(" ")) {
            formattedText = formattedText + Character.toUpperCase(word.charAt(0)) + word.substring(1) + " ";
        }
        System.out.println("Formatted text: "+formattedText);
    }
}

class TextFormatterContext {
    private FormatterStrategy strategy;

    public void setStrategy (FormatterStrategy strategy) {
        this.strategy = strategy;
    }

    public void format (String text) {
        strategy.format(text);
    }
}

public class Main {
    public static void main (String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Select Formatter");
        System.out.println("1. Upper Case");
        System.out.println("2. Lower Case");
        System.out.println("3. Title Case");
        int type = sc.nextInt();
        sc.nextLine();
        System.out.println("Provide text");
        String text = sc.nextLine();
        TextFormatterContext context = new TextFormatterContext();
        switch (type) {
            case 1:
                context.setStrategy(new UpperCaseFormatterStrategy());
                context.format(text);
                break;
            case 2:
                context.setStrategy(new LowerCaseFormatterStrategy());
                context.format(text);
                break;
            case 3:
                context.setStrategy(new TitleCaseFormatterStrategy());
                context.format(text);
                break;
            default:
                break;
        }
        sc.close();
    }
}
