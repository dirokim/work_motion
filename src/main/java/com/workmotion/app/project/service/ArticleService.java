package com.workmotion.app.project.service;


import com.workmotion.app.project.dao.ArticleDAO;
import com.workmotion.app.project.model.ArticleDTO;
import com.workmotion.app.project.model.ProjectDTO;
import com.workmotion.app.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    @Autowired
    private ArticleDAO articleDAO;

    public int createArticle(ArticleDTO articleDTO) throws Exception {
        return articleDAO.createArticle(articleDTO);
    }

    public List<ArticleDTO> getArticleList(ProjectDTO projectDTO, Pager pager) throws Exception {
        Map<String, Object> map = new HashMap<>();
        pager.setLastNum(pager.getPage() * 10);
        pager.setStartNum(pager.getLastNum() - 9);
        map.put("project", projectDTO);
        map.put("pager", pager);
        return articleDAO.getArticleList(map);
    }

    public int updateArticle(ArticleDTO articleDTO) throws Exception {
        return articleDAO.updateArticle(articleDTO);
    }

    public int deleteArticle(ArticleDTO articleDTO) throws Exception {
        return articleDAO.deleteArticle(articleDTO);
    }

}
