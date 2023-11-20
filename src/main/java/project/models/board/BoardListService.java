package project.models.board;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entities.BoardData;
import project.entities.QBoardData;

import java.util.List;

@Service
@Transactional
public class BoardListService {
    @PersistenceContext
    private EntityManager em;

    public List<BoardData> getList(){
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QBoardData boardData = QBoardData.boardData;
        JPAQuery<BoardData> query = queryFactory.selectFrom(boardData)
                .leftJoin(boardData.member)
                .fetchJoin();

        List<BoardData> items = query.fetch();

        return items;

    }
}
