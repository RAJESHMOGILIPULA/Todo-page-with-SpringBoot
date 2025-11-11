package com.in28minutes.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
	//"say-hello" => "hello! what are you learning today?"
	
	@RequestMapping("/say-hello")
	@ResponseBody
	public String sayHello() {
		return "hello! what are you learning today?";
	}
	
	
	//Using HTML HardCode
	@RequestMapping("/say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer sb=new StringBuffer();
		sb.append("<html>");
		sb.append("</head>");
		sb.append("<title>Myfirst HTML page</title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("my first html page with body. - changed");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	// folder => /src/main/resources/META-INF/resources/WEB_INF/jsp/sayHello.jsp
	//JSP file sayhello.jsp=> "sey Hello to Jsp"
	@RequestMapping("/say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}
	
}
