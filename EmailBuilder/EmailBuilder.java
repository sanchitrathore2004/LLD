import java.util.ArrayList;
import java.util.List;

class Email {
    private String to;
    private String subject;
    private List<String> cc = new ArrayList<>();
    private List<String> bcc = new ArrayList<>();
    private String body;
    private String priority;
    private List<String> attachments = new ArrayList<>();

    private Email (Builder builder) {
        this.to = builder.to;
        this.subject = builder.subject;
        this.cc = builder.cc;
        this.bcc = builder.bcc;
        this.body = builder.body;
        this.priority = builder.priority;
        this.attachments = builder.attachments;
    }

    public void display () {
        if(priority!=null) System.out.println("priority: "+priority);
        System.out.println("TO: "+to);
        if(cc!=null) {
            for (String recipient : cc) {
                System.out.println("CC: "+recipient);
            }
        }
        System.out.println("Subject: "+subject);
        if(body!=null) System.out.println("Body: "+body);
        if(attachments!=null) {
            for (String attachment : attachments) {
                System.out.println("Attachments: "+attachment);
            }
        }
    }

    public static class Builder {
        private String to;
        private String subject;
        private List<String> cc = new ArrayList<>();
        private List<String> bcc = new ArrayList<>();
        private String body;
        private String priority;
        private List<String> attachments = new ArrayList<>();

        public Builder (String to, String subject) {
            this.to = to;
            this.subject = subject;
        }

        public Builder cc (String email) {
            this.cc.add(email);
            return this;
        }

        public Builder bcc (String email) {
            this.bcc.add(email);
            return this;
        }

        public Builder body (String body) {
            this.body = body;
            return this;
        }

        public Builder priority (String priority) {
            this.priority=priority;
            return this;
        }

        public Builder attachments (String attachment) {
            attachments.add(attachment);
            return this;
        }

        public Email build () {
            return new Email(this);
        }
    }
}

public class EmailBuilder {
    public static void main (String args[]) {
        Email email = new Email
        .Builder("rathoresanchit786@gmail.com", "SBI Elite CC Statement")
        .cc("rathoresanchit125@gmail.com")
        .body("Total CC limit used for the month of July 2026 is $1119.95. Last day of payment is 19th August 2026")
        .priority("Medium")
        .build();
        email.display();
    }
}
