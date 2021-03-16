package com.ex.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //테이블로 매핑하지 않고, 자식 클래스에게 매핑 정보를 상속하기 위한 어노테이션
@EntityListeners(AuditingEntityListener.class) //해당 entity는 Auditing 기능을 사용한다는 것을 알림
public abstract class TimeEntity { //데이터 조작 시 자동으로 날짜를 수정해주는 jpa의 auditing기능을 사용
    @CreatedDate //entity가 처음 생성될 때 생성일을 주입
    @Column(updatable = false) //생성일은 업데이트할 필요 없기 때문에 false 속성 추가
    private LocalDateTime createdDate;

    @LastModifiedDate //entity가 수정될 때 수정일자를 주입
    private LocalDateTime modifiedDate;
}
