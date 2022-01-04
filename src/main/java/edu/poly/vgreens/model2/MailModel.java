package edu.poly.vgreens.model2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {
    String from = "VGreens Market <vgreenMarket@gmail.com>";
    String to;
    String subject;
    String body;
    String[] cc;
    String[] bbc;
    String[] files;

    public MailModel(String to, String subject, String body) {
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
}
