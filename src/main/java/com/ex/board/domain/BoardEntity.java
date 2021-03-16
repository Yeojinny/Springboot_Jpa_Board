package com.ex.board.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) //파라미터가 없는 기본 생성자를 추가해준다.
@Getter
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity {

    @Id //기본키임을 명시
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본키 생성전략 IDENTITY : 기본 키 생성을 데이터베이스에 위임
    private Long id;

    @Column(length = 10, nullable = false) //엔티티의 필드를 테이블의 칼럼에 매핑 length:문자 길이 제약 조건, nullable : null 값 허용 여부
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder //setter 사용 대신 빌더패턴을 사용해야 안정성 보장할 수 있다.
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}