package cn.company1075.xiaofu.utils;

import java.io.Serializable;

public class Skin implements Serializable{

    /**
     * Code : 0
     * Message : success
     * Data : {"DistinguishId":"33534f26-67ae-4257-8465-1953ae77e1ed","TotalScore":73,"SkinType":"油性肤质","T":"偏油","U":"偏油","Dryness":"很干燥","Senility":"轻熟肌","Sensitive":"不敏感","Pore":"较好","LeftColor":9,"RightColor":9,"SkinColor":9,"ForeheadOil":"偏油","LeftOil":"偏油","RightOil":"偏油","ChinOil":"偏油","NoseOil":"偏油","Makeup":"发现您的肌肤上疑似覆有粉类妆品，可能会影响本次检测结果准确性"}
     * T : 1532605726737
     */

    private int Code;
    private String Message;
    private DataBean Data;
    private long T;

    public Skin(int code, String message, DataBean data, long t) {
        Code = code;
        Message = message;
        Data = data;
        T = t;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public DataBean getData() {
        return Data;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public long getT() {
        return T;
    }

    public void setT(long T) {
        this.T = T;
    }

    public static class DataBean implements  Serializable{
        /**
         * DistinguishId : 33534f26-67ae-4257-8465-1953ae77e1ed
         * TotalScore : 73
         * SkinType : 油性肤质
         * T : 偏油
         * U : 偏油
         * Dryness : 很干燥
         * Senility : 轻熟肌
         * Sensitive : 不敏感
         * Pore : 较好
         * LeftColor : 9
         * RightColor : 9
         * SkinColor : 9
         * ForeheadOil : 偏油
         * LeftOil : 偏油
         * RightOil : 偏油
         * ChinOil : 偏油
         * NoseOil : 偏油
         * Makeup : 发现您的肌肤上疑似覆有粉类妆品，可能会影响本次检测结果准确性
         */

        private String DistinguishId;
        private int TotalScore;
        private String SkinType;
        private String T;
        private String U;
        private String Dryness;
        private String Senility;
        private String Sensitive;
        private String Pore;
        private int LeftColor;
        private int RightColor;
        private int SkinColor;
        private String ForeheadOil;
        private String LeftOil;
        private String RightOil;
        private String ChinOil;
        private String NoseOil;
        private String Makeup;

        public String getDistinguishId() {
            return DistinguishId;
        }

        public void setDistinguishId(String DistinguishId) {
            this.DistinguishId = DistinguishId;
        }

        public int getTotalScore() {
            return TotalScore;
        }

        public void setTotalScore(int TotalScore) {
            this.TotalScore = TotalScore;
        }

        public String getSkinType() {
            return SkinType;
        }

        public void setSkinType(String SkinType) {
            this.SkinType = SkinType;
        }

        public String getT() {
            return T;
        }

        public void setT(String T) {
            this.T = T;
        }

        public String getU() {
            return U;
        }

        public void setU(String U) {
            this.U = U;
        }

        public String getDryness() {
            return Dryness;
        }

        public void setDryness(String Dryness) {
            this.Dryness = Dryness;
        }

        public String getSenility() {
            return Senility;
        }

        public void setSenility(String Senility) {
            this.Senility = Senility;
        }

        public String getSensitive() {
            return Sensitive;
        }

        public void setSensitive(String Sensitive) {
            this.Sensitive = Sensitive;
        }

        public String getPore() {
            return Pore;
        }

        public void setPore(String Pore) {
            this.Pore = Pore;
        }

        public int getLeftColor() {
            return LeftColor;
        }

        public void setLeftColor(int LeftColor) {
            this.LeftColor = LeftColor;
        }

        public int getRightColor() {
            return RightColor;
        }

        public void setRightColor(int RightColor) {
            this.RightColor = RightColor;
        }

        public int getSkinColor() {
            return SkinColor;
        }

        public void setSkinColor(int SkinColor) {
            this.SkinColor = SkinColor;
        }

        public String getForeheadOil() {
            return ForeheadOil;
        }

        public void setForeheadOil(String ForeheadOil) {
            this.ForeheadOil = ForeheadOil;
        }

        public String getLeftOil() {
            return LeftOil;
        }

        public void setLeftOil(String LeftOil) {
            this.LeftOil = LeftOil;
        }

        public String getRightOil() {
            return RightOil;
        }

        public void setRightOil(String RightOil) {
            this.RightOil = RightOil;
        }

        public String getChinOil() {
            return ChinOil;
        }

        public void setChinOil(String ChinOil) {
            this.ChinOil = ChinOil;
        }

        public String getNoseOil() {
            return NoseOil;
        }

        public void setNoseOil(String NoseOil) {
            this.NoseOil = NoseOil;
        }

        public String getMakeup() {
            return Makeup;
        }

        public void setMakeup(String Makeup) {
            this.Makeup = Makeup;
        }
    }
}
