package app.model;

import app.model.generic.Model;

public class Mail extends Model {
    public String mail;
    public String subject;
    public String body;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mail='" + mail + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                "} " + super.toString();
    }
}
