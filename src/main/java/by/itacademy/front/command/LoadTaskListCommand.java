package by.itacademy.front.command;

import by.itacademy.entities.front.FrontPage;
import by.itacademy.entities.front.FrontTask;
import by.itacademy.entities.task.Task;
import by.itacademy.front.converter.Converter;
import by.itacademy.front.converter.impl.TaskListToFrontTaskListConverter;
import by.itacademy.security.service.SecurityContext;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public abstract class LoadTaskListCommand extends FrontCommand {

    private Converter<List<Task>, List<FrontTask>> taskConverter;

    protected Pageable pageable;
    protected int id;

    @Override
    public void init(ServletContext servletContext, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        super.init(servletContext, servletRequest, servletResponse);

        this.id = SecurityContext.getInstance().getPrincipal().getId();
        taskConverter = new TaskListToFrontTaskListConverter();

        String queryString = request.getQueryString();
        Map<String, String[]> params  = new HashMap<>();
        if (queryString != null) {
            params = javax.servlet.http.HttpUtils.parseQueryString( queryString );
        }

        pageable = PageRequest.of(Integer.parseInt((params.get("page")[0])), Integer.parseInt((params.get("size")[0])));
    }

    protected void returnTskList(Page<Task> page) throws IOException {
        List<FrontTask> frontTasks = taskConverter.convert(page.getContent());
        FrontPage<List<FrontTask>> frontPage = new FrontPage(frontTasks, page.getTotalElements());
        String json = new Gson().toJson(frontPage);
        returnResponse(json);
    }

    protected List<FrontTask> convert(List<Task> tasks) {
        return taskConverter.convert(tasks);
    }
}
