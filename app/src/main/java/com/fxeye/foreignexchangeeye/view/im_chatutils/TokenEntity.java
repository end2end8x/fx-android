package com.fxeye.foreignexchangeeye.view.im_chatutils;

public class TokenEntity {
    private DataBean Data;
    private boolean Success;
    private int code;
    private String msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.Data;
    }

    public void setData(DataBean dataBean) {
        this.Data = dataBean;
    }

    public boolean isSuccess() {
        return this.Success;
    }

    public void setSuccess(boolean z) {
        this.Success = z;
    }

    public static class DataBean {
        private String code;
        private String message;
        private ResultBean result;
        private boolean succeed;

        public ResultBean getResult() {
            return this.result;
        }

        public void setResult(ResultBean resultBean) {
            this.result = resultBean;
        }

        public boolean isSucceed() {
            return this.succeed;
        }

        public void setSucceed(boolean z) {
            this.succeed = z;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String str) {
            this.message = str;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String str) {
            this.code = str;
        }

        public static class ResultBean {
            private String access_token;
            private int expires_in;

            public String getAccess_token() {
                return this.access_token;
            }

            public void setAccess_token(String str) {
                this.access_token = str;
            }

            public int getExpires_in() {
                return this.expires_in;
            }

            public void setExpires_in(int i) {
                this.expires_in = i;
            }
        }
    }
}
