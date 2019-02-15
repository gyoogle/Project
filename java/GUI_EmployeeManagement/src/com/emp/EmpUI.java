package com.emp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class EmpUI implements ActionListener{
	private JFrame frame;
	private JList list;
	private JTextField txtEmpNo;
	private JTextField txtName;
	private JTextField txtDept;
	private JTextField txtPosition;
	private JLabel lblStatus;
	private JButton btnAdd;
	private JButton btnSearch;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnUpload;
	private JButton btnClear;
	
	//IEmpMgr mgr=EmpMgrImpl.getInstance();
	EmpMgrImpl mgr = EmpMgrImpl.getInstance();   /////////////////////////  Singleton 구현 후 위의 코드로 변경할 것
	
	public EmpUI(){
		frame = new JFrame("EmpManager Client");
		lblStatus = new JLabel(" ");
		lblStatus.setBackground(Color.LIGHT_GRAY);
		lblStatus.setForeground(Color.BLUE);
		txtEmpNo = new JTextField();
		txtName = new JTextField();
		txtPosition = new JTextField();
		txtDept = new JTextField();
		btnAdd = new JButton("Add");
		btnUpdate = new JButton("Update");
		btnSearch = new JButton("Search");
		btnClear= new JButton("Clear");
		btnDelete = new JButton("Delete");
		btnSave = new JButton("SaveToFile");
		btnUpload = new JButton("SendToServer");
		list = new JList();
		
		JPanel inputs = new JPanel();
		JPanel inputLables = new JPanel();
		JPanel inputFields = new JPanel();
		JPanel inputBtns = new JPanel();
		inputLables.setLayout(new GridLayout(4,1));
		JLabel lblEmpNo = new JLabel("EmpNo");
		lblEmpNo.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblGrade = new JLabel("Position");
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel lblDiv = new JLabel("Department");
		lblDiv.setHorizontalAlignment(SwingConstants.CENTER);
		inputLables.add(lblEmpNo);
		inputLables.add(lblName);
		inputLables.add(lblGrade);
		inputLables.add(lblDiv);
		inputFields.setLayout(new GridLayout(4,1));
		inputFields.add(txtEmpNo);
		inputFields.add(txtName);
		inputFields.add(txtPosition);
		inputFields.add(txtDept);
		inputBtns.setLayout(new GridLayout(1,4));
		inputBtns.add(btnAdd);
		inputBtns.add(btnUpdate);
		inputBtns.add(btnDelete);
		inputBtns.add(btnSearch);
		inputBtns.add(btnClear);

		inputs.setLayout(new BorderLayout());
		inputs.add(inputLables, BorderLayout.WEST);
		inputs.add(inputFields, BorderLayout.CENTER);
		inputs.add(inputBtns, BorderLayout.SOUTH);
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		center.add(list);
		center.add(lblStatus, BorderLayout.NORTH);
		
		JPanel pnlBtns = new JPanel();
		pnlBtns.setLayout(new GridLayout(1,4));
		pnlBtns.add(btnSave);
		pnlBtns.add(btnUpload);
		
		frame.add(inputs, BorderLayout.NORTH);
		frame.add(center);
		frame.add(pnlBtns, BorderLayout.SOUTH);
		frame.setSize(500, 400);
		frame.setVisible(true);
		showList();

	}
	/** 이벤트를 등록합니다. */
	public void addEvent(){
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});		
		btnAdd.addActionListener(this);
		btnClear.addActionListener(this);
		btnDelete.addActionListener(this);
		btnSave.addActionListener(this);
		btnSearch.addActionListener(this);
		btnUpdate.addActionListener(this);
		btnUpload.addActionListener(this);
		list.addListSelectionListener(new ListSelectionListener() {			
			@Override
			public void valueChanged(ListSelectionEvent ee) {		
				if(list.getSelectedValue()==null) return;
					Employee e=(Employee) list.getSelectedValue();
					txtEmpNo.setText(e.getEmpNo()+"");
					txtName.setText(e.getName());
					txtPosition.setText(e.getPosition());
					txtDept.setText(e.getDept());
				}
			});		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnAdd){
			addRecord();
		}else if(e.getSource()==btnUpdate){
			updateRecord();
		}else if(e.getSource()==btnSearch){
			searchRecord();
		}else if(e.getSource()==btnClear){
			clearField();
			lblStatus.setText(" ");
		}else if(e.getSource()==btnDelete){
			deleteRecord();
		}else if(e.getSource()==btnSave){
			saveRecord();
		}else if(e.getSource()==btnUpload){
			new EmpClient(mgr.search()).start();			//////////// EmpClient가 구현 되면 주석 제거
		}
		
	}
	private void addRecord() {
		String empNo=txtEmpNo.getText().trim();
		String name=txtName.getText().trim();
		String position=txtPosition.getText().trim();
		String dept=txtDept.getText().trim();
		if(empNo.equals("") ||name.equals("") ||position.equals("")||dept.equals("")){
			lblStatus.setText("모든 항목를 입력해 주세요.");
			return;
		}
		try {
			mgr.add(new Employee(Integer.parseInt(empNo),name,position,dept));			 //////////////// mgr.add() 구현 되면 주석 제거
			lblStatus.setText("등록 성공");			
			showList();
			clearField();
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (DuplicateException e) {               //////////////// DulicateException 이 구현 되면 주석 제거
			lblStatus.setText(e.toString());           //////////////// DulicateException 이 구현 되면 주석 제거
		}
	}
	
	private void updateRecord(){
		String empNo=txtEmpNo.getText().trim();
		String name=txtName.getText().trim();
		String position=txtPosition.getText().trim();
		String dept=txtDept.getText().trim();
		
		if(empNo.equals("") ||name.equals("") ||position.equals("")||dept.equals("")){
			lblStatus.setText("모든 항목를 입력해 주세요.");
			return;
		}
		
		try {
			Employee empl = new Employee(Integer.parseInt(empNo), name, position, dept);
			mgr.update(empl);
			lblStatus.setText("수정 성공");
			showList();
			clearField();
		}  catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {             
			lblStatus.setText(e.toString());           
		}
		
		
	}
	private void searchRecord(){
		String empNo = txtEmpNo.getText().trim();
		
		if(empNo.equals("")) {
			lblStatus.setText("직원 번호를 입력해 주세요.");
			return;
		}
		
		try {
			Employee e = mgr.search(Integer.parseInt(empNo));
			
			mgr.search(e.getEmpNo());
			lblStatus.setText("검색 성공");
			showList();
			txtEmpNo.setText(Integer.toString(e.getEmpNo()));;
			txtName.setText(e.getName());;
			txtPosition.setText(e.getPosition());
			txtDept.setText(e.getDept());
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {             
			lblStatus.setText(e.toString());           
		}
	}
	
	private void deleteRecord(){
		
		String empNo = txtEmpNo.getText().trim();
		
		if(empNo.equals("")) {
			lblStatus.setText("직원 번호를 입력해 주세요.");
			return;
		}
		
		try {
			mgr.delete(Integer.parseInt(empNo));
			lblStatus.setText("삭제 성공");
			showList();
			clearField();
		} catch (NumberFormatException e) {
			lblStatus.setText("EmpNo은 숫자로 입력해주세요");
			return;
		} catch (RecordNotFoundException e) {             
			lblStatus.setText(e.toString());           
		}
		
	}
	private void saveRecord(){
		try {
			mgr.save("emp.dat");       ////////   mgr.save("emp.dat")구현되면  주석 제거
			lblStatus.setText("저장 성공");
		} catch (Exception e) {
			lblStatus.setText(e.toString());
		}
	}	
	
	
	/** AWT List 컴포넌트에 직원정보를 표시합니다. */
	private void showList(){
		List<Employee> ee=mgr.search();
		list.removeAll();
		list.setListData(ee.toArray());
	}
	/** 직원정보를 입력하는 TextField의 내용을 지움니다. */
	private void clearField(){
		txtEmpNo.setText(" ");
		txtName.setText(" ");
		txtPosition.setText(" ");
		txtDept.setText(" ");
		
		
	}
	public static void main(String[] args){
		EmpUI ui = new EmpUI();
		ui.addEvent();
	}
	
}






