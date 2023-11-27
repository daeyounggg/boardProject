package project.models.board.config;

import com.querydsl.core.BooleanBuilder;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import project.commons.ListData;
import project.commons.Pagination;
import project.controllers.admins.BoardSearch;
import project.entities.Board;
import project.repositories.BoardRepository;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class BoardConfigInfoService {
    private final BoardRepository repository;
    private final HttpServletRequest request;

    public Board get(String bId){
        Board data = repository.findById(bId).orElseThrow(BoardNotFoundException::new);

        return data;
    }

    public ListData<Board> getList(BoardSearch search){

        BooleanBuilder andBuilder = new BooleanBuilder();

        int page = search.getPage();
        int limit = search.getLimit();

        // Sort.Order.desc("엔티티 속성명"), Sort.order.asc("엔티티 속성명")

        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(desc("createdAt")));

        Page<Board> data = repository.findAll(andBuilder, pageable);

        Pagination pagination = new Pagination(page, (int)data.getTotalElements(), 10, limit, request);

        ListData<Board> listData = new ListData<>();
        listData.setContent(data.getContent());
        listData.setPagination(pagination);

        return listData;
    }
}
