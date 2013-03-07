package com.yitengls.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yitengls.bean.TuserBean;

/**
 * Servlet Filter implementation class Filter2URLMapping
 */
@WebFilter("*.jsp")
public class Filter2URLMapping implements Filter {

    /**
     * Default constructor. 
     */
    public Filter2URLMapping() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		String target = req.getServletPath();
//		req.getSession().setAttribute("uri", req.getServletPath());
		HttpSession session = req.getSession(false);
		if(!"/login.jsp".equals(target)){
			if(session==null || session.getAttribute("user")==null){
				if(session!=null){
					session.setAttribute("lastReq", req.getContextPath()+target);
				}
				System.out.println("sendRedirect in filter2: "+req.getContextPath()+"/login.jsp");
				resp.sendRedirect(req.getContextPath()+"/login.jsp");
				return;
			}
		}else{
			if(session!=null && session.getAttribute("user")!=null){
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
			}
		}
		
//		System.out.println("Filter2URLMapping........");
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
