package start17.Memento.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.model.dto.chatGPT.ChatGPTResponseDto;
import start17.Memento.model.dto.chatGPT.QuestionRequestDto;
import start17.Memento.service.ChatGPTService;

@RestController
@RequestMapping("/keyword")
public class ChatGPTController {
    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService, ChatGPTService chatGPTService1){

        this.chatGPTService = chatGPTService1;
    }

    @PostMapping("/question")
    public ChatGPTResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto){
        return chatGPTService.askQuestion(requestDto);
    }
}
