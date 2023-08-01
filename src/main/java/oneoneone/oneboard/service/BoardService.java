package oneoneone.oneboard.service;

import lombok.RequiredArgsConstructor;
import oneoneone.oneboard.dto.BoardDTO;
import oneoneone.oneboard.entity.BoardEntity;
import oneoneone.oneboard.entity.BoardFileEntity;
import oneoneone.oneboard.repository.BoardFileRepository;
import oneoneone.oneboard.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final BoardFileRepository boardFileRepository;
    public void save(BoardDTO boardDTO) throws IOException {
        //  파일 첨부 여부에 따라 로직 분리
        if(boardDTO.getBoardFile().isEmpty()){
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        }
        else{
        //  1. DTO 담긴 파일을 꺼내기
        //  2. 파일 이름 가져오기
        //  3. 서버 저장용 이름을 만들기
            // ex) apple.jpg ==> 23415151_apple.jpg 처럼 난수를 붙여 생성
        //  4. 저장 경로 설정
        //  5. 해당 경로에 파일 저장
        //  6. board_table에 해당 데이터 save 처리
        //  7. board_file_table에 해당 데이터 save 처리
            BoardEntity boardEntity = BoardEntity.toSaveFileEntity(boardDTO);
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity board= boardRepository.findById(saveId).get();
            for(MultipartFile boardFile : boardDTO.getBoardFile()){
            String originalFilename = boardFile.getOriginalFilename();  //  2
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename;    //  3
            //currentTimeMillis => 1970년01월01일을 기준으로 발생한 현재 시간 차이
            String savePath = "D:/springFolder/"+storedFileName;        //  4
            boardFile.transferTo(new File(savePath));                   //  5

            BoardFileEntity boardFileEntity =BoardFileEntity.
                    toBoardFileEntity(board,originalFilename,storedFileName);
            boardFileRepository.save(boardFileEntity);
            }

        }
    }
    //  부모 엔티티에서 자식 엔티티로 접근할 때는 @Transactional 어노테이션 사용 해야함.
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    //  부모 엔티티에서 자식 엔티티로 접근할 때는 @Transactional 어노테이션 사용 해야함.
    @Transactional
    public BoardDTO findById(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        } else {
            return null;
        }
    }
    @Transactional
    public BoardDTO update(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
        return findById(boardDTO.getId());
    }


    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber() - 1;
        int pageLimit = 3; // 한 페이지에 보여줄 글 갯수
        // 한페이지당 3개씩 글을 보여주고 정렬 기준은 id 기준으로 내림차순 정렬
        // page 위치에 있는 값은 0부터 시작
        Page<BoardEntity> boardEntities =
                boardRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id")));

        System.out.println("boardEntities.getContent() = " + boardEntities.getContent()); // 요청 페이지에 해당하는 글
        System.out.println("boardEntities.getTotalElements() = " + boardEntities.getTotalElements()); // 전체 글갯수
        System.out.println("boardEntities.getNumber() = " + boardEntities.getNumber()); // DB로 요청한 페이지 번호
        System.out.println("boardEntities.getTotalPages() = " + boardEntities.getTotalPages()); // 전체 페이지 갯수
        System.out.println("boardEntities.getSize() = " + boardEntities.getSize()); // 한 페이지에 보여지는 글 갯수
        System.out.println("boardEntities.hasPrevious() = " + boardEntities.hasPrevious()); // 이전 페이지 존재 여부
        System.out.println("boardEntities.isFirst() = " + boardEntities.isFirst()); // 첫 페이지 여부
        System.out.println("boardEntities.isLast() = " + boardEntities.isLast()); // 마지막 페이지 여부

        // 목록: id, writer, title, hits, createdTime
        Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(), board.getBoardWriter(), board.getBoardTitle(), board.getBoardHits(), board.getCreatedTime()));
        return boardDTOS;
    }

}