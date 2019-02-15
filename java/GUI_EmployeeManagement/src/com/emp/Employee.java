package com.emp;

import java.io.Serializable;


public class Employee implements Serializable{
		private int empNo;
		private String name;
		private String position;
		private String dept;
		

		public Employee() {
		}

		public Employee(int empNo, String name, String position, String dept ) {
			super();
			this.empNo = empNo;
			this.name = name;
			this.position = position;
			this.dept = dept;
		}

		public int getEmpNo() {
			return empNo;
		}
		
		public void setEmpNo(int empNo) {
			this.empNo = empNo;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}	

		public String getDept() {
			return dept;
		}

		public void setDept(String dept) {
			this.dept = dept;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(empNo);
			builder.append("  ");
			builder.append(name);
			builder.append("  ");
			builder.append(position);
			builder.append("  ");
			builder.append(dept);
			return builder.toString();
		}
		
}
