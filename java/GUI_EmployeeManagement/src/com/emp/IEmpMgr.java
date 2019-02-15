package com.emp;

import java.util.List;

public interface IEmpMgr {
	// filename에서 객체를 읽어들이는 메서드
	public void load(String filename) ;
	// filename에 객체를 쓰는 메서드
	public void save(String filename) ;
	// Employee를 저장하는 매서드
	public void add(Employee b) throws DuplicateException;
	// 전체 직원정보를 리턴하는 메서드
	public List<Employee> search(); 
	// 번호를 이용하여 검색된 직원을 리턴하는 메서드
	public Employee search(int num) throws RecordNotFoundException;  
	// 번호를 찾아 직원정보를 수정하는 메서드
	public void update(Employee b) throws RecordNotFoundException;
	// 번호를 찾아 직원 정보를 삭제하는 메서드
	public void delete(int num) throws RecordNotFoundException;
}