package br.feevale.controller.report;

import br.feevale.controller.Action;
import br.feevale.dao.TagDAO;
import br.feevale.http.response.Forward;
import br.feevale.http.response.Responder;
import br.feevale.http.servlet.Servlet;
import br.feevale.model.TagCounter;
import br.feevale.service.ConvertToPolarReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Index implements Action {

    public Responder execute(Servlet servlet, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ConvertToPolarReport polarReportService = new ConvertToPolarReport();

        TagDAO tagDAO = new TagDAO();
        List<TagCounter> tags = tagDAO.listTagsCounter();
        String radarReportGeneralTagsCount = polarReportService.generateCount(tags);
        String radarReportGeneralTagsAvarage = polarReportService.generateAvarage(tags);


        request.setAttribute("radarReportGeneralTagsCount", radarReportGeneralTagsCount);
        request.setAttribute("radarReportGeneralTagsAvarage", radarReportGeneralTagsAvarage);


        return new Forward("/WEB-INF/views/report/index.jsp");
    }
    
}
