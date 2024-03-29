package start17.Memento.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import start17.Memento.model.dto.s3.FileDto;
import start17.Memento.service.FileService;
import start17.Memento.service.S3Service;

import java.io.IOException;

@Api(tags = "S3File")
@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
@Slf4j
public class S3Controller {

    private final S3Service s3Service;
    private final FileService fileService;

    @ApiOperation(value = "이미지 업로드", notes = "file을 업로드하면 해당하는 url을 리턴한다.")
    @PostMapping("/upload")
    public String uploadFile(FileDto fileDto) throws IOException {
        String url = s3Service.upload(fileDto.getFile());
        fileDto.setUrl(url);

        return url;
    }
}
