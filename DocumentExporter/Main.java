package DocumentExporter;

import java.util.Scanner;

interface Document {
    public void export (String content);
}

class PDF implements Document {
    @Override
    public void export (String content) {
        System.out.println("Exported to PDF: "+content);
    }
}

class CSV implements Document {
    @Override
    public void export (String content) {
        System.out.println("Exported to CSV: "+content);
    }
}

class XML implements Document {
    @Override
    public void export (String content) {
        System.out.println("Exported to XML: "+content);
    }
}

abstract class DocumentCreator {
    public abstract Document createDocument ();
    public void export (String content) {
        Document document = createDocument();
        document.export(content);
    }
}

class PDFCreator extends DocumentCreator {
    @Override
    public Document createDocument () {
        return new PDF();
    }
}

class CSVCreator extends DocumentCreator {
    @Override
    public Document createDocument () {
        return new CSV();
    }
}

class XMLCreator extends DocumentCreator {
    @Override
    public Document createDocument () {
        return new XML();
    }
}

public class Main {
    public static void main (String args[]) {
        System.out.println("1. PDF");
        System.out.println("2. CSV");
        System.out.println("3. XML");
        System.out.print("Enter Type: ");
        Scanner sc = new Scanner(System.in);

        int type = sc.nextInt();
        DocumentCreator creator;
        
        switch (type) {
            case 1:
                creator = new PDFCreator();
                creator.export("Hello");
                break;
            case 2:
                creator = new CSVCreator();
                creator.export("Hello");
                break;
            case 3:
                creator = new XMLCreator();
                creator.export("Hello");
                break;
            default:
                break;
        }

        sc.close();
    }
}
