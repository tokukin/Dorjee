package com.example.tokukin.dorjee;

import java.io.Serializable;
import java.util.List;

public class FruitResult {


    /**
     * result : 0
     * totalCount : 20
     * data : [{"name":"苹果","desc":"苹果描述","image":"http://pic35.photophoto.cn/20150420/0035035508833597_b.jpg"}]
     */

    private int result;
    private int totalCount;
    private List<FruitBean> data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<FruitBean> getData() {
        return data;
    }

    public void setData(List<FruitBean> data) {
        this.data = data;
    }

    public static class FruitBean implements Serializable {
        /**
         * name : 苹果
         * desc : 苹果描述
         * image : http://pic35.photophoto.cn/20150420/0035035508833597_b.jpg
         */

        private String name;
        private String desc;
        private String image;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
