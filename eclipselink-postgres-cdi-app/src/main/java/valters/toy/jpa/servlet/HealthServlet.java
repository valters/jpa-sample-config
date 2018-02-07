package valters.toy.jpa.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import valters.toy.jpa.entity.Sample;
import valters.toy.jpa.postgres.JpaShowcase;

@SuppressWarnings("serial")
@WebServlet("/")
public class HealthServlet extends HttpServlet {

    @Inject
    private JpaShowcase jpa;

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("*** GET /");
        jpa.updateItem();

        final Sample s = jpa.loadSample(2);
        resp.getWriter().println("got: " + s);
        resp.getWriter().close();
    }

}
