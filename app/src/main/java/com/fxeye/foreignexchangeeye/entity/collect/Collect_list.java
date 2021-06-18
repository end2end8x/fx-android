package com.fxeye.foreignexchangeeye.entity.collect;

import java.util.List;

public class Collect_list {
    private ContentBean Content;
    private String RequestId;
    private String Timestamp;

    public ContentBean getContent() {
        return this.Content;
    }

    public void setContent(ContentBean contentBean) {
        this.Content = contentBean;
    }

    public String getRequestId() {
        return this.RequestId;
    }

    public void setRequestId(String str) {
        this.RequestId = str;
    }

    public String getTimestamp() {
        return this.Timestamp;
    }

    public void setTimestamp(String str) {
        this.Timestamp = str;
    }

    public static class ContentBean {
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

        public static class ResultBean {
            private List<ItemsBean> items;
            private int total;

            public int getTotal() {
                return this.total;
            }

            public void setTotal(int i) {
                this.total = i;
            }

            public List<ItemsBean> getItems() {
                return this.items;
            }

            public void setItems(List<ItemsBean> list) {
                this.items = list;
            }

            public static class ItemsBean {
                private boolean IsTop200;
                private String annotation;
                private String color;
                private String fullName;
                private List<LabelsBean> labels;
                private String languageCode;
                private String localFullName;
                private String localShortName;
                private String logo;
                private String score;
                private String shortName;
                private String traderCode;
                private String updatetime;

                public String getUpdatetime() {
                    return this.updatetime;
                }

                public void setUpdatetime(String str) {
                    this.updatetime = str;
                }

                public String getLanguageCode() {
                    return this.languageCode;
                }

                public void setLanguageCode(String str) {
                    this.languageCode = str;
                }

                public String getTraderCode() {
                    return this.traderCode;
                }

                public void setTraderCode(String str) {
                    this.traderCode = str;
                }

                public String getLogo() {
                    return this.logo;
                }

                public void setLogo(String str) {
                    this.logo = str;
                }

                public String getFullName() {
                    return this.fullName;
                }

                public void setFullName(String str) {
                    this.fullName = str;
                }

                public String getShortName() {
                    return this.shortName;
                }

                public void setShortName(String str) {
                    this.shortName = str;
                }

                public String getScore() {
                    return this.score;
                }

                public void setScore(String str) {
                    this.score = str;
                }

                public String getAnnotation() {
                    return this.annotation;
                }

                public void setAnnotation(String str) {
                    this.annotation = str;
                }

                public String getColor() {
                    return this.color;
                }

                public void setColor(String str) {
                    this.color = str;
                }

                public boolean isIsTop200() {
                    return this.IsTop200;
                }

                public void setIsTop200(boolean z) {
                    this.IsTop200 = z;
                }

                public String getLocalShortName() {
                    return this.localShortName;
                }

                public void setLocalShortName(String str) {
                    this.localShortName = str;
                }

                public String getLocalFullName() {
                    return this.localFullName;
                }

                public void setLocalFullName(String str) {
                    this.localFullName = str;
                }

                public List<LabelsBean> getLabels() {
                    return this.labels;
                }

                public void setLabels(List<LabelsBean> list) {
                    this.labels = list;
                }

                public static class LabelsBean {
                    private List<DataBean> data;
                    private int type;

                    public int getType() {
                        return this.type;
                    }

                    public void setType(int i) {
                        this.type = i;
                    }

                    public List<DataBean> getData() {
                        return this.data;
                    }

                    public void setData(List<DataBean> list) {
                        this.data = list;
                    }

                    public static class DataBean {
                        private String labelName;

                        public String getLabelName() {
                            return this.labelName;
                        }

                        public void setLabelName(String str) {
                            this.labelName = str;
                        }
                    }
                }
            }
        }
    }
}
