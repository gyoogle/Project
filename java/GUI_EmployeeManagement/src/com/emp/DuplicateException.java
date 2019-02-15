package com.emp;

class DuplicateException extends Exception {

	@Override
	public String toString() {
		return super.toString() + ": 데이터가 중복되었습니다.";
	}

}
