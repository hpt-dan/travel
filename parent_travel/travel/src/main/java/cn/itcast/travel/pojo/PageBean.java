package cn.itcast.travel.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页类
 */
public class PageBean<T> implements Serializable {
    private List<T> data;//当前页列表
    private int firstPage=1;//首页
    private int prePage;//上一页
    private int curPage;//当前页
    private int nextPage;//下一页
    private int totalPage;//总页数
    private int count;//总记录数
    private int pageSize;//每页多少条

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(int firstPage) {
        this.firstPage = firstPage;
    }

    public int getPrePage() {
        prePage =this.curPage-1;
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getNextPage() {
        nextPage = this.curPage+1;
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        totalPage = count%pageSize==0?count/pageSize:count/pageSize+1;
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    public PageBean(){}
    public PageBean(List<T> data, int firstPage, int prePage, int curPage, int nextPage, int totalPage, int count, int pageSize) {
        this.data = data;
        this.firstPage = firstPage;
        this.prePage = prePage;
        this.curPage = curPage;
        this.nextPage = nextPage;
        this.totalPage = totalPage;
        this.count = count;
        this.pageSize = pageSize;
    }

	@Override
	public String toString() {
		return "PageBean [data=" + data + ", firstPage=" + firstPage
				+ ", prePage=" + prePage + ", curPage=" + curPage
				+ ", nextPage=" + nextPage + ", totalPage=" + totalPage
				+ ", count=" + count + ", pageSize=" + pageSize + "]";
	}
    
}
