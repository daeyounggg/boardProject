package project.models.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.entities.BoardData;
import project.repositories.BoardDataRepository;

@Service
@RequiredArgsConstructor
public class BoardInfoService {

    private final BoardDataRepository boardDataRepository;

    public BoardData get(Long seq) {

        BoardData data = boardDataRepository.findById(seq).orElseThrow(BoardDataNotFoundException::new);

        return data;
    }
}