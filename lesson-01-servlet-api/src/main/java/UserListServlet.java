import persist.User;
import persist.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/users")
public class UserListServlet extends HttpServlet {

    private UserRepository userRepository;

    @Override
    public void init() throws ServletException {
        this.userRepository = (UserRepository)getServletContext().getAttribute("userRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder builder;
        builder = new StringBuilder()
                .append("<html>")
                .append("<head><meta charset=\"utf-8\"><title>")
                .append("Список пользователей")
                .append("</title></head>")
                .append("<body>");
        List<User> users = this.userRepository.findAll();
        if (!users.isEmpty()) {
            builder.append("<table border=2> <caption> Таблица пользователей </caption>")
                    .append("<tr><th>ID</th><th>Имя пользователя</th></tr>");
            users.forEach(user ->
                    builder.append("<tr><td>")
                            .append(user.getId())
                            .append("</td><td>")
                            .append(user.getUsername())
                            .append("</td></tr>"));
            builder.append("</table>");
        } else {
           builder.append("<b>Пользователи не найдены!</b>");
        }
        builder.append("</body></html>");
        resp.getWriter().write(builder.toString());
    }
}
