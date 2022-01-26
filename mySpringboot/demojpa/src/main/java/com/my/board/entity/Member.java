package com.my.board.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbl_members")
public class Member {
	@Id
	private String m_uid;
	private String m_upw;
	private String m_uname;
	public Member() {
		super();
	}
	public String getM_uid() {
		return m_uid;
	}
	public void setM_uid(String m_uid) {
		this.m_uid = m_uid;
	}
	public String getM_upw() {
		return m_upw;
	}
	public void setM_upw(String m_upw) {
		this.m_upw = m_upw;
	}
	public String getM_uname() {
		return m_uname;
	}
	public void setM_uname(String m_uname) {
		this.m_uname = m_uname;
	}
	@Override
	public int hashCode() {
		return Objects.hash(m_uid);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return Objects.equals(m_uid, other.m_uid);
	}
	
	
}
