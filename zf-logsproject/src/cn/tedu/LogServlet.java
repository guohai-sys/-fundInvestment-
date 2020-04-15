package cn.tedu;



import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class LogServlet extends HttpServlet {
	
	private Logger logger = Logger.getLogger(LogServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String info = URLDecoder.decode(request.getQueryString(), "utf-8");
		/**
		 * url=http://localhost:8080/FluxAppServer/b.jsp&urlname=b.jsp&title=页面B&chset=UTF-8&scr=1920x1080&col=24-bit&lg=zh-cn
		 * &je=0&ce=1&fv=&cnv=0.7476978707350572&ref=http://localhost:8080/FluxAppServer/a.jsp&uagent=Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.9 Safari/537.36&
		 * stat_uv=25117899035363433412&stat_ss=3640260325_5_1586499905252
		 */
		String[] urls = info.split("\\&");
		StringBuffer bf = new StringBuffer();
		for (String url : urls) {
			String sp = url.split("=").length==2?url.split("=")[1]:"";
			//ref属性没有数据,数组越界
			bf.append(sp+"|");
		}
		bf.append(request.getRemoteAddr());//用户访问ip地址
		//log记录的级别从低到高debug>info>warn>error>fatal
		logger.info(bf);
	//	System.out.println(bf);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
