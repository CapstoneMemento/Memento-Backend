package start17.Memento.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import start17.Memento.config.ChatGPTConfig;
import start17.Memento.model.dto.chatGPT.ChatGPTRequestDto;
import start17.Memento.model.dto.chatGPT.ChatGPTResponseDto;
import start17.Memento.model.dto.chatGPT.QuestionRequestDto;

@Service
public class ChatGPTService {
    private static RestTemplate restTemplate = new RestTemplate();
    public HttpEntity<ChatGPTRequestDto> buildHttpEntity(ChatGPTRequestDto requestDto){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGPTConfig.MEDIA_TYPE));
        headers.add(ChatGPTConfig.AUTHORIZATION, ChatGPTConfig.BEARER + ChatGPTConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGPTResponseDto getResponse(HttpEntity<ChatGPTRequestDto>chatGPTRequestDtoHttpEntity){
        ResponseEntity<ChatGPTResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGPTConfig.URL,
                chatGPTRequestDtoHttpEntity,
                ChatGPTResponseDto.class);
        return responseEntity.getBody();
    }
    public String[] askQuestion(QuestionRequestDto requestDto){
        String message =  this.getResponse(
                this.buildHttpEntity(
                        new ChatGPTRequestDto(
                                ChatGPTConfig.MODEL,
                                requestDto.getQuestion(),
                                ChatGPTConfig.MAX_TOKEN,
                                ChatGPTConfig.TEMPERATURE,
                                ChatGPTConfig.TOP_P
                        )
                )
        ).getChoices().get(0).getText();
        message = message.substring(4);
        message = message.replaceAll("[/\n.]","");
        return message.split(", ");
    }
}
