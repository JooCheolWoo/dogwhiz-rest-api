package com.galaxypoby.dogwhiz.mail;

import com.galaxypoby.dogwhiz.code.ErrorCode;
import com.galaxypoby.dogwhiz.common.CustomException;
import com.galaxypoby.dogwhiz.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendEmail(Member member, String title, String template) throws CustomException {
        MailHandler mailHandler = null;
        try {
            mailHandler = new MailHandler(mailSender);

            // Create a Thymeleaf context
            Context context = new Context();
            context.setVariable("email", member.getEmail());
            context.setVariable("emailKey", member.getEmailKey());
            context.setVariable("nickname", member.getNickname());

            // Generate the HTML body using Thymeleaf
            String htmlBody = templateEngine.process(template, context);

            mailHandler.setSubject(title);
            mailHandler.setText(htmlBody);  // true indicates that the text is HTML
            mailHandler.setFrom("galaxypoby@gmail.com", "도그위즈");
            mailHandler.setTo(member.getEmail());
            mailHandler.send();
            log.info(member.getEmail() + " 이메일 전송 완료!!");
        } catch (Exception e) {
            throw new CustomException(ErrorCode.FAIL_SEND_EMAIL);
        }
    }

}
