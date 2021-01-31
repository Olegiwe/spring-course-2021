import persist.User;
import persist.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class BootstrapListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        UserRepository userRepository = new UserRepository();
        context.setAttribute("userRepository", userRepository);

        userRepository.insert(new User("Alice"));
        userRepository.insert(new User("Bob"));
        userRepository.insert(new User("Clive"));
        userRepository.insert(new User("Daisy"));
        userRepository.insert(new User("Eugene"));
        userRepository.insert(new User("Fey"));
        userRepository.insert(new User("George"));
        userRepository.insert(new User("Harry"));

    }
}
