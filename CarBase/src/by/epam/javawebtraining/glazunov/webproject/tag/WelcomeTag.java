package by.epam.javawebtraining.glazunov.webproject.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class WelcomeTag extends TagSupport {
	private static final long serialVersionUID = 8466643861485342756L;
	private String name;
	private String surname;

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public int doStartTag() throws JspException {
		
		try {
			pageContext.getOut().write(name + " " + surname + "!");
		} catch (IOException e) {
			throw new JspException(e.getMessage());
		}
		
		return SKIP_BODY;
	}

}
