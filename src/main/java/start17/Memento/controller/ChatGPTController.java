package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.model.dto.chatGPT.ChatGPTResponseDto;
import start17.Memento.model.dto.chatGPT.QuestionRequestDto;
import start17.Memento.service.ChatGPTService;
@Api(tags = "ChatGPT")
@RestController
@RequestMapping("/keyword")
public class ChatGPTController {
    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService, ChatGPTService chatGPTService1){

        this.chatGPTService = chatGPTService1;
    }

    @ApiOperation(value = "ChatGPT에게 질문하기" , notes = "question에 '해당 판례 본문 내용' + '\n위 글에서 각 문장 내에서 중요한 단어들을 ','로 구분해서 나열해줘' 넣어줌 ")
    @PostMapping("/question")
    public ChatGPTResponseDto sendQuestion(@RequestBody QuestionRequestDto requestDto){
        return chatGPTService.askQuestion(requestDto);
    }
}
