package org.qfln.android_wuyue.bean;

import java.util.List;

/**
 * @描述：
 * @创建人： LN
 * @创建时间: 2016/4/6 14:28
 * @备注：
 */
public class CommitEntity {

    /**
     * code : 200
     * data : {"comments":[{"content":"用过这同种类的笔，那个笔还有个APP。用那个笔写作业（只是数学作业和数学考试），通过数据线传到电脑，电脑批改作业。在电脑上可以看到排名和成绩趋势。那个笔在国外生产，中国暂时还没有这种写字和纪录同步的技术。","created_at":1457608844,"id":441116,"item_id":1052280,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/20160112/jtqntyb42_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":1780633,"nickname":"好好学习，天天向上","role":0}},{"content":"好贵","created_at":1457578467,"id":440945,"item_id":1052280,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/151219/02d843dad_a-w180","can_mobile_login":false,"guest_uuid":null,"id":2377664,"nickname":"巴黎左岸的小镇°】","role":0}}],"paging":{"next_url":"http://api.liwushuo.com/v2/items/1052280/comments?limit=20&offset=20"}}
     * message : OK
     */

    private int code;
    /**
     * comments : [{"content":"用过这同种类的笔，那个笔还有个APP。用那个笔写作业（只是数学作业和数学考试），通过数据线传到电脑，电脑批改作业。在电脑上可以看到排名和成绩趋势。那个笔在国外生产，中国暂时还没有这种写字和纪录同步的技术。","created_at":1457608844,"id":441116,"item_id":1052280,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/20160112/jtqntyb42_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":1780633,"nickname":"好好学习，天天向上","role":0}},{"content":"好贵","created_at":1457578467,"id":440945,"item_id":1052280,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/151219/02d843dad_a-w180","can_mobile_login":false,"guest_uuid":null,"id":2377664,"nickname":"巴黎左岸的小镇°】","role":0}}]
     * paging : {"next_url":"http://api.liwushuo.com/v2/items/1052280/comments?limit=20&offset=20"}
     */

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
         * next_url : http://api.liwushuo.com/v2/items/1052280/comments?limit=20&offset=20
         */

        private PagingEntity paging;
        /**
         * content : 用过这同种类的笔，那个笔还有个APP。用那个笔写作业（只是数学作业和数学考试），通过数据线传到电脑，电脑批改作业。在电脑上可以看到排名和成绩趋势。那个笔在国外生产，中国暂时还没有这种写字和纪录同步的技术。
         * created_at : 1457608844
         * id : 441116
         * item_id : 1052280
         * reply_to_id : null
         * show : true
         * status : 1
         * user : {"avatar_url":"http://img01.liwushuo.com/avatar/20160112/jtqntyb42_i.png-w180","can_mobile_login":true,"guest_uuid":null,"id":1780633,"nickname":"好好学习，天天向上","role":0}
         */

        private List<CommentsEntity> comments;

        public void setPaging(PagingEntity paging) {
            this.paging = paging;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
        }

        public PagingEntity getPaging() {
            return paging;
        }

        public List<CommentsEntity> getComments() {
            return comments;
        }

        public static class PagingEntity {
            private String next_url;

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }

            public String getNext_url() {
                return next_url;
            }
        }

        public static class CommentsEntity {
            private String content;
            private long created_at;
            private int id;
            private int item_id;
            private Object reply_to_id;
            private boolean show;
            private int status;
            /**
             * avatar_url : http://img01.liwushuo.com/avatar/20160112/jtqntyb42_i.png-w180
             * can_mobile_login : true
             * guest_uuid : null
             * id : 1780633
             * nickname : 好好学习，天天向上
             * role : 0
             */

            private UserEntity user;

            public void setContent(String content) {
                this.content = content;
            }

            public void setCreated_at(long created_at) {
                this.created_at = created_at;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public void setReply_to_id(Object reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public void setUser(UserEntity user) {
                this.user = user;
            }

            public String getContent() {
                return content;
            }

            public long getCreated_at() {
                return created_at;
            }

            public int getId() {
                return id;
            }

            public int getItem_id() {
                return item_id;
            }

            public Object getReply_to_id() {
                return reply_to_id;
            }

            public boolean isShow() {
                return show;
            }

            public int getStatus() {
                return status;
            }

            public UserEntity getUser() {
                return user;
            }

            public static class UserEntity {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public void setRole(int role) {
                    this.role = role;
                }

                public String getAvatar_url() {
                    return avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public String getNickname() {
                    return nickname;
                }

                public int getRole() {
                    return role;
                }
            }
        }
    }
}
