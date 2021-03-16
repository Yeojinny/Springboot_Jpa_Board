package com.ex.board.service;


import com.ex.board.domain.BoardEntity;
import com.ex.board.dto.BoardDto;
import com.ex.board.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {
    private BoardRepository boardRepository;

    //* 게시물 저장하기*//
    @Transactional //트랜잭션 : 데이터베이스에서 상태를 변환시키기 위해 수행하는 작업의 단위
    public Long savePost(BoardDto boardDto){

        return boardRepository.save(boardDto.toEntity()).getId();
        //save() : jparepository에 정의된 메서드로 db에 insert, update를 담당한다.
    }

    //* 게시물 전체 리스트 가져오기*//
    @Transactional
    public List<BoardDto> getBoardlist(){
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        //repository에서 가져온 entity를 반복문을 통해 dto로 변환하는 작업
        //controller와 service간에 전달은 dto 객체로 이루어짐
        for(BoardEntity boardEntity : boardEntities){
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }
        return boardDtoList;
    }

    //* 게시물 가져오기(상세조회용)*//
    @Transactional
    public BoardDto getPost(Long id){
        //findById() : PK 값을 where조건으로 하여 데이터를 가져오기 위한 메서드 (JpaRepository 인터페이스 함수)
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);

        //파라미터로 넘어온 id에 해당하는 게시물을 받고
        BoardEntity boardEntity = boardEntityWrapper.get();

        //dto형태로 전달하기위해 빌더
        BoardDto boardDTO = this.convertEntityToDto(boardEntity);

        return boardDTO;
    }

    /* 게시물 삭제하기*/
    @Transactional
    public void deletePost(Long id) {
        //deleteById : 데이터 삭제를 위한 메서
        boardRepository.deleteById(id);
    }


    /* 게시물 검색하기 */
    @Transactional
    public List<BoardDto> searchPosts(String keyword){
        List<BoardEntity> boardEntities = boardRepository.findByTitleContaining(keyword);
        List<BoardDto> boardDtoList = new ArrayList<>();

        if(boardEntities.isEmpty()) return boardDtoList;

        for(BoardEntity boardEntity : boardEntities){
            boardDtoList.add(this.convertEntityToDto(boardEntity));
        }

        return boardDtoList;
    }

    //Entity를 Dto로 변환하는 작업 => 함수처리
    private BoardDto convertEntityToDto(BoardEntity boardEntity){
        return BoardDto.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .writer(boardEntity.getWriter())
                .createdDate(boardEntity.getCreatedDate())
                .build();
    }
}
