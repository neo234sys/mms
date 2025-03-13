package com.sbmtech.mms.payload.response;
import java.util.List;

public class PaginationResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean isFirst;
    private boolean isLast;

    public PaginationResponse(List<T> content, int currentPage, int totalPages, long totalElements, boolean isFirst, boolean isLast) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    // Getters
    public List<T> getContent() { return content; }
    public int getCurrentPage() { return currentPage; }
    public int getTotalPages() { return totalPages; }
    public long getTotalElements() { return totalElements; }
    public boolean isFirst() { return isFirst; }
    public boolean isLast() { return isLast; }
}