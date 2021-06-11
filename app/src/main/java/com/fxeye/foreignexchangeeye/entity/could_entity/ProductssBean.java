package com.fxeye.foreignexchangeeye.entity.could_entity;

import java.util.List;

public class ProductssBean {
    private List<ProductsBean> products;

    public List<ProductsBean> getProducts() {
        return this.products;
    }

    public void setProducts(List<ProductsBean> list) {
        this.products = list;
    }

    public static class ProductsBean {
        private String barcode;
        private String concessions;
        private int count;
        private String image;
        private String name;

        /* renamed from: op */
        private double f2238op;
        private String pid;
        private String property;

        /* renamed from: sp */
        private double f2239sp;
        private String tag;
        private int type;

        public String getPid() {
            return this.pid;
        }

        public void setPid(String str) {
            this.pid = str;
        }

        public String getBarcode() {
            return this.barcode;
        }

        public void setBarcode(String str) {
            this.barcode = str;
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public int getType() {
            return this.type;
        }

        public void setType(int i) {
            this.type = i;
        }

        public double getOp() {
            return this.f2238op;
        }

        public void setOp(double d) {
            this.f2238op = d;
        }

        public double getSp() {
            return this.f2239sp;
        }

        public void setSp(double d) {
            this.f2239sp = d;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getImage() {
            return this.image;
        }

        public void setImage(String str) {
            this.image = str;
        }

        public String getProperty() {
            return this.property;
        }

        public void setProperty(String str) {
            this.property = str;
        }

        public String getTag() {
            return this.tag;
        }

        public void setTag(String str) {
            this.tag = str;
        }

        public String getConcessions() {
            return this.concessions;
        }

        public void setConcessions(String str) {
            this.concessions = str;
        }
    }
}
