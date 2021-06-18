package com.fxeye.foreignexchangeeye.entity.trader;

import android.graphics.Bitmap;
import android.text.TextUtils;
//import com.github.mikephil.charting.utils.Utils;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class TraderResponse implements Serializable {
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

    public static class ResultBean implements Serializable {
        private List<RegulatorsBean> Regulators;
        private SkyRiskBean SkyRisk;
        private String annotation;
        private List<AttachBean> attach;
        private String cflag;
        private int channel;
        private String color;
        private List<ComplaintBean> complaint;
        private ConfigBean config;
        private List<ContractsBean> contracts;
        private String country;
        private String countryName;
        private String epc;
        private ExtendBean extend;
        private String flag;
        private String fullName;
        private boolean hasCloud;
        private boolean hasFlue;
        private String hico;
        private String ico;
        private boolean isOpen;
        private boolean isadvanced;
        private List<LabelsBean> labels;
        private String localFullName;
        private String localShortName;
        private String logo;
        private int matrix;
        private String mobileUrl;
        private RatingBean rating;
        private String score;
        private List<ScoresBean> scores;
        private String seal;
        private String shortName;
        private List<String> sites;
        private String star;
        private int status;
        private String top;
        private String traderCode;
        private String ultimatex;
        private String webUrl;
        private String xls;

        public boolean isHasFlue() {
            return this.hasFlue;
        }

        public void setHasFlue(boolean z) {
            this.hasFlue = z;
        }

        public boolean isHasCloud() {
            return this.hasCloud;
        }

        public void setHasCloud(boolean z) {
            this.hasCloud = z;
        }

        public String getCflag() {
            return this.cflag;
        }

        public void setCflag(String str) {
            this.cflag = str;
        }

        public String getCountryName() {
            return this.countryName;
        }

        public void setCountryName(String str) {
            this.countryName = str;
        }

        public String getMobileUrl() {
            return this.mobileUrl;
        }

        public void setMobileUrl(String str) {
            this.mobileUrl = str;
        }

        public int getMatrix() {
            return this.matrix;
        }

        public void setMatrix(int i) {
            this.matrix = i;
        }

        public boolean isOpen() {
            return this.isOpen;
        }

        public void setOpen(boolean z) {
            this.isOpen = z;
        }

        public boolean isIsadvanced() {
            return this.isadvanced;
        }

        public void setIsadvanced(boolean z) {
            this.isadvanced = z;
        }

        public String getEpc() {
            return this.epc;
        }

        public void setEpc(String str) {
            this.epc = str;
        }

        public String getStar() {
            return this.star;
        }

        public void setStar(String str) {
            this.star = str;
        }

        public String getUltimatex() {
            return this.ultimatex;
        }

        public void setUltimatex(String str) {
            this.ultimatex = str;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getTraderCode() {
            return this.traderCode;
        }

        public void setTraderCode(String str) {
            this.traderCode = str;
        }

        public String getCountry() {
            return this.country;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public String getIco() {
            return this.ico;
        }

        public void setIco(String str) {
            this.ico = str;
        }

        public String getHico() {
            return this.hico;
        }

        public void setHico(String str) {
            this.hico = str;
        }

        public String getLogo() {
            return this.logo;
        }

        public void setLogo(String str) {
            this.logo = str;
        }

        public String getFlag() {
            return this.flag;
        }

        public void setFlag(String str) {
            this.flag = str;
        }

        public String getSeal() {
            return this.seal;
        }

        public void setSeal(String str) {
            this.seal = str;
        }

        public String getTop() {
            return this.top;
        }

        public void setTop(String str) {
            this.top = str;
        }

        public String getWebUrl() {
            return this.webUrl;
        }

        public void setWebUrl(String str) {
            this.webUrl = str;
        }

        public double getScore() {
//            return TextUtils.isEmpty(this.score) ? Utils.DOUBLE_EPSILON : Double.valueOf(this.score).doubleValue();
            return TextUtils.isEmpty(this.score) ? Double.longBitsToDouble(1) : Double.valueOf(this.score).doubleValue();
        }

        public void setScore(double d) {
            this.score = String.valueOf(d);
        }

        public String getXls() {
            return this.xls;
        }

        public void setXls(String str) {
            this.xls = str;
        }

        public String getAnnotation() {
            return this.annotation;
        }

        public void setAnnotation(String str) {
            this.annotation = str;
        }

        public String getColor() {
            if (TextUtils.isEmpty(this.color)) {
                this.color = "#000000";
            }
            return this.color;
        }

        public void setColor(String str) {
            this.color = str;
        }

        public int getChannel() {
            return this.channel;
        }

        public void setChannel(int i) {
            this.channel = i;
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

        public List<String> getSites() {
            return this.sites;
        }

        public void setSites(List<String> list) {
            this.sites = list;
        }

        public SkyRiskBean getSkyRisk() {
            return this.SkyRisk;
        }

        public void setSkyRisk(SkyRiskBean skyRiskBean) {
            this.SkyRisk = skyRiskBean;
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

        public List<ContractsBean> getContracts() {
            return this.contracts;
        }

        public void setContracts(List<ContractsBean> list) {
            this.contracts = list;
        }

        public List<AttachBean> getAttach() {
            return this.attach;
        }

        public void setAttach(List<AttachBean> list) {
            this.attach = list;
        }

        public List<ScoresBean> getScores() {
            return this.scores;
        }

        public void setScores(List<ScoresBean> list) {
            this.scores = list;
        }

        public List<LabelsBean> getLabels() {
            return this.labels;
        }

        public void setLabels(List<LabelsBean> list) {
            this.labels = list;
        }

        public List<ComplaintBean> getComplaint() {
            return this.complaint;
        }

        public void setComplaint(List<ComplaintBean> list) {
            this.complaint = list;
        }

        public List<RegulatorsBean> getRegulators() {
            return this.Regulators;
        }

        public void setRegulators(List<RegulatorsBean> list) {
            this.Regulators = list;
        }

        public ConfigBean getConfig() {
            return this.config;
        }

        public void setConfig(ConfigBean configBean) {
            this.config = configBean;
        }

        public ExtendBean getExtend() {
            return this.extend;
        }

        public void setExtend(ExtendBean extendBean) {
            this.extend = extendBean;
        }

        public RatingBean getRating() {
            return this.rating;
        }

        public void setRating(RatingBean ratingBean) {
            this.rating = ratingBean;
        }

        public static class ConfigBean implements Serializable {
            private static final long serialVersionUID = 710683742772681292L;
            private int btn;
            private int link;
            private int open;

            public int getBtn() {
                return this.btn;
            }

            public void setBtn(int i) {
                this.btn = i;
            }

            public int getLink() {
                return this.link;
            }

            public void setLink(int i) {
                this.link = i;
            }

            public int getOpen() {
                return this.open;
            }

            public void setOpen(int i) {
                this.open = i;
            }
        }

        public static class SkyRiskBean implements Serializable {
            private String detectedAt;
            private List<ItemsBean> items;
            private int level;
            private String title;
            private int total;

            public int getTotal() {
                return this.total;
            }

            public void setTotal(int i) {
                this.total = i;
            }

            public int getLevel() {
                return this.level;
            }

            public void setLevel(int i) {
                this.level = i;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public String getDetectedAt() {
                return this.detectedAt;
            }

            public void setDetectedAt(String str) {
                this.detectedAt = str;
            }

            public List<ItemsBean> getItems() {
                return this.items;
            }

            public void setItems(List<ItemsBean> list) {
                this.items = list;
            }

            public static class ItemsBean implements Serializable {
                private String content;
                private String image;
                private int level;

                public int getLevel() {
                    return this.level;
                }

                public void setLevel(int i) {
                    this.level = i;
                }

                public String getImage() {
                    return this.image;
                }

                public void setImage(String str) {
                    this.image = str;
                }

                public String getContent() {
                    return this.content;
                }

                public void setContent(String str) {
                    this.content = str;
                }
            }
        }

        public static class ContractsBean implements Serializable {
            private String contract;
            private String languageCode;
            private int type;

            public String getLanguageCode() {
                return this.languageCode;
            }

            public void setLanguageCode(String str) {
                this.languageCode = str;
            }

            public String getContract() {
                return this.contract;
            }

            public void setContract(String str) {
                this.contract = str;
            }

            public int getType() {
                return this.type;
            }

            public void setType(int i) {
                this.type = i;
            }
        }

        public static class AttachBean implements Serializable {
            private String languageCode;
            private String name;
            private String path;

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getPath() {
                return this.path;
            }

            public void setPath(String str) {
                this.path = str;
            }

            public String getLanguageCode() {
                return this.languageCode;
            }

            public void setLanguageCode(String str) {
                this.languageCode = str;
            }
        }

        public static class ScoresBean implements Serializable {
            private String code;
            private String img;
            private double score;
            private double weight;

            public String getCode() {
                return this.code;
            }

            public void setCode(String str) {
                this.code = str;
            }

            public double getWeight() {
                return this.weight;
            }

            public void setWeight(double d) {
                this.weight = d;
            }

            public double getScore() {
                return this.score;
            }

            public void setScore(double d) {
                this.score = d;
            }

            public String getImg() {
                return this.img;
            }

            public void setImg(String str) {
                this.img = str;
            }
        }

        public static class RegulatorsBean implements Serializable {
            private String address;
            private String annotation;
            private List<AttachBean> attach;
            private Bitmap bitmap;
            private String cflag;
            private String color;
            private String country;
            private String eShortName;
            private String effective;
            private String email;
            private String expire;
            private String flag;
            private String fullName;
            private String ico;
            private boolean isShow;
            private JumpBean jump;
            private String lcolor;
            private String licenseName;
            private String logo;
            private String name;
            private String number;
            private String scolor;
            private String seal;
            private String sharename;
            private List<SharetradersBean> sharetraders;
            private String shortName;
            private String site;
            private int summary;
            private String tel;

            public String toString() {
                return "RegulatorsBean{fullName='" + this.fullName + '\'' + ", shortName='" + this.shortName + '\'' + ", site='" + this.site + '\'' + ", tel='" + this.tel + '\'' + ", email='" + this.email + '\'' + ", country='" + this.country + '\'' + ", address='" + this.address + '\'' + ", name='" + this.name + '\'' + ", number='" + this.number + '\'' + ", effective='" + this.effective + '\'' + ", expire='" + this.expire + '\'' + ", ico='" + this.ico + '\'' + ", licenseName='" + this.licenseName + '\'' + ", logo='" + this.logo + '\'' + ", annotation='" + this.annotation + '\'' + ", color='" + this.color + '\'' + ", flag='" + this.flag + '\'' + ", summary=" + this.summary + ", attach=" + this.attach + ", isShow=" + this.isShow + ", bitmap=" + this.bitmap + ", seal='" + this.seal + '\'' + '}';
            }

            public String getLcolor() {
                return this.lcolor;
            }

            public void setLcolor(String str) {
                this.lcolor = str;
            }

            public static class SharetradersBean implements Serializable {
                private String Ico;
                private String LocalShortName;
                private Object Logo;
                private String ShortName;
                private String TraderCode;

                public String getTraderCode() {
                    return this.TraderCode;
                }

                public void setTraderCode(String str) {
                    this.TraderCode = str;
                }

                public Object getLogo() {
                    return this.Logo;
                }

                public void setLogo(Object obj) {
                    this.Logo = obj;
                }

                public String getIco() {
                    return this.Ico;
                }

                public void setIco(String str) {
                    this.Ico = str;
                }

                public String getShortName() {
                    return this.ShortName;
                }

                public void setShortName(String str) {
                    this.ShortName = str;
                }

                public String getLocalShortName() {
                    return this.LocalShortName;
                }

                public void setLocalShortName(String str) {
                    this.LocalShortName = str;
                }
            }

            public String getSharename() {
                return this.sharename;
            }

            public void setSharename(String str) {
                this.sharename = str;
            }

            public String getScolor() {
                return this.scolor;
            }

            public void setScolor(String str) {
                this.scolor = str;
            }

            public List<SharetradersBean> getSharetraders() {
                return this.sharetraders;
            }

            public void setSharetraders(List<SharetradersBean> list) {
                this.sharetraders = list;
            }

            public JumpBean getJump() {
                return this.jump;
            }

            public void setJump(JumpBean jumpBean) {
                this.jump = jumpBean;
            }

            public String geteShortName() {
                return this.eShortName;
            }

            public void seteShortName(String str) {
                this.eShortName = str;
            }

            public String getCflag() {
                return this.cflag;
            }

            public void setCflag(String str) {
                this.cflag = str;
            }

            public String getSeal() {
                return this.seal;
            }

            public void setSeal(String str) {
                this.seal = str;
            }

            public Bitmap getBitmap() {
                return this.bitmap;
            }

            public void setBitmap(Bitmap bitmap2) {
                this.bitmap = bitmap2;
            }

            public boolean isShow() {
                return this.isShow;
            }

            public void setShow(boolean z) {
                this.isShow = z;
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

            public String getSite() {
                return this.site;
            }

            public void setSite(String str) {
                this.site = str;
            }

            public String getTel() {
                return this.tel;
            }

            public void setTel(String str) {
                this.tel = str;
            }

            public String getEmail() {
                return this.email;
            }

            public void setEmail(String str) {
                this.email = str;
            }

            public String getCountry() {
                return this.country;
            }

            public void setCountry(String str) {
                this.country = str;
            }

            public String getAddress() {
                return this.address;
            }

            public void setAddress(String str) {
                this.address = str;
            }

            public String getName() {
                return this.name;
            }

            public void setName(String str) {
                this.name = str;
            }

            public String getNumber() {
                return this.number;
            }

            public void setNumber(String str) {
                this.number = str;
            }

            public String getEffective() {
                return this.effective;
            }

            public void setEffective(String str) {
                this.effective = str;
            }

            public String getExpire() {
                return this.expire;
            }

            public void setExpire(String str) {
                this.expire = str;
            }

            public String getIco() {
                return this.ico;
            }

            public void setIco(String str) {
                this.ico = str;
            }

            public String getLicenseName() {
                return this.licenseName;
            }

            public void setLicenseName(String str) {
                this.licenseName = str;
            }

            public String getLogo() {
                return this.logo;
            }

            public void setLogo(String str) {
                this.logo = str;
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

            public String getFlag() {
                return this.flag;
            }

            public void setFlag(String str) {
                this.flag = str;
            }

            public int getSummary() {
                return this.summary;
            }

            public void setSummary(int i) {
                this.summary = i;
            }

            public List<AttachBean> getAttach() {
                return this.attach;
            }

            public void setAttach(List<AttachBean> list) {
                this.attach = list;
            }

            public static class JumpBean implements Serializable {
                private boolean isjump;
                private String url;

                public boolean isIsjump() {
                    return this.isjump;
                }

                public void setIsjump(boolean z) {
                    this.isjump = z;
                }

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }

            public static class AttachBean implements Serializable {
                private String languageCode;
                private String name;
                private String path;

                public String toString() {
                    return "AttachBean{name='" + this.name + '\'' + ", path='" + this.path + '\'' + ", languageCode='" + this.languageCode + '\'' + '}';
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public String getPath() {
                    return this.path;
                }

                public void setPath(String str) {
                    this.path = str;
                }

                public String getLanguageCode() {
                    return this.languageCode;
                }

                public void setLanguageCode(String str) {
                    this.languageCode = str;
                }
            }
        }

        public static class ComplaintBean implements Serializable {
            private String country;
            private String effective;
            private String expire;
            private String fullName;
            private String number;
            private String regulatorName;
            private String regulatorNumber;
            private String shortName;

            public String getNumber() {
                return this.number;
            }

            public void setNumber(String str) {
                this.number = str;
            }

            public String getEffective() {
                return this.effective;
            }

            public void setEffective(String str) {
                this.effective = str;
            }

            public String getExpire() {
                return this.expire;
            }

            public void setExpire(String str) {
                this.expire = str;
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

            public String getRegulatorNumber() {
                return this.regulatorNumber;
            }

            public void setRegulatorNumber(String str) {
                this.regulatorNumber = str;
            }

            public String getRegulatorName() {
                return this.regulatorName;
            }

            public void setRegulatorName(String str) {
                this.regulatorName = str;
            }

            public String getCountry() {
                return this.country;
            }

            public void setCountry(String str) {
                this.country = str;
            }
        }

        public static class LabelsBean implements Serializable {
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

            public static class DataBean implements Serializable {
                private String labelName;

                public String getLabelName() {
                    return this.labelName;
                }

                public void setLabelName(String str) {
                    this.labelName = str;
                }
            }
        }

        public static class ExtendBean implements Serializable {
            private List<VideoBean> video;

            /* renamed from: vr */
            private VrBean f2271vr;

            public VrBean getVr() {
                return this.f2271vr;
            }

            public void setVr(VrBean vrBean) {
                this.f2271vr = vrBean;
            }

            public List<VideoBean> getVideo() {
                return this.video;
            }

            public void setVideo(List<VideoBean> list) {
                this.video = list;
            }

            public static class VrBean implements Serializable {
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

                public static class ItemsBean implements Serializable {
                    private String countryFlag;
                    @SerializedName("countryName")
                    private String countryNameX;
                    private String coverImg;
                    private String loadingPic;
                    private String showTime;
                    @SerializedName("traderCode")
                    private String traderCodeX;
                    private String vrUrl;

                    public String getCoverImg() {
                        return this.coverImg;
                    }

                    public void setCoverImg(String str) {
                        this.coverImg = str;
                    }

                    public String getLoadingPic() {
                        return this.loadingPic;
                    }

                    public void setLoadingPic(String str) {
                        this.loadingPic = str;
                    }

                    public String getTraderCodeX() {
                        return this.traderCodeX;
                    }

                    public void setTraderCodeX(String str) {
                        this.traderCodeX = str;
                    }

                    public String getCountryFlag() {
                        return this.countryFlag;
                    }

                    public void setCountryFlag(String str) {
                        this.countryFlag = str;
                    }

                    public String getCountryNameX() {
                        return this.countryNameX;
                    }

                    public void setCountryNameX(String str) {
                        this.countryNameX = str;
                    }

                    public String getVrUrl() {
                        return this.vrUrl;
                    }

                    public void setVrUrl(String str) {
                        this.vrUrl = str;
                    }

                    public String getShowTime() {
                        return this.showTime;
                    }

                    public void setShowTime(String str) {
                        this.showTime = str;
                    }
                }
            }

            public static class VideoBean implements Serializable {
                private String code;
                private String img;
                private String name;
                private String path;

                public String getCode() {
                    return this.code;
                }

                public void setCode(String str) {
                    this.code = str;
                }

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public String getPath() {
                    return this.path;
                }

                public void setPath(String str) {
                    this.path = str;
                }

                public String getImg() {
                    return this.img;
                }

                public void setImg(String str) {
                    this.img = str;
                }
            }
        }

        public static class RatingBean implements Serializable {
            @SerializedName("score")
            private String scoreX;
            private String signs;

            public String getScoreX() {
                return this.scoreX;
            }

            public void setScoreX(String str) {
                this.scoreX = str;
            }

            public String getSigns() {
                return this.signs;
            }

            public void setSigns(String str) {
                this.signs = str;
            }
        }
    }
}
