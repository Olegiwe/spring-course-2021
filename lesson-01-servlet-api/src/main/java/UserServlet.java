import persist.EntityNotFoundException;
import persist.User;
import persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository) getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder;
        builder = new StringBuilder()
                .append("<html>")
                .append("<head><meta charset=\"utf-8\"><title>")
                .append("Пользователь")
                .append("</title></head>")
                .append("<body>");
        User user;
        try {
            String idString = Arrays.stream(req.getPathInfo().split("/"))
                    .skip(1L)
                    .findFirst().orElseThrow(() -> new RuntimeException("Incorrect path parameter"));
            user = this.userRepository.findById(Long.valueOf(idString));
            builder.append("<b>ID: </b>")
                    .append(user.getId())
                    .append("<br><b> Username: </b>")
                    .append(user.getUsername());
        } catch (EntityNotFoundException enfEx) {
            builder.append("<b>Пользователь с данным id отсутствует в базе</b>");
        } catch (RuntimeException ex) {
            builder.append("<b>ОШИБКА: неверный формат url</b>");
        }
        builder.append("</body></html>");
        resp.getWriter().write(builder.toString());
    }
}
