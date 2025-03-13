package com.sbmtech.mms.payload.request;
public class PaginationRequest {
    private int page = 0; // Default page number
    private int size = 10; // Default page size
    private String sortBy = "id"; // Default sorting field
    private String sortDirection = "asc"; // Default sorting direction

    // Getters and Setters
    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getSortBy() { return sortBy; }
    public void setSortBy(String sortBy) { this.sortBy = sortBy; }

    public String getSortDirection() { return sortDirection; }
    public void setSortDirection(String sortDirection) { this.sortDirection = sortDirection; }
}