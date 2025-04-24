package com.sbmtech.mms.service.impl;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("serial")
public class CustomPageImpl<T> extends PageImpl<T> {

	private final boolean hasNext;
	private final boolean hasPrevious;

	public CustomPageImpl(List<T> content, Pageable pageable, long total) {
		super(content, pageable, total);
		this.hasNext = pageable.getPageNumber() < (getTotalPages() - 1);
		this.hasPrevious = pageable.getPageNumber() > 0;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public boolean isHasPrevious() {
		return hasPrevious;
	}
}