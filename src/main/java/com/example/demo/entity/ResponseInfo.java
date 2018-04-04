package com.example.demo.entity;

public class ResponseInfo<T> {

	private ResponseHeader header;
	private ResponseBodys<T> body;

	public ResponseHeader getHeader() {
		return header;
	}

	public void setHeader(ResponseHeader header) {
		this.header = header;
	}

	public ResponseBodys<T> getBody() {
		return body;
	}

	public void setBody(ResponseBodys<T> body) {
		this.body = body;
	}

}
