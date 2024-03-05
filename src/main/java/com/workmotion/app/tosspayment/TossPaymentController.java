package com.workmotion.app.tosspayment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.workmotion.app.member.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Controller
@RequestMapping("/tosspayment/*")
public class TossPaymentController {
    @Autowired
    private TossPaymentService tossPaymentService;

    @ResponseBody
    @PostMapping("server")
    public void server(@RequestBody TossPaymentDTO tossPayMentDTO, HttpSession session,
    		Model model) throws Exception {
        MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
        tossPayMentDTO.setCompany_id(memberDTO.getCompany_id());
        TossPaymentDTO buycheck = new TossPaymentDTO();
        buycheck.setCompany_id(memberDTO.getCompany_id());
        buycheck = tossPaymentService.getTossPaymentDetail(buycheck);

        if (buycheck == null) {

            int result1 = tossPaymentService.createTossPayment(tossPayMentDTO);

            String widgetSecretKey = "test_sk_jExPeJWYVQj9n09L47Lgr49R5gvN";
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes("UTF-8"));
            String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

            URL address = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String tossPay = mapper.writeValueAsString(tossPayMentDTO);
            OutputStream send = connection.getOutputStream();
            DataOutputStream datasane = new DataOutputStream(send);
            datasane.writeBytes(tossPay);
            datasane.close();
            int result = connection.getResponseCode();
        } else {
            tossPayMentDTO.setCreate_dt(buycheck.getCreate_dt());
            tossPayMentDTO.setPeriod(buycheck.getPeriod() + tossPayMentDTO.getPeriod());
            int result1 = tossPaymentService.updateTossPayment(tossPayMentDTO);

            String widgetSecretKey = "test_sk_jExPeJWYVQj9n09L47Lgr49R5gvN";
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] encodedBytes = encoder.encode((widgetSecretKey + ":").getBytes("UTF-8"));
            String authorizations = "Basic " + new String(encodedBytes, 0, encodedBytes.length);

            URL address = new URL("https://api.tosspayments.com/v1/payments/confirm");
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", authorizations);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String tossPay = mapper.writeValueAsString(tossPayMentDTO);    
            OutputStream send = connection.getOutputStream();
            DataOutputStream datasane = new DataOutputStream(send);
            datasane.writeBytes(tossPay);
            datasane.close();
            int result = connection.getResponseCode();
            
            	TossPaymentDTO tossPaymentDTO2 = new TossPaymentDTO();
            	tossPaymentDTO2.setCompany_id(memberDTO.getCompany_id());
            	tossPaymentDTO2 = tossPaymentService.getTossPaymentDetail(tossPaymentDTO2);
            String date1 = tossPayMentDTO.getCreate_dt();
           session.setAttribute("toss", tossPaymentDTO2.getPeriod());
        }

    }

}
