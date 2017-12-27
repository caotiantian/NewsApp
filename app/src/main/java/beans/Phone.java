package beans;

import java.util.List;

/**
 * Created by admin on 2017/12/14.
 */

public class Phone {

    /**
     * message : success
     * data : {"pc_feed_focus":[{"title":"韩国第一大在野党选出新任国会代表 当选议员磕头致谢","display_url":"/group/6498975472737583630/","has_video":false,"image_url":"//p1.pstatp.com/origin/4ad0001662c4ba1ca674","has_image":true,"group_id":6498975472737583630,"media_url":"http://toutiao.com/m5784742177"},{"title":"玉器市场蝶变转型玩起直播 为顾客猎贵货最高收10%佣金","display_url":"/group/6498947532914164237/","has_video":false,"image_url":"//p1.pstatp.com/origin/4ad1001656304235f565","has_image":true,"group_id":6498947532914164237,"media_url":"http://toutiao.com/m5739097906"},{"title":"宋轶半马尾展亲切笑容 皮肤白到发光真是太美了","display_url":"/group/6498869090155758093/","has_video":false,"image_url":"//p3.pstatp.com/origin/4ad3000c1fe4943ef03a","has_image":true,"group_id":6498869090155758093,"media_url":"http://toutiao.com/m5738017030"},{"title":"150万一节课！伍兹私人高尔夫教学课拍出天价","display_url":"/group/6498940263380025869/","has_video":false,"image_url":"//p3.pstatp.com/origin/4ad100165860e245b70e","has_image":true,"group_id":6498940263380025869,"media_url":"http://toutiao.com/m6675759548"},{"title":"全球仅三家船厂能建造整个航母舰队，其中有两家属于我国","display_url":"/group/6498881164080579085/","has_video":false,"image_url":"//p3.pstatp.com/origin/4ad0001662e1541f0460","has_image":false,"group_id":6498881164080579085,"media_url":"http://toutiao.com/m3995104383"},{"title":"车太贤朱智勋出席电影《与神同行》发布会 金香起黑色露空裙性感","display_url":"/group/6498871559984251405/","has_video":false,"image_url":"//p3.pstatp.com/origin/4ad0001663256974bea0","has_image":true,"group_id":6498871559984251405,"media_url":"http://toutiao.com/m5738017030"},{"title":"哈尔滨最牛钉子楼后有大坑 大坑里面住着多户人家","display_url":"/group/6498599105793622541/","has_video":false,"image_url":"//p3.pstatp.com/origin/4acf001374c91b7da4af","has_image":true,"group_id":6498599105793622541,"media_url":"http://toutiao.com/m5739097906"},{"title":"罗志祥壕投300万助简恺乐开个唱 这样的老板请给我来一打！","display_url":"/group/6498507803571782158/","has_video":false,"image_url":"//p1.pstatp.com/origin/4ad10012a289a5625ce6","has_image":true,"group_id":6498507803571782158,"media_url":"http://toutiao.com/m5738017030"},{"title":"欧洲是如何衰败的？04年耗资3600万欧的雅典奥运场馆已废弃","display_url":"/group/6498539933446177294/","has_video":false,"image_url":"//p3.pstatp.com/origin/4ad4000855954259e98a","has_image":true,"group_id":6498539933446177294,"media_url":"http://toutiao.com/m6675759548"},{"title":"全球最科幻战舰集体趴窝，美国舰艇工业还能否造精品军舰","display_url":"/group/6498440064186450446/","has_video":false,"image_url":"//p3.pstatp.com/origin/4acf001374299fa4515c","has_image":false,"group_id":6498440064186450446,"media_url":"http://toutiao.com/m3995104383"}]}
     */

    private String message;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<PcFeedFocusBean> pc_feed_focus;

        public List<PcFeedFocusBean> getPc_feed_focus() {
            return pc_feed_focus;
        }

        public void setPc_feed_focus(List<PcFeedFocusBean> pc_feed_focus) {
            this.pc_feed_focus = pc_feed_focus;
        }

        public static class PcFeedFocusBean {
            /**
             * title : 韩国第一大在野党选出新任国会代表 当选议员磕头致谢
             * display_url : /group/6498975472737583630/
             * has_video : false
             * image_url : //p1.pstatp.com/origin/4ad0001662c4ba1ca674
             * has_image : true
             * group_id : 6498975472737583630
             * media_url : http://toutiao.com/m5784742177
             */

            private String title;
            private String display_url;
            private boolean has_video;
            private String image_url;
            private boolean has_image;
            private long group_id;
            private String media_url;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDisplay_url() {
                return display_url;
            }

            public void setDisplay_url(String display_url) {
                this.display_url = display_url;
            }

            public boolean isHas_video() {
                return has_video;
            }

            public void setHas_video(boolean has_video) {
                this.has_video = has_video;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public boolean isHas_image() {
                return has_image;
            }

            public void setHas_image(boolean has_image) {
                this.has_image = has_image;
            }

            public long getGroup_id() {
                return group_id;
            }

            public void setGroup_id(long group_id) {
                this.group_id = group_id;
            }

            public String getMedia_url() {
                return media_url;
            }

            public void setMedia_url(String media_url) {
                this.media_url = media_url;
            }
        }
    }
}
