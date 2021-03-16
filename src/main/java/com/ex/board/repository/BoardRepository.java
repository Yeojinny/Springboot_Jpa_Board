package com.ex.board.repository;

import com.ex.board.domain.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//Repository : 데이터 조작을 담당
//JpaRepository 상속 => 데이터 조작을 다루는 함수가 정의 되어 있기 때문에 crud 작업이 편해진다.
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    List<BoardEntity> findByTitleContaining(String keyword);
    //JpaRepository에서 메서드명의 By 이후는 sql의 where 조건 절에 대응된다.
    //Containing을 붙여주면 Like검색이 된다 => %{keyword}%
    //select*from movied where title like %in%;
}
