package com.workmotion.app.project.dao;

import com.workmotion.app.project.model.ArticleDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ArticleDAO {
    @Autowired
    private SqlSession sqlSession;
    private final String NAMESPACE = "com.workmotion.app.project.dao.ArticleDAO.";

    public int createArticle(ArticleDTO articleDTO) {
        return sqlSession.insert(NAMESPACE + "createArticle", articleDTO);
    }

    public List<ArticleDTO> getArticleList(Map<String, Object> map) {
        return sqlSession.selectList(NAMESPACE + "getArticleList", map);
    }

    public int updateArticle(ArticleDTO articleDTO) {
        return sqlSession.update(NAMESPACE + "updateArticle", articleDTO);
    }

    public int deleteArticle(ArticleDTO articleDTO) {
        return sqlSession.delete(NAMESPACE + "deleteArticle", articleDTO);
    }
}
