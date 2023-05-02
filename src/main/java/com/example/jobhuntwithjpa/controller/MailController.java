package com.example.jobhuntwithjpa.controller;

import com.example.jobhuntwithjpa.Service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/mail")
public class MailController {

    private MailSenderService mailSenderService;

    @Autowired
    public MailController(MailSenderService mailSenderService){
        this.mailSenderService = mailSenderService;
    }

    @GetMapping()
    public void testSendMail() throws Exception{
        mailSenderService.sendEmail("scg9268@naver.com", "test mail 입니다.", "뭘봐 임마 ㅋ");
    }
}
