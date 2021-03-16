package com.ex.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableJpaAuditing //jpa auditing 활성화를 위해 추가
@SpringBootApplication
public class BoardApplication {

	public static void main(String[] args) {

		SpringApplication.run(BoardApplication.class, args);
	}
		@Bean
		public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
			HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
			return filter;
		}
}


