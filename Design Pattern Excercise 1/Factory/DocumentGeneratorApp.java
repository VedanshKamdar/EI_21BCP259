package Factory;
import java.util.ArrayList;
import java.util.List;

// Product interface
interface Document {
    void open();
    void save();
    void close();
}

// Concrete products
class PDFDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document");
    }

    @Override
    public void close() {
        System.out.println("Closing PDF document");
    }
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document");
    }

    @Override
    public void close() {
        System.out.println("Closing Word document");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel document");
    }

    @Override
    public void save() {
        System.out.println("Saving Excel document");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel document");
    }
}

// Creator abstract class
abstract class DocumentCreator {
    public abstract Document createDocument();

    public void editDocument() {
        Document doc = createDocument();
        doc.open();
        System.out.println("Editing document content");
        doc.save();
        doc.close();
    }
}

// Concrete creators
class PDFCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new PDFDocument();
    }
}

class WordCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class ExcelCreator extends DocumentCreator {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Client code
public class DocumentGeneratorApp {
    public static void main(String[] args) {
        List<DocumentCreator> creators = new ArrayList<>();
        creators.add(new PDFCreator());
        creators.add(new WordCreator());
        creators.add(new ExcelCreator());

        for (DocumentCreator creator : creators) {
            System.out.println("Creating and editing document with " + creator.getClass().getSimpleName());
            creator.editDocument();
            System.out.println();
        }
    }
}