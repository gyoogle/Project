package com.emp;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EmpMgrImpl implements IEmpMgr {	
	
    /** 직원나 매니저의 정보를 저장하기 위한 리스트 */
	private List<Employee> emps;
	
	private static EmpMgrImpl instance;
	
	private  EmpMgrImpl() {
		emps = new ArrayList<Employee>();
		load("emp.dat");
	}
	
	public static EmpMgrImpl getInstance() {
		if(instance == null)
			instance = new EmpMgrImpl();
		
		return instance;
	}

	
	/** 파일로 부터 자료 읽어서 메모리(ArrayList)에 저장하기*/
	public void load(String filename) {
		File  file=new File(filename);
		System.out.println(file);
		if (!file.exists()) return; 
		
		emps.clear();
		
		ObjectInputStream ois=null;
		Object ob=null;
		try{	
			ois=new ObjectInputStream(new FileInputStream(file));
			while(true){//마지막 EOF Exception발생
				ob=ois.readObject();
				emps.add((Employee)ob);
			}
		}catch(EOFException ee){System.out.println("읽기 완료");
		}catch(FileNotFoundException fe){
			System.out.println("파일이 존재하지 않습니다");
		}catch(IOException ioe){
			System.out.println(ioe);
		}catch(ClassNotFoundException ce){
			System.out.println("같은 클래스 타입이 아닙니다");
		}finally{
			if(ois !=null){
				try{
					ois.close();
				}catch(IOException oe){System.out.println("파일을 닫는데 실패했습니다");}
			}
		}
	}
  

	@Override
	public void save(String filename) {
		File file = new File(filename);
		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			
			for(Employee e : emps) {
				oos.writeObject(e);
				oos.flush();
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e);
		} catch (IOException e) {
			System.err.println(e);
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		
		
	}

	@Override
	public void add(Employee b) throws DuplicateException {
		
		for (Employee e : emps) {
			if(e.getEmpNo() == b.getEmpNo()) {
				throw new DuplicateException();
			}
		}
		
		emps.add(b);
		
	}
	
	@Override
	public List<Employee> search(){
		return emps;
	}

	@Override
	public Employee search(int num) throws RecordNotFoundException {
		
		for (Employee e : emps) {
			if(e.getEmpNo() == num) {
				return e;
			}
		}
		throw new RecordNotFoundException();
	}

	@Override
	public void update(Employee b) throws RecordNotFoundException {
		
		boolean chk = true;
		
		for (Employee e : emps) {
			if(e.getEmpNo() == b.getEmpNo() ) {
				e.setName(b.getName());
				e.setPosition(b.getPosition());
				e.setDept(b.getDept());
				chk = false;
				break;
			}
		}
		
		if(chk)
			throw new RecordNotFoundException();
	}

	@Override
	public void delete(int num) throws RecordNotFoundException {
		Employee e = search(num);
		emps.remove(e);
	}
	
	
	
	
	

}
