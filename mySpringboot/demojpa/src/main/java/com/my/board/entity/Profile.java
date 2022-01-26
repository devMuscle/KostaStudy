package com.my.board.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tbl_profile")
@SequenceGenerator(
		   name = "tbl_profile_seq_generator"// 사용할 sequence 이름
		  ,sequenceName ="tbl_profile_seq" // 실제 데이터베이스 sequence 이름
		  ,initialValue = 1
		  , allocationSize = 1
		)
public class Profile {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE
			,generator = "tbl_profile_seq_generator")
	private Long p_fno;//파일번호
	private String p_fname;//이미지파일이름
	private boolean p_current;//현재여부
	
	@ManyToOne
	private Member member;
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Profile() {
		super();
	}
	public Long getP_fno() {
		return p_fno;
	}
	public void setP_fno(Long p_fno) {
		this.p_fno = p_fno;
	}
	public String getP_fname() {
		return p_fname;
	}
	public void setP_fname(String p_fname) {
		this.p_fname = p_fname;
	}
	public boolean isP_current() {
		return p_current;
	}
	public void setP_current(boolean p_current) {
		this.p_current = p_current;
	}
	@Override
	public int hashCode() {
		return Objects.hash(p_fno);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(p_fno, other.p_fno);
	}
	
	
}
