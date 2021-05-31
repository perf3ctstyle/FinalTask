package com.epam.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class FooterTag extends TagSupport {

    private static final String FOOTER = "<div>";
    private static final String PARAGRAPH = "<p class=\"footer\">";
    private static final String FOOTER_TEXT = "&copy; EPAM Systems, Inc. ";
    private static final String CLOSED_PARAGRAPH = "</p>";
    private static final String CLOSED_FOOTER = "</div>";

    private int currentYear;

    @Override
    public int doStartTag() {
        try {
            JspWriter jspWriter = pageContext.getOut();
            jspWriter.write(FOOTER + PARAGRAPH +
                    FOOTER_TEXT + currentYear + CLOSED_PARAGRAPH + CLOSED_FOOTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        return SKIP_BODY;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }
}
