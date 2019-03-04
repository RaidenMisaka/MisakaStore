package cn.misaka.store.entity;

public class ResponseResult<T> {
	private Integer state;
	private String message;
	private T data;
	
	public static final Integer STATE_OK = 1;
	public static final Integer STATE_ERR = 0;

	public ResponseResult(Integer state) {
		super();
		setState(state);
	}

	public ResponseResult(Integer state, String message) {
		super();
		setState(state);
		setMessage(message);
	}

	public ResponseResult(Exception e) {
		super();
		setState(state);
		setMessage(e.getMessage());
	}

	public ResponseResult() {
		super();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
