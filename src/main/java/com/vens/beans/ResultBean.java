package com.vens.beans;


import java.io.Serializable;

/**
 * @author LuZhiqing
 * @Description:
 * @date 2018/11/12
 */
public class ResultBean <T> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;

    public static final int SUCCESS = 0;

    public static final int CHECK_FAIL = 1;

    public static final int NO_PERMISSION = 2;

    public static final int UNKNOWN_EXCEPTION = -99;



    private String msg = "success";

    private int code = SUCCESS;

    private T data;

    public ResultBean() {
        super();
    }

    public ResultBean(T data) {
        super();
        this.data = data;
    }

    public ResultBean(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = UNKNOWN_EXCEPTION;
    }


    /**
     * ���ص���Ϣ(��Ҫ�����ʱ��ʹ��)
     */
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * �ӿڷ�����, 0��ʾ�ɹ�, ��������Ӧ�Ķ���
     * �������Ƽ���������:
     * 0   : �ɹ�
     * >0 : ��ʾ��֪���쳣(������ʾ�����, ��Ҫ���õط���������)
     * <0 : ��ʾδ֪���쳣(����Ҫ��������, ���÷�ͳһ����)
     */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * ���ص�����
     */
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
