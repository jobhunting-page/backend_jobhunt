package com.example.jobhuntwithjpa.controller;

import com.example.jobhuntwithjpa.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/mail")
public class MailController {

    private MailSenderService mailSenderService;

    @Autowired
    public MailController(MailSenderService mailSenderService){
        this.mailSenderService = mailSenderService;
    }

    @PostMapping()
    public String testSendMail(@RequestBody String mail) throws Exception{
        return mailSenderService.sendEmail(mail, "test mail 입니다.", "뭘봐 임마 ㅋ");
    }
}
