package com.safehome;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
@CrossOrigin(origins = "*")
@RestController
public class HandleRequest {
    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody ResponseDto responseDto) {
        System.out.println(responseDto.getValue());
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "key=AAAAAP601jA:APA91bENsinXQa6joblZKxGGmoxh8R13CypW9wSP8fud7BA-oiFNCuxI5-QEGWDti5PIM1HBsbVa3RK-5OO1xU0UXLOwF2-_haP5YEVTxm_f5R57nFlSUBxStqifhefeZj2V5pm8XucU");

        try {
            JSONObject body = new JSONObject();
            body.put("to", "d4ey42idRIamo4louykYAi:APA91bFS99ep7TWG6GWYC3QTui-75Hii0A9jXX8XfTSh9FoKLD8bjWSuwXI776oU3QgOQYWmVUr70Elio2SXYCnEFlJOI2GFeRax8KtDU0fU2v1LxXHpCMEzbEqp7Gv2cTqFa5EdwFw1");
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("title", "SafeHome");
            notification.put("body", "SafeHome detected gas leakage at your home.");
            body.put("notification", notification);
        HttpEntity<String> request = new HttpEntity<String>(body.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity("https://fcm.googleapis.com/fcm/send", request, String.class);
        return response.getBody();
        }
        catch (Exception e){
            return "something went wrong";
        }

    }

    @PostMapping("/notice")
    public String noticeFromC(@RequestBody ResponseDto responseDto){
        System.out.println("response dto is "+responseDto.getValue());
        return "response dto received";
    }
    @GetMapping("/notice")
    public String noticeget(){
        return "response received";
    }
}

class ResponseDto{
    private String value;
    public ResponseDto(){

    }

    public ResponseDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}