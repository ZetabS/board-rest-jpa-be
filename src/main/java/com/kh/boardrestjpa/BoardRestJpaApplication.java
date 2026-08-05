package com.kh.boardrestjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.kh.boardrestjpa.board.model.repository.BoardRepository;
import com.kh.boardrestjpa.board.model.vo.Board;

@SpringBootApplication
public class BoardRestJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(BoardRestJpaApplication.class, args);
    }

    @Bean
    protected Logger logger() {
        return LoggerFactory.getLogger(BoardRestJpaApplication.class);
    }

    @Bean
    protected CommandLineRunner seed(BoardRepository boardRepository) {
        return (args) -> {
            if (boardRepository.count() == 0) {
                for (int i = 0; i < 23; i++) {
                    String title = "테스트 게시글 " + (i + 1) + "번";
                    String content = "테스트 게시글 " + i + "번 내용 "
                            + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas vestibulum fringilla leo malesuada feugiat. Proin sit amet lectus ac lectus lobortis luctus. Proin a accumsan dui, ut malesuada augue. Donec auctor augue at turpis mattis pretium. Nullam facilisis vitae felis sed maximus. Donec eget nisi ut dui tincidunt tristique. Fusce felis lectus, blandit quis iaculis at, dignissim aliquam risus. Pellentesque pretium dui ipsum, dictum pharetra enim posuere eu. Cras feugiat, eros ac lacinia sodales, lorem massa convallis libero, sed euismod ex massa et tortor. Nullam sit amet leo mi. Mauris a congue dolor. Vivamus consequat id erat et molestie. Sed eu orci at sem imperdiet pellentesque ut in lacus. Etiam vehicula quam ultricies, consequat nibh vitae, egestas metus. Aenean porta nulla vitae justo laoreet porta id non felis. Nunc aliquet mauris at ornare efficitur.";
                    Board board = new Board(title, content);
                    boardRepository.save(board);
                }
            }
        };
    }

}
