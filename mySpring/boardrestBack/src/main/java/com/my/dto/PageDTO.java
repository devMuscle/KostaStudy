package com.my.dto;

import java.util.List;

public class PageDTO<T> {
	private String url; // ex. board/list
	private int currentPage = 1; // 현재페이지
	private int cntPerPage = 5; // 페이지별 목록수(페이지별 게시글 수)
	private int cntPerPageGroup = 3; // 페이지그룹 페이지수(밑에 번호로 보이는거)
	private int totalCnt; // 총목록수(총 게시글 수)
	private int totalPage; // 총페이지수
	private int startPage; // 시작페이지
	private int endPage; // 끝페이지
	private List<T> list; // ex. 게시글목록

	public PageDTO() {
		super();
	}

	public PageDTO(String url, int totalCnt, //
			List<T> list) {
		super();
		this.url = url;
		this.totalCnt = totalCnt;
		this.totalPage = (int) Math.ceil((float) totalCnt / cntPerPage);
		if (currentPage % cntPerPageGroup == 0) {
			this.startPage = currentPage - 2;
			this.endPage = currentPage;
		} else if (currentPage % cntPerPageGroup == 1) {
			if (currentPage + 2 > totalPage) {
				this.endPage = totalPage;
			}
			this.startPage = currentPage;
			this.endPage = currentPage + 2;
		} else if (currentPage % cntPerPageGroup == 2) {
			if (currentPage + 1 > totalPage) {
				this.endPage = totalPage;
			}
			this.startPage = currentPage - 1;
			this.endPage = currentPage + 1;
		}
		this.list = list;

		System.out.println(totalPage + " " + startPage + " " + endPage);
	}

	public PageDTO(String url, int currentPage, int cntPerPage, int cntPerPageGroup, int totalCnt, //
			List<T> list) {
		super();
		this.url = url;
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		this.cntPerPageGroup = cntPerPageGroup;
		this.totalCnt = totalCnt;
		this.totalPage = (int) Math.ceil((float) totalCnt / cntPerPage);
		
		for(int i=0; i<cntPerPageGroup; i++) {
			if (currentPage % cntPerPageGroup == i) {
				//각 경우 해결해 보기
				this.startPage = currentPage;
				this.endPage = currentPage;
			} 
		}
		
		/*
		if (currentPage % cntPerPageGroup == 0) {
			this.startPage = currentPage - 2;
			this.endPage = currentPage;
			
		} else if (currentPage % cntPerPageGroup == 1) {
			if (currentPage + 2 > totalPage) {
				this.endPage = totalPage;
			} else {
				this.endPage = currentPage + 2;
			}
			this.startPage = currentPage;
			
		} else if (currentPage % cntPerPageGroup == 2) {
			if (currentPage + 1 > totalPage) {
				this.endPage = totalPage;
			} else {
				this.endPage = currentPage + 1;
			}
			this.startPage = currentPage - 1;
		}
		*/
		this.list = list;

		System.out.println(totalPage + " " + startPage + " " + endPage);
		System.out.println("totalPage:" + totalPage + ", cntPerPageGroup: " + cntPerPageGroup + ", currentPage:" + currentPage);
		System.out.println("currentPage % cntPerPageGroup: " + currentPage % cntPerPageGroup + ", currentPage + 2 :" + (currentPage + 2));
	}

	public PageDTO(String url, int currentPage, int cntPerPage, int cntPerPageGroup, int totalCnt, int totalPage,
			int startPage, int endPage, List<T> list) {
		super();
		this.url = url;
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		this.cntPerPageGroup = cntPerPageGroup;
		this.totalCnt = totalCnt;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
		this.list = list;
	}

	public static void main(String[] args) {

		PageDTO page1 = new PageDTO("board/list", 19, null);
		PageDTO page2 = new PageDTO("board/list", 2, 5, 3, 19, null);
		PageDTO page3 = new PageDTO("board/list", 3, 5, 3, 19, null);
		PageDTO page4 = new PageDTO("board/list", 4, 5, 3, 19, null);
		PageDTO page5 = new PageDTO("board/list", 2, 10, 4, 19, null);
		PageDTO page6 = new PageDTO("board/list", 2, 7, 3, 19, null);
		PageDTO page7 = new PageDTO("board/list", 3, 7, 3, 19, null);
		PageDTO page8 = new PageDTO("board/list", 2, 5, 3, 20, null);
		PageDTO page9 = new PageDTO("board/list", 3, 5, 3, 20, null);
		PageDTO page10 = new PageDTO("board/list", 4, 5, 3, 20, null);
		PageDTO page11 = new PageDTO("board/list", 1, 5, 5, 20, null);
		
		int a=2;
		

	}
}
