package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.domain.note.Note;
import start17.Memento.model.dto.chatGPT.ChatGPTResponseDto;
import start17.Memento.model.dto.chatGPT.QuestionRequestDto;
import start17.Memento.model.dto.note.NoteSaveRequestDto;
import start17.Memento.service.ChatGPTService;
@Api(tags = "ChatGPT")

@RestController
@RequestMapping("/chat-gpt")
public class ChatGPTController {
    private final ChatGPTService chatGPTService;

    public ChatGPTController(ChatGPTService chatGPTService){

        this.chatGPTService = chatGPTService;
    }

    @ApiOperation(value = "ChatGPT에게 질문하기" , notes = "노트 저장 시 사용하던 객체를 넣어주면 String 배열로 키워드 반환함")
    @PostMapping("/question")
    public String[] sendQuestion(@RequestBody NoteSaveRequestDto note){
        String question = note.getContent();
        QuestionRequestDto requestDto = new QuestionRequestDto();
        requestDto.setQuestion("'"+ question +"' 각 문장 안에서 상징적인 단어들을 ','을 기준으로 나열해줘");
        return chatGPTService.askQuestion(requestDto);
    }
}
