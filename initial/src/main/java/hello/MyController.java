package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private JmsTemplate jmsTemplate;


    @PostMapping("/{to}")
    public ResponseEntity<?> registerStudentForCourse(
            @PathVariable String to, @RequestBody String body) {

        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Email(to, body));

        return ResponseEntity.ok("");
    }
}
