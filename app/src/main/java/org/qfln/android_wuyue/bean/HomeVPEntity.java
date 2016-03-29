package org.qfln.android_wuyue.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/3/28 21:12
 * @备注：
 */
public class HomeVPEntity {

    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":559,"image_url":"http://img02.liwushuo.com/image/160321/pn5ylons0.jpg-w720","order":230,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/160321/3wzackc9w.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/160321/3wzackc9w.jpg?imageView2/0/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160321/1t61v21o9.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160321/1t61v21o9.jpg?imageView2/0/w/720/q/85/format/webp","created_at":1458528309,"id":228,"posts_count":7,"status":0,"subtitle":"出游清单已备好，走赏花去！","title":"旅行の意义","updated_at":1458528309},"target_id":228,"target_url":"","type":"collection","webp_url":"http://img02.liwushuo.com/image/160321/pn5ylons0.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":562,"image_url":"http://img02.liwushuo.com/image/160328/6feg841rv.jpg-w720","order":220,"status":0,"target_id":null,"target_url":"http://redirect.liwushuo.com/j/yima28","type":"url","webp_url":"http://img02.liwushuo.com/image/160328/6feg841rv.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":560,"image_url":"http://img02.liwushuo.com/image/160324/7cqn2tpnk.jpg-w720","order":211,"status":0,"target_id":null,"target_url":"http://redirect.liwushuo.com/j/yima92","type":"url","webp_url":"http://img02.liwushuo.com/image/160324/7cqn2tpnk.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":551,"image_url":"http://img02.liwushuo.com/image/160309/1bmt62nkc.jpg-w720","order":210,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/160309/rhcjhk38h.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/160309/rhcjhk38h.jpg?imageView2/0/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/160309/2subki5cj.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/160309/2subki5cj.jpg?imageView2/0/w/720/q/85/format/webp","created_at":1457501995,"id":225,"posts_count":9,"status":0,"subtitle":"办公神器轻松解救工作党","title":"办公必备神器","updated_at":1457501995},"target_id":225,"target_url":"","type":"collection","webp_url":"http://img02.liwushuo.com/image/160309/1bmt62nkc.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":558,"image_url":"http://img02.liwushuo.com/image/160317/0hgn4qiwg.jpg-w720","order":200,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/160317/1waks9fpe.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/160317/1waks9fpe.jpg?imageView2/0/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160317/3af7hpan1.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160317/3af7hpan1.jpg?imageView2/0/w/720/q/85/format/webp","created_at":1458185256,"id":227,"posts_count":6,"status":0,"subtitle":"让男神心动的早春穿搭","title":"早春穿搭指南","updated_at":1458185256},"target_id":227,"target_url":"","type":"collection","webp_url":"http://img02.liwushuo.com/image/160317/0hgn4qiwg.jpg?imageView2/0/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":556,"image_url":"http://img02.liwushuo.com/image/160316/z5filxz8l.jpg-w720","order":190,"status":0,"target":{"banner_image_url":"http://img01.liwushuo.com/image/160316/posf67kiv.jpg-w300","banner_webp_url":"http://img01.liwushuo.com/image/160316/posf67kiv.jpg?imageView2/0/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/160316/uru4aobbu.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/160316/uru4aobbu.jpg?imageView2/0/w/720/q/85/format/webp","created_at":1458120650,"id":226,"posts_count":5,"status":0,"subtitle":"是来自玻璃杯的艺术","title":"握在手中的美好","updated_at":1458120650},"target_id":226,"target_url":"","type":"collection","webp_url":"http://img02.liwushuo.com/image/160316/z5filxz8l.jpg?imageView2/0/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * ad_monitors : []
         * channel : all
         * id : 559
         * image_url : http://img02.liwushuo.com/image/160321/pn5ylons0.jpg-w720
         * order : 230
         * status : 0
         * target : {"banner_image_url":"http://img03.liwushuo.com/image/160321/3wzackc9w.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/160321/3wzackc9w.jpg?imageView2/0/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/160321/1t61v21o9.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/160321/1t61v21o9.jpg?imageView2/0/w/720/q/85/format/webp","created_at":1458528309,"id":228,"posts_count":7,"status":0,"subtitle":"出游清单已备好，走赏花去！","title":"旅行の意义","updated_at":1458528309}
         * target_id : 228
         * target_url :
         * type : collection
         * webp_url : http://img02.liwushuo.com/image/160321/pn5ylons0.jpg?imageView2/0/w/720/q/85/format/webp
         */

        private List<BannersEntity> banners;

        public void setBanners(List<BannersEntity> banners) {
            this.banners = banners;
        }

        public List<BannersEntity> getBanners() {
            return banners;
        }

        public static class BannersEntity implements Serializable{
            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            /**
             * banner_image_url : http://img03.liwushuo.com/image/160321/3wzackc9w.jpg-w300
             * banner_webp_url : http://img03.liwushuo.com/image/160321/3wzackc9w.jpg?imageView2/0/w/300/q/85/format/webp
             * cover_image_url : http://img03.liwushuo.com/image/160321/1t61v21o9.jpg-w720
             * cover_webp_url : http://img03.liwushuo.com/image/160321/1t61v21o9.jpg?imageView2/0/w/720/q/85/format/webp
             * created_at : 1458528309
             * id : 228
             * posts_count : 7
             * status : 0
             * subtitle : 出游清单已备好，走赏花去！
             * title : 旅行の意义
             * updated_at : 1458528309
             */

            private TargetEntity target;
            private int target_id;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setTarget(TargetEntity target) {
                this.target = target;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public String getChannel() {
                return channel;
            }

            public int getId() {
                return id;
            }

            public String getImage_url() {
                return image_url;
            }

            public int getOrder() {
                return order;
            }

            public int getStatus() {
                return status;
            }

            public TargetEntity getTarget() {
                return target;
            }

            public int getTarget_id() {
                return target_id;
            }

            public String getTarget_url() {
                return target_url;
            }

            public String getType() {
                return type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public static class TargetEntity {
                private String banner_image_url;
                private String banner_webp_url;
                private String cover_image_url;
                private String cover_webp_url;
                private int created_at;
                private int id;
                private int posts_count;
                private int status;
                private String subtitle;
                private String title;
                private int updated_at;

                public void setBanner_image_url(String banner_image_url) {
                    this.banner_image_url = banner_image_url;
                }

                public void setBanner_webp_url(String banner_webp_url) {
                    this.banner_webp_url = banner_webp_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public void setCover_webp_url(String cover_webp_url) {
                    this.cover_webp_url = cover_webp_url;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setPosts_count(int posts_count) {
                    this.posts_count = posts_count;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }

                public String getBanner_image_url() {
                    return banner_image_url;
                }

                public String getBanner_webp_url() {
                    return banner_webp_url;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public String getCover_webp_url() {
                    return cover_webp_url;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public int getId() {
                    return id;
                }

                public int getPosts_count() {
                    return posts_count;
                }

                public int getStatus() {
                    return status;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public String getTitle() {
                    return title;
                }

                public int getUpdated_at() {
                    return updated_at;
                }
            }
        }
    }
}
