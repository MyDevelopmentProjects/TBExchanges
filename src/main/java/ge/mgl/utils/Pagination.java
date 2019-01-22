package ge.mgl.utils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.Writer;

/**
 * Created by Mikheil on 7/10/2017.
 */
public class Pagination extends SimpleTagSupport {

    private String uri;
    private int currPage = 0;
    private int maxPage = 0;
    private int totalItems = 0;
    private int maxSize = 5;
    private int pageSize = 10;

    private Writer getWriter() {
        JspWriter out = getJspContext().getOut();
        return out;
    }

    @Override
    public void doTag() throws JspException {
        Writer out = getWriter();

        if(currPage < 0){
            currPage = 0;
        }

        int totalPages =  Math.max((int) Math.ceil(this.totalItems / this.pageSize), 1) + 1;
        int startPage = 1, endPage = totalPages;
        boolean isMaxSized = this.maxSize < totalPages;

        try{
            out.write("<div class=\"paging\"><a class=\"paging__item\"><</a>");


            for(int i = startPage; i <= endPage; i++){
                out.write(constructLink(i, (i == currPage+1)));
            }

            out.write("<a class=\"paging__item\">></a></div>");
        }  catch (java.io.IOException ex) {
            throw new JspException("Error in Paginator tag", ex);
        }
    }


    private String constructLink(int page,boolean isActive) {
        return constructLink(page, String.valueOf(page), isActive);
    }

    private String constructLink(int page, String text, boolean isActive) {
        StringBuilder link = new StringBuilder();
        link.append("<a ");
        if(isActive){
            link.append("class='paging__item ").append("paging__item--active").append("' ");
        } else {
            link.append("class='paging__item ").append("' ");
        }
        link.append("href='").append(uri).append("?pageNumber=").append(String.valueOf((page > 1) ? page-1:0)).append("'>")
                .append(String.valueOf(page))
                .append("</a>");
        return link.toString();
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
