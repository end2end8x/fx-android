package com.fxeye.foreignexchangeeye.entity.could_entity;

import java.util.List;

public class ProductEntity {
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
            private List<ButtonListBean> ButtonList;
            private boolean IsPay;
            private List<OrderPriceBean> OrderPrice;
            private List<ProductsBean> Products;
            private List<TotalPriceBean> TotalPrice;

            public boolean isIsPay() {
                return this.IsPay;
            }

            public void setIsPay(boolean z) {
                this.IsPay = z;
            }

            public List<ProductsBean> getProducts() {
                return this.Products;
            }

            public void setProducts(List<ProductsBean> list) {
                this.Products = list;
            }

            public List<TotalPriceBean> getTotalPrice() {
                return this.TotalPrice;
            }

            public void setTotalPrice(List<TotalPriceBean> list) {
                this.TotalPrice = list;
            }

            public List<OrderPriceBean> getOrderPrice() {
                return this.OrderPrice;
            }

            public void setOrderPrice(List<OrderPriceBean> list) {
                this.OrderPrice = list;
            }

            public List<ButtonListBean> getButtonList() {
                return this.ButtonList;
            }

            public void setButtonList(List<ButtonListBean> list) {
                this.ButtonList = list;
            }

            public static class ProductsBean {
                private String Currency;
                private String CurrencyCode;
                private String Fee;
                private String Language;
                private String Num;
                private String PriceColor;
                private String SellPrice;
                private String SquareImg;
                private String Title;

                public String getTitle() {
                    return this.Title;
                }

                public void setTitle(String str) {
                    this.Title = str;
                }

                public String getSquareImg() {
                    return this.SquareImg;
                }

                public void setSquareImg(String str) {
                    this.SquareImg = str;
                }

                public String getSellPrice() {
                    return this.SellPrice;
                }

                public void setSellPrice(String str) {
                    this.SellPrice = str;
                }

                public String getFee() {
                    return this.Fee;
                }

                public void setFee(String str) {
                    this.Fee = str;
                }

                public String getPriceColor() {
                    return this.PriceColor;
                }

                public void setPriceColor(String str) {
                    this.PriceColor = str;
                }

                public String getLanguage() {
                    return this.Language;
                }

                public void setLanguage(String str) {
                    this.Language = str;
                }

                public String getCurrency() {
                    return this.Currency;
                }

                public void setCurrency(String str) {
                    this.Currency = str;
                }

                public String getCurrencyCode() {
                    return this.CurrencyCode;
                }

                public void setCurrencyCode(String str) {
                    this.CurrencyCode = str;
                }

                public String getNum() {
                    return this.Num;
                }

                public void setNum(String str) {
                    this.Num = str;
                }
            }

            public static class TotalPriceBean {
                private String Color;
                private String Name;
                private double Price;
                private int Type;
                private String Value;

                public String getName() {
                    return this.Name;
                }

                public void setName(String str) {
                    this.Name = str;
                }

                public String getValue() {
                    return this.Value;
                }

                public void setValue(String str) {
                    this.Value = str;
                }

                public String getColor() {
                    return this.Color;
                }

                public void setColor(String str) {
                    this.Color = str;
                }

                public int getType() {
                    return this.Type;
                }

                public void setType(int i) {
                    this.Type = i;
                }

                public double getPrice() {
                    return this.Price;
                }

                public void setPrice(double d) {
                    this.Price = d;
                }
            }

            public static class OrderPriceBean {

                /* renamed from: Id */
                private String f2237Id;
                private List<PriceBean> Price;

                public String getId() {
                    return this.f2237Id;
                }

                public void setId(String str) {
                    this.f2237Id = str;
                }

                public List<PriceBean> getPrice() {
                    return this.Price;
                }

                public void setPrice(List<PriceBean> list) {
                    this.Price = list;
                }

                public static class PriceBean {
                    private String Color;
                    private String Name;
                    private double Price;
                    private int Type;
                    private String Value;

                    public String getName() {
                        return this.Name;
                    }

                    public void setName(String str) {
                        this.Name = str;
                    }

                    public String getValue() {
                        return this.Value;
                    }

                    public void setValue(String str) {
                        this.Value = str;
                    }

                    public String getColor() {
                        return this.Color;
                    }

                    public void setColor(String str) {
                        this.Color = str;
                    }

                    public int getType() {
                        return this.Type;
                    }

                    public void setType(int i) {
                        this.Type = i;
                    }

                    public double getPrice() {
                        return this.Price;
                    }

                    public void setPrice(double d) {
                        this.Price = d;
                    }
                }
            }

            public static class ButtonListBean {
                private String Color;
                private String Name;
                private int Type;

                public String getName() {
                    return this.Name;
                }

                public void setName(String str) {
                    this.Name = str;
                }

                public String getColor() {
                    return this.Color;
                }

                public void setColor(String str) {
                    this.Color = str;
                }

                public int getType() {
                    return this.Type;
                }

                public void setType(int i) {
                    this.Type = i;
                }
            }
        }
    }
}
