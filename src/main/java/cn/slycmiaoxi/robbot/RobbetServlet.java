package cn.slycmiaoxi.robbot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 图灵机器人servlet
 * </p>
 *
 * @author slycmiaoxi
 * @since 2019-06-17
 */
public class RobbetServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String info = request.getParameter("info");
        String result = RobbetService.getTheResult(info);
        response.getWriter().print(result);
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
    
}
