package oneoneone.oneboard.service;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.BoardDTO;
import oneoneone.oneboard.entity.BoardEntity;
import oneoneone.oneboard.repository.BoardRepository;
import oneoneone.oneboard.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//  DTO -> Entity
//  Entity -> DTO
//  Entity Class는 Db와 연관이 있으므로 view에서는 노출하지 않도록 데이터 변환을 해보려고 한다

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;


    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity : boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    //  수동적인 쿼리를 수행 해야 할 경우 @Transactional 사용하여 영송성 컨텍스트들을 처리 하는 경우
    @Transactional
    public void updateHits(Long boardId) {
        boardRepository.updateHits(boardId);
    }

    public BoardDTO findById(Long boardId) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(boardId);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }
        else{
            return null;
        }
    }

}
