package start17.Memento.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import start17.Memento.domain.s3.FileEntity;
import start17.Memento.repository.FileRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;
//
//    public void save(FileDto fileDto) {
//        FileEntity fileEntity = new FileEntity(fileDto.getTitle(), fileDto.getUrl());
//        fileRepository.save(fileEntity);
//    }

    public List<FileEntity> getFiles() {
        List<FileEntity> all = fileRepository.findAll();
        return all;
    }
}
